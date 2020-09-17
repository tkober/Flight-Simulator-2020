package me.kobair.msfs2020.fcu.lib.keyboard;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class VirtualKeyboard {

    final Robot robot;

    public VirtualKeyboard() throws AWTException {
        this.robot = new Robot();
        robot.setAutoDelay(200);
    }

    public void simulateKeyPress(final Key key) {
        this.simulateKeyCombination(key);
    }

    public void simulateKeyCombination(final Key... keys) {
        this.simulateKeyCombination(Arrays.asList(keys));
    }

    public void simulateKeyCombination(final List<Key> keys) {
        final Stack<Key> keyReleaseStack = new Stack<>();

        for (Key key : keys) {
            this.robot.keyPress(key.getValue());
            keyReleaseStack.push(key);
        }

        while (!keyReleaseStack.empty()) {
            final Key key = keyReleaseStack.pop();
            this.robot.keyRelease(key.getValue());
        }
    }
}
