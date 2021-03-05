import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
/***
 * Class Sprite to update each sprite on the map
 *
 */
public class Sprite {

 private double x = this.getX();
 private double y = this.getY();
 // Initially, we don't need to move at all
 private Image image;
 private Camera camera;
 private boolean first_selection = true;
 private boolean movable = false;
 private Object max_capacity;
 private int carrying;
 /***
  * set value true if the sprite needs to move
  * @param move
  */
 public void setMovable(boolean move) {
  this.movable = move;
 }
 /***
  * 
  * @return x coordinates of sprite
  */
 public double getX() {
  return x;
 }
 /***
  * 
  * @return y coordinates of sprite
  */
 public double getY() {
  return y;
 }
 
 /***
  * Set Image for the sprite
  * @param image
  */
 public void setImage(Image image) {
  this.image = image;
 }
 /***
  * 
  * @return getImage of the sprite
  */
 public Image getImage() {
  return image;
 }
/***
 * 
 * @param coordX set x coordinates
 */
 public void setX(double coordX) {
  this.x = coordX;
 }
 /***
  * 
  * @param maxCapacity maxcapacity of metal engineers can hold
  */
 public void setCarryingCapacity(int maxCapacity) {
  this.max_capacity = maxCapacity;

 }
 /***
  * 
  * @param carrying2 set carrying amount for engineer
  */
 public void setCarrying(int carrying2) {
  this.carrying = carrying2;

 }
 private double SPEED;
/***
 * Set speed for units
 * @param speed
 */
 public void setSPEED(double speed) {

  this.SPEED = speed;
 }

/***
 * empty Sprite constructor
 */
 public Sprite() {}
 private double targetX = x;
 private double targetY = y;
 private int mineral;
 boolean mining;
 private double x2, x3;
 private double y2, y3;
 private double resX;
 private double resY;
 private int mine;

