package cs2.game;

import java.util.ArrayList;

import cs2.util.Vec2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class EnemySwarm {
  private ArrayList<Enemy> swarm;
  public Image bulletPic;
  /*public int numCols;
  public int numRows;*/

  /*
  // This constructor should create a swarm of enemies in a grid
  // The grid should be nRows x nCols in size.
  // The enemPic and bullPic should be used to create the Enemy instances
  // that are added to the ArrayList. The enemies should be spaced out
  // in a grid pattern across the top of the screen.
  public EnemySwarm(int nRows, int nCols, Image enemPic, Image bullPic) { }
  */
  public EnemySwarm(int nRows, int nCols, Image enemPic, Image bullPic){
    /*for(int i = 0; i < (nRows * nCols); i++){
      swarm.add(new Enemy(enemPic, bullPic, new Vec2()));
    }*/
    double yPos = 50;
    double xPos = 0;
    swarm = new ArrayList<>();
    for(int i = 0; i < nRows; i ++){
      ArrayList<Enemy> row = new ArrayList<>();
      xPos = 80;
      for(int j = 0; j < nCols; j ++){
        row.add(new Enemy(enemPic, bullPic, new Vec2(xPos, yPos)));
        xPos += 65;
      }
      for(int j = 0; j < row.size(); j ++){
        swarm.add(row.get(j));
      }
      yPos += 50;
    }
  }
  /*
  // This method should display all enemies in the swarm
  public void display(GraphicsContext g) { }
  */
  public void display(GraphicsContext g, double w, double h){
    for(int i = 0; i < swarm.size(); i++){
      Enemy enemy = swarm.get(i);
      g.drawImage(enemy.img, enemy.pos.getX(), enemy.pos.getY(), w, h);
    }
  }
  /*
  // This method should choose one enemy at random from the swarm,
  // and have that enemy shoot a bullet. Return that Bullet.
  public Bullet shoot() { }
  */
  public Bullet shoot(){
    int randomInt = (int) (Math.random() * (swarm.size() + 1));
    Enemy enemy = swarm.get(randomInt);
    return enemy.shoot();
  }
}
