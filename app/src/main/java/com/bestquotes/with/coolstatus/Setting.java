package com.bestquotes.with.coolstatus;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class Setting extends Fragment {
    RelativeLayout first, second, third;
    DrawerLayout drawerLayout;
    android.support.v7.widget.Toolbar settingtoolbar;
    NavigationView navigationView;
    InterstitialAd interstitialAdd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_setting, container, false);
        drawerLayout = (DrawerLayout) v.findViewById(R.id.Setting_DrawerLayout_id);
        navigationView = (NavigationView) v.findViewById(R.id.Setting_NavigationView_id);
        first = (RelativeLayout) v.findViewById(R.id.FirstLayout);
        second = (RelativeLayout) v.findViewById(R.id.SecondLayout);
        third = (RelativeLayout) v.findViewById(R.id.ThirdLayout);
        interstitialAdd = new InterstitialAd(getContext());
        interstitialAdd.setAdUnitId("ca-app-pub-4580368318288335/8308111539");
        interstitialAdd.loadAd(new AdRequest.Builder().build());
        MobileAds.initialize(getContext(), "ca-app-pub-4580368318288335~5075443539");

        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent rateusintent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getActivity().getPackageName()));
                startActivity(rateusintent);
            }
        });
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rateusintent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getActivity().getPackageName()));
                startActivity(rateusintent);
            }
        });
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rateusintent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getActivity().getPackageName()));
                startActivity(rateusintent);
            }
        });
        settingtoolbar = (android.support.v7.widget.Toolbar) v.findViewById(R.id.SettingToolbar_id);
        // Drawer Coding
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, settingtoolbar, R.string.Navigation_drawer_open, R.string.Navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        // click listener

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
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
                                        if (interstitialAdd.isLoaded()) {
                                            interstitialAdd.show();
                                            interstitialAdd.setAdListener(new AdListener() {

                                                                              @Override
                                                                              public void onAdClosed() {
                                                                                  super.onAdClosed();
                                                                                  dialog.cancel();
                                                                              }
                                                                          }
                                            );
                                        } else {
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
