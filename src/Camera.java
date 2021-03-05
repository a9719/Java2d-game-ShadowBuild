import org.newdawn.slick.Input;

/**
 * Camera class to update position of camera
 */
public class Camera {
	private double x = 0;
	private double y = 0;
	private Sprite target;
	private double camera_speed=0.4;
	private boolean pressed;
	
	/***
	 * set the target sprite
	 * @param target
	 */
	public void followSprite(Sprite target) {
		this.target = target;
	}
	/***
	 * globalXToScreenX
	 * @param x
	 * @return x
	 */
	public double globalXToScreenX(double x) {
		return x - this.x;
	}
	/***
	 * globalYToScreenY
	 * @param y
	 * @return y
	 */
	public double globalYToScreenY(double y) {
		return y - this.y;
	}
	/***
	 * 
	 * @param x
	 * @return x
	 */
	public double screenXToGlobalX(double x) {
		return x + this.x;
	}
	/***
	 * 
	 * @param y
	 * @return y
	 */
	public double screenYToGlobalY(double y) {
		return y + this.y;
	}
	/***
	 * 
	 * @return get cam x pos
	 */
	public double getXcam()
	{
		return x;
	}
	/***
	 * 
	 * @return get cam y pos
	 */
	public double getYcam()
	{
		return y;
	}
	
	/***
	 * update function for camera
	 * @param world
	 */
	public void update(World world) {
		pressed=false;
		if(target!=null) {//check if target to follow selected
		double targetX = target.getX() - App.WINDOW_WIDTH / 2;
		double targetY = target.getY() - App.WINDOW_HEIGHT / 2;
		
		x = Math.min(targetX, world.getMapWidth() -	 App.WINDOW_WIDTH);
		x = Math.max(x, 0);
		y = Math.min(targetY, world.getMapHeight() - App.WINDOW_HEIGHT);
		y = Math.max(y, 0);}
		
	
	//move camera with wasd keys
	
	if ((world.getInput()).isKeyDown(Input.KEY_W)) {
		target=null;
		pressed=true;
		y=y-camera_speed;
	}
	
	if ((world.getInput()).isKeyDown(Input.KEY_S)) {
		target=null;
		pressed=true;
		y=y+camera_speed;
	}
	
	if ((world.getInput()).isKeyDown(Input.KEY_A)) {
		target=null;
		pressed=true;
		x=x-camera_speed;
		
	}
	
	if ((world.getInput()).isKeyDown(Input.KEY_D)) {
		target=null;
		pressed=true;
		x=x+camera_speed;
	}
	//set the camera boundaries
	if(target==null) {
	x = Math.min(x, world.getMapWidth() -	 App.WINDOW_WIDTH);
	x = Math.max(x, 0);
	y = Math.min(y, world.getMapHeight() - App.WINDOW_HEIGHT);
	y = Math.max(y, 0);
	}
	//set the camera to return to the selected sprite once wasd is not pressed
	if (pressed==true) {world.select=null;
	
	}else
	{
		world.select=world.prevselect;
	}
	
	
	
	}
	
	
}
