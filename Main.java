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
	
	public void start(Stage primaryStage) throws Exception {
		Group root = new Group();
		Scene scene = new Scene(root, Constants.stageWidth, Constants.stageHeight);
		
		Player.p().setXPosition(0);
		Player.p().setYPosition(0);
		Player.p().getAppearance().setX(Constants.halfStageWidth - 20);
		Player.p().getAppearance().setY(Constants.halfStageHeight - 70);
		
		root.getChildren().add(Player.p().getAppearance());
		
		for (Platform pf : Platform.platforms) {
			root.getChildren().addAll(pf.getAppearance(), pf.getCollisionBox());
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
					case SPACE:
						Player.p().isShooting = true;
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
					case SPACE:
						Player.p().isShooting = false;
				}
			}
		});
		
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				if (Player.p().onPlatform) {
					Player.p().onPlatformMovement();
				} else {
					Player.p().inAirMovement();
				}
				
				Player.p().setXVelocity(Player.p().getXVelocity() + Player.p().getXAccel());
				
				Player.p().setXPosition(Player.p().getXPosition() + Player.p().getXVelocity());
				Player.p().setYPosition(Player.p().getYPosition() + Player.p().getYVelocity());
				
				if (Player.p().getCurrentShotDelay() > 0) {
					Player.p().setCurrentShotDelay(Player.p().getCurrentShotDelay() - 1);
				}
				
				if (Player.p().isShooting && Player.p().getCurrentShotDelay() == 0) {
					int xDirection = Player.p().getDirection() == Direction.LEFT ? -5 : 5;
					Projectile pr = new Projectile(Player.p().getXPosition(), Player.p().getYPosition() + 50, xDirection, 0, 120);
					root.getChildren().add(pr.getAppearance());
					Player.p().setCurrentShotDelay(Player.p().getMaxShotDelay());
				}
				
				boolean onAnyPlatform = false;
				
				for (Platform pf : Platform.platforms) {
					if (pf.collidingWithPlayer(Player.p())) {
						Player.p().clipToPlatform(pf);
						onAnyPlatform = true;
						break;
					}
				}
				
				if (!onAnyPlatform) {
					Player.p().onPlatform = false;
				}
				
				int xOffset = Player.p().getXPosition() - Constants.halfStageWidth;
				int yOffset = Player.p().getYPosition() + Constants.halfStageHeight;
				
				for (Platform pf : Platform.platforms) {
					pf.getAppearance().setX(pf.getXPosition() - 81 - xOffset);
					pf.getAppearance().setY(-pf.getYPosition() - 20 + yOffset);
					pf.getCollisionBox().setX(pf.getXPosition() - (int) Math.round((double) pf.getWidth() / 2) - xOffset);
					pf.getCollisionBox().setY(-pf.getYPosition() + yOffset);
				}
				
				for (int i = Projectile.projectiles.size() - 1; i >= 0; i--) {
					Projectile pr = Projectile.projectiles.get(i);
					pr.setLifeTime(pr.getLifeTime() - 1);
					
					if (pr.getLifeTime() == 0) {
						root.getChildren().remove(pr.getAppearance());
						Projectile.projectiles.remove(i);
						continue;
					}
					
					pr.setXPosition(pr.getXPosition() + pr.getXVelocity());
					//pr.setXVelocity(pr.getXVelocity() + 1);
					pr.getAppearance().setX(pr.getXPosition() - xOffset);
					pr.getAppearance().setY(-pr.getYPosition() + yOffset);
				}
				
				Player.p().getAppearance().toFront();
			}
		};
		
		timer.start();
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}