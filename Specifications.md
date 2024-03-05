# Full Week Project: Wanderer - The RPG game

Build a hero based walking on tiles and killing monsters type of game. The hero
is controlled in a maze using the keyboard. Heroes and monsters have levels and
stats depending on their levels. The goal is reach the highest level by killing
the monsters holding the keys to the next level.

### 1. Go through the project specification

#### 🟩The Game screen

- 🟩The screen contains the first area, which is 10 x 10 tiles where the hero (and
  the monsters) can move.
  - 🟩Every area contains 10 x 10 tiles.
- 🟩There are tiles that cannot be occupied by any character (hero or monster).
- 🟩Every area contains 3-6 monsters.
- 🟩The monsters levels come from the number of the area.
  - 🟩If it's the Xth area, the monsters have level X (with 50% chance) or level
    X+1 (40%) or level X+2 (10%).
- 🟩One of the monsters is the boss.
- 🟩One of the monsters (not the boss) is holding the key, so if it is killed the
  hero goes to the next area, but also the boss has to be killed.
- 🟩The game screen also contains a text area where info of the characters' are
  provided.
  - 🟩It shows all stats of the hero.
  - 🟩If the hero is on the same tile with a monster, it shows all stats of the
    monster as well.

#### 🟩Moving

- 🟩The hero can move tile-by-tile in four directions on the screen by using the
  corresponding arrows (or "WASD" if preferred).
- 🟩After every two move, the monsters move one tile as well.
- 🟥(debug) some monsters don't move at certain situations

#### 🟩The Characters

- 🟩Every character has a (max and current) health point (HP), a defend (DP) and
  strike point (SP).
- 🟩These values can change during the game.
- 🟩When a character's health point is 0 or below, it is dead.
  - 🟩It disappears from the area.
  - 🟩If it's the hero, it is the end of the game.

#### 🟩Starting stats

🟩(d6 is a random number between 1 and 6 aka 6 sided die roll)

- 🟩Hero:
  - HP: 20 + 3 \* d6
  - DP: 2 \* d6
  - SP: 5 + d6
- 🟩Monster Level X:
  - HP: 2 \* X \* d6
  - DP: X/2 \* d6
  - SP: X \* d6
- 🟩Boss Level X:
  - HP: 2 \* X \* d6 + d6
  - DP: X/2 \* d6 + d6 / 2
  - SP: X \* d6 + X

#### 🟩Battle

- 🟩When a hero enters a tile which is occupied by a monster, a battle forms.
- 🟥(debug) When a monster meets another monster, no battle occurs.
- 🟩The character entering the occupied tile is the attacker.
- 🟩When the player hits `space` his hero strikes on the defender and then it
  strikes back.
- 🟩The attacker strikes on the defender, then the defender strikes and this
  continues until one of the characters dies.
- 🟩After a won battle if the character is a hero, it levels up.

#### 🟩Strike

- 🟩On a strike, a strike value (SV) is calculated from SP and a d6 doubled.
- 🟩The strike is successful if 2 \* d6 + SP is higher than the other character's
  DP.
- 🟩On a successful strike the other character's HP is decreased by the SV - the
  other character's DP.

#### 🟩Leveling

- 🟩After successfully won battle the character is leveling up.
- 🟩His max HP increases by d6.
- 🟩His DP increases by d6.
- 🟩His SP increases by d6.

#### 🟩Entering next area

- 🟩When killing the monster who held the key to the next area, the hero enters
  immediately.
  - 🟩Which is like the previous one just with new and higher level monsters.
  - 🟥After the last level, the game prints out the end stats.
- 🟩When entering a new area the hero has:
  - 🟩10% chance to restore all his HP.
  - 🟩40% chance to restore the third of his HP.
  - 🟩50% chance to restore 10% of his HP.
- 🟩Monster Level X:
  - 🟩HP: 2 \* X \* d6
  - 🟩DP: X / 2 \* d6
  - 🟩SP: X \* d6

## Optional features

### 🟩Update characters on moving hero

- 🟩The characters should only move when the player moves the hero

### 🟥Random map

- 🟥When map is created the placement of walls should be random
- 🟥Make sure that all floor tiles are connected

### 🟥Leveling

- 🟥Add more hp / damage to the hero according to the specification
- 🟥Add more hp / damage to the monsters
- 🟥Implement random events which happen when entering the new area

### 🟥Monsters moving around

- 🟥Move the monsters around regardless of player moving hero or not
- 🟥Speed up their movement level by level
