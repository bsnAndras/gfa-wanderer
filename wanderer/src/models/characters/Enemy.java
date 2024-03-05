package models.characters;

import controllers.MainController;
import models.areaelements.Tile;

public class Enemy extends MovingCharacter {

  public Enemy(String name, int level, int x, int y) {
    //maxHP, sP, dP are set in the super constructor, but with this class' implementations
    super(name, level, x, y);
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
}
