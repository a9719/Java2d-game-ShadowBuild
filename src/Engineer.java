import org.lwjgl.input.Mouse;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
/***
 * Engineer class
 *
 */
public class Engineer extends Unit {
	private Image image;
	private static final double speed=0.1;
	private static final int max_capacity=2;
	private int carrying = 0;
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
	public Engineer(int x, int y, Camera camera) throws SlickException {
		this.setX(x);
		this.setY(y);
		image= new Image("assets/units/engineer.png");
		this.setCamera(camera);
		this.setImage(image);
		this.setCarryingCapacity(max_capacity);
		this.setCarrying(carrying);
		
		this.setSPEED(speed);
	}

	

	 
	
	

}
