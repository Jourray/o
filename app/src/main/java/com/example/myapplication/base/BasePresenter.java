package com.example.myapplication.base;

import java.util.ArrayList;
import java.util.List;

public abstract class BasePresenter<V extends BaseView, M extends BaseModel> {
    protected V view;
    protected M model;
    List<M> modellist = new ArrayList<>();

    public BasePresenter(V view) {
        this.view = view;
        this.model = initModel();
        modellist.add(model);
    }

    protected abstract M initModel();

    public void destroy() {
        //空View 断model
        view = null;
        model.destroy();
        if (modellist != null) {
            for (M model : modellist) {
                model.destroy();
            }
        }
    }
}
