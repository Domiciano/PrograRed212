package control;

import model.*;

public interface GameActions {
    void OnGameUpdate(GameStatus game);
    void OnHit(Hit hit);
}
