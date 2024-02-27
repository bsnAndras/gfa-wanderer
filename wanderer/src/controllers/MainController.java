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
    if (e.getKeyCode() == KeyEvent.VK_UP ||
        e.getKeyChar() == 'w') {
      hero.moveUp();
    } else if (e.getKeyCode() == KeyEvent.VK_DOWN ||
        e.getKeyChar() == 's') {
      hero.moveDown();
    } else if (e.getKeyCode() == KeyEvent.VK_LEFT ||
        e.getKeyChar() == 'a') {
      hero.moveLeft();
    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT ||
        e.getKeyChar() == 'd') {
      hero.moveRight();
    }

    board.repaint();
  }
}
