package cs2.game;

import cs2.util.Vec2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Sprite {
  protected Image img; // the image to be displayed for this sprite
  protected Vec2 pos; // the current position of this sprite
  public double width;
  public double height;

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

  public Sprite(Image i, Vec2 p, double w, double h){
    img = i;
    pos = p;
    width = w;
    height = h;
  }

  /*
  // This method should draw the image at the current position
  public void display(GraphicsContext g) { }
  */

  public void display(GraphicsContext g) { 
    g.drawImage(img, pos.getX(), pos.getY(), width, height);
  }

  /*
  // This method should change the location/position of the sprite
  // by the amount specified in the parameter delta
  public void move(Vec2 delta) { }
   */
  
  public void move(Vec2 delta){
    pos.addThis(delta);
  }
  
  public boolean intersection(Sprite obj) {
    double x1 = pos.getX() + width / 2; 
    double y1 = pos.getY() + height / 2; 
    double x2 = obj.pos.getX() + obj.width / 2; 
    double y2 = obj.pos.getY() + obj.height / 2; 
    double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    double threshold = 20; 
    
    if (distance < threshold) {
        double w1 = width;
        double h1 = height;
        double w2 = obj.width;
        double h2 = obj.height;

        return x1 < x2 + w2 &&
               x1 + w1 > x2 &&
               y1 < y2 + h2 &&
               y1 + h1 > y2;
    } 
    else {
        return false;
    }
  }
}