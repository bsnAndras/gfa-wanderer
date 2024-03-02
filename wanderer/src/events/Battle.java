package events;

import controllers.MainController;
import models.characters.MovingCharacter;

public class Battle {
  public MovingCharacter attacker;
  public MovingCharacter defender;

  public Battle(MovingCharacter attacker, MovingCharacter defender) {
    this.attacker = attacker;
    this.defender = defender;
    defender.isUnderBattle = true;
    System.out.println("Battle!");
  }

  public void fight() {
    strike(attacker);
    strike(defender);
  }

  public void strike(MovingCharacter attacker) {
    MovingCharacter defender;
    if (attacker == this.attacker) {
      defender = this.defender;
    } else {
      defender = this.attacker;
    }
    int sV = attacker.getsP() + 2 * MainController.diceRoll(6);
    int dP = defender.getdP();
    if (sV > dP) {
      defender.setHealth(defender.getHealth() - (sV - dP));
    }
  }


}
