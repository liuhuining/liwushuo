package com.qf.liuyong.liwushuo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qf.liuyong.liwushuo.R;
import com.qf.liuyong.liwushuo.model.bean.SelectBean;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/6.
 */
public class SelectAdapter extends BaseExpandableListAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<String> dataList;
    private Map<String,List<SelectBean.DataBean.ItemsBean>> map;

    public SelectAdapter(Context context, List<String> dataList, Map<String, List<SelectBean.DataBean.ItemsBean>> map) {
        this.context = context;
        this.dataList = dataList;
        this.map = map;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String date = dataList.get(groupPosition);
        List<SelectBean.DataBean.ItemsBean> itemsBeen = map.get(date);
        return itemsBeen == null ? 0 : itemsBeen.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        TextView view = (TextView) convertView;
        if (view == null) {
            view = new TextView(context);
        }
        String date = dataList.get(groupPosition);
        view.setText(date);
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.select_item_view,null);
            viewHolder = new ViewHolder(convertView);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String key = dataList.get(groupPosition);
        SelectBean.DataBean.ItemsBean itemsBean = map.get(key).get(childPosition);
        viewHolder.textView.setText(itemsBean.getTitle());
        Picasso.with(context).load(itemsBean.getCover_image_url()).into(viewHolder.imageView);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
    class ViewHolder{
        @BindView(R.id.select_item_iv)
        ImageView imageView;
        @BindView(R.id.select_item_title_txt)
        TextView textView;
        public ViewHolder (View view){

            ButterKnife.bind(this,view);
            view.setTag(this);
        }
    }
}
