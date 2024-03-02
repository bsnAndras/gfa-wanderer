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
  private List<Enemy> enemies;

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
  public List<Enemy> createEnemies(int areaLevel) {
    this.enemies = new ArrayList<>();
    Boss boss;
    Enemy enemy;
    Random random = new Random();
    int enemyCount = random.nextInt(3, 7);

    do { //place the Boss somewhere on the board
      boss = new Boss(areaLevel + randomLevel(), randomCoord(), randomCoord());
    } while (getTiles(areaLevel)[boss.getY()][boss.getX()].isOccupied()
        || getTiles(areaLevel)[boss.getY()][boss.getX()] instanceof Wall);
    enemies.add(boss);
    //set the Tile occupied
    getTiles(areaLevel)[boss.getY()][boss.getX()].occupy();

    for (int i = 1; i < enemyCount; i++) {
      do { //place enemies somewhere on the board
        enemy = new Enemy(areaLevel + randomLevel(), randomCoord(), randomCoord());
      } while (getTiles(areaLevel)[enemy.getY()][enemy.getX()].isOccupied()
          || getTiles(areaLevel)[enemy.getY()][enemy.getX()] instanceof Wall);
      enemies.add(enemy);
      //set the Tile occupied
      getTiles(areaLevel)[enemy.getY()][enemy.getX()].occupy();
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

  /**
   *
   * @param x
   * @param y
   * @return <ul>
   *   <li>0 if free to go</li>
   *   <li>1 if Wall found</li>
   *   <li>-1 if Enemy found</li>
   * </ul>
   */
  public int detectObstacle(int x, int y) {

    if (x < 0 || x > 9 ||
        y < 0 || y > 9) {
      //if out of the area -> 1
      return 1;
    } else if (getTiles(1)[y][x] instanceof Wall) {
      //if wall -> 1
      return 1;
    } else if (getTiles(1)[y][x].isOccupied()) {
      //if enemy found -> -1
      return -1;
    } else {
      return 0;
    }
  }
}
