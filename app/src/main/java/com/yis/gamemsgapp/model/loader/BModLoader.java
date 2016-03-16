package com.yis.gamemsgapp.model.loader;

import android.content.Context;
import android.util.Log;

import com.yis.gamemsgapp.bean.NewsSummary;
import com.yis.gamemsgapp.bean.Person;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;
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


    public void load(Context context,FindListener<NewsSummary> listener) {
        BmobQuery<NewsSummary> query = new BmobQuery<NewsSummary>();
//        //查询playerName叫“比目”的数据
//        query.addWhereEqualTo("playerName", "比目");
        //返回50条数据，如果不加上这条语句，默认返回10条数据
        query.setLimit(10);
        //执行查询方法
        query.findObjects(context, listener);
    }

    public void add(Context context,NewsSummary bean){
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
