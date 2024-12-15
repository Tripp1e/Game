package org.jantor.utils;

import org.jantor.constants.Constants;
import org.jantor.elements.Element;

public class CollisionManager {
    public static boolean wouldCollide(int dx, int dy, Class<?> cls, Element element) {
        int halfWidth = Constants.elementSize.x / 2;
        int[][] offsets = {{-1, -1}, {1, -1}, {-1, 1}, {1, 1}};

        for (int[] offset : offsets) {
            int newX = element.getX() + offset[0] * halfWidth + dx;
            int newY = element.getY() + offset[1] * halfWidth + dy;
            if (element.getOneObjectAtOffset(newX - element.getX(), newY - element.getY(), cls) != null) {
                return true;
            }
        }
        return false;
    }
}
