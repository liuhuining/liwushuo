package com.qf.liuyong.liwushuo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qf.liuyong.liwushuo.R;
import com.qf.liuyong.liwushuo.model.bean.SelectBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/6.
 */
public class Base_Adapter extends BaseAdapter {
    //
    private List<SelectBean.DataBean.ItemsBean> beanList ;
    private Context mContext;
    private LayoutInflater mLayoutInfalter;

    public Base_Adapter(List<SelectBean.DataBean.ItemsBean> beanList, Context mContext) {
        this.beanList = beanList;
        this.mContext = mContext;
        mLayoutInfalter = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {

        return beanList == null ? 0 : beanList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder = null;
        if (view == null){
            view = mLayoutInfalter.inflate(R.layout.base_item_view,null);
            viewHolder = new ViewHolder(view);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        SelectBean.DataBean.ItemsBean itemsBean = beanList.get(position);
        if (itemsBean != null) {
            String title = itemsBean.getTitle();
            viewHolder.textView.setText(title);
            Picasso.with(mContext).load(itemsBean.getCover_image_url()).into(viewHolder.imageView);
        }

        return view;
    }
    class ViewHolder{
        @BindView(R.id.base_item_iv)
        ImageView imageView;
        @BindView(R.id.base_item_title_txt)
        TextView textView;
        public ViewHolder(View view){
            view.setTag(this);
            ButterKnife.bind(this,view);
        }
    }
}
