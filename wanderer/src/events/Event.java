package events;

import java.awt.event.KeyListener;
import java.util.List;
import models.areaelements.Tile;
import models.characters.MovingCharacter;

public abstract class Event implements KeyListener {
  private List<?> participants;
  Tile[][] tiles;

  public Event(Tile[][] tiles, List<?> participants) {
    this.participants = participants;
    this.tiles = tiles;
  }
}
