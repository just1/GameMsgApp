package com.yis.gamemsgapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.yis.gamemsgapp.fragment.ListFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private MyAdapter mAdapter;
    private List mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.vp_main);
        mList =new ArrayList();

        mList.add(new ListFragment());
        mList.add(new ListFragment());
        mList.add(new ListFragment());

        mAdapter = new MyAdapter(getSupportFragmentManager(),mList);
        mViewPager.setAdapter(mAdapter);
    }


    class MyAdapter extends FragmentPagerAdapter {

        List<Fragment> mList;


        public MyAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            mList = list;
        }

        @Override
        public Fragment getItem(int i) {
            if ((null != mList) && (mList.size() > i)) {
                return mList.get(i);
            } else {
                return null;
            }
        }

        @Override
        public int getCount() {
            if(null != mList) {
                return mList.size();
            }else {
                return 0;
            }
        }
    }
}
