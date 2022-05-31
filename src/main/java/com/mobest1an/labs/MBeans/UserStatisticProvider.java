package com.mobest1an.labs.MBeans;

import com.mobest1an.labs.services.Result;

import java.util.List;

public class UserStatisticProvider implements UserStatisticProviderMBean {

    private int userPointsCount;
    private boolean userDoubleMissed;

    private List<Result> results;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    private void calculateUserPointsCount() {
        userPointsCount = results.size();
    }

    private void calculateUserDoubleMissed() {
        userDoubleMissed = results.get(results.size() - 1).getHit().equals("Нет")
                && results.get(results.size() - 2).getHit().equals("Нет");
    }

    @Override
    public int getUserPointsCount() {
        calculateUserPointsCount();
        return userPointsCount;
    }

    @Override
    public void setUserPointsCount(int pointsCount) {
        this.userPointsCount = pointsCount;
    }

    @Override
    public boolean isUserDoubleMissed() {
        calculateUserDoubleMissed();
        return userDoubleMissed;
    }

    @Override
    public void setUserDoubleMissed(boolean userDoubleMissed) {
        this.userDoubleMissed = userDoubleMissed;
    }
}
