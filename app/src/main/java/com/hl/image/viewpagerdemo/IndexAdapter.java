package com.hl.image.viewpagerdemo;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by zzq on 16/10/31.
 *
 * @param:
 * @param:
 */

public class IndexAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<ImageView> imageList;
    public IndexAdapter(Context context,ArrayList<ImageView> imageList){
        this.context = context;
        this.imageList = imageList;
    }
    // 需要实现以下四个方法

    @Override
    public int getCount() {
        // 获得页面的总数
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        // 判断view和Object对应是否有关联关系
        if (view == object) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // 获得相应位置上的view； container view的容器，其实就是viewpage自身,
        // position: viewpager上的位置
        // 给container添加内容
        container.addView(imageList.get(position % imageList.size()));

        return imageList.get(position % imageList.size());
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // 销毁对应位置上的Object
        // super.destroyItem(container, position, object);
        container.removeView((View) object);
        object = null;
    }
}
