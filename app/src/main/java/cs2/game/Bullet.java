package cs2.game;

import cs2.util.Vec2;
import javafx.scene.image.Image;

public class Bullet extends Sprite {
  private Vec2 vel; // the velocity/speed the bullet moves

  public Bullet(Image bul, Vec2 p, Vec2 v, double w, double h){ //Creates an instance of bullet
    super(bul, p, w, h);
    vel = v;
  }

  public void update(){ //Moves the bullet along the screen
    pos.addThis(vel);
  }
}