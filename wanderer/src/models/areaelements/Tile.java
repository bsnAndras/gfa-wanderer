package models.areaelements;

public abstract class Tile {
  /**
   * determines whether the tile is occupied by a character
   */
  private boolean isOccupied;

  public Tile() {
    this.isOccupied = false;
  }

  public boolean isOccupied() {
    return isOccupied;
  }

  public void occupy() {
    isOccupied = true;
  }

  public void leave() {
    isOccupied = false;
  }
}
