package com.mobest1an.labs.models;

import com.mobest1an.labs.models.checkers.Checking;
import com.mobest1an.labs.services.Result;
import com.mobest1an.labs.services.ResultService;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

@ManagedBean(name = "programBean", eager = true)
@ApplicationScoped
public class ProgramBean {

    @ManagedProperty(value = "#{areaCheckerBean}")
    private Checking checking;

    @ManagedProperty(value = "#{resultServiceBean}")
    private ResultService resultService;

    private Result result;

    @DecimalMin(value = "-5", message = "Число не соответствует диапазону! (-5..3)")
    @DecimalMax(value = "3", message = "Число не соответствует диапазону! (-5..3)")
    private double x;

    @DecimalMin(value = "-3", message = "Число не соответствует диапазону! (-3..3)")
    @DecimalMax(value = "3", message = "Число не соответствует диапазону! (-3..3)")
    private double y;

    @DecimalMin(value = "1", message = "Число не соответствует диапазону! [1..5]")
    @DecimalMax(value = "5", message = "Число не соответствует диапазону! [1..5]")
    private double r;

    private String message;

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

    public Checking getChecking() {
        return checking;
    }

    public void setChecking(Checking checking) {
        this.checking = checking;
    }

    public ResultService getResultService() {
        return resultService;
    }

    public void setResultService(ResultService resultService) {
        this.resultService = resultService;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void doAction() {
        result = new Result(x, y, r);
        result.setHit(checking.check(result));
        if (resultService.addResult(result)) {
            message = "";
        } else {
            message = "Не удалось добавить элемент!";
        }
    }
}
