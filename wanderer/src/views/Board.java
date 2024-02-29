package views;

import models.Area;
import models.Direction;
import models.Hero;
import models.areaelements.Floor;
import models.areaelements.Tile;

import javax.swing.*;
import java.awt.*;
import models.characters.Boss;
import models.characters.Skeleton;

public class Board extends JComponent {

  private Hero hero;
  private Boss boss;
  private Area area;
  private Skeleton skeleton1;
  private Skeleton skeleton2;
  private Skeleton skeleton3;

  private final int tileSize;

  public Board() {
    tileSize = 72;
    setPreferredSize(new Dimension(tileSize * 10, tileSize * 10 + 40));
    setVisible(true);
  }

  public void setHero(Hero hero) {
    this.hero = hero;
  }

  public void setBoss(Boss boss) {
    this.boss = boss;
  }

  public void setSkeleton1(Skeleton skeleton1) {
    this.skeleton1 = skeleton1;
  }

  public void setSkeleton2(Skeleton skeleton2) {
    this.skeleton2 = skeleton2;
  }

  public void setSkeleton3(Skeleton skeleton3) {
    this.skeleton3 = skeleton3;
  }

  public void setArea(Area area) {
    this.area = area;
  }

  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);

    drawTiles(graphics);
    drawHero(graphics);
    drawEnemy(graphics);
    drawStatistics(graphics);
  }

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

  private void drawEnemy(Graphics graphics) {
    String bossImagePath = "img/boss.png";
    String skeletonImagePath = "img/skeleton.png";

    PositionedImage enemyImage = new PositionedImage(bossImagePath,
            boss.getX() * tileSize,
            boss.getY() * tileSize);
    enemyImage.draw(graphics);
    enemyImage = new PositionedImage(skeletonImagePath,
        skeleton1.getX() * tileSize,
        skeleton1.getY() * tileSize);
    enemyImage.draw(graphics);
    enemyImage = new PositionedImage(skeletonImagePath,
        skeleton2.getX() * tileSize,
        skeleton2.getY() * tileSize);
    enemyImage.draw(graphics);
    enemyImage = new PositionedImage(skeletonImagePath,
        skeleton3.getX() * tileSize,
        skeleton3.getY() * tileSize);
    enemyImage.draw(graphics);
  }

  private void drawStatistics(Graphics graphics) {
    String heroStats = String.format("Hero (Level %d) HP: %d/%d | DP: %d | SP: %d",
        hero.getLevel(),hero.getHealth(),hero.getMaxHealth(),hero.getdP(),hero.getsP());
    System.out.println(heroStats);
    graphics.setColor(Color.BLACK);
    graphics.fillRect(0, 720, 720, 40);
    graphics.setColor(Color.WHITE);
    graphics.setFont(new Font("Arial", Font.BOLD, 16));
    graphics.drawString(heroStats, 20, 740);
  }
}