package org.jantor.utils;

public class Vector2D {
    public int x;
    public int y;

    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D add(Vector2D other) {
        this.x += other.x;
        this.y += other.y;
        return this;
    }
    public Vector2D subtract(Vector2D other) {
        this.x -= other.x;
        this.y -= other.y;
        return this;
    }

    public Vector2D multiply(Vector2D other) {
        this.x *= other.x;
        this.y *= other.y;
        return this;
    }
    public Vector2D multiply(Double factor) {
        this.x *= factor;
        this.y *= factor;
        return this;
    }
    public Vector2D divide(Vector2D other) {
        this.x /= other.x;
        this.y /= other.y;
        return this;
    }

    public void copy(Vector2D other) {
        this.x = other.x;
        this.y = other.y;
    }
    public static Vector2D copyFrom(Vector2D other) {
        return new Vector2D(other.x, other.y);
    }

    public Vector2D normalize() {
        this.x = this.x == 0 ? 0 : this.x / Math.abs(this.x);
        this.y = this.y == 0 ? 0 : this.y / Math.abs(this.y);
        return this;
    }

    public boolean equals(Vector2D other) {
        return this.x == other.x && this.y == other.y;
    }

    public String toString() {
        return "[" + this.x + ", " + this.y + "]";
    }

    public void toZero() {
        this.x = 0;
        this.y = 0;
    }

}
