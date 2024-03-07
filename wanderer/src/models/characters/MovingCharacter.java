package models.characters;

import events.Battle;
import models.Direction;
import models.areaelements.Tile;
import models.areaelements.Wall;

public abstract class MovingCharacter {
  public final String name;
  protected int level;
  protected boolean hasKey;
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
  private Tile currentTile;
  public boolean canMove;
  public boolean isUnderBattle;

  public MovingCharacter(String name, int level, int x, int y, boolean hasKey) {
    this.name = name;
    this.level = level;
    this.hasKey = hasKey;
    this.x = x;
    this.y = y;
    canMove = true;
    isUnderBattle = false;
    setInitialMaxHealth();
    setInitialdP();
    setInitialsP();
  }

  /**
   * <p>For setting up the maxHealth in the beginning of the game</p>
   * <p>made abstract for use in subclasses</p>
   */
  public abstract void setInitialMaxHealth();

  /**
   * <p>For setting up the dP (Defense Points) in the beginning of the game</p>
   * <p>made abstract for use in subclasses</p>
   */
  protected abstract void setInitialdP();

  /**
   * <p>For setting up the sP (Strike Points) in the beginning of the game</p>
   * <p>made abstract for use in subclasses</p>
   */
  protected abstract void setInitialsP();

  public void setMaxHealth(int maxHealth) {
    this.maxHealth = maxHealth;
  }

  public void setdP(int dP) {
    this.dP = dP;
  }

  public void setsP(int sP) {
    this.sP = sP;
  }

  public boolean hasKey() {
    return hasKey;
  }

  public void giveKey() {
    hasKey = true;
  }

  public void loseKey() {
    hasKey = false;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public void levelUp() {
    setLevel(level + 1);
  }

  public int getMaxHealth() {
    return maxHealth;
  }

  public int getHealth() {
    return health;
  }

  /**
   * Sets the current health of the character
   *
   * @param health the new health points to set
   * @return if the character lives
   */
  public boolean setHealth(int health) {

    this.health = health;
    if (health <= 0) {
      this.health = 0;
      die();
      return false;
    } else {
      return true;
    }
  }

  private void die() {
    currentTile.leave();
    System.out.printf("%s died.\n", name);
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

  public void setPosition(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public Tile getCurrentTile() {
    return currentTile;
  }

  public void setCurrentTile(Tile currentTile) {
    this.currentTile = currentTile;
  }

  /**
   * <p>Move the character 1 tile in the given direction if viable</p>
   * <ul>
   *   <li>Checks if the character is currently under a battle, and got a valid direction</li>
   *   <li>then checks for obstacles in the given direction and moves accordingly</li>
   *   <li>If Enemy is found, initiates a battle</li>
   * </ul>
   *
   * @param direction The given direction of movement
   * @param toTile    The Tile, where the character tries to move
   * @return a Battle, if a battle began after the move
   */
  public Battle move(Direction direction, Tile toTile) {
    int obstacle;
    if (!isUnderBattle && direction != null) {
      if (toTile == null) { //in case of invalid index
        obstacle = 1;
      } else {
        obstacle = detectObstacle(toTile);
      }
      if (obstacle == 0) {
        switch (direction) {
          case UP -> y--;
          case RIGHT -> x++;
          case DOWN -> y++;
          case LEFT -> x--;
        }
        currentTile.leave();
        toTile.occupy(this);
      } else {
        if (obstacle < 0) {
          isUnderBattle = true;
          return new Battle(this, toTile.getCharacter());
        }
      }
    }
    return null;
  }

  /**
   * A method for checking whether a Tile can be accessed and if it contains Enemy or not
   *
   * @param tileMap an array that contain that particular Tile
   * @param x       The X coordinate of the Tile
   * @param y       The Y coordinate of the Tile
   * @return <ul>
   * <li>0 if free to go</li>
   * <li>1 if cannot go (e.g. Wall found)</li>
   * <li>-1 if Enemy found</li>
   * </ul>
   */

  public int detectObstacle(Tile[][] tileMap, int x, int y) {
    Tile tile;
    try {
       tile = tileMap[y][x];
    } catch (IndexOutOfBoundsException e) {
      return 1;
    }
    return detectObstacle(tile);
  }

  /**
   * A method for checking whether a Tile can be accessed and if it contains Enemy or not
   *
   * @param tile the Tile what we want to check if it is an obstacle
   * @return <ul>
   * <li>0 if free to go</li>
   * <li>1 if cannot go (e.g. Wall found)</li>
   * <li>-1 if Enemy found</li>
   * </ul>
   */

  public int detectObstacle(Tile tile) {

    if (tile instanceof Wall) {
      //if wall -> 1
      return 1;
    } else if (tile.isOccupied()) {
      //if enemy found -> -1
      return -1;
    } else {
      return 0;
    }
  }
}
