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

    int[][] secondTiles =
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
    tiles.add(generateTileMap(secondTiles));
  }

  @NotNull
  private static Tile[][] generateTileMap(int[][] firstTiles) {
    Tile[][] firstTileMap = new Tile[firstTiles.length][firstTiles.length];
    for (int i = 0; i < firstTiles.length; i++) {
      for (int j = 0; j < firstTiles[i].length; j++) {
        firstTileMap[i][j] = firstTiles[i][j] == 0 ? new Floor() : new Wall();
      }
    }
    return firstTileMap;
  }

  /**
   * <p>fills and returns a List of Enemy randomly with a Boss and 2-5 additional enemies.</p>
   * @param level the level of the Hero
   * @return a random List of Enemy with the same level as the Hero
   */
  public List<Enemy> createEnemies(int level) {
    this.enemies = new ArrayList<>();
    Random random = new Random();
    int enemyCount = random.nextInt(3,7);
    Boss boss = new Boss(level, 2, 1);

    enemies.add(boss);

    for (int i = 1; i < enemyCount; i++) {
      enemies.add(new Enemy(level,i+2,i+2));
    }
    return enemies;
  }

  public Tile[][] getTiles(int index) {
    return tiles.get(index);
  }

  public boolean detectObstacle(int x, int y) {
    if (x < 0 || x > 9 ||
        y < 0 || y > 9) {
      return true;
    } else if (getTiles(1)[y][x] instanceof Wall) {
      return true;
    } else {
      return false;
    }
  }
}
