import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 * Class World renders the entire game map and text for the selected items
 * 
 * Code has been modified from Eleanor Mcmurty's Original sample project 1
 * 
 */
public class World {
 int metalmined = 0;
 int unbotainiummined = 0;

 private static final String MAP_PATH = "assets/main.tmx";
 private static final String SOLID_PROPERTY = "solid";
 ArrayList < Sprite > sprites = new ArrayList < > ();
 private Sprite sprite;
 private static boolean initialise = true;
 private Image unit_highlight = new Image("assets/highlight.png");
 private Image non_unit_highlight = new Image("assets/highlight_large.png");
 private TiledMap map;
 private Camera camera = new Camera();
 boolean gamestart = true;
 int pylonactivated = 0;

 private Input lastInput;
 private int lastDelta;
 Sprite select;
 Sprite prevselect;
 private Sprite engineer;
 private double targetX;
 private double y;
 private double targetY;
 private int time, time1;
 private int timestopper, time1stopper;
 private int pressed;


/***
 * 
 * @return return current input
 */
 public Input getInput() {
  return lastInput;
 }
 /***
  * 
  * @return delta value
  */
 public int getDelta() {
  return lastDelta;
 }
 /***
  * 
  * @param x the x cooedinates of the location to be checked if free or not
  * @param y the y coordinates of the location to be checked if free or not
  * @return return boolean value if free or not free
  */
 public boolean isPositionFree(double x, double y) {
  int tileId = map.getTileId(worldXToTileX(x), worldYToTileY(y), 0);
  return !Boolean.parseBoolean(map.getTileProperty(tileId, SOLID_PROPERTY, "false"));
 }
 /***
  * 
  * @return map width
  */
 public double getMapWidth() {
  return map.getWidth() * map.getTileWidth();
 }
 /***
  * 
  * @return return map height
  */
 public double getMapHeight() {
  return map.getHeight() * map.getTileHeight();
 }
 /***
  * Constructor to initialise World for rendering map camera and also to load csv 
  * @throws SlickException
  * @throws IOException
  */
 public World() throws SlickException, IOException {
  map = new TiledMap(MAP_PATH);
  camera = new Camera();
  //load all data from csv and add them to sprites 
  try (BufferedReader k =
   new BufferedReader(new FileReader("assets/objects.csv"))) {
   String line;
   while ((line = k.readLine()) != null) {
    String linesplit[] = line.split(",");
    int x1 = Integer.parseInt(linesplit[1]);
    int y1 = Integer.parseInt(linesplit[2]);
    switch (linesplit[0]) {
     case "engineer":
      sprites.add(new Engineer(x1, (y1), camera));
      break;
     case "metal_mine":
      sprites.add(new MetalMine(x1, (y1), camera));
      break;
     case "unobtainium_mine":
      sprites.add(new UnobtaniumtMine(x1, (y1), camera));
      break;
     case "pylon":
      sprites.add(new Pylon(x1, (y1), camera));
      break;
     case "command_centre":
      sprites.add(new CommandCentre(x1, (y1), camera));
      break;
    }
   }

  }

 }
 /***
  * funtion to update World
  * @param input 
  * @param delta
  * @throws SlickException
  */
 public void update(Input input, int delta) throws SlickException {
  lastInput = input;
  lastDelta = delta;

  // if left mouse clicked on a sprite select it
  if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
   gamestart = false;
   double leftMouseX = camera.getXcam() + Mouse.getX();
   double leftMouseY = camera.getYcam() + App.WINDOW_HEIGHT - Mouse.getY();
   for (int i = 0; i < sprites.size(); i++) {
    if ((sprites.get(i).toString()).contains("Engineer") || (sprites.get(i).toString()).contains("Scout") || (sprites.get(i).toString()).contains("Truck") || (sprites.get(i).toString()).contains("Builder")) {
     double dist = distance(sprites.get(i).getX(), sprites.get(i).getY(), leftMouseX, leftMouseY);
     if (dist <= 32) {// nearest unit 
      select = sprites.get(i);
      prevselect = sprites.get(i);
      select.setMovable(true);

      break;
     }

    } else {
     if (prevselect != null) {// if no unit nearby then select 
      select.setMovable(false);
     }
     select = null;
     prevselect = null;
    }

   }
   if (select == null) {
    for (int i1 = 0; i1 < sprites.size(); i1++) {// if no unit nearby then select the nearest building or resource
     double dist = distance(sprites.get(i1).getX(), sprites.get(i1).getY(), leftMouseX, leftMouseY);
     if (dist <= 32) {
      select = sprites.get(i1);
      prevselect = sprites.get(i1);
     }


    }
   }
  }

