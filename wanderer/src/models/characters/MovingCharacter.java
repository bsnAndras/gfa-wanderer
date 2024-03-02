package models.characters;

import events.Battle;
import models.areaelements.Tile;
import models.areaelements.Wall;

public abstract class MovingCharacter {
  public final String name;
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
  public boolean canMove;
  public boolean isUnderBattle;

  public MovingCharacter(String name, int level, int x, int y) {
    this.name = name;
    this.level = level;
    this.x = x;
    this.y = y;
    canMove = true;
    isUnderBattle = false;
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

  /**
   * Move the character 1 tile upwards, if it can
   * @return a Battle, if a battle began after the move
   */
  public Battle moveUp(Tile fromTile, Tile toTile) {
    int obstacle;
    if (!isUnderBattle) {
      if (fromTile == toTile) {
        obstacle = 1;
      } else {
        obstacle = detectObstacle(toTile);
      }
      if (obstacle == 0) {
        y--;
        fromTile.leave();
        toTile.occupy(this);
      } else {
        if (obstacle < 0) {
          isUnderBattle = true;
          Battle battle = new Battle(this, toTile.getCharacter());
          return battle;
        }
      }
    }
    return null;
  }

  /**
   * Move the character 1 tile downwards, if it can
   *
   * @return a Battle, if a battle began after the move
   */
  public Battle moveDown(Tile fromTile, Tile toTile) {
    int obstacle;
    if (!isUnderBattle) {
      if (fromTile == toTile) {
        obstacle = 1;
      } else {
        obstacle = detectObstacle(toTile);
      }
      if (obstacle == 0) {
        y++;
        fromTile.leave();
        toTile.occupy(this);
      } else {
        if (obstacle < 0) {
          isUnderBattle = true;
          Battle battle = new Battle(this, toTile.getCharacter());
          return battle;
        }
      }
    }
    return null;
  }

  /**
   * Move the character 1 tile left, if it can
   *
   * @return a Battle, if a battle began after the move
   */
  public Battle moveLeft(Tile fromTile, Tile toTile) {
    int obstacle;
    if (!isUnderBattle) {
      if (fromTile == toTile) {
        obstacle = 1;
      } else {
        obstacle = detectObstacle(toTile);
      }
      if (obstacle == 0) {
        x--;
        fromTile.leave();
        toTile.occupy(this);
      } else {
        if (obstacle < 0) {
          isUnderBattle = true;
          Battle battle = new Battle(this, toTile.getCharacter());
          return battle;
        }
      }
    }
    return null;
  }

  /**
   * Move the character 1 tile right, if it can
   *
   * @return a Battle, if a battle began after the move
   */
  public Battle moveRight(Tile fromTile, Tile toTile) {
    int obstacle;
    if (!isUnderBattle) {
      if (fromTile == toTile) {
        obstacle = 1;
      } else {
        obstacle = detectObstacle(toTile);
      }
      if (obstacle == 0) {
        x++;
        fromTile.leave();
        toTile.occupy(this);
      } else {
        if (obstacle < 0) {
          isUnderBattle = true;
          Battle battle = new Battle(this, toTile.getCharacter());
          return battle;
        }
      }
    }
    return null;
  }

  /**
   * A method for checking whether a Tile can be accessed and if it contains Enemy or not
   *
   * @param tile the Tile what we want to check if it is an obstacle
   * @return <ul>
   * <li>0 if free to go</li>
   * <li>1 if Wall found</li>
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
