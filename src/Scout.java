import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
/***
 * scout class
 *
 */
public class Scout extends Unit {
	
	private Image image;
	private static final double speed=0.3;

	
	boolean moving=false;
	private double mouseRightX;
	private double mouseRightY;
	private boolean selected=false;
	/***
	 * constructor
	 * @param x
	 * @param y
	 * @param camera
	 * @throws SlickException
	 */
	public Scout(int x, int y, Camera camera) throws SlickException {
		this.setX(x);
		this.setY(y);
		image= new Image("assets/units/scout.png");
		this.setCamera(camera);
		this.setImage(image);
		
		this.setSPEED(speed);
	}

	

	 

}