  //mining	
  for (int i = 0; i < sprites.size(); i++) {
   if ((sprites.get(i).toString()).contains("Engineer")) {     // only engineers can mine
    for (int j = 0; j < sprites.size(); j++) {
     if ((sprites.get(j).toString()).contains("MetalMine") || (sprites.get(j).toString()).contains("UnobtaniumtMine")) {
      double distance = distance(sprites.get(i).getX(), sprites.get(i).getY(), sprites.get(j).getX(), sprites.get(j).getY());
      if (distance <= 32 && sprites.get(j).getResourcesCount() > 0) {
       double nearest = 10000;

       for (int k = 0; k < sprites.size(); k++) {

        if ((sprites.get(k).toString()).contains("CommandCentre")) {
         double distance1 = distance(sprites.get(j).getX(), sprites.get(j).getY(), sprites.get(k).getX(), sprites.get(k).getY());
         time += delta; // if engineer nearby a mine and for more than 5 seconds only then start mining
         if (nearest > distance1 && time - timestopper >= 5000) {
          timestopper = time;
          nearest = distance1;
          sprites.get(i).setMining(true);
          sprites.get(i).setCctarget(sprites.get(k).getX(), sprites.get(k).getY(), sprites.get(j).getX(), sprites.get(j).getY(), j);

         }
        }
       }
      }
     }
    }
   }
  }

  //See if any unit nearby a Pylon if yes activate it
  for (int i = 0; i < sprites.size(); i++) {
   if ((sprites.get(i).toString()).contains("Engineer") || (sprites.get(i).toString()).contains("Scout") || (sprites.get(i).toString()).contains("Truck") || (sprites.get(i).toString()).contains("Builder")) {
    for (int j = 0; j < sprites.size(); j++) {
     if ((sprites.get(j).toString()).contains("Pylon")) {
      if (sprites.get(j).getActive() == false) {
       double distance = distance(sprites.get(i).getX(), sprites.get(i).getY(), sprites.get(j).getX(), sprites.get(j).getY());
       if (distance <= 32) {
        sprites.get(j).setActive(true);
        sprites.get(j).setImage(new Image("assets/buildings/pylon_active.png"));
        ++pylonactivated;

       }


      }

     }
    }

   }
  }
  // follow camera and update everything
  camera.followSprite(select);

  camera.update(this);


  for (int i = 0; i < sprites.size(); i++) {


   sprites.get(i).update(this);
  }


 }

/***
 * Renders everything onto the screen 
 * @param g
 */
 public void render(Graphics g) {
  map.render((int) camera.globalXToScreenX(0),
   (int) camera.globalYToScreenY(0));


// highlights the current selection of sprites
  for (int i = 0; i < sprites.size(); i++) {
   if (select != null) {
    if (select == sprites.get(i) && (select.toString()).contains("Engineer") || (select.toString().toString()).contains("Scout") || (select.toString()).contains("Truck") || (select.toString()).contains("Builder")) {
     unit_highlight.drawCentered((int) camera.globalXToScreenX(select.getX()),
      (int)(camera.globalYToScreenY(select.getY())));
    } else if (select == sprites.get(i) && (sprites.get(i).toString()).contains("CommandCentre") || (sprites.get(i).toString()).contains("Factory") || (sprites.get(i).toString()).contains("Truck") || (sprites.get(i).toString()).contains("Pylon")) {
     non_unit_highlight.drawCentered((int) camera.globalXToScreenX(select.getX()),
      (int)(camera.globalYToScreenY(select.getY())));
    }
   }
  }


  //render text to show info for the selected sprite
  if (initialise == true) {
   for (int i = 0; i < sprites.size(); i++) {
    sprites.get(i).render(g);
   }
  }
  g.drawString(String.format("Metal: %d \nUnobtainium: %d", metalmined, unbotainiummined), 32, 32);
  if (select != null) {
   if ((select.toString()).contains("CommandCentre")) {
    g.drawString("1-Create Scout \n2-Create Builder \n3-Create Engineer\n", 32, 100);
   } else if ((select.toString()).contains("Engineer")) {
    g.drawString(String.format("Carryinng: %d \nMax Carrying Capacity: %d", (select.mining ? (2 + pylonactivated) : 0), (2 + pylonactivated)), 32, 100);
   } else if ((select.toString()).contains("Builder")) {
    g.drawString("1-Create Factory", 32, 100);
   } else if ((select.toString()).contains("Truck")) {
    g.drawString("1-Create Command Centre", 32, 100);
   } else if ((select.toString()).contains("Factories")) {
    g.drawString("1-Create Truck", 32, 100);
   } else if ((select.toString()).contains("Builder")) {
    g.drawString("1-Create Factory", 32, 100);
   }


  }


 }

 // This should probably be in a separate static utilities class, but it's a bit excessive for one method.
 /***
  * Calculated distance between two coordinates
  * @param x1 
  * @param y1
  * @param x2
  * @param y2
  * @return the distance value
  */
 public static double distance(double x1, double y1, double x2, double y2) {
  return (double) Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
 }

 private int worldXToTileX(double x) {
  return (int)(x / map.getTileWidth());
 }
 private int worldYToTileY(double y) {
  return (int)(y / map.getTileHeight());
 }
}