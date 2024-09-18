package cs2.game;

import cs2.util.Vec2;
import javafx.scene.image.Image;

public class Enemy extends Sprite {
  private Image bulletPicture;
  public double width;
  public double height;

  public Enemy(Image avatar, Image bulletPic, Vec2 p, double w, double h){  //Creates an instance of one enemy
    super(avatar, p, w, h);
    bulletPicture = bulletPic;
    width = w;
    height = h;
  }

  public Bullet shoot(){ //Creates a bullet to shoot from the current enemy's position plus an offset
    Vec2 clonedPos = new Vec2(pos.getX() + 2, pos.getY() + 18);
    return new Bullet(bulletPicture, clonedPos, new Vec2(0, 8), width, height);
  }
}
