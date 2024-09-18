package cs2.game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import cs2.util.Vec2;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SpaceGameApp extends Application {

  public static final Image playerSprite = new Image("file:GalagaShip.png");
  public static final Image enemySprite2 = new Image("file:Butterfly.png");
  public static final Image bulletSprite = new Image("file:Bullet.png");
  public final long playerShootCooldown = 225_000_000;
  public final long enemyShootCooldown = 300_000_000;

  public void start(Stage stage) {
    stage.setTitle("GALAGA");
    stage.show();

    Canvas canvas = new Canvas(800,800);
    stage.setScene(new Scene(new StackPane(canvas)));
    GraphicsContext g = canvas.getGraphicsContext2D();

    Player player = new Player(playerSprite, bulletSprite, new Vec2(350.0, 700.0), 100, 100); //Creates an instance of the player
    ArrayList<Bullet> playerBullets = new ArrayList<>(); //Creates an array list for each bullet shot by the player to enter
    ArrayList<Bullet> enemyBullets = new ArrayList<>(); //Same but for enemy bullets
    Set<KeyCode> keys = new HashSet<>(); //Creates an array list for each key input to be stored

    AnimationTimer timer = new AnimationTimer() {
      long playerLastShotTime = 0;
      long enemyLastShotTime = 0;
      EnemySwarm swarm;
      boolean gameOver = false;
      int lives = 3;
      boolean swarmMade = false;
      public void handle(long t) {
        g.setFill(Color.BLACK);
        g.fillRect(0,0, 800,800);

        canvas.setOnKeyPressed(event -> { //Puts each key press into array list
          keys.add(event.getCode());
        });
    
        canvas.setOnKeyReleased(event -> { //Takes each released key out of array list
          keys.remove(event.getCode());
        });
    
        canvas.requestFocus();

        if(gameOver == false){ //All code underneath only runs if there is no game over

          if(lives <= 0){
            gameOver = true;
          }

          if(!swarmMade){ //Makes new swarm at start of game and after restarting the game
            swarm = new EnemySwarm(5, 10, enemySprite2, bulletSprite, 50, 50, 0);
            swarmMade = true;
          }
          player.display(g); //Makes player visible on screen
          swarm.display(g, 50, 50); //Makes swarm visible on screen
          g.setFill(Color.WHITE);
          g.setFont(Font.font("JGB18030 Bitmap", 25));
          g.fillText("Score: " + swarm.getScore(), 250, 38);
          g.fillText("Lives: " + lives, 450, 38);

          long currentTime = System.nanoTime();
          if (currentTime - enemyLastShotTime >= enemyShootCooldown) { //Makes sure swarm doesn't constantly fire
            swarm.shoot();
            enemyBullets.add(swarm.shoot());
            enemyLastShotTime = currentTime;
          }

          for(KeyCode key : keys){
            if (key == KeyCode.RIGHT && player.pos.getX() <= 718.5) { //Checks to make sure player can't move twice as fast using both D and RIGHT keys
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
            if (key == KeyCode.SPACE) { //Shoots but makes sure player can't constantly fire
              if (currentTime - playerLastShotTime >= playerShootCooldown) {
                  player.shoot();
                  playerBullets.add(player.shoot());
                  playerLastShotTime = currentTime;
              }
            }
          }

          for(Bullet enemyBullet : enemyBullets){ //Makes each enemy bullet hurt player if intersection is triggered
            enemyBullet.display(g);
            enemyBullet.update();
            if(enemyBullet.intersection(player)){
              lives --;
              player.resetPos();
              enemyBullets.remove(enemyBullet);
            }
            if(enemyBullet.pos.getY() > 800){ //Removes off-screen enemy bullets
              enemyBullets.remove(enemyBullet);
            }
          }

          for(Bullet playerBullet : playerBullets){ //Makes each player bullet remove an enemy if intersection is triggered
            playerBullet.display(g);
            playerBullet.update();
            swarm.enemyIntersection(playerBullet);
            for(Bullet enemyBullet : enemyBullets){ //Makes player and enemy bullets destroy each other if they collide
              if((playerBullet.intersection(enemyBullet)) || enemyBullet.intersection(playerBullet)){
                playerBullets.remove(playerBullet);
                enemyBullets.remove(enemyBullet);
              }
            }
            if(playerBullet.pos.getY() < -75){ //Removes off-screen player bullets
              playerBullets.remove(playerBullet);
            }
          }

          for(Enemy enemy : swarm.getEnemies()){ //Player resets position and loses life if they collide with enemy 
            if(enemy.intersection(player)){
              lives --;
              player.resetPos();
            }
          }

          playerBullets.remove(swarm.getBullet());

          if(swarm.enemyCount() <= 0){ //Makes new swarm when all enemies die
            swarm = new EnemySwarm(5, 10, enemySprite2, bulletSprite, 50, 50, swarm.getScore());
          }
        }

      else{ //End screen lets you restart and resets everything
        g.setFill(Color.WHITE);
        g.setFont(Font.font("JGB18030 Bitmap", 50));
        g.fillText("GAME OVER", 250, 375);
        g.setFont(Font.font("JGB18030 Bitmap", 25));
        g.fillText("Press Enter to restart", 275, 450);
        for(KeyCode key : keys){
          if (key == KeyCode.ENTER) {
            lives = 3;
            swarmMade = false;
            Platform.runLater(() -> swarm.setScore(0));
            for(Bullet enemyBullet : enemyBullets){
              enemyBullets.remove(enemyBullet);
            }
            for(Bullet playerBullet : playerBullets){
              playerBullets.remove(playerBullet);
            }
            gameOver = false;
          }
        }
      }
    }
  };
  timer.start();
  }
}