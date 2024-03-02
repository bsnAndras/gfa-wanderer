package events;

import java.util.ArrayList;
import java.util.Random;
import models.areaelements.Tile;
import models.characters.Enemy;
import models.characters.MovingCharacter;

public class MonstersMovingEvent {
  public MonstersMovingEvent() {
  }

  public static Battle monstersMoving(Tile[][] tiles,
                                      ArrayList<Enemy> movingEnemies) {
    Battle battle;
    for (MovingCharacter enemy : movingEnemies) {
      battle = moveRandomly(enemy, tiles);
      if (battle != null) {
        return battle;
      }
    }
    return null;
  }

  public static Battle moveRandomly(MovingCharacter enemy, Tile[][] tileMap) {
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
          return enemy.moveUp(fromTile, toTile);
        }
        case 1 -> {
          try {
            toTile = tileMap[enemy.getY() + 1][enemy.getX()];
          } catch (IndexOutOfBoundsException e) {
            continue;
          }
          return enemy.moveDown(fromTile, toTile);
        }
        case 2 -> {
          try {
            toTile = tileMap[enemy.getY()][enemy.getX() - 1];
          } catch (IndexOutOfBoundsException e) {
            continue;
          }
          return enemy.moveLeft(fromTile, toTile);
        }
        case 3 -> {
          try {
            toTile = tileMap[enemy.getY()][enemy.getX() + 1];
          } catch (IndexOutOfBoundsException e) {
            continue;
          }
          return enemy.moveRight(fromTile, toTile);
        }
      }
    }
  }
}
