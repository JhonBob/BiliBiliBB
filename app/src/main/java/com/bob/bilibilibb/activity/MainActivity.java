package com.bob.bilibilibb.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.support.design.widget.TabLayout.Tab;

import android.widget.TextView;


import com.bob.bilibilibb.R;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ActionBarDrawerToggle toggle;
    private String[] tabs=new String[]{"直播","番剧","推荐","分区","关注","发现"};
    private Tab tab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar=findView(R.id.tool_bar);
        mToolbar.setTitle(R.string.app_name);
        setSupportActionBar(mToolbar);
        mTabLayout= (android.support.design.widget.TabLayout) findViewById(R.id.tab_layout);
        mViewPager= (ViewPager) findViewById(R.id.view_pager);
        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle=new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        initNavigationView();
        addTab();
    }

    private void initNavigationView() {
        mNavigationView= (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void addTab(){
        for (String s : tabs) {
            tab = mTabLayout.newTab();
            tab.setText(s);
            mTabLayout.addTab(tab);
        }
    }
}
