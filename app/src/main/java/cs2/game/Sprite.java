package cs2.game;

import cs2.util.Vec2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Sprite {
  protected Image img; // the image to be displayed for this sprite
  protected Vec2 pos; // the current position of this sprite

  /* The remained of the constructors and methods should be uncommented
   * as you write your code. I recommend keeping your project in a state
   * that it can always be run, even if it doesn't do much.
   * Then slowly over time, you can un-comment and add in additional
   * features.
   * DO NOT TRY TO WRITE EVERYTHING ALL AT ONCE. IT WILL NOT WORK.
   */

  /*public static final Image playerSprite = new Image("file:GalagaShip.png");
  public static final Image enemySprite1 = new Image("file:Bee.webp");
  public static final Image enemySprite2 = new Image("file:Butterfly.png");
  public static final Image enemySprite3 = new Image("file:Boss.webp");
  public static final Image bulletSprite = new Image("file:Bullet.png");*/

  /*
  // The constructor should initialize the fields of the class
  public Sprite(Image i, Vec2 p) { }
  */

  public Sprite(Image i, Vec2 p){
    img = i;
    pos = p;
  }

  /*
  // This method should draw the image at the current position
  public void display(GraphicsContext g) { }
  */

  public void display(GraphicsContext g, double w, double h) { 
    g.drawImage(img, pos.getX(), pos.getY(), w, h);
  }

  /*
  // This method should change the location/position of the sprite
  // by the amount specified in the parameter delta
  public void move(Vec2 delta) { }
   */
  
  public void move(Vec2 delta){
    pos.addThis(delta);
  }

}
