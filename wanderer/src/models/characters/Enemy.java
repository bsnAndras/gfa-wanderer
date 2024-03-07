package models.characters;

import controllers.MainController;
import models.areaelements.Tile;
import models.areaelements.Wall;

public class Enemy extends MovingCharacter {

  public Enemy(String name, int level, int x, int y, boolean hasKey) {
    //maxHP, sP, dP are set in the super constructor, but with this class' implementations
    super(name, level, x, y, hasKey);
    health = maxHealth;
  }

  public void setInitialMaxHealth() {
    maxHealth = level * 2 * MainController.diceRoll(6);
  }

  @Override
  protected void setInitialdP() {
    dP = level / 2 * MainController.diceRoll(6);
  }

  @Override
  protected void setInitialsP() {
    sP = level * MainController.diceRoll(6);
  }

  public int detectObstacle(Tile tile) {
    if (tile instanceof Wall ||
        tile.getCharacter() instanceof Enemy) {
      //if wall or friendly character -> 1
      return 1;
    } else if (tile.isOccupied()) {
      //if enemy found -> -1
      return -1;
    } else {
      return 0;
    }
  }
}
