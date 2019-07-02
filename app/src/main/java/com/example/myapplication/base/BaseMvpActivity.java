package com.example.myapplication.base;

public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity {

    protected P mpresenter;

    @Override
    protected void initMvp() {
        mpresenter = initPresenter();
    }

    protected abstract P initPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mpresenter.destroy();
    }
}
