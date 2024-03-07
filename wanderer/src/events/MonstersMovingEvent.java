package events;

import java.util.ArrayList;
import java.util.Random;
import models.Direction;
import models.areaelements.Tile;
import models.areaelements.Wall;
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
    Tile fromTile = enemy.getCurrentTile();
    //Add the four Direction options to a Tile[]
    // also checks validity of all: 0 - can go, -1 - enemy found
    Tile[] neighbourTiles = new Tile[4];

    //UP
    if (enemy.detectObstacle(tileMap, enemy.getX(), enemy.getY() - 1) <= 0) {
      neighbourTiles[0] = tileMap[enemy.getY() - 1][enemy.getX()];
    }

    //RIGHT
    if (enemy.detectObstacle(tileMap, enemy.getX()+1, enemy.getY()) <= 0) {
      neighbourTiles[1] = tileMap[enemy.getY()][enemy.getX()+1];
    }

    //DOWN
    if (enemy.detectObstacle(tileMap, enemy.getX(), enemy.getY() + 1) <= 0) {
      neighbourTiles[2] = tileMap[enemy.getY() + 1][enemy.getX()];
    }

    //LEFT
    if (enemy.detectObstacle(tileMap, enemy.getX()-1, enemy.getY()) <= 0) {
      neighbourTiles[3] = tileMap[enemy.getY()][enemy.getX()-1];
    }

    //choose a direction randomly

    Tile toTile = null;
    Direction direction = null;

    int randDirection = random.nextInt(4);
    do {
      switch (randDirection) {
        case 0: { //Go UP
          if (neighbourTiles[0] != null) {
            direction = Direction.UP;
            toTile = neighbourTiles[0];
            break;
          }
        }
        case 1: { //Go RIGHT
          if (neighbourTiles[1] != null) {
            direction = Direction.RIGHT;
            toTile = neighbourTiles[1];
            break;
          }
        }
        case 2: { //Go DOWN
          if (neighbourTiles[2] != null) {
            direction = Direction.DOWN;
            toTile = neighbourTiles[2];
            break;
          }
        }
        case 3: { //Go LEFT
          if (neighbourTiles[3] != null) {
            direction = Direction.LEFT;
            toTile = neighbourTiles[3];
            break;
          }
        }
        default:
          randDirection--;
      }
      if (direction != null) {
        break;
      }
    } while (randDirection >= 0);
    return enemy.move(direction, toTile);
  }
}
