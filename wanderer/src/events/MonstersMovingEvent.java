package events;

import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;
import models.areaelements.Tile;
import models.characters.Enemy;

public class MonstersMovingEvent extends Event{
  public MonstersMovingEvent(Tile[][] tiles,
                             List<Enemy> movingEnemies) {
    super(tiles,movingEnemies);
    monstersMoving(movingEnemies);
  }

  public static void monstersMoving(List<Enemy> enemies){
    for (Enemy enemy : enemies) {

      moveRandomly(enemy);
    }
  }
  public static void moveRandomly(Enemy enemy) {
    Random random = new Random();
    int direction = random.nextInt(4);
    switch (direction){
      case 0 -> enemy.moveUp(true);
      case 1 -> enemy.moveRight(true);
      case 2 -> enemy.moveDown(true);
      case 3 -> enemy.moveLeft(true);
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {

  }

  @Override
  public void keyReleased(KeyEvent e) {

  }
}
