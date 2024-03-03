package events;

import controllers.MainController;
import java.util.ArrayList;
import models.Hero;
import models.areaelements.Tile;
import models.characters.Enemy;
import models.characters.MovingCharacter;

public class Battle {
  public MovingCharacter attacker;
  public MovingCharacter defender;
  private ArrayList<Enemy> enemyList;

  public Hero hero;

  public Battle(MovingCharacter attacker, MovingCharacter defender) {
    this.attacker = attacker;
    this.defender = defender;
    defender.isUnderBattle = true;
    System.out.println("Battle!");
  }

  public void setEnemyList(ArrayList<Enemy> enemyList) {
    this.enemyList = enemyList;
  }

  public MovingCharacter fight() {
    if (strike(attacker) != null) {
      return attacker;
    }else return strike(defender);
  }

  public MovingCharacter strike(MovingCharacter striker) {
    MovingCharacter defender;
    if (striker == this.attacker) {
      defender = this.defender;
    } else {
      defender = this.attacker;
    }
    int sV = striker.getsP() + 2 * MainController.diceRoll(6);
    int dP = defender.getdP();
    if (sV > dP) {
      if (!defender.setHealth(defender.getHealth() - (sV - dP))) {
        kill(defender);
        return striker;
      }
    }
    return null;
  }

  private void kill(MovingCharacter victim) {
    //with the setHealth <= 0 it already died
    if (victim instanceof Hero) {
      System.out.println("GAME OVER!");
    } else {
      enemyList.remove(victim);
    }
    attacker.isUnderBattle = false;
  }

}
