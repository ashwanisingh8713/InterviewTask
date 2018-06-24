package com.interview.task.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.interview.task.R;
import com.interview.task.model.ItemBean;
import com.interview.task.model.TwoColumnBean;
import com.interview.task.util.GlideUtil;

import java.util.ArrayList;

/**
 * Created by ashwanisingh on 24/06/18.
 */
public class TwoColumnAdapter extends ArrayAdapter<TwoColumnBean> {

    private ArrayList<TwoColumnBean> mImageBeans;
    private LayoutInflater mInflater;

    public TwoColumnAdapter(@NonNull Context context, int resource, ArrayList<TwoColumnBean> imageBeans) {
        super(context, resource, imageBeans);
        this.mImageBeans = imageBeans;
        mInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mImageBeans.size();
    }

    /*@Nullable
    @Override
    public TwoColumnBean getItem(int position) {
        return mImageBeans.get(position);
    }*/

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_row_two_column, parent, false);
            holder.column1 = convertView.findViewById(R.id.column1);
            holder.column2 = convertView.findViewById(R.id.column2);
            holder.column1Img = convertView.findViewById(R.id.column1_img);
            holder.column2Img = convertView.findViewById(R.id.column2_img);
            holder.column1Tv = convertView.findViewById(R.id.column1_tv);
            holder.column2Tv = convertView.findViewById(R.id.column2_tv);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        TwoColumnBean bean = getItem(position);

        ItemBean item1 = bean.getColumn1();
        ItemBean item2 = bean.getColumn2();

        if(item2 == null) {
            holder.column2.setVisibility(View.GONE);
            holder.column1Img.setAdjustViewBounds(true);
        } else {
            holder.column2.setVisibility(View.VISIBLE);
            holder.column2Tv.setText(item2.getTag());
            GlideUtil.loadImage(getContext(), holder.column2Img, item2.getImgUrl(), R.mipmap.ic_launcher);
        }


        holder.column1Tv.setText(item1.getTag());
        GlideUtil.loadImage(getContext(), holder.column1Img, item1.getImgUrl(), R.mipmap.ic_launcher);

        return convertView;
    }

    private static class ViewHolder {
        private ImageView column1Img;
        private ImageView column2Img;
        private TextView column1Tv;
        private TextView column2Tv;
        private RelativeLayout column1;
        private RelativeLayout column2;
    }


    public void updateData(ArrayList<TwoColumnBean> imageBeans) {
        clear();
        addAll(imageBeans);
    }


}
