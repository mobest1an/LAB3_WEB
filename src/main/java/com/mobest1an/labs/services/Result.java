package com.mobest1an.labs.services;

import java.util.Date;

public class Result {

    private double x;
    private double y;
    private double r;
    private Date creationDate;
    private String hit;

    public Result(double x, double y, double r, Date creationDate) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.creationDate = creationDate;
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

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public String getHit() {
        return hit;
    }

    public void setHit(String hit) {
        this.hit = hit;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
