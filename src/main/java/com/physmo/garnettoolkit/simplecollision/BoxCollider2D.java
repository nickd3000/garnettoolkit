package com.physmo.garnettoolkit.simplecollision;

import com.physmo.garnettoolkit.GameObject;
import com.physmo.garnettoolkit.Rect;

public class BoxCollider2D implements Collider {

    GameObject parent;
    double offsetX;
    double offsetY;
    double width;
    double height;

    public void setValues(GameObject parent, double offsetX, double offsetY, double width, double height) {
        this.parent = parent;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean testCollision(Collider other) {
        Rect thisRect = getWorldRect();
        Rect otherRect = ((BoxCollider2D) other).getWorldRect();

        return thisRect.intersect(otherRect);
    }

    public Rect getWorldRect() {
        return new Rect(parent.getTransform().x + offsetX, parent.getTransform().y + offsetY, width, height);
    }

    @Override
    public void render() {
//
//        double outputScale = 2;
//        Rect rect = getWorldRect();
//
//        glBegin(GL_LINE_STRIP);
//        {
//            glVertex2f((float) (rect.x * outputScale), (float) (rect.y * outputScale));
//            glVertex2f((float) ((rect.x + rect.w) * outputScale), (float) (rect.y * outputScale));
//            glVertex2f((float) ((rect.x + rect.w) * outputScale), (float) ((rect.y + rect.h) * outputScale));
//            glVertex2f((float) (rect.x * outputScale), (float) ((rect.y + rect.h) * outputScale));
//            glVertex2f((float) (rect.x * outputScale), (float) (rect.y * outputScale));
//        }
//        glEnd();

    }

}
