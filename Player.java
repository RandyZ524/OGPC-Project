import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player {
	private int xPosition;
	private int yPosition;
	private int xVelocity = 0;
	private int yVelocity = 0;
	private int xAccel = 0;
	private int yAccel = -1;
	public boolean onPlatform = false;
	public boolean isJumping = false;
	public boolean isMovingLeft = false;
	public boolean isMovingRight = false;
	private ImageView appearance = new ImageView(new Image("player_0.png"));
	
	static Player Player = null;
	
	public static Player p() {
		if (Player == null) {
			Player = new Player();
		}
		
		return Player;
	}
	
	public int getXPosition() { return xPosition; }
	public void setXPosition(int x) {
		xPosition = x;
	}
	public int getYPosition() { return yPosition; }
	public void setYPosition(int y) {
		yPosition = y;
	}
	public int getXVelocity() { return xVelocity; }
	public void setXVelocity(int x) {
		xVelocity = x;
	}
	public int getYVelocity() { return yVelocity; }
	public void setYVelocity(int y) {
		yVelocity = y;
	}
	public int getXAccel() { return xAccel; }
	public void setXAccel(int x) {
		xAccel = x;
	}
	public int getYAccel() { return yAccel; }
	public void setYAccel(int y) {
		yAccel = y;
	}
	public ImageView getAppearance() { return appearance; }
	public void setAppearance(ImageView a) {
		appearance = a;
	}
}