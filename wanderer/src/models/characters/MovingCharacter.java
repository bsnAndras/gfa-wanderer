package models.characters;

public class MovingCharacter {

  protected int x;
  protected int y;

  public MovingCharacter(int x, int y) {
    this.x = x;
    this.y = y;
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
   * Get the x-y coordinates of the Character
   *
   * @return int{x,y} position
   */
  public int[] getPosition() {
    return new int[] {this.x, this.y};
  }

  public void moveUp(boolean canMove) {
    if (canMove) {
      y--;
    }
  }

  public void moveDown(boolean canMove) {
    if (canMove) {
      y++;
    }
  }

  public void moveLeft(boolean canMove) {
    if (canMove) {
      x--;
    }
  }

  public void moveRight(boolean canMove) {
    if (canMove) {
      x++;
    }
  }
}
