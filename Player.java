import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player {
	private int xPosition;
	private int yPosition;
	private int xVelocity = 0;
	private int yVelocity = 0;
	private int xAccel = 0;
	private int yAccel = -1;
	private int maxShotDelay = 0;
	private int currentShotDelay = maxShotDelay;
	public boolean onPlatform = false;
	public boolean isJumping = false;
	public boolean isMovingLeft = false;
	public boolean isMovingRight = false;
	public boolean isShooting = false;
	private Direction direction = Direction.RIGHT;
	private ImageView appearance = new ImageView(new Image("player_0.png"));
	
	static Player Player = null;
	
	public static Player p() {
		if (Player == null) {
			Player = new Player();
		}
		
		return Player;
	}
	
	public void onPlatformMovement() {
		if (isJumping) {
			yVelocity = 15;
		}
		
		if (isMovingLeft) {
			xVelocity = -4;
			direction = Direction.LEFT;
		} else if (!isMovingRight) {
			xVelocity = 0;
		}
		
		if (isMovingRight) {
			xVelocity = 4;
			direction = Direction.RIGHT;
		} else if (!isMovingLeft) {
			xVelocity = 0;
		}
	}
	
	public void inAirMovement() {
		yVelocity += yAccel;
		
		if (yVelocity < -10) {
			yVelocity = -10;
		}
	}
	
	public void clipToPlatform(Platform pf) {
		yPosition = pf.getYPosition();
		yVelocity = 0;
		onPlatform = true;
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
	public int getMaxShotDelay() { return maxShotDelay; }
	public void setMaxShotDelay(int m) {
		maxShotDelay = m;
	}
	public int getCurrentShotDelay() { return currentShotDelay; }
	public void setCurrentShotDelay(int c) {
		currentShotDelay = c;
	}
	public Direction getDirection() { return direction; }
	public void setDirection(Direction d) {
		direction = d;
	}
	public ImageView getAppearance() { return appearance; }
	public void setAppearance(ImageView a) {
		appearance = a;
	}
}