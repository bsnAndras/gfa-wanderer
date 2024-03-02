package controllers;

import events.MonstersMovingEvent;
import java.util.List;
import java.util.Random;
import models.Area;
import models.Hero;
import models.characters.Enemy;
import views.Board;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainController implements KeyListener {

  private Board board;
  int areaLevel;
  private Hero hero;
  private List<Enemy> enemies;
  private Area area;
  public static Random dice = new Random();

  public MainController(Board board) {
    //sets up every map layout with Tiles
    area = new Area();
    //sets up the Hero
    this.hero = new Hero(1, 0, 0);
    this.areaLevel =1;
    area.getTiles(areaLevel)[0][0].occupy();
    //sets up a random number of monsters based on hero's level
    this.enemies = area.createEnemies(areaLevel);
    //loads the board and give it the hero, the monsters
    // and the tiles(area) to draw
    this.board = board;
    board.setHero(hero);
    board.setEnemies(enemies);
    board.setArea(area);
  }

  /**
   * <p>Simulates a dice roll with a given sided dice</p>
   *
   * @param diceSide the number of sides
   * @return the result of the roll
   */
  public static int diceRoll(int diceSide) {
    return dice.nextInt(1, diceSide + 1);
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {

  }


  @Override
  public void keyReleased(KeyEvent e) {
    boolean heroCanMove;
    if (e.getKeyCode() == KeyEvent.VK_UP ||
        e.getKeyChar() == 'w') {
      heroCanMove = !area.detectObstacle(hero.getX(), hero.getY() - 1);
      hero.moveUp(heroCanMove);
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN ||
        e.getKeyChar() == 's') {
      heroCanMove = !area.detectObstacle(hero.getX(), hero.getY() + 1);
      hero.moveDown(heroCanMove);
    }
    if (e.getKeyCode() == KeyEvent.VK_LEFT ||
        e.getKeyChar() == 'a') {
      heroCanMove = !area.detectObstacle(hero.getX() - 1, hero.getY());
      hero.moveLeft(heroCanMove);
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT ||
        e.getKeyChar() == 'd') {
      heroCanMove = !area.detectObstacle(hero.getX() + 1, hero.getY());
      hero.moveRight(heroCanMove);
    }

    board.repaint();
  }
}
