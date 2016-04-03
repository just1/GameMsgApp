package com.yis.gamemsgapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.yis.gamemsgapp.fragment.ListFragment;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private SlidingMenu mMenu;
    private MyAdapter mAdapter;
    private List mList;

    private final static String TAG = "MainActivity";
    private final static String BMOD_KEY = "1fa08f8d5754e18b8db11f4afab235aa";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // 初始化 Bmob SDK
        Bmob.initialize(this, BMOD_KEY);

//        testAddData();

        initView();
//        initSlidingMenu();
    }

    private void initSlidingMenu(){
        // configure the SlidingMenu
        mMenu = new SlidingMenu(this);
        mMenu.setMode(SlidingMenu.LEFT);
        // 设置触摸屏幕的模式
        mMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        mMenu.setShadowWidthRes(R.dimen.shadow_width);
//        menu.setShadowDrawable(R.drawable.shadow);

        // 设置滑动菜单视图的宽度
        mMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // 设置渐入渐出效果的值
        mMenu.setFadeDegree(0.35f);
        /**
         * SLIDING_WINDOW will include the Title/ActionBar in the content
         * section of the SlidingMenu, while SLIDING_CONTENT does not.
         */
        //把滑动菜单添加进所有的Activity中，可选值SLIDING_CONTENT ， SLIDING_WINDOW
        mMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //为侧滑菜单设置布局
        mMenu.setMenu(R.layout.leftmenu);

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

//    private void testAddData(){
//        for(int i=1;i<200;i++) {
//            NewsList bean =new NewsList("标题"+i,
//                    "描述"+i,
//                    String.valueOf(i),
//                    "lh");
//
//            BModLoader.getInstance().add(this, bean);
//
//        }
//    }

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
