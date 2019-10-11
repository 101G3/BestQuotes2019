package com.bestquotes.with.coolstatus;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AttitudeActivity extends AppCompatActivity {

    RecyclerView recyclerView;


    //  Varibales for Main Images Downloading
    List<MainImagesGTST> listgtst;
    DatabaseReference firstreference;
    MainImagesRecyclerViewAdapter imagesRecyclerViewAdapter;
    ImageView Backimg;
    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attitude);
        Backimg = (ImageView) findViewById(R.id.AttitudeBackArrow_id);
        recyclerView = (RecyclerView) findViewById(R.id.ContributerAttitudeRV_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerView.setHasFixedSize(true);



        // Banner Ad View

        adView = (AdView)findViewById(R.id.AttitudeBannerAdView_id);
        adView.setAdListener(new AdListener()
                             {
                                 @SuppressLint("ResourceAsColor")
                                 @Override
                                 public void onAdLoaded() {
                                     super.onAdLoaded();
                                     adView.setVisibility(View.VISIBLE);
                                     adView.setBackgroundColor(R.color.Black);
                                 }
                             }
        );
        MobileAds.initialize(AttitudeActivity.this, String.valueOf(R.string.AppAdID));
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);



        // Back Button Code

        Backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            // Code For Main Image Dwonaload from firebase
            listgtst = new ArrayList<>();
            firstreference = FirebaseDatabase.getInstance().getReference("Attitude");
            firstreference.keepSynced(true);
            firstreference.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Log.d("Tone", "MainActivity : addValueListener : OnDataChange Call");
                    for (int i = 0; i < 10; i++) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            MainImagesGTST gt = dataSnapshot1.getValue(MainImagesGTST.class);
                            listgtst.add(gt);
                        }
                        imagesRecyclerViewAdapter = new MainImagesRecyclerViewAdapter(AttitudeActivity.this, listgtst);
                        Log.d("Tone", "MainActivity : addValueListener : Adapter Set Called");
                        recyclerView.setAdapter(imagesRecyclerViewAdapter);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.d("Tone","MainActivity : addValueListener : OnCancelled Call");
                }
            });
            Log.d("Tone"," MainActivity : OnStart End");

        }


     else
            {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(AttitudeActivity.this);
                builder1.setMessage("No Internet connection. Make sure that WiFi or Mobile Data is turn on,then try again.");
                builder1.setTitle("Unable to connect to the internet");
                builder1.setCancelable(true);
                builder1.setNegativeButton(
                        "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();

                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();





        }


    }
}

