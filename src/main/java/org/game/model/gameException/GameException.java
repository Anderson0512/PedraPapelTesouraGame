package org.game.model.gameException;

import java.io.Serializable;

public class GameException extends RuntimeException implements Serializable {
    public static final long serialVersionUID = 1L;

    public GameException(String message) {
        super(message);
    }
}
