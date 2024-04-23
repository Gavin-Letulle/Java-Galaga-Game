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
import javafx.scene.input.KeyCode;
import java.util.*;

public class SpaceGameApp extends Application {

  public static final Image playerSprite = new Image("file:GalagaShip.png");
  public static final Image enemySprite1 = new Image("file:Bee.webp");
  public static final Image enemySprite2 = new Image("file:Butterfly.png");
  public static final Image enemySprite3 = new Image("file:Boss.webp");
  public static final Image bulletSprite = new Image("file:Bullet.png");
  public final long playerShootCooldown = 225_000_000;
  public final long enemyShootCooldown = 300_000_000;

  public void start(Stage stage) {
    stage.setTitle("GALAGA");
    stage.show();

    Canvas canvas = new Canvas(800,800);
    stage.setScene(new Scene(new StackPane(canvas)));
    GraphicsContext g = canvas.getGraphicsContext2D();

    Player player = new Player(playerSprite, bulletSprite, new Vec2(350.0, 700.0), 100, 100);
    ArrayList<Bullet> playerBullets = new ArrayList<>();
    ArrayList<Bullet> enemyBullets = new ArrayList<>();
    Set<KeyCode> keys = new HashSet<>();

    AnimationTimer timer = new AnimationTimer() {
      EnemySwarm swarm = new EnemySwarm(5, 10, enemySprite2, bulletSprite, 50, 50);
      public long playerLastShotTime = 0;
      public long enemyLastShotTime = 0;
      public void handle(long t) {
        g.setFill(Color.BLACK);
        g.fillRect(0,0, 800,800);

        player.display(g);
        swarm.display(g, 50, 50);

        long currentTime = System.nanoTime();
        if (currentTime - enemyLastShotTime >= enemyShootCooldown) {
          /*if(Math.random() * 45 < 1){
            swarm.shoot();
            enemyBullets.add(swarm.shoot());
            enemyLastShotTime = currentTime;
          }*/
          swarm.shoot();
          enemyBullets.add(swarm.shoot());
          enemyLastShotTime = currentTime;
        }

        for(KeyCode key : keys){
          if (key == KeyCode.RIGHT && player.pos.getX() <= 718.5) {
            if(keys.contains(KeyCode.D)){
              player.moveHalfRight();
            }
            else{
              player.moveRight();
            }
          }
          if (key == KeyCode.D && player.pos.getX() <= 718.5) {
            if(keys.contains(KeyCode.RIGHT)){
              player.moveHalfRight();
            }
            else{
              player.moveRight();
            }
          }
          if (key == KeyCode.LEFT && player.pos.getX() >= -10) {
            if(keys.contains(KeyCode.A)){
              player.moveHalfLeft();
            }
            else{
              player.moveLeft();
            }
          }
          if (key == KeyCode.A && player.pos.getX() >= -10) {
            if(keys.contains(KeyCode.LEFT)){
              player.moveHalfLeft();
            }
            else{
              player.moveLeft();
            }
          }
          if (key == KeyCode.UP && player.pos.getY() >= 0) {
            if(keys.contains(KeyCode.W)){
              player.moveHalfUp();
            }
            else{
              player.moveUp();
            }
          }
          if (key == KeyCode.W && player.pos.getY() >= 0) {
            if(keys.contains(KeyCode.UP)){
              player.moveHalfUp();
            }
            else{
              player.moveUp();
            }
          }
          if (key == KeyCode.DOWN && player.pos.getY() <= 718.5) {
            if(keys.contains(KeyCode.S)){
              player.moveHalfDown();
            }
            else{
              player.moveDown();
            }
          }
          if (key == KeyCode.S && player.pos.getY() <= 718.5) {
            if(keys.contains(KeyCode.DOWN)){
              player.moveHalfDown();
            }
            else{
              player.moveDown();
            }
          }
          if (key == KeyCode.SPACE) {
            if (currentTime - playerLastShotTime >= playerShootCooldown) {
                player.shoot();
                playerBullets.add(player.shoot());
                playerLastShotTime = currentTime;
            }
          }
        }

        for(Bullet enemyBullet : enemyBullets){
          enemyBullet.display(g);
          enemyBullet.update();
          if(enemyBullet.intersection(player)){
            player.resetPos();
            enemyBullets.remove(enemyBullet);
          }
          if(enemyBullet.pos.getY() > 800){
            enemyBullets.remove(enemyBullet);
          }
        }

        for(Bullet playerBullet : playerBullets){
          playerBullet.display(g);
          playerBullet.update();
          swarm.enemyIntersection(playerBullet);
          for(Bullet enemyBullet : enemyBullets){
            if((playerBullet.intersection(enemyBullet)) || enemyBullet.intersection(playerBullet)){
              playerBullets.remove(playerBullet);
              enemyBullets.remove(enemyBullet);
            }
          }
          if(playerBullet.pos.getY() < -75){
            playerBullets.remove(playerBullet);
          }
        }

        for(Enemy enemy : swarm.getEnemies()){
          if(enemy.intersection(player)){
            player.resetPos();
          }
        }

        playerBullets.remove(swarm.getBullet());

        if(swarm.enemyCount() <= 0){
          swarm = new EnemySwarm(5, 10, enemySprite2, bulletSprite, 50, 50);
        }
      }
    };
    timer.start();

    canvas.setOnKeyPressed(event -> {
      keys.add(event.getCode());
    });

    canvas.setOnKeyReleased(event -> {
      keys.remove(event.getCode());
    });

    canvas.requestFocus();
  }
}