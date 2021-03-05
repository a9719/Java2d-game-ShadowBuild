import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
/***
 * Metal mine class
 *
 */
public class MetalMine extends Resources {

	private Image unobtainium;
	/***
	 * constructor
	 * @param x
	 * @param y
	 * @param camera
	 * @throws SlickException
	 */
	public MetalMine(int x, int y, Camera camera) throws SlickException {
		this.setX(x);
		this.setY(y);
		unobtainium= new Image("assets/resources/metal_mine.png");
		this.setCamera(camera);
		this.setImage(unobtainium);
		this.setResourcesCount(500);
	}
	public void render(Graphics g) {
		super.render(g);}


}
