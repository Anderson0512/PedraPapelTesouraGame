package org.game.model.gameException;

import java.io.Serial;
import java.io.Serializable;

public class GameException extends RuntimeException implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public GameException(String message) {
        super(message);
    }
}
