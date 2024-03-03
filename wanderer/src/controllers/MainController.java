package controllers;

import events.Battle;
import events.MonstersMovingEvent;
import java.util.ArrayList;
import java.util.Random;
import models.Area;
import models.Hero;
import models.areaelements.Tile;
import models.characters.Enemy;
import models.characters.MovingCharacter;
import views.Board;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainController implements KeyListener {

  private Board board;
  int areaLevel;
  private Hero hero;
  private ArrayList<Enemy> enemies;
  private Area area;
  public static Random dice = new Random();
  int movementCounter = 0;
  Battle battle;

  public MainController(Board board) {
    //sets up every map layout with Tiles
    area = new Area();
    //sets up the Hero
    this.hero = new Hero("Hero", 1, 0, 0);
    this.areaLevel = 1;
    area.getTiles(areaLevel)[0][0].occupy(hero);
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
    Tile fromTile = area.getTiles(areaLevel)[hero.getY()][hero.getX()];
    Tile toTile;

    //If there's peace
    if (!hero.isUnderBattle) {
      //Hero's turn
      if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyChar() == 'w') {

        try {
          toTile = area.getTiles(areaLevel)[hero.getY() - 1][hero.getX()];
        } catch (IndexOutOfBoundsException ex) {
          toTile = fromTile;
        }
        battle = hero.moveUp(fromTile, toTile);

      } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyChar() == 's') {

        try {
          toTile = area.getTiles(areaLevel)[hero.getY() + 1][hero.getX()];
        } catch (IndexOutOfBoundsException ex) {
          toTile = fromTile;
        }

        battle = hero.moveDown(fromTile, toTile);

      } else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyChar() == 'a') {

        try {
          toTile = area.getTiles(areaLevel)[hero.getY()][hero.getX() - 1];
        } catch (IndexOutOfBoundsException ex) {
          toTile = fromTile;
        }

        battle = hero.moveLeft(fromTile, toTile);

      } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyChar() == 'd') {

        try {
          toTile = area.getTiles(areaLevel)[hero.getY()][hero.getX() + 1];
        } catch (IndexOutOfBoundsException ex) {
          toTile = fromTile;
        }

        battle = hero.moveRight(fromTile, toTile);
      }
      movementCounter++;

      //enemy's turn if there's peace and hero moved enough
      if (!hero.isUnderBattle && movementCounter > 1) {
        battle = MonstersMovingEvent.monstersMoving(area.getTiles(areaLevel), enemies);
        movementCounter = 0;
      }
    }
    board.repaint();

    //if there's BATTLE
    if (hero.isUnderBattle) {

      board.setDefender(battle.defender);
      if (battle.defender == hero) {
        battle.strike(battle.attacker);
        board.setOpponent((Enemy) battle.attacker);
      } else {
        board.setOpponent((Enemy) battle.defender);
      }

      board.repaint();

      if (e.getKeyCode() == KeyEvent.VK_SPACE) {
        //battle.fight();
        kill(battle.defender);
        board.setHero(hero);
        board.setEnemies(enemies);
        battle.attacker.isUnderBattle = false;
        board.setOpponent(null);
        return;
      }
    }

    board.repaint();
  }

  private void kill(MovingCharacter defender) {
    defender.setHealth(0);
    area.getTiles(areaLevel)[defender.getY()][defender.getX()].leave();
    if (defender instanceof Hero) {
      System.out.println("GAME OVER!");
      board.drawEndGame(board.getGraphics());
    } else {
      enemies.remove(defender);
    }
  }
}
