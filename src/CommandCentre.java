import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
/***
 * Command Centre class
 *
 */
public class CommandCentre extends Building {

	private Image image;
	/***
	 * constructor
	 * @param x
	 * @param y
	 * @param camera
	 * @throws SlickException
	 */
	public CommandCentre(int x, int y, Camera camera) throws SlickException {
		this.setX(x);
		this.setY(y);
		image= new Image("assets/buildings/command_centre.png");
		this.setCamera(camera);
		this.setImage(image);
	}
	
	
		
   }
