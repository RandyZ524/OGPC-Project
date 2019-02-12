import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	public static int xOffset = -500;
	public static int yOffset = 300;
	public static Group root;
	public static ArrayList<Platform> platforms = new ArrayList<Platform>();
	
	public void start(Stage primaryStage) throws Exception {
		root = new Group();
		Scene scene = new Scene(root, 1000, 600);
		
		Player.p().setXPosition(0);
		Player.p().setYPosition(0);
		Player.p().getAppearance().setX(500 - 20);
		Player.p().getAppearance().setY(300 - 70);
		
		Platform pf1 = new Platform(0, -100, 162, 15, false);
		Platform pf2 = new Platform(200, -100, 162, 15, false);
		platforms.add(pf1);
		platforms.add(pf2);
		
		root.getChildren().add(Player.p().getAppearance());
		
		for (Platform pf : platforms) {
			root.getChildren().addAll(pf.getAppearance()/*, pf.getCollisionBox()*/);
		}
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
					case UP:
						Player.p().isJumping = true;
						break;
					case LEFT:
						Player.p().isMovingLeft = true;
						
						if (Player.p().isMovingRight) {
							Player.p().isMovingRight = false;
						}
						break;
					case RIGHT:
						Player.p().isMovingRight = true;
						
						if (Player.p().isMovingLeft) {
							Player.p().isMovingLeft = false;
						}
						break;
				}
			}
		});
		
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
					case UP:
						Player.p().isJumping = false;
						break;
					case LEFT:
						Player.p().isMovingLeft = false;
						break;
					case RIGHT:
						Player.p().isMovingRight = false;
						break;
				}
			}
		});
		
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				if (Player.p().onPlatform) {
					if (Player.p().isJumping) {
						Player.p().setYVelocity(15);
					}
					
					if (Player.p().isMovingLeft) {
						Player.p().setXVelocity(-4);
					} else if (!Player.p().isMovingRight) {
						Player.p().setXVelocity(0);
					}
					
					if (Player.p().isMovingRight) {
						Player.p().setXVelocity(4);
					} else if (!Player.p().isMovingLeft) {
						Player.p().setXVelocity(0);
					}
				}
				
				Player.p().setXVelocity(Player.p().getXVelocity() + Player.p().getXAccel());
				
				if (!Player.p().onPlatform) {
					Player.p().setYVelocity(Player.p().getYVelocity() + Player.p().getYAccel());
					
					if (Player.p().getYVelocity() < -10) {
						Player.p().setYVelocity(-10);
					}
				}
				
				Player.p().setXPosition(Player.p().getXPosition() + Player.p().getXVelocity());
				Player.p().setYPosition(Player.p().getYPosition() + Player.p().getYVelocity());
				
				boolean onAnyPlatform = false;
				
				for (Platform pf : platforms) {
					int xDistance = Math.abs(pf.getXPosition() - Player.p().getXPosition());
					int yDistance = pf.getYPosition() - Player.p().getYPosition();
					if (xDistance <= (int) Math.round((double) pf.getWidth() / 2)
							&& (yDistance >= 0 && yDistance <= pf.getHeight())) {
						Player.p().setYPosition(pf.getYPosition());
						Player.p().setYVelocity(0);
						Player.p().onPlatform = true;
						onAnyPlatform = true;
						break;
					}
				}
				
				if (!onAnyPlatform) {
					Player.p().onPlatform = false;
				}
				
				xOffset = Player.p().getXPosition() - 500;
				yOffset = Player.p().getYPosition() + 300;
				
				for (Platform pf : platforms) {
					pf.getAppearance().setX(pf.getXPosition() - 81 - xOffset);
					pf.getAppearance().setY(-pf.getYPosition() - 20 + yOffset);
					pf.getCollisionBox().setX(pf.getXPosition() - (int) Math.round((double) pf.getWidth() / 2) - xOffset);
					pf.getCollisionBox().setY(-pf.getYPosition() + yOffset);
				}
				
				Player.p().getAppearance().toFront();
			}
		};
		
		timer.start();
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}