package com.moonrose.moonrosemod;

public class SingletonCoordinate
{
    private double x;
    private double y;
    private double z;
    private static SingletonCoordinate instance;

    private SingletonCoordinate() {
        // シングルトンのためのプライベートなコンストラクタ
    }

    public static SingletonCoordinate getInstance() {
        if (instance == null) {
            synchronized (SingletonCoordinate.class) {
                if (instance == null) {
                    instance = new SingletonCoordinate();
                }
            }
        }
        return instance;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
