package com.mobest1an.labs.models.checkers;

import com.mobest1an.labs.services.Result;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "areaCheckerBean", eager = true)
@ApplicationScoped
public class AreaCheckerBean implements Checking {

    @Override
    public boolean check(Object object) {
        Result result = (Result) object;
        double x = result.getX();
        double y = result.getY();
        double r = result.getR();

        if (x <= 0 && y >= 0 && x >= -r && y <= r) {
            return true;
        }

        if (x <= 0 && y <= 0 && (Math.pow(x, 2) + Math.pow(y, 2)) <= Math.pow(r / 2, 2)) {
            return true;
        }

        if (x >= 0 && y <= 0 && y - x >= (-r / 2)) {
            return true;
        }

        return false;
    }
}
