package views;

import java.util.List;
import models.Area;
import models.Direction;
import models.Hero;
import models.areaelements.Floor;
import models.areaelements.Tile;

import javax.swing.*;
import java.awt.*;
import models.characters.Boss;
import models.characters.Enemy;

public class Board extends JComponent {

  private Hero hero;
  private List<Enemy> enemies;
  private Area area;

  private final int tileSize;

  public Board() {
    tileSize = 72;
    setPreferredSize(new Dimension(tileSize * 10, tileSize * 10 + 40));
    setVisible(true);
  }

  public void setHero(Hero hero) {
    this.hero = hero;
  }

  public void setEnemies(List<Enemy> enemies) {
    this.enemies = enemies;
  }

  public void setArea(Area area) {
    this.area = area;
  }

  /**
   * <p>Draws the board then the Hero, then the Enemies, and then the Statistics</p>
   *
   * @param graphics the <code>Graphics</code> context in which to paint
   */
  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);

    drawTiles(graphics);
    drawHero(graphics);
    drawEnemy(graphics);
    drawStatistics(graphics);
  }

  /**
   * <p>Draws the Tiles based on the map set up in the Area class</p>
   * <p>This method iterates through the whole Area and loads the image for every Tile
   * based upon its Class type (Floor or Wall)</p>
   *
   * @param graphics
   */
  private void drawTiles(Graphics graphics) {
    Tile[][] tiles = area.getTiles(1);
    for (int i = 0; i < tiles.length; i++) {
      for (int j = 0; j < tiles[0].length; j++) {
        String image = tiles[j][i] instanceof Floor ? "img/floor.png" : "img/wall.png";
                /*
                Ternary operator (condition ? doIfTrue : else)  is the same as:
                if (tiles[j][i] instanceof Floor) {
                    image = "img/floor.png";
                } else {
                    image = "img/wall.png";
                }
                 */
        new PositionedImage(image, i * tileSize, j * tileSize).draw(graphics);
      }
    }
  }

  /**
   * <p>Draws the hero on the board</p>
   * <p>The image related to every direction is loaded here</p>
   *
   * @param graphics
   */
  private void drawHero(Graphics graphics) {
    String heroImagePath = "";
    if (hero.getDirection() == Direction.UP) {
      heroImagePath = "img/hero-up.png";
    } else if (hero.getDirection() == Direction.DOWN) {
      heroImagePath = "img/hero-down.png";
    } else if (hero.getDirection() == Direction.LEFT) {
      heroImagePath = "img/hero-left.png";
    } else if (hero.getDirection() == Direction.RIGHT) {
      heroImagePath = "img/hero-right.png";
    }

    PositionedImage heroImage =
        new PositionedImage(heroImagePath, hero.getX() * tileSize, hero.getY() * tileSize);
    heroImage.draw(graphics);
  }

  /**
   * <p>Draws enemies on the board using the coordinates stored in the enemy classes</p>
   * <p>The pictures of the enemies are loaded here</p>
   *
   * @param graphics
   */
  private void drawEnemy(Graphics graphics) {
    String imagePath;
    PositionedImage enemyImage;

    for (Enemy enemy : enemies) {
      if (enemy instanceof Boss) {
        imagePath = "img/boss.png";
      } else {
        imagePath = "img/skeleton.png";
      }
      enemyImage = new PositionedImage(imagePath,
          enemy.getX() * tileSize,
          enemy.getY() * tileSize);
      enemyImage.draw(graphics);
    }
  }

  private void drawStatistics(Graphics graphics) {
    String heroStats = String.format("Hero (Level %d) HP: %d/%d | DP: %d | SP: %d",
        hero.getLevel(), hero.getHealth(), hero.getMaxHealth(), hero.getdP(), hero.getsP());
    //System.out.println(heroStats);
    graphics.setColor(Color.BLACK);
    graphics.fillRect(0, 720, 720, 40);
    graphics.setColor(Color.WHITE);
    graphics.setFont(new Font("Arial", Font.BOLD, 16));
    graphics.drawString(heroStats, 20, 740);
  }
}