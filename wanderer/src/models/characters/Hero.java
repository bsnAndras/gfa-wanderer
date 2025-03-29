package models.characters;

import controllers.MainController;
import events.Battle;
import models.Direction;
import models.areaelements.Tile;

public class Hero extends MovingCharacter {

  private Direction direction;

  public Hero(String name, int level, int x, int y) {
    //maxHP, sP, dP are set in the super constructor, but with this class' implementations
    super(name, level, x, y, false);
    setHealth(maxHealth);
    setDirection(Direction.DOWN);
  }

  @Override
  public void setInitialMaxHealth() {
    maxHealth = 20 + 3 * MainController.diceRoll(6);
  }

  @Override
  protected void setInitialdP() {
    dP = 2 * MainController.diceRoll(6);
  }

  @Override
  protected void setInitialsP() {
    sP = 5 + MainController.diceRoll(6);
  }

  public void levelUp() {
    super.levelUp();
    setMaxHealth(maxHealth + MainController.diceRoll(6));
    setdP(dP + MainController.diceRoll(6));
    setsP(sP + MainController.diceRoll(6));
  }

  public Direction getDirection() {
    return direction;
  }

  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  public void setPosition(int x, int y, Direction direction) {
    super.setPosition(x, y);
    setDirection(direction);
  }

  @Override
  public Battle move(Direction direction, Tile toTile) {
    this.direction=direction;
    return super.move(direction, toTile);
  }
}