 public boolean training = false;
 private int pressed;
 private int time1stopper;
 private int time1;
 private int time2stopper;
 private int time2;
 private int time3stopper;
 private int time3;
 public boolean Active;
/***
 * Update each sprite here and also add/remove sprites depending upon input
 * @param world
 * @throws SlickException
 */
 public void update(World world) throws SlickException {
	// first selection then set target as current position so that unit automatically doesnt move as soon as game begins 
  if (this.first_selection) {
   targetX = this.getX();
   targetY = this.getY();
   this.first_selection = false;
  }
  Input input = world.getInput();//input
  //check what key is pressed and the selected sprite
  if (world.select != null) {
   if ((world.select.toString()).contains("CommandCentre")) {
    if (input.isKeyPressed(Input.KEY_1) && world.metalmined >= 5 && world.select.training == false) {
     world.select.pressed = 1;
     world.select.training = true;
     world.metalmined -= 5;
     world.select.time1stopper = 0;
     world.select.time1 = 0;
    }
    if (input.isKeyPressed(Input.KEY_2) && world.metalmined >= 10 && world.select.training == false) {

     world.metalmined -= 10;
     world.select.pressed = 2;
     world.select.training = true;
     time1stopper = 0;
     time1 = 0;
    }
    if (input.isKeyPressed(Input.KEY_3) && world.metalmined >= 20 && world.select.training == false) {

     world.metalmined -= 20;
     world.select.pressed = 3;
     world.select.training = true;
     time1stopper = 0;
     time1 = 0;
    }

   }
   if ((world.select.toString()).contains("Builder")) {
    if (input.isKeyPressed(Input.KEY_1) && world.metalmined >= 100 && world.select.training == false) {
     world.metalmined -= 100;
     world.select.pressed = 4;
     world.select.training = true;
     world.select.movable = false;
     world.select.time2stopper = 0;
     world.select.time2 = 0;

    }


   }
   if ((world.select.toString()).contains("Factories")) {
    if (input.isKeyPressed(Input.KEY_1) && world.metalmined >= 150 && world.select.training == false) {
     world.select.pressed = 1;
     world.select.training = true;
     world.metalmined -= 150;
     world.select.time1stopper = 0;
     world.select.time1 = 0;

    }

   }
   if ((world.select.toString()).contains("Truck")) {
    if (input.isKeyPressed(Input.KEY_1)) {
     world.select.pressed = 5;
     world.select.training = true;

     world.select.time3stopper = 0;
     world.select.time3 = 0;

    }


   }

  }
  //time1,time2,time3 are used for time delays for creation of new sprites
  this.time1 += 1;
//5 seconds delay
  if (this.time1 - this.time1stopper >= 5000) {
   this.time1stopper = this.time1;
   this.time1 = 0;
   if (this.pressed == 1 && this.toString().contains("Factories")) {
    world.sprites.add(new Truck((int) this.getX(), (int) this.getY(), camera));
    this.pressed = 0;
    this.training = false;

   } else if (this.pressed == 1) {
    world.sprites.add(new Scout((int) this.getX() - 65, (int) this.getY(), camera));

    this.pressed = 0;
    this.training = false;

   } else if (this.pressed == 2) {
    world.sprites.add(new Builder((int) this.getX() + 65, (int) this.getY(), camera));

    this.pressed = 0;
    this.training = false;

   } else if (this.pressed == 3) {
    world.sprites.add(new Engineer((int) this.getX(), (int) this.getY() - 65, camera));

    this.pressed = 0;
    this.training = false;

   }


  }
  //10seconds delay
  this.time2 += 1;
  if (this.time2 - this.time2stopper >= 10000) {
   this.time2stopper = this.time2;
   this.time2 = 0;
   if (this.pressed == 4) {
    world.sprites.add(new Factories((int) this.getX(), (int) this.getY(), camera));

    this.pressed = 0;
    this.training = false;


   }

  }
  //15seconds delay
  this.time3 += 1;
  if (this.time3 - this.time3stopper >= 15000) {
   this.time3stopper = this.time3;
   this.time3 = 0;
   if (this.pressed == 5) {
    world.sprites.add(new CommandCentre((int) this.getX(), (int) this.getY(), camera));
    world.sprites.remove(this);

    this.pressed = 0;
    this.training = false;


   }

  }

//if mining then update target location for engineer
  if (this.mining == true && world.sprites.get(this.mine).getResourcesCount() > 0) {
   this.movable = true;
   targetX = this.x2;
   targetY = this.y2;


  }


  //moves the sprites
  if (this.movable == true) {

   // If the mouse button is being clicked, set the target to the cursor location
   if (world.select == this && input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
    System.out.println((world.select == this) + " " + world.select + " " + this);
    targetX = camera.screenXToGlobalX(input.getMouseX());
    targetY = camera.screenYToGlobalY(input.getMouseY());
    System.out.println(this.getX() + " " + this.getY() + " " + targetX + " " + targetY);


    this.mining = false;
   }



   // If close to target reset our position depending on sprite

   if (World.distance(this.getX(), this.getY(), targetX, targetY) <= this.SPEED && this.mining == false) {

    resetTarget();
   } else if (World.distance(this.getX(), this.getY(), targetX, targetY) <= this.SPEED && this.mining == true) {

    x2 = resX;
    y2 = resY;
    resX = targetX;
    resY = targetY;
    if (world.sprites.get(this.mine).toString().contains("MetalMine") && world.sprites.get(this.mine).getX() == targetX) {
     world.metalmined += 2 + world.pylonactivated;
     world.sprites.get(this.mine).setResourcesCount(world.sprites.get(this.mine).getResourcesCount() - (2 + world.pylonactivated));
    } else if (world.sprites.get(this.mine).toString().contains("UnobtainiumMine") && world.sprites.get(this.mine).getX() == targetX) {
     world.unbotainiummined += 2 + world.pylonactivated;
     world.sprites.get(this.mine).setResourcesCount(world.sprites.get(this.mine).getResourcesCount() - (2 + world.pylonactivated));
    }



   } else {

    // Calculate the appropriate x and y distances
    double theta = Math.atan2(targetY - this.getY(), targetX - this.getX());
    double dx = (double) Math.cos(theta) * world.getDelta() * this.SPEED;
    double dy = (double) Math.sin(theta) * world.getDelta() * this.SPEED;
    // Check the tile is free before moving; otherwise, we stop moving
    if (world.isPositionFree(x + dx, y + dy)) {
     x += dx;
     y += dy;
    } else {
     resetTarget();
    }
   }
  }





 }

 private void resetTarget() {
  targetX = this.getX();
  targetY = this.getY();
 }

/***
 * set y coordinates of sprite
 * @param coordY
 */
 public void setY(double coordY) {
  this.y = coordY;
 }
 /***
  * set Camera position
  * @param camera
  */
 public void setCamera(Camera camera) {
  this.camera = camera;
 }

/***
 * render
 * @param g
 */
 public void render(Graphics g) {
  this.getImage().drawCentered((int) camera.globalXToScreenX(x),
   (int) camera.globalYToScreenY(y));

 }
 /***
  * 
  * @return return the resource amount
  */
 public int getResourcesCount() {
  return mineral;
 }
 /***
  * set the resource amount
  * @param mineral1
  */
 public void setResourcesCount(int mineral1) {
  this.mineral = mineral1;
 }
 /***
  * if engineer mining or not set its value
  * @param b
  */
 public void setMining(boolean b) {
  this.mining = b;

 }
 /***
  * set the command centre target and the mine for engineer
  * @param x2
  * @param y2
  * @param d
  * @param e
  * @param i
  */
 public void setCctarget(double x2, double y2, double d, double e, int i) {

  this.x2 = x2;
  this.y2 = y2;
  this.resX = d;
  this.resY = e;
  this.mine = i;

 }
 /***
  * 
  * @return get true if pylon activated
  */
 public boolean getActive() {

  return this.Active;
 }
 /***
  * set pylon activated true
  * @param b
  */
 public void setActive(boolean b) {
  this.Active = b;

 }
}