package controllers;

import java.util.Random;
import models.Area;
import models.Hero;
import models.characters.Boss;
import models.characters.Skeleton;
import views.Board;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainController implements KeyListener {

  private Board board;
  private Hero hero;
  private Boss boss;
  private Skeleton skeleton1;
  private Skeleton skeleton2;
  private Skeleton skeleton3;
  private Area area;
  public static Random dice = new Random();

  public MainController(Board board) {
    area = new Area();
    this.hero = new Hero(1, 0, 0);
    this.boss = new Boss(hero.getLevel(), 2, 1);
    this.skeleton1 = new Skeleton(hero.getLevel(), 3, 3);
    this.skeleton2 = new Skeleton(hero.getLevel(), 3, 8);
    this.skeleton3 = new Skeleton(hero.getLevel(), 5, 3);

    this.board = board;
    board.setHero(hero);
    board.setBoss(boss);
    board.setSkeleton1(skeleton1);
    board.setSkeleton2(skeleton2);
    board.setSkeleton3(skeleton3);

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
