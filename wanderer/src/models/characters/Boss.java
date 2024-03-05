package models.characters;

import controllers.MainController;

public class Boss extends Enemy {


  public Boss(int level, int x, int y) {
    //maxHP, sP, dP are set in the super constructor, but updated with this class' implementations
    super("Boss", level, x, y,false);
  }

  public void setInitialMaxHealth() {
    super.setInitialMaxHealth(); //maxHP = 2 * level * d6
    maxHealth += MainController.diceRoll(6);
  }

  @Override
  protected void setInitialdP() {
    super.setInitialdP(); //dP = X/2 * d6
    dP += MainController.diceRoll(6) / 2;
  }

  @Override
  protected void setInitialsP() {
    super.setInitialsP(); //sP = X * d6
    sP += level;
  }
}
