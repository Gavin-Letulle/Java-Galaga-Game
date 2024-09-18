package cs2.game;

import cs2.util.Vec2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Sprite {
  protected Image img; // the image to be displayed for this sprite
  protected Vec2 pos; // the current position of this sprite
  public double width;
  public double height;

  public Sprite(Image i, Vec2 p, double w, double h){
    img = i;
    pos = p;
    width = w;
    height = h;
  }

  public void display(GraphicsContext g) { 
    g.drawImage(img, pos.getX(), pos.getY(), width, height);
  }
  
  public void move(Vec2 delta){
    pos.addThis(delta);
  }

  public boolean intersection(Sprite obj) {
    double x1 = pos.getX() + width / 2; 
    double y1 = pos.getY() + height / 2; 
    double x2 = obj.pos.getX() + obj.width / 2; 
    double y2 = obj.pos.getY() + obj.height / 2; 
    double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    double threshold = 22; 
    
    if (distance < threshold) {
        double w1 = width;
        double h1 = height;
        double w2 = obj.width;
        double h2 = obj.height;

        return x1 < x2 + w2 && x1 + w1 > x2 && y1 < y2 + h2 && y1 + h1 > y2;
    } 
    else {
        return false;
    }
  }
}