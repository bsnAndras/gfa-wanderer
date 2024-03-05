package models;

import java.util.Random;
import models.areaelements.Floor;
import models.areaelements.Tile;
import models.areaelements.Wall;
import models.characters.Boss;
import models.characters.Enemy;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Area {
  private List<Tile[][]> tiles;
  private ArrayList<Enemy> enemies;

  public Area() {
    tiles = new ArrayList<>();
    generateTiles();
  }

  private void generateTiles() {
    int[][] zeroTiles =
        {
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 1, 1, 1, 1},
            {0, 1, 1, 1, 1, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
            {0, 0, 1, 0, 1, 0, 1, 0, 0, 0},
            {0, 1, 1, 0, 1, 0, 1, 1, 0, 1},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
            {1, 1, 1, 1, 1, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 1, 1, 1, 0, 0, 0, 1, 0}};

    tiles.add(generateTileMap(zeroTiles));

    int[][] firstTiles =
        {
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 1, 1, 1, 1},
            {0, 1, 1, 1, 1, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
            {0, 0, 1, 0, 1, 0, 1, 0, 0, 0},
            {0, 1, 1, 0, 1, 0, 1, 1, 0, 1},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
            {1, 1, 1, 1, 1, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 1, 1, 1, 0, 0, 0, 1, 0}};
    tiles.add(generateTileMap(firstTiles));
  }

  @NotNull
  private static Tile[][] generateTileMap(int[][] intTiles) {
    Tile[][] tileMap = new Tile[intTiles.length][intTiles.length];
    for (int i = 0; i < intTiles.length; i++) {
      for (int j = 0; j < intTiles[i].length; j++) {
        tileMap[i][j] = intTiles[i][j] == 0 ? new Floor() : new Wall();
      }
    }
    return tileMap;
  }

  /**
   * <p>fills and returns a List of Enemy randomly with a Boss and 2-5 additional enemies.</p>
   *
   * @param areaLevel the level of the Hero
   * @return a random List of Enemy with the same level as the Hero
   */
  public ArrayList<Enemy> createEnemies(int areaLevel) {
    this.enemies = new ArrayList<>();
    Boss boss;
    Enemy enemy;
    Random random = new Random();
    int enemyCount = random.nextInt(3, 7);
    int hasKeyIndex = random.nextInt(1, enemyCount);

    do { //place the Boss somewhere on the board
      boss = new Boss(areaLevel + randomLevel(), randomCoord(), randomCoord());
    } while (getTiles(areaLevel)[boss.getY()][boss.getX()].isOccupied()
        || getTiles(areaLevel)[boss.getY()][boss.getX()] instanceof Wall);
    enemies.add(boss);
    //set the Tile occupied
    getTiles(areaLevel)[boss.getY()][boss.getX()].occupy(boss);

    for (int i = 1; i < enemyCount; i++) {
      boolean hasKey;
      do { //place enemies somewhere on the board
        hasKey = i == hasKeyIndex;
        enemy = new Enemy("Skeleton " + i, areaLevel + randomLevel(), randomCoord(), randomCoord(),
            hasKey);
      } while (getTiles(areaLevel)[enemy.getY()][enemy.getX()].isOccupied()
          || getTiles(areaLevel)[enemy.getY()][enemy.getX()] instanceof Wall);
      enemies.add(enemy);
      //set the Tile occupied
      getTiles(areaLevel)[enemy.getY()][enemy.getX()].occupy(enemy);
    }
    return enemies;
  }

  private int randomLevel() {
    int randLevel;
    Random random = new Random();
    randLevel = random.nextInt(10);
    if (randLevel < 5) {
      randLevel = 0;
    } else if (randLevel < 9) {
      randLevel = 1;
    } else {
      randLevel = 2;
    }
    return randLevel;
  }

  /**
   * Gives back a random number between 0-9
   * to use as a coordinate on a 10x10 board
   *
   * @return a random X or Y coordinate
   */
  private int randomCoord() {
    Random random = new Random();
    return random.nextInt(10);
  }


  public Tile[][] getTiles(int areaLevel) {
    return tiles.get(areaLevel);
  }
}
