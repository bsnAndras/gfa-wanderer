package models.areaelements;

import models.characters.MovingCharacter;

public abstract class Tile {
  /**
   * determines whether the tile is occupied by a character
   */
  private boolean isOccupied;
  private MovingCharacter character;

  public Tile() {
    this.isOccupied = false;
  }

  public boolean isOccupied() {
    return isOccupied;
  }

  public void occupy(MovingCharacter character) {
    isOccupied = true;
    this.character=character;
  }

  public void leave() {
    isOccupied = false;
    this.character=null;
  }

  public MovingCharacter getCharacter() {
    return character;
  }
}
