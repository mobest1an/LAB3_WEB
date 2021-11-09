package com.mobest1an.labs.models;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean(name = "programBean", eager = true)
@ApplicationScoped
public class ProgramBean {

    @ManagedProperty(value = "#{dataBaseManagerBean}")
    private DataBaseManagerBean dataBaseManagerBean;

    private double x;
    private double y;
    private double r;

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

    public DataBaseManagerBean getDataBaseManagerBean() {
        return dataBaseManagerBean;
    }

    public void setDataBaseManagerBean(DataBaseManagerBean dataBaseManagerBean) {
        this.dataBaseManagerBean = dataBaseManagerBean;
    }

    public void doAction() {
        dataBaseManagerBean.addValue(x, y, r, "test");
    }
}
