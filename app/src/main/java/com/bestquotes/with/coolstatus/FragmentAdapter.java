package com.bestquotes.with.coolstatus;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class FragmentAdapter extends FragmentPagerAdapter
{
    ArrayList<Fragment> fragments = new ArrayList<>();
    ArrayList<String>   Title = new ArrayList<>();


    public FragmentAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int i)
    {
        return fragments.get(i);
    }

    @Override
    public int getCount()
    {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return Title.get(position);
    }

    public void FragmentSupplier(Fragment fragment , String titles)
    {

        fragments.add(fragment);
        Title.add(titles);


    }







}
