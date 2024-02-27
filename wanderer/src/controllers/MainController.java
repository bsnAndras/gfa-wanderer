package controllers;

import models.Area;
import models.Direction;
import models.Hero;
import views.Board;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.AreaAveragingScaleFilter;

public class MainController implements KeyListener {

  private Board board;
  private Hero hero;

  private Area area;

  public MainController(Board board) {
    area = new Area();
    this.hero = new Hero(0, 0);
    this.board = board;
    board.setHero(hero);
    board.setArea(area);
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
      heroCanMove=!area.detectObstacle(hero.getX(),hero.getY()-1);
      hero.moveUp(heroCanMove);
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN ||
        e.getKeyChar() == 's') {
      heroCanMove=!area.detectObstacle(hero.getX(),hero.getY()+1);
      hero.moveDown(heroCanMove);
    }
    if (e.getKeyCode() == KeyEvent.VK_LEFT ||
        e.getKeyChar() == 'a') {
      heroCanMove=!area.detectObstacle(hero.getX()-1,hero.getY());
      hero.moveLeft(heroCanMove);
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT ||
        e.getKeyChar() == 'd') {
      heroCanMove=!area.detectObstacle(hero.getX()+1,hero.getY());
      hero.moveRight(heroCanMove);
    }

    board.repaint();
  }
}
