package com.bestquotes.with.coolstatus;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.List;

public class Menu extends Fragment  {


    RecyclerView recyclerView;
    MenuRecyclerViewAdapter Adapter;
    List<MenuGetterSetter> MenuGT;
    DrawerLayout drawerLayout;
    android.support.v7.widget.Toolbar menutoolbar;
    NavigationView navigationView;
    InterstitialAd interstitialAdd;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_menu, container, false);
        drawerLayout = (DrawerLayout) v.findViewById(R.id.Menu_DrawerLayout_id);
        navigationView = (NavigationView) v.findViewById(R.id.Menu_NavigationView_id);
        MenuGT = new ArrayList<>();
        interstitialAdd = new InterstitialAd(getContext());
        interstitialAdd.setAdUnitId("ca-app-pub-4580368318288335/8308111539");
        interstitialAdd.loadAd(new AdRequest.Builder().build());
        MobileAds.initialize(getContext(), "ca-app-pub-4580368318288335~5075443539");
        recyclerView = (RecyclerView) v.findViewById(R.id.Menu_RV);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        MenuGT.add(new MenuGetterSetter("Smile", R.drawable.ic_navigate_next_black_24dp, R.id.menulayout_id));
        MenuGT.add(new MenuGetterSetter("Attitude", R.drawable.ic_navigate_next_black_24dp, R.id.menulayout_id));
        MenuGT.add(new MenuGetterSetter("Leadership", R.drawable.ic_navigate_next_black_24dp, R.id.menulayout_id));
        MenuGT.add(new MenuGetterSetter("Life", R.drawable.ic_navigate_next_black_24dp, R.id.menulayout_id));
        MenuGT.add(new MenuGetterSetter("Sad", R.drawable.ic_navigate_next_black_24dp, R.id.menulayout_id));
        MenuGT.add(new MenuGetterSetter("Inspirational", R.drawable.ic_navigate_next_black_24dp, R.id.menulayout_id));
        MenuGT.add(new MenuGetterSetter("Motivational", R.drawable.ic_navigate_next_black_24dp, R.id.menulayout_id));
        MenuGT.add(new MenuGetterSetter("Happiness", R.drawable.ic_navigate_next_black_24dp, R.id.menulayout_id));
        Adapter = new MenuRecyclerViewAdapter(v.getContext(), MenuGT);
        recyclerView.setAdapter(Adapter);
        menutoolbar = (android.support.v7.widget.Toolbar) v.findViewById(R.id.poetToolbar_id);
        // Drawer Coding
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, menutoolbar, R.string.Navigation_drawer_open, R.string.Navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        // click listener
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.Nav_RateUs_id:
                        Intent rateusintent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getActivity().getPackageName()));
                        startActivity(rateusintent);
                        break;
                    case R.id.Nav_DownloadOtherApp_id:
                        Intent doimtent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getActivity().getPackageName()));
                        startActivity(doimtent);
                        break;
                    case R.id.Nav_AboutUs_id:
                        Intent aboutusintent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getActivity().getPackageName()));
                        startActivity(aboutusintent);
                        break;
                    case R.id.Nav_RemoveAds_id: {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                        builder1.setTitle("Comming Soon !");
                        builder1.setCancelable(true);
                        builder1.setNegativeButton(
                                "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(final DialogInterface dialog, int id) {
                                        if(interstitialAdd.isLoaded())
                                        {
                                            interstitialAdd.show();
                                            interstitialAdd.setAdListener(new AdListener()
                                                                          {

                                                                              @Override
                                                                              public void onAdClosed() {
                                                                                  super.onAdClosed();
                                                                                  dialog.cancel();
                                                                              }
                                                                          }
                                            );
                                        }
                                        else
                                        {
                                            dialog.cancel();
                                        }


                                    }
                                });
                        AlertDialog alert11 = builder1.create();
                        alert11.show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                    }
                        break;
                }
                menuItem.setCheckable(false);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        return v;

    }

}
