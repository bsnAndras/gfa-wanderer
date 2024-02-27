package models;

import models.characters.MovingCharacter;

public class Hero extends MovingCharacter {

  private Direction direction;

  public Hero(int x, int y) {
    super(x, y);
    direction = Direction.DOWN;
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
