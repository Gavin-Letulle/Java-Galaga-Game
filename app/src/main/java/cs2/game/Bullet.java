package cs2.game;

import cs2.util.Vec2;
import javafx.scene.image.Image;

public class Bullet extends Sprite {
  private Vec2 vel; // the velocity/speed the bullet should move
  //public static final Image bulletSprite = new Image("file:Bullet.png");

  /*
  //This constructor should initialize all fields
  //**Remember that some fields are inherited from Sprite
  public Bullet(Image bul, Vec2 p, Vec2 v) { }
  */
  public Bullet(Image bul, Vec2 p, Vec2 v, double w, double h){
    super(bul, p, w, h);
    vel = v;
  }
  /*
  // This method should update the position of the bullet by adding
  // the velocity to the current position
  public void update() { }
  */
  public void update(){
    pos.addThis(vel);
  }
}