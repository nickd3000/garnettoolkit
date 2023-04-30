package com.physmo.garnettoolkit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameObjectBucketGrid {
    Map<Integer, List<Object>> objects = new HashMap<>();
    int cellWidth;
    int cellHeight;
    int maxObjectsPerCell = 10;

    public GameObjectBucketGrid(int cellWidth, int cellHeight) {
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;

    }

    public int getCellWidth() {
        return cellWidth;
    }

    public int getCellHeight() {
        return cellHeight;
    }

    public int getSize() {
        return objects.size();
    }

    public void clear() {
        objects = new HashMap<>();
    }

    public int[] getCellCoordsForPoint(int x, int y) {
        return new int[]{x / cellWidth, y / cellHeight};
    }

    public void addObject(Object o, int x, int y) {
        int num = encoder(x / cellWidth, y / cellHeight);

        if (!objects.containsKey(num)) {
            objects.put(num, new ArrayList<>());
        }

        if (objects.get(num).size() > maxObjectsPerCell) return;

        objects.get(num).add(o);
    }

    private int encoder(int x, int y) {
        if (x < 0) x = 0;
        if (y < 0) y = 0;
        if (x > 100) x = 100;
        if (y > 100) y = 100;

        return ((x & 0b1111_1111_1111) << 12) + (y & 0b1111_1111_1111);
    }

    public List<Integer[]> getListOfActiveCells() {
        List<Integer[]> activeCells = new ArrayList<>();
        for (Integer integer : objects.keySet()) {
            activeCells.add(decoder(integer));
        }
        return activeCells;
    }

    private Integer[] decoder(int v) {
        int x = (v >> 12) & 0b1111_1111_1111;
        int y = (v) & 0b1111_1111_1111;
        return new Integer[]{x, y};
    }

    /**
     * Return a list of objects contained in the cell referred to by the supplied coordinate.
     *
     * @param cellX
     * @param cellY
     * @return
     */
    public List<Object> getCellObjects(int cellX, int cellY) {
        int index = encoder(cellX, cellY);
        if (objects.containsKey(index)) {
            return objects.get(index);
        }

        return new ArrayList<>();
    }

    public List<Object> getSurroundingObjects(int cellX, int cellY, int tileRadius) {
        int cx = cellX;
        int cy = cellY;
        List<Object> list = new ArrayList<>();
        for (int y = cy - tileRadius; y <= cy + tileRadius; y++) {
            for (int x = cx - tileRadius; x <= cx + tileRadius; x++) {
                int index = encoder(x, y);
                if (objects.containsKey(index)) {
                    list.addAll(objects.get(index));
                }
            }
        }
        return list;
    }
}
