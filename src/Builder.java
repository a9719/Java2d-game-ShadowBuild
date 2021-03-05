import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
/***
 * Builder class
 *
 */
public class Builder extends Unit {
	
	private Image image;
	private static final double speed=0.1;

	
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
	public Builder(int x, int y, Camera camera) throws SlickException {
		this.setX(x);
		this.setY(y);
		image= new Image("assets/units/builder.png");
		this.setCamera(camera);
		this.setImage(image);
		
		this.setSPEED(speed);
	}

}
