package models.characters;

public class MovingCharacter {
  protected int maxHealth;
  protected int health;
  protected int dP;
  protected int sP;
  protected int x;
  protected int y;

  public MovingCharacter(int x, int y) {
    this.x = x;
    this.y = y;
  }
  public int getMaxHealth() {
    return maxHealth;
  }

  public void setMaxHealth(int maxHealth) {
    this.maxHealth = maxHealth;
  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public int getdP() {
    return dP;
  }

  public void setdP(int dP) {
    this.dP = dP;
  }

  public int getsP() {
    return sP;
  }

  public void setsP(int sP) {
    this.sP = sP;
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
