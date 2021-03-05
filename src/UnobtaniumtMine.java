import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
/***
 * UnobtaniumMine class
 *
 */
public class UnobtaniumtMine extends Resources {
	private Image image;
	/***
	 * constructor
	 * @param x
	 * @param y
	 * @param camera
	 * @throws SlickException
	 */
	public UnobtaniumtMine(int x, int y, Camera camera) throws SlickException {
		this.setX(x);
		this.setY(y);
		image= new Image("assets/resources/unobtainium_mine.png");
		this.setCamera(camera);
		this.setImage(image);
		this.setResourcesCount(50);
		
	}
	

}
