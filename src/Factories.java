import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
/***
 * Factory class
 *
 */
public class Factories extends Building {
	private Image image;
	/***
	 * constructor
	 * @param x
	 * @param y
	 * @param camera
	 * @throws SlickException
	 */
	public Factories(int x, int y, Camera camera) throws SlickException {
		this.setX(x);
		this.setY(y);
		image= new Image("assets/buildings/factory.png");
		this.setCamera(camera);
		this.setImage(image);
	}
	
	
		
 }
