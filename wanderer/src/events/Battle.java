package events;

import models.characters.MovingCharacter;

public class Battle {
  public MovingCharacter attacker;
  public MovingCharacter defender;

  public Battle(MovingCharacter attacker, MovingCharacter defender) {
    this.attacker = attacker;
    this.defender = defender;
    defender.isUnderBattle = true;

  }

  private boolean battle() {

    return false;
  }

}
