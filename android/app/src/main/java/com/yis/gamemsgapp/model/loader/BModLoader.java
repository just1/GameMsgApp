package com.yis.gamemsgapp.model.loader;

import android.content.Context;
import android.util.Log;

import com.yis.gamemsgapp.bean.NewsList;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by yinjianhua on 16/3/16.
 */
public class BModLoader {

    private static final String TAG = "BModLoader";

    private static BModLoader mInstance;

    private BModLoader(){

    }

    public static BModLoader getInstance(){
        if(null == mInstance){
            mInstance = new BModLoader();
        }
        return mInstance;
    }


    public void load(Context context,FindListener<NewsList> listener) {
        BmobQuery<NewsList> query = new BmobQuery<NewsList>();
        //返回50条数据，如果不加上这条语句，默认返回10条数据
        query.setLimit(10);
        //执行查询方法
        query.findObjects(context, listener);
    }

    public void loadPage(Context context,int curCount,FindListener<NewsList> listener) {
        BmobQuery<NewsList> query = new BmobQuery<NewsList>();
        //返回50条数据，如果不加上这条语句，默认返回10条数据
        query.setLimit(10);
        query.setSkip(curCount);
        //执行查询方法
        query.findObjects(context, listener);
    }

    public void add(Context context,NewsList bean){
        bean.save(context, new SaveListener() {

            @Override
            public void onSuccess() {
                Log.w(TAG,"Success");
            }

            @Override
            public void onFailure(int code, String arg0) {
                Log.w(TAG,"Failure");
            }
        });


    }


}
