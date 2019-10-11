package com.bestquotes.with.coolstatus;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SliderScreenViewPagerAdapter extends PagerAdapter

{
    Context context;
    List<SliderScreenItems> SliderScreenItemsList;



    public SliderScreenViewPagerAdapter(Context context, List<SliderScreenItems> sliderScreenItemsList) {
        this.context = context;
        this.SliderScreenItemsList = sliderScreenItemsList;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.activity_slider_screen,null);
        ImageView img = v.findViewById(R.id.sliderscreenimage);
        TextView texttitle = v.findViewById(R.id.sliderscreentitle);
        TextView textdiscrp = v.findViewById(R.id.sliderscreendecription);
        texttitle.setText(SliderScreenItemsList.get(position).getTitle());
        textdiscrp.setText(SliderScreenItemsList.get(position).getDiscription());
        img.setImageResource(SliderScreenItemsList.get(position).getScreenImg());
        container.addView(v);
        return  v;
    }

    @Override
    public int getCount() {
        return SliderScreenItemsList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return  view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View)object);
    }

}
