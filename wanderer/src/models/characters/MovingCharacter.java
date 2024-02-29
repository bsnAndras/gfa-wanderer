package models.characters;

public abstract class MovingCharacter {
  protected int level;
  protected int maxHealth;
  protected int health;
  /**
   * dP = Defense Points
   */
  protected int dP;
  /**
   * sp = Strike Points
   */
  protected int sP;
  protected int x;
  protected int y;

  public MovingCharacter(int level, int x, int y) {
    this.level = level;
    this.x = x;
    this.y = y;
    setMaxHealth();
    setdP();
    setsP();
  }

  /**
   * <p>For setting up the maxHealth in the beginning of the game</p>
   * <p>made abstract for use in subclasses</p>
   */
  public abstract void setMaxHealth();
  /**
   * <p>For setting up the dP (Defense Points) in the beginning of the game</p>
   * <p>made abstract for use in subclasses</p>
   */
  protected abstract void setdP();
  /**
   * <p>For setting up the sP (Strike Points) in the beginning of the game</p>
   * <p>made abstract for use in subclasses</p>
   */
  protected abstract void setsP();

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public int getMaxHealth() {
    return maxHealth;
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

  public int getsP() {
    return sP;
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

  /** Move the character 1 tile upwards if <u>canMove = TRUE</u>
   *
   * @param canMove checks is the character can move to that direction
   */
  public void moveUp(boolean canMove) {
    if (canMove) {
      y--;
    }
  }
  /** Move the character 1 tile downwards if <u>canMove = TRUE</u>
   *
   * @param canMove checks is the character can move to that direction
   */
  public void moveDown(boolean canMove) {
    if (canMove) {
      y++;
    }
  }
  /** Move the character 1 tile left if <u>canMove = TRUE</u>
   *
   * @param canMove checks is the character can move to that direction
   */
  public void moveLeft(boolean canMove) {
    if (canMove) {
      x--;
    }
  }
  /** Move the character 1 tile right if <u>canMove = TRUE</u>
   *
   * @param canMove checks is the character can move to that direction
   */
  public void moveRight(boolean canMove) {
    if (canMove) {
      x++;
    }
  }
}
