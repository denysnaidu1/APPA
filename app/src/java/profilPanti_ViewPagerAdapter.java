package com.daredevil.appa;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class profilPanti_ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater inflater;
    private Integer [] image={R.drawable.williambooth,R.drawable.williambooth2,R.drawable.williambooth3};

    public profilPanti_ViewPagerAdapter(Context context) {
        this.context = context;

    }

    @Override
    public int getCount() {
        return image.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.custom_layout,null);
        ImageView imageV=(ImageView)view.findViewById(R.id.imageView);
        imageV.setImageResource(image[position]);

        ViewPager vp=(ViewPager)container;
        vp.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager vp=(ViewPager)container;
        View view=(View) object;
        vp.removeView(view);
    }
}
