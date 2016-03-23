package com.bob.bilibilibb.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by asus on 2016/1/20.
 */
public abstract class BaseFragment extends Fragment {

    private View mContentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = initContentView(inflater,container);
        initData();
        initListener();
        return mContentView;
    }

    protected abstract View initContentView(LayoutInflater inflater,ViewGroup container);


    public View getContentView(){
        return mContentView;
    }

    protected abstract void initData();

    protected abstract void initListener();
}
