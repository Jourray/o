package com.example.myapplication;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.fragment.WeChatFragment;
import com.example.myapplication.fragment.ZhihuMainFragment;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    private int lastType = 0;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.view_search)
    MaterialSearchView viewSearch;
    @BindView(R.id.toolbar_container)
    FrameLayout toolbarContainer;
    @BindView(R.id.fl_main_content)
    FrameLayout flMainContent;
    @BindView(R.id.navigation)
    NavigationView navigation;
    @BindView(R.id.drawer)
    DrawerLayout drawer;
    private ArrayList<Fragment> list;
    private FragmentManager supportFragmentManager;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initMvp() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("");
        setSupportActionBar(toolBar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolBar, R.string.drawer_open, R.string.drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        list = new ArrayList<Fragment>();
        list.add(new ZhihuMainFragment());
        list.add(new WeChatFragment());
        supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fl_main_content, list.get(0));
        fragmentTransaction.commit();
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.drawer_zhihu:
                        SwicthFragment(0);
                        break;
                    case R.id.drawer_wechat:
                        SwicthFragment(1);
                        break;

                }
                MenuItem lastitem = navigation.getMenu().findItem(R.id.drawer_zhihu);
                if (lastitem!=null){
                lastitem.setChecked(false);
                }
                menuItem.setChecked(true);
                drawer.closeDrawer(Gravity.LEFT);
                return false;
            }
        });
    }

    public void SwicthFragment(int type) {
        FragmentTransaction tran = supportFragmentManager.beginTransaction();
        Fragment fragment = list.get(type);
        if (!fragment.isAdded()) {
            tran.add(R.id.fl_main_content, fragment);
        }
        Fragment fragment1 = list.get(lastType);
        tran.hide(fragment1);
        tran.show(fragment);
        tran.commit();
        lastType = type;
    }
}
