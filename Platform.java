import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Platform {
	private int xPosition;
	private int yPosition;
	private int width;
	private int height;
	public boolean debugMode;
	private ImageView appearance;
	private Rectangle collisionBox;
	
	public Platform(int x, int y, int w, int h, boolean dm) {
		xPosition = x;
		yPosition = y;
		width = w;
		height = h;
		debugMode = dm;
		appearance = new ImageView(new Image("platform.png"));
		collisionBox = new Rectangle(width, height, Color.TRANSPARENT);
		collisionBox.setStroke(Color.RED);
	}
	
	public int getXPosition() { return xPosition; }
	public void setXPosition(int x) {
		xPosition = x;
	}
	public int getYPosition() { return yPosition; }
	public void setYPosition(int y) {
		yPosition = y;
	}
	public int getWidth() { return width; }
	public void setWidth(int w) {
		width = w;
	}
	public int getHeight() { return height; }
	public void setHeight(int h) {
		height = h;
	}
	public ImageView getAppearance() { return appearance; }
	public void setAppearance(ImageView a) {
		appearance = a;
	}
	public Rectangle getCollisionBox() { return collisionBox; }
	public void setCollisionBox(Rectangle cb) {
		collisionBox = cb;
	}
}