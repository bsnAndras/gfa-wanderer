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
    this.character = character;
    character.setCurrentTile(this);
    isOccupied = true;
  }

  public void leave() {
    character.setCurrentTile(null);
    this.character = null;
    isOccupied = false;
  }

  public MovingCharacter getCharacter() {
    return character;
  }
}
