package models;

import controllers.MainController;
import models.characters.MovingCharacter;

public class Hero extends MovingCharacter {
  private int level;

  private Direction direction;

  public Hero(int x, int y) {
    super(x, y);
    level = 1;
    maxHealth=(20 + 3 * MainController.diceRoll(6));
    health=maxHealth;
    dP = 2 * MainController.diceRoll(6);
    sP = 5 + MainController.diceRoll(6);
    direction = Direction.DOWN;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
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
