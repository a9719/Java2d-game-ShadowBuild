import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
/***
 * Pylon class
 *
 */
public class Pylon extends Sprite {

	private Image image;
	private Image activatedimage=new Image("assets/buildings/pylon_active.png");
	/***
	 * constructor
	 * @param x
	 * @param y
	 * @param camera
	 * @throws SlickException
	 */
	public Pylon(int x, int y, Camera camera) throws SlickException {
		this.setX(x);
		this.setY(y);
		image= new Image("assets/buildings/pylon.png");
		this.setCamera(camera);
		this.setImage(image);
	}

}
