package com.physmo.garnettoolkit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GameObjectBucketGrid {
    Map<Integer, Set<GameObject>> objects = new HashMap<>();
    int cellWidth;
    int cellHeight;

    public GameObjectBucketGrid(int cellWidth, int cellHeight) {
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
    }

    public int getSize() {
        return objects.size();
    }

    public void clear() {
        objects = new HashMap<>();
    }

    public void addObject(GameObject o) {
        int num = num((int) (o.getTransform().x / cellWidth), (int) (o.getTransform().y / cellHeight));

        if (!objects.containsKey(num)) {
            objects.put(num, new HashSet<>());
        }
        objects.get(num).add(o);
    }

    private int num(int x, int y) {
        if (x < 3) x = 3;
        if (y < 3) y = 3;
        if (x > 100) x = 100;
        if (y > 100) y = 100;

        return ((x & 0b1111_1111_1111) << 12) + (y & 0b1111_1111_1111);
    }

    public List<GameObject> getObjects(int xPos, int yPos, int tileRadius) {
        int cx = xPos / cellWidth;
        int cy = yPos / cellHeight;
        List<GameObject> list = new ArrayList<>();
        for (int y = cy - tileRadius; y < cy + tileRadius; y++) {
            for (int x = cx - tileRadius; x < cx + tileRadius; x++) {
                int index = num(x, y);
                if (objects.containsKey(index)) {
                    list.addAll(objects.get(index));
                }
            }
        }
        return list;
    }
}
