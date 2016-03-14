package com.yis.gamemsgapp.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.yis.gamemsgapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yinjianhua on 16/3/14.
 */
public class ListFragment extends Fragment {

    private List<String> mList = new ArrayList();
    private ListView mListView;
    private MyAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.frag_list,null);
        mListView = (ListView) root.findViewById(R.id.lv_list);
        mList.add("hahah");
        mList.add("hahah");
        mList.add("hahah");
        mList.add("hahah");
        mList.add("hahah");
        mAdapter = new MyAdapter(getContext(),mList);
        mListView.setAdapter(mAdapter);


        return root;
    }


    class MyAdapter extends BaseAdapter{

        private List<String> mList;
        private LayoutInflater mInflater;

        public MyAdapter(Context context,List<String> list){
            mList = list;
            mInflater =  LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            if(null != mList){
                return mList.size();
            }

            return 0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            ViewHolder holder;
            if (view == null || view.getTag() == null || !(view.getTag() instanceof ViewHolder)) {
                view = mInflater.inflate(R.layout.item_list, null);
                holder = new ViewHolder();
                holder.textView = (TextView) view.findViewById(R.id.tv_title);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            holder.textView.setText(mList.get(i));

            return view;
        }

        class ViewHolder{
            TextView textView;
        }
    }
}
