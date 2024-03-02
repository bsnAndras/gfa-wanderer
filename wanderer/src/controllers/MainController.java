package controllers;

import events.Battle;
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
    this.areaLevel = 1;
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
    int obstacle;

    if (e.getKeyCode() == KeyEvent.VK_UP ||
        e.getKeyChar() == 'w') {
      obstacle = area.detectObstacle(hero.getX(), hero.getY() - 1);
      if (obstacle < 1) {
        heroCanMove = true;
        if(obstacle<0){
          Battle battle = new Battle();
        }
      }else heroCanMove=false;
      hero.moveUp(heroCanMove);
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN ||
        e.getKeyChar() == 's') {
      obstacle = area.detectObstacle(hero.getX(), hero.getY() + 1);
      if (obstacle < 1) {
        heroCanMove = true;
        if(obstacle<0){
          Battle battle = new Battle();
        }
      }else heroCanMove=false;
      hero.moveDown(heroCanMove);
    }
    if (e.getKeyCode() == KeyEvent.VK_LEFT ||
        e.getKeyChar() == 'a') {
      obstacle = area.detectObstacle(hero.getX() - 1, hero.getY());
      if (obstacle < 1) {
        heroCanMove = true;
        if(obstacle<0){
          Battle battle = new Battle();
        }
      }else heroCanMove=false;
      hero.moveLeft(heroCanMove);
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT ||
        e.getKeyChar() == 'd') {
      obstacle = area.detectObstacle(hero.getX() + 1, hero.getY());
      if (obstacle < 1) {
        heroCanMove = true;
        if(obstacle<0){
          Battle battle = new Battle();
        }
      }else heroCanMove=false;
      hero.moveRight(heroCanMove);
    }
    MonstersMovingEvent mME = new MonstersMovingEvent(area.getTiles(areaLevel), enemies);
    board.repaint();
  }
}
