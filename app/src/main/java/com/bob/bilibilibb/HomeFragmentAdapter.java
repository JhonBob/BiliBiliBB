package com.bob.bilibilibb;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bob.bilibilibb.fragment.DummyFragment;
import com.bob.bilibilibb.fragment.HomeCategoryNavFragment;
import com.bob.bilibilibb.fragment.HomeRecommendFragment;

import java.util.ArrayList;

/**
 * Created by asus on 2016/1/21.
 */
public class HomeFragmentAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private String[] mTabTitle;
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    public HomeFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
        mTabTitle = mContext.getResources().getStringArray(R.array.home_tab_title);
        mFragments.add(new DummyFragment());
        mFragments.add(new DummyFragment());
        mFragments.add(new HomeRecommendFragment());
        mFragments.add(new HomeCategoryNavFragment());
        mFragments.add(new DummyFragment());
        mFragments.add(new DummyFragment());//TODO 改为其他Fragment
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mTabTitle.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTitle[position];
    }
}
