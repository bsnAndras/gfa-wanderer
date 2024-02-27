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

  /**
   * Get the x-y coordinates of the Hero
   * @return int{x,y} position
   */
  public int[] getPosition(){
    return new int[] {this.x,this.y};
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
