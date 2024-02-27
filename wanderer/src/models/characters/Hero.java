package models;

public class Hero {

  private int x;
  private int y;

  private Direction direction;

  public Hero(int x, int y) {
    this.x = x;
    this.y = y;
    direction = Direction.DOWN;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public Direction getDirection() {
    return direction;
  }

  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  public void moveUp() {
    if (y > 0) {
      y--;
    }
    direction = Direction.UP;
  }

  public void moveDown() {
    if (y < 9) {
      y++;
    }
    direction = Direction.DOWN;
  }

  public void moveLeft() {
    if (x > 0) {
      x--;
    }
    direction = Direction.LEFT;
  }

  public void moveRight() {
    if (x < 9) {
      x++;
    }
    direction = Direction.RIGHT;
  }
}
