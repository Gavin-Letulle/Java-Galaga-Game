package cs2.game;

import cs2.util.Vec2;
import javafx.scene.image.Image;

public class Player extends Sprite {
  private Image bulletPicture;

  /*
  //This constructor should initialize all fields
  //**Remember that some fields are inherited from Sprite
  public Player(Image avatar, Image bullPic, Vec2 p) { }
  */
  public Player(Image avatar, Image bullPic, Vec2 p) {
    super(avatar, p);
    bulletPicture = bullPic;
  }
  /*
  // This method should create a new Bullet object and return it
  // The Bullet should be initialized with the bulletPicture, the
  // current position of the player, and a velocity going up the screen
  public Bullet shoot() { }
  */
  public Bullet shoot(){
    Vec2 clonedPos = new Vec2(pos.getX()+25, pos.getY()-25);
    return new Bullet(bulletPicture, clonedPos, new Vec2(0, -25));
  }
  /*
  // This method should move the player left by some amount
  public void moveLeft() { }
  */

  public void resetPos(){
    //System.out.println("Player position reset");
    pos = new Vec2(350.00, 700.00);
  }
  public void moveLeft(){
    move(new Vec2(-6, 0));
  }

  public void moveHalfLeft(){
    move(new Vec2(-3, 0));
  }
  /*
  // This method should move the player right by some amount
  public void moveRight() { }
  */
  public void moveRight(){
    move(new Vec2(6, 0));
  }

  public void moveHalfRight(){
    move(new Vec2(3, 0));
  }

  public void moveUp(){
    move(new Vec2(0, -6));
  }

  public void moveHalfUp(){
    move(new Vec2(0, -3));
  }

  public void moveDown(){
    move(new Vec2(0, 6));
  }

  public void moveHalfDown(){
    move(new Vec2(0, 3));
  }
}