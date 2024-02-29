package models;

import controllers.MainController;
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

  public void moveUp(boolean canMove) {
    if (canMove) {
      y--;
    }
    direction = Direction.UP;
  }

  public void moveDown(boolean canMove) {
    if (canMove) {
      y++;
    }
    direction = Direction.DOWN;
  }

  public void moveLeft(boolean canMove) {
    if (canMove) {
      x--;
    }
    direction = Direction.LEFT;
  }

  public void moveRight(boolean canMove) {
    if (canMove) {
      x++;
    }
    direction = Direction.RIGHT;
  }
}
