package events;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import models.characters.MovingCharacter;

public class Battle {
  MovingCharacter attacker;
  MovingCharacter defender;

  public Battle(MovingCharacter attacker, MovingCharacter defender) {
    this.attacker = attacker;
    this.defender = defender;

    battle();
  }

  private boolean battle() {
    System.out.println("Battle happens");
    return false;
  }
}
