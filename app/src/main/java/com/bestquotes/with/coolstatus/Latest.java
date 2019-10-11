package com.bestquotes.with.coolstatus;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Latest extends Fragment
{
    RecyclerView recyclerView;

    //  Varibales for Main Images Downloading
    List<MainImagesGTST> listgtst;
    DatabaseReference firstreference;
    MainImagesRecyclerViewAdapter imagesRecyclerViewAdapter;
    AdView adView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        View v = inflater.inflate(R.layout.latest,container,false);
        recyclerView = (RecyclerView)v.findViewById(R.id.ContributerLatestRV_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);


        // BannerAdView Coding





        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {


        }


        else
        {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
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




        // Code For Main Image Dwonaload from firebase
        listgtst = new ArrayList<>();
        firstreference = FirebaseDatabase.getInstance().getReference("Latest");
        firstreference.keepSynced(true);
        firstreference.addValueEventListener(new ValueEventListener() {

            @SuppressLint("ResourceAsColor")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("Tone", "MainActivity : addValueListener : OnDataChange Call");
                for (int i = 0; i < 10; i++) {

                    for (final DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        final MainImagesGTST gt = dataSnapshot1.getValue(MainImagesGTST.class);
                        listgtst.add(gt);


                    }
                    imagesRecyclerViewAdapter = new MainImagesRecyclerViewAdapter(getContext(), listgtst);
                    Log.d("Tone", "MainActivity : addValueListener : Adapter Set Called");
                    recyclerView.setAdapter(imagesRecyclerViewAdapter);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Tone","MainActivity : addValueListener : OnCancelled Call");

                Toast.makeText(getContext(),"Check Your Internet Connection",Toast.LENGTH_SHORT).show();

            }
        });
        Log.d("Tone"," MainActivity : OnStart End");










        return v;
    }


}
