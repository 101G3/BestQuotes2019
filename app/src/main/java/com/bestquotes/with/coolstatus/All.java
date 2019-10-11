package com.bestquotes.with.coolstatus;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class All extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_all, container, false);
        TabLayout TB = (TabLayout) v.findViewById(R.id.Toolbar_id);
        ViewPager VP = (ViewPager) v.findViewById(R.id.ViewPager_id);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getChildFragmentManager());
        fragmentAdapter.FragmentSupplier(new Latest(), "Most Popular");
        // Popular Fragment is Commented
        VP.setAdapter(fragmentAdapter);
        TB.setupWithViewPager(VP);
        TB.getTabAt(0).setIcon(R.drawable.ic_format_quote_black_24dp);


        return v;
    }

}

