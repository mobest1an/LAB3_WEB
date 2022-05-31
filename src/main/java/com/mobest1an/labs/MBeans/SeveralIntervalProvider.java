package com.mobest1an.labs.MBeans;

import com.mobest1an.labs.services.Result;

import java.util.List;

public class SeveralIntervalProvider implements SeveralIntervalProviderMBean {

    private double severalInterval;

    private List<Result> results;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    private void calculateSeveralInterval() {
        severalInterval = 0;

        if (results.size() <= 1) {
            return;
        }

        for (int i = 1; i < results.size(); i++) {
            double legX = Math.abs(results.get(i).getX() - results.get(i - 1).getX());
            double legY = Math.abs(results.get(i).getY() - results.get(i - 1).getY());

            double hypothesis = Math.sqrt(Math.pow(legX, 2) + Math.pow(legY, 2));

            severalInterval += hypothesis;
        }

        severalInterval = severalInterval / (results.size() - 1);
    }

    @Override
    public double getSeveralInterval() {
        calculateSeveralInterval();
        return severalInterval;
    }

    @Override
    public void setSeveralInterval(double severalInterval) {
        this.severalInterval = severalInterval;
    }
}
