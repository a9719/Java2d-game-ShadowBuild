import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
/***
 * Truck unit class
 *
 */
public class Truck extends Unit {
	private Image image;
	private static final double speed=0.25;
	boolean moving=false;
	
	private boolean selected=false;
	/***
	 * constructor
	 * @param x
	 * @param y
	 * @param camera
	 * @throws SlickException
	 */
	public Truck(int x, int y, Camera camera) throws SlickException {
		this.setX(x);
		this.setY(y);
		image= new Image("assets/units/truck.png");
		this.setCamera(camera);
		this.setImage(image);
		this.setSPEED(speed);
	}

}
