package controllers;

import events.Battle;
import events.MonstersMovingEvent;
import java.util.ArrayList;
import java.util.Random;
import models.Area;
import models.Direction;
import models.Hero;
import models.areaelements.Tile;
import models.characters.Enemy;
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
    board.setLevel(areaLevel);
    board.setHero(hero);
    board.setEnemies(enemies);
    board.setArea(area);
  }

  public void goNextLevel(int level) {
    //reinitialize Area
    this.areaLevel = level;

    //Hero level up
    Random random = new Random();
    int chance;
    int newHealthPoints = hero.getHealth();

    chance = random.nextInt(100);
    if (chance < 10) {
      newHealthPoints = hero.getMaxHealth();
    } else if (chance < 40) {
      newHealthPoints = hero.getHealth() + hero.getMaxHealth() / 3;
    } else if (chance < 50) {
      newHealthPoints = hero.getHealth() + hero.getMaxHealth() / 10;
    }
    if (newHealthPoints <= hero.getMaxHealth()) {
      hero.setHealth(newHealthPoints);
    } else {
      hero.setHealth(hero.getMaxHealth());
    }

    //relocate Hero
    area.getTiles(areaLevel)[0][0].occupy(hero);
    hero.setPosition(0, 0, Direction.DOWN);
    hero.loseKey();

    //sets up a random number of monsters based on areas level
    this.enemies = area.createEnemies(areaLevel);
    //gives the board:
    // The new level
    // The hero
    // The new monsters
    board.setLevel(areaLevel);
    board.setHero(hero);
    board.setEnemies(enemies);
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
    Tile fromTile = hero.getCurrentTile();
    Tile toTile;

    //If there's peace
    if (!hero.isUnderBattle) {
      //Hero's turn
      if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyChar() == 'w') {

        try {
          toTile = area.getTiles(areaLevel)[hero.getY() - 1][hero.getX()];
        } catch (IndexOutOfBoundsException ex) {
          toTile = null;
        }
        battle = hero.move(Direction.UP, toTile);

      } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyChar() == 's') {

        try {
          toTile = area.getTiles(areaLevel)[hero.getY() + 1][hero.getX()];
        } catch (IndexOutOfBoundsException ex) {
          toTile = null;
        }

        battle = hero.move(Direction.DOWN, toTile);

      } else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyChar() == 'a') {

        try {
          toTile = area.getTiles(areaLevel)[hero.getY()][hero.getX() - 1];
        } catch (IndexOutOfBoundsException ex) {
          toTile = null;
        }

        battle = hero.move(Direction.LEFT, toTile);

      } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyChar() == 'd') {

        try {
          toTile = area.getTiles(areaLevel)[hero.getY()][hero.getX() + 1];
        } catch (IndexOutOfBoundsException ex) {
          toTile = null;
        }

        battle = hero.move(Direction.RIGHT, toTile);
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

      battle.setEnemyList(enemies);

      board.setDefender(battle.defender);
      if (battle.defender == hero) {
        //battle.strike(battle.attacker);
        board.setOpponent((Enemy) battle.attacker);
      } else {
        board.setOpponent((Enemy) battle.defender);
      }

      board.repaint();

      if (e.getKeyCode() == KeyEvent.VK_SPACE) {
        if (battle.fight() != null) {
          board.setDefender(null);
          board.setOpponent(null);
          if(hero.getHealth()<=0){
            board.endGame = true;
          }
          if (hero.hasKey()) {
            try {
              goNextLevel(areaLevel + 1);
            } catch (IndexOutOfBoundsException ex) {
              areaLevel--;
              board.endGame = true;
            }
          }
        }
      }
    }

    board.repaint();
  }


}
