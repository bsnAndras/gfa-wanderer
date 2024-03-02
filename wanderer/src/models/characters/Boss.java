package models.characters;

import controllers.MainController;

public class Boss extends Enemy {


  public Boss(int level, int x, int y) {
    //maxHP, sP, dP are set in the super constructor, but updated with this class' implementations
    super("Boss", level, x, y);
  }

  public void setMaxHealth() {
    super.setMaxHealth(); //maxHP = 2 * level * d6
    maxHealth += MainController.diceRoll(6);
  }

  @Override
  protected void setdP() {
    super.setdP(); //dP = X/2 * d6
    dP += MainController.diceRoll(6) / 2;
  }

  @Override
  protected void setsP() {
    super.setsP(); //sP = X * d6
    sP += level;
  }
}
