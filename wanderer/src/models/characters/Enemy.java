package models.characters;

import controllers.MainController;

public class Enemy extends MovingCharacter {

  public Enemy(int level, int x, int y) {
    //maxHP, sP, dP are set in the super constructor, but with this class' implementations
    super(level,x, y);
    health = maxHealth;
  }

  public void setMaxHealth() {
    maxHealth = level * 2 * MainController.diceRoll(6);
  }

  @Override
  protected void setdP() {
    dP = level / 2 * MainController.diceRoll(6);
  }

  @Override
  protected void setsP() {
    sP = level * MainController.diceRoll(6);
  }
}
