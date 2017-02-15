package com.alibaba.sdk.android.oss.app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.sdk.android.oss.model.OSSObjectSummary;
import com.alibaba.sdk.android.oss.sample.ListObjectsSamples;

/**
 * Created by wangzheng on 2017/2/14.
 */

public class ListObjectActivity extends Activity{


    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_listobject);
        ListObjectsSamples.getInstance().AyncListObjects();
        listView = (ListView) findViewById(R.id.list);
        ListAdapter mAdapter = new ListAdapter(this); //得到一个MyAdapter对象
        listView.setAdapter(mAdapter); //为ListView绑定Adapter
    }


    // 新建一个类继承BaseAdapter，实现视图与数据的绑定
    private class ListAdapter extends BaseAdapter {

        private LayoutInflater mInflater; //得到一个LayoutInfalter对象用来导入布局

        public ListAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return ListObjectsSamples.getInstance().objects.size(); //返回数组的长度
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.objects_list_item, null);
                holder = new ViewHolder();
                /*得到各个控件的对象*/
                holder.title = (TextView) convertView.findViewById(R.id.key);
                holder.text = (TextView) convertView.findViewById(R.id.type);
                convertView.setTag(holder); //绑定ViewHolder对象
            }
            else {
                holder = (ViewHolder) convertView.getTag(); //取出ViewHolder对象
            }

//            /*设置TextView显示的内容，即我们存放在动态数组中的数据*/
            OSSObjectSummary obj = ListObjectsSamples.getInstance().objects.get(position);
            holder.title.setText(obj.getKey());
            holder.text.setText(""+obj.getLastModified());

            return convertView;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

    }

    /*存放控件 的ViewHolder*/
    public final class ViewHolder {
        public TextView title;
        public TextView text;
        public Button bt;
    }


}
