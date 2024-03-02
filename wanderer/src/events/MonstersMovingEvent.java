package events;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import models.areaelements.Tile;
import models.characters.Enemy;
import models.characters.MovingCharacter;

public class MonstersMovingEvent {
  public MonstersMovingEvent() {
  }

  public static void monstersMoving(Tile[][] tiles,
                                    ArrayList<Enemy> movingEnemies) {
    for (MovingCharacter enemy : movingEnemies) {

      moveRandomly(enemy, tiles);
    }
  }

  public static void moveRandomly(MovingCharacter enemy, Tile[][] tileMap) {
    Random random = new Random();
    Tile fromTile = tileMap[enemy.getY()][enemy.getX()];
    Tile toTile;

    while (true) {
      int direction = random.nextInt(4);
      switch (direction) {
        case 0 -> {
          try {
            toTile = tileMap[enemy.getY() - 1][enemy.getX()];
          } catch (IndexOutOfBoundsException e) {
            continue;
          }
          enemy.moveUp(fromTile, toTile);
          return;
        }
        case 1 -> {
          try {
            toTile = tileMap[enemy.getY() + 1][enemy.getX()];
          } catch (IndexOutOfBoundsException e) {
            continue;
          }
          enemy.moveDown(fromTile, toTile);
          return;
        }
        case 2 -> {
          try {
            toTile = tileMap[enemy.getY()][enemy.getX() - 1];
          } catch (IndexOutOfBoundsException e) {
            continue;
          }
          enemy.moveLeft(fromTile, toTile);
          return;
        }
        case 3 -> {
          try {
            toTile = tileMap[enemy.getY()][enemy.getX() + 1];
          } catch (IndexOutOfBoundsException e) {
            continue;
          }
          enemy.moveRight(fromTile, toTile);
          return;
        }
      }
    }
  }
}
