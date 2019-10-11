package com.bestquotes.with.coolstatus;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MainImagesRecyclerViewAdapter extends RecyclerView.Adapter<MainImagesRecyclerViewAdapter.RVViewHolder> {

    private Context mContext;
    private List<MainImagesGTST> mGetterSetter;
    // List<ContributerGetterSetter> contributerGetterSetterList;
    BitmapDrawable drawable;
    Bitmap bitmap;


    public MainImagesRecyclerViewAdapter(Context mContext, List<MainImagesGTST> mGetterSetter) {
        Log.d("Tone", "RecyclerView Constructor");
        this.mContext = mContext;
        this.mGetterSetter = mGetterSetter;
    }

    @NonNull
    @Override
    public RVViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.mainimagerecyclerview, viewGroup, false);
        return new RVViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull final RVViewHolder rvViewHolder, int i) {
        Log.d("Tone", "RecyclerView : OnBindViewHolder Called");
        final MainImagesGTST gtst = mGetterSetter.get(i);
        Picasso.get().load(gtst.getImage()).placeholder(R.drawable.loading).into(rvViewHolder.imge);
        Log.d("Tone", "RecyclerView : OnBindViewHolder set");
        rvViewHolder.imge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (rvViewHolder.interstitialAdd.isLoaded()) {
                    rvViewHolder.interstitialAdd.show();
                    rvViewHolder.interstitialAdd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            super.onAdClosed();
                            AdRequest adRequest = new AdRequest.Builder().build();
                            rvViewHolder.interstitialAdd.loadAd(adRequest);

                            // Opening Detail Activity After Ad Closing

                            Intent intent = new Intent(mContext, DetailActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("Mainimage", gtst.getImage());
                            int MainImage = intent.getIntExtra("Mainimage", 1);
                            intent.putExtras(bundle);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mContext.getApplicationContext().startActivity(intent);
                        }
                    });
                } else {
                    // Opening Detail Activity When
                    Intent intent = new Intent(mContext, DetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("Mainimage", gtst.getImage());
                    int MainImage = intent.getIntExtra("Mainimage", 1);
                    intent.putExtras(bundle);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.getApplicationContext().startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mGetterSetter.size();
    }

    public class RVViewHolder extends RecyclerView.ViewHolder {
        ImageView imge;
        InterstitialAd interstitialAdd;

        public RVViewHolder(@NonNull View itemView) {
            super(itemView);

            interstitialAdd = new InterstitialAd(mContext);
            interstitialAdd.setAdUnitId("ca-app-pub-4580368318288335/8308111539");
            interstitialAdd.loadAd(new AdRequest.Builder().build());
            MobileAds.initialize(mContext, "ca-app-pub-4580368318288335~5075443539");

            Log.d("Tone", "RecyclerView : ViewHolder Called");
            imge = (ImageView) itemView.findViewById(R.id.firstimg);
            Log.d("Tone", "RecyclerView : ViewHolder Set");


        }
    }
}