import java.util.ArrayList;
import javafx.scene.image.ImageView;

public class Projectile {
	private int xPosition;
	private int yPosition;
	private int xVelocity;
	private int yVelocity;
	private int lifeTime;
	private ImageView appearance;
	
	public static ArrayList<Projectile> projectiles;
	
	static {
		projectiles = new ArrayList<Projectile>();
	}
	
	public Projectile(int xp, int yp, int xv, int yv, int lt) {
		xPosition = xp;
		yPosition = yp;
		xVelocity = xv;
		yVelocity = yv;
		lifeTime = lt;
		appearance = new ImageView("projectile.png");
		
		projectiles.add(this);
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
	public int getLifeTime() { return lifeTime; }
	public void setLifeTime(int lt) {
		lifeTime = lt;
	}
	public ImageView getAppearance() { return appearance; }
	public void setAppearance(ImageView a) {
		appearance = a;
	}
}