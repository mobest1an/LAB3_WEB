package com.mobest1an.labs.services;

import com.mobest1an.labs.DAO.DataBaseManagerBean;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ManagedBean(name = "resultServiceBean", eager = true)
@ApplicationScoped
public class ResultServiceBean implements ResultService {

    @ManagedProperty(value = "#{dataBaseManagerBean}")
    private DataBaseManagerBean dataBaseManagerBean;
    private List<Result> results = Collections.synchronizedList(new ArrayList<>());

    public boolean addResult(Result result) {
        if (dataBaseManagerBean.isConnected()) {
            if (dataBaseManagerBean.addResult(result)) {
                results.add(result);
                return true;
            }
        }
        return false;
    }

    public List<Result> getResults() {
        if (dataBaseManagerBean.isConnected()) {
            List<Result> dbList = dataBaseManagerBean.getResults();
            if (dbList != null) {
                results.addAll(dbList);
            }
        }
        return results;
    }

    public DataBaseManagerBean getDataBaseManagerBean() {
        return dataBaseManagerBean;
    }

    public void setDataBaseManagerBean(DataBaseManagerBean dataBaseManagerBean) {
        this.dataBaseManagerBean = dataBaseManagerBean;
    }
}
