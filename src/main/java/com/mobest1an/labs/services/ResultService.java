package com.mobest1an.labs.services;

import java.util.List;

public interface ResultService {

    boolean addResult(Result result);
    List<Result> getResults();
    boolean clearResults();
}
