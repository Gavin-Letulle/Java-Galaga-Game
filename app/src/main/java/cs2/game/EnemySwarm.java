package cs2.game;

import java.util.ArrayList;

import cs2.util.Vec2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class EnemySwarm {
  public ArrayList<Enemy> swarm;
  public Image bulletPic;
  public Bullet bullet;
  public int score;

  public EnemySwarm(int nRows, int nCols, Image enemPic, Image bullPic, double w, double h, int newScore){ //Creates a grid of enemies
    score = newScore;
    double yPos = 50;
    double xPos = 0;
    swarm = new ArrayList<>();
    for(int i = 0; i < nRows; i ++){
      ArrayList<Enemy> row = new ArrayList<>();
      xPos = 80;
      for(int j = 0; j < nCols; j ++){
        row.add(new Enemy(enemPic, bullPic, new Vec2(xPos, yPos), w, h));
        xPos += 65;
      }
      for(int j = 0; j < row.size(); j ++){
        swarm.add(row.get(j));
      }
      yPos += 50;
    }
  }

  public void display(GraphicsContext g, double w, double h){ //Calls display on each enemy in swarm
    for(int i = 0; i < swarm.size(); i++){
      Enemy enemy = swarm.get(i);
      g.drawImage(enemy.img, enemy.pos.getX(), enemy.pos.getY(), w, h);
    }
  }

  public Bullet shoot(){ //Makes random enemy in swarm shoot
    int randomInt = (int) (Math.random() * (swarm.size() + 1));
    Enemy enemy = swarm.get(randomInt);
    return enemy.shoot();
  }

  public boolean enemyIntersection(Bullet obj){ //Removes an enemy if they are hit by a bullet
    boolean intersect = false;
    for(Enemy enemy : swarm){
      if(enemy.intersection(obj)){
        intersect = true;
        bullet = obj;
        swarm.remove(enemy);
        score ++;
      }
    }
    return intersect;
  }

  public Bullet getBullet(){
    return bullet;
  }

  public int getScore(){
    return score;
  }

  public void setScore(int sc){
    score = sc;
  }

  public ArrayList<Enemy> getEnemies(){
    ArrayList<Enemy> enemies = new ArrayList<>();
    for(Enemy enemy : swarm){
      enemies.add(enemy);
    }
    return enemies;
  }

  public int enemyCount(){
    return swarm.size();
  }
}