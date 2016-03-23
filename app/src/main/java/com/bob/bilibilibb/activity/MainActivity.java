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
import android.widget.ImageView;

import com.bob.bilibilibb.HomeFragmentAdapter;
import com.bob.bilibilibb.R;
import com.bob.bilibilibb.activity.BaseActivity;


public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private Tab tab;
    private ImageView menu,avator;
    private ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar=findView(R.id.tool_bar);
        mToolbar.setTitle(R.string.app_name);
        setSupportActionBar(mToolbar);


        mTabLayout= (android.support.design.widget.TabLayout) findViewById(R.id.tab_layout);
        mViewPager= (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(new HomeFragmentAdapter(getApplicationContext(), getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(1);
        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        initNavigationView();
    }

    private void initNavigationView() {
        mNavigationView= (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }
}
