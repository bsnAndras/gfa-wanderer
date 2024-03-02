package models;

import controllers.MainController;
import events.Battle;
import models.areaelements.Tile;
import models.characters.MovingCharacter;

public class Hero extends MovingCharacter {

  private Direction direction;

  public Hero(int level, int x, int y) {
    //maxHP, sP, dP are set in the super constructor, but with this class' implementations
    super(level, x, y);
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

  public Battle moveUp(Tile fromTile, Tile toTile) {
    direction = Direction.UP;
    return super.moveUp(fromTile,toTile);
  }

  public Battle moveDown(Tile fromTile, Tile toTile) {
    direction = Direction.DOWN;
    return super.moveDown(fromTile,toTile);
  }

  public Battle moveLeft(Tile fromTile, Tile toTile) {
    direction = Direction.LEFT;
    return super.moveLeft(fromTile,toTile);
  }

  public Battle moveRight(Tile fromTile, Tile toTile) {
    direction = Direction.RIGHT;
    return super.moveRight(fromTile,toTile);
  }
}
