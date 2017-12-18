package Controller;

import Model.Game;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KeyHandler {
    private static Set<KeyCode> keyCodes = new HashSet<>();

    public static void handleKeyPressed(KeyEvent e) {
        keyCodes.add(e.getCode());
    }

    public static void handleKeyReleased(KeyEvent e) {
        keyCodes.remove(e.getCode());
    }

    public static void processKeys(Game g) {
        int speed = 5;

        int p0x = 0;
        int p1x = 0;
        int p0y = 0;
        int p1y = 0;

        if (keyCodes.contains(KeyCode.W)) {
            p0y -= speed;
        }
        if (keyCodes.contains(KeyCode.S)) {
            p0y += speed;
        }
        if (keyCodes.contains(KeyCode.UP)) {
            p1y -= speed;
        }
        if (keyCodes.contains(KeyCode.DOWN)) {
            p1y += speed;
        }

        g.setPaddleVelocity(0, p0x, p0y);
        g.setPaddleVelocity(1, p1x, p1y);
    }
}
