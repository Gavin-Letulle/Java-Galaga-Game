package cs2.game;

import cs2.util.Vec2;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.scene.input.KeyCode;

public class SpaceGameApp extends Application {

  public static final Image playerSprite = new Image("file:GalagaShip.png");
  public static final Image enemySprite1 = new Image("file:Bee.webp");
  public static final Image enemySprite2 = new Image("file:Butterfly.png");
  public static final Image enemySprite3 = new Image("file:Boss.webp");
  public static final Image bulletSprite = new Image("file:Bullet.png");

  public void start(Stage stage) {
    stage.setTitle("GALAGA");
    stage.show();
    //You can change the window size here if you want
    Canvas canvas = new Canvas(800,800);
    stage.setScene(new Scene(new StackPane(canvas)));
    GraphicsContext g = canvas.getGraphicsContext2D();

    /* Your main game logic will go here
     * This will likely mean creating instances of Player and EnemySwarm, along
     * with a collection (probably ArrayList) of Bullets.
     * 
     * You should create a KeyPressed event handler that moves the player
     * when the left or right arrow keys are pressed, and fires a bullet when
     * the space bar is pressed. Add the bullet created to the collection.
     * 
     * You should also create an AnimationTimer that displays everything and
     * moves the bullets around the screen. Also, an enemy chosen from the swarm
     * at random should shoot and have that bullet added to the collection.
     */

    Player player = new Player(playerSprite, bulletSprite, new Vec2(350.0, 700.0));
    EnemySwarm swarm = new EnemySwarm(5, 10, enemySprite2, bulletSprite);
    ArrayList<Bullet> bullets = new ArrayList<>();

    AnimationTimer timer = new AnimationTimer() {
      public void handle(long t) {
        g.setFill(Color.BLACK);
        g.fillRect(0,0, 800,800);

        player.display(g, 100, 100);
        swarm.display(g, 50, 50);

        if(Math.random() * 45 < 1){
          swarm.shoot();
          bullets.add(swarm.shoot());
        }

        for(int i = 0; i < bullets.size(); i ++){
          bullets.get(i).display(g, 50, 60);
          bullets.get(i).update();
        }
      }
    };
    timer.start();

    canvas.setOnKeyPressed(event -> {
      if (((event.getCode() == KeyCode.RIGHT) || (event.getCode() == KeyCode.D)) && player.pos.getX() <= 718.5) {
          player.moveRight();
      }
      if (((event.getCode() == KeyCode.LEFT) || (event.getCode() == KeyCode.A)) && player.pos.getX() >= -10) {
        player.moveLeft();
      }
      if (((event.getCode() == KeyCode.UP) || (event.getCode() == KeyCode.W)) && player.pos.getY() >= 0) {
        player.moveUp();
      }
      if (((event.getCode() == KeyCode.DOWN) || (event.getCode() == KeyCode.S)) && player.pos.getY() <= 718.5) {
        player.moveDown();
      }
      if (event.getCode() == KeyCode.SPACE) {
        player.shoot();
        bullets.add(player.shoot());
      }
    });
    canvas.requestFocus();
    
  }
}