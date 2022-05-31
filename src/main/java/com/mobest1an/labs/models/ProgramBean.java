package com.mobest1an.labs.models;

import com.mobest1an.labs.MBeans.SeveralIntervalProvider;
import com.mobest1an.labs.MBeans.UserStatisticProvider;
import com.mobest1an.labs.models.checkers.Checking;
import com.mobest1an.labs.services.Result;
import com.mobest1an.labs.services.ResultService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.lang.management.ManagementFactory;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "programBean", eager = true)
@ApplicationScoped
public class ProgramBean {

    private UserStatisticProvider userStatisticProvider;
    private SeveralIntervalProvider severalIntervalProvider;

    @PostConstruct
    void init() {
        try {
            MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

            ObjectName userStatisticProviderName = new ObjectName("com.mobest1an.labs.MBeans:type=UserStatisticProvider");
            ObjectName severalIntervalProviderName = new ObjectName("com.mobest1an.labs.MBeans:type=SeveralIntervalProvider");

            userStatisticProvider = new UserStatisticProvider();
            severalIntervalProvider = new SeveralIntervalProvider();

            mBeanServer.registerMBean(userStatisticProvider, userStatisticProviderName);
            mBeanServer.registerMBean(severalIntervalProvider, severalIntervalProviderName);

            userStatisticProvider.setResults(results);
            userStatisticProvider.calculateUserDoubleMissed();
            severalIntervalProvider.setResults(results);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    @ManagedProperty(value = "#{resultServiceBean.results}")
    private List<Result> results;

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

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void doAction() {
        result = new Result(x, y, r, new Date());
        if (checking.check(result)) {
            result.setHit("Да");
        } else {
            result.setHit("Нет");
        }
        if (resultService.addResult(result)) {
            message = "";
        } else {
            message = "Не удалось добавить элемент!";
        }

        userStatisticProvider.calculateUserDoubleMissed();
    }

    public void doClear() {
        if (resultService.clearResults()) {
            message = "Таблица очищена!";
        } else {
            message = "Не удалось очистить таблицу!";
        }
    }
}
