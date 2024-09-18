package cs2.game;

import cs2.util.Vec2;
import javafx.scene.image.Image;

public class Player extends Sprite {
  private Image bulletPicture;

  public Player(Image avatar, Image bullPic, Vec2 p, double w, double h) { //Creates an instance of player
    super(avatar, p, w, h);
    bulletPicture = bullPic;
  }

  public Bullet shoot(){ //Creates a bullet to shoot from the player's current position
    Vec2 clonedPos = new Vec2(pos.getX()+25, pos.getY()-25);
    return new Bullet(bulletPicture, clonedPos, new Vec2(0, -25), 50, 50);
  }

  public void resetPos(){
    pos = new Vec2(350.00, 700.00);
  }
  public void moveLeft(){
    move(new Vec2(-6, 0));
  }

  public void moveHalfLeft(){ //Used when the player tries to use the A and LEFT keys at the same time to prevent double the speed
    move(new Vec2(-3, 0));
  }

  public void moveRight(){
    move(new Vec2(6, 0));
  }

  public void moveHalfRight(){ //Same thing but for moving right
    move(new Vec2(3, 0));
  }

  public void moveUp(){
    move(new Vec2(0, -6));
  }

  public void moveHalfUp(){ //Same but for moving up
    move(new Vec2(0, -3));
  }

  public void moveDown(){
    move(new Vec2(0, 6));
  }

  public void moveHalfDown(){ //Same but for moving down
    move(new Vec2(0, 3));
  }
}