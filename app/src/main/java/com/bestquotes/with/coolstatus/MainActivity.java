package com.bestquotes.with.coolstatus;

import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity  {


  public  static   BottomNavigationView bottomNavigationView;
    AdView adView;
    Fragment f;
    public static int DrawerItemClicked = 0;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("Find", "Main Activity is Running");
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.BottomNavView_id);



        // Adview Code

        adView = (AdView)findViewById(R.id.MainBannerAdView_id);
        adView.setAdListener(new AdListener()
                             {
                                 @Override
                                 public void onAdLoaded() {
                                     super.onAdLoaded();
                                     adView.setVisibility(View.VISIBLE);
                                 }
                             }
        );
        MobileAds.initialize(MainActivity.this, String.valueOf(R.string.AppAdID));
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);



        // Menu Drawer Layout Code


        getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayout_id, new All()).commit();
        BottomNavigationView.OnNavigationItemSelectedListener ItemSelecter = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment SelectFragment = null;
                Log.i("Find", "Main Activity botom navigation method is Running");

                switch (menuItem.getItemId()) {

                    case R.id.menu_All_id :
                            SelectFragment = new All();
                        break;
                    case R.id.menu_Poets_id:
                        SelectFragment = new Menu();
                        break;
                    case R.id.menu_Setting_id:
                        SelectFragment = new Setting();

                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayout_id, SelectFragment).disallowAddToBackStack().commit();
                return true;
            }
        };
        bottomNavigationView.setOnNavigationItemSelectedListener(ItemSelecter);
    }

    @Override
    public void onCreate( Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);



    }
}

