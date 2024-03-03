package models;

import controllers.MainController;
import events.Battle;
import models.areaelements.Tile;
import models.characters.MovingCharacter;

public class Hero extends MovingCharacter {

  private Direction direction;

  public Hero(String name, int level, int x, int y) {
    //maxHP, sP, dP are set in the super constructor, but with this class' implementations
    super(name, level, x, y);
    health = maxHealth;
    direction = Direction.DOWN;
  }

  @Override
  public void setMaxHealth() {
    maxHealth = 20 + 3 * MainController.diceRoll(6);
  }

  @Override
  protected void setdP() {
    dP = 2 * MainController.diceRoll(6);
  }

  @Override
  protected void setsP() {
    sP = 5 + MainController.diceRoll(6);
  }

  public Direction getDirection() {
    return direction;
  }

  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  public Battle moveUp(Tile toTile) {
    direction = Direction.UP;
    return super.moveUp(toTile);
  }

  public Battle moveDown(Tile toTile) {
    direction = Direction.DOWN;
    return super.moveDown(toTile);
  }

  public Battle moveLeft(Tile toTile) {
    direction = Direction.LEFT;
    return super.moveLeft(toTile);
  }

  public Battle moveRight(Tile toTile) {
    direction = Direction.RIGHT;
    return super.moveRight(toTile);
  }
}
