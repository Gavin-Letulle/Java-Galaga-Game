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
    return new Bullet(bulletPicture, pos, new Vec2(0, 50));
  }
  /*
  // This method should move the player left by some amount
  public void moveLeft() { }
  */
  public void moveLeft(){
    move(new Vec2(-1, 0));
  }
  /*
  // This method should move the player right by some amount
  public void moveRight() { }
  */
  public void moveRight(){
    move(new Vec2(1, 0));
  }
}
