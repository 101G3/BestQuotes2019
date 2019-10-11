package com.bestquotes.with.coolstatus;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.List;


public class MenuRecyclerViewAdapter extends RecyclerView.Adapter<MenuRecyclerViewAdapter.ViewH> {
    private Context mContext;
    private List<MenuGetterSetter> menuGetterSetters;
    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);


    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        itemClickListener = listener;

    }

    public MenuRecyclerViewAdapter(Context mContext, List<MenuGetterSetter> MmenuGetterSetter) {
        this.mContext = mContext;
        this.menuGetterSetters = MmenuGetterSetter;
    }

    @NonNull
    @Override
    public ViewH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.menulistlayout, viewGroup, false);
        ViewH viewHolder = new ViewH(v, itemClickListener);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewH viewH, final int i) {
        final MenuGetterSetter Menu = menuGetterSetters.get(i);
        viewH.txt.setText(Menu.getText());
        viewH.img.setImageDrawable(mContext.getResources().getDrawable(Menu.getImg()));
        viewH.RL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                switch (i) {
                    case 0: {
                        if (viewH.interstitialAdd.isLoaded()) {
                            viewH.interstitialAdd.show();
                            viewH.interstitialAdd.setAdListener(new AdListener() {
                                @Override
                                public void onAdClosed() {
                                    super.onAdClosed();
                                    AdRequest adRequest = new AdRequest.Builder().build();
                                    viewH.interstitialAdd.loadAd(adRequest);
                                    Intent i = new Intent(mContext, SmileActivity.class);
                                    mContext.startActivity(i);

                                }
                            });


                        }
                        else
                        {
                            Intent i = new Intent(mContext, SmileActivity.class);
                            mContext.startActivity(i);
                        }



                    }
                    break;
                    case 1: {
                        if (viewH.interstitialAdd.isLoaded()) {
                            viewH.interstitialAdd.show();
                            viewH.interstitialAdd.setAdListener(new AdListener() {
                                @Override
                                public void onAdClosed() {
                                    super.onAdClosed();
                                    AdRequest adRequest = new AdRequest.Builder().build();
                                    viewH.interstitialAdd.loadAd(adRequest);
                                    Intent i = new Intent(mContext, AttitudeActivity.class);
                                    mContext.startActivity(i);


                                }
                            });


                        } else {


                            Intent i = new Intent(mContext, AttitudeActivity.class);
                            mContext.startActivity(i);

                        }
                    }
                    break;
                    case 2: {
                        if (viewH.interstitialAdd.isLoaded()) {
                            viewH.interstitialAdd.show();
                            viewH.interstitialAdd.setAdListener(new AdListener() {
                                @Override
                                public void onAdClosed() {
                                    super.onAdClosed();
                                    AdRequest adRequest = new AdRequest.Builder().build();
                                    viewH.interstitialAdd.loadAd(adRequest);
                                    Intent i = new Intent(mContext, ConfidenceActivity.class);
                                    mContext.startActivity(i);


                                }
                            });


                        } else {


                            Intent i = new Intent(mContext, ConfidenceActivity.class);
                            mContext.startActivity(i);

                        }
                    }
                    break;
                    case 3: {
                        if (viewH.interstitialAdd.isLoaded()) {
                            viewH.interstitialAdd.show();
                            viewH.interstitialAdd.setAdListener(new AdListener() {
                                @Override
                                public void onAdClosed() {
                                    super.onAdClosed();
                                    AdRequest adRequest = new AdRequest.Builder().build();
                                    viewH.interstitialAdd.loadAd(adRequest);
                                    Intent i = new Intent(mContext, LoveActivity.class);
                                    mContext.startActivity(i);


                                }
                            });


                        } else {


                            Intent i = new Intent(mContext, LoveActivity.class);
                            mContext.startActivity(i);

                        }
                    }
                    break;
                    case 4: {
                        if (viewH.interstitialAdd.isLoaded()) {
                            viewH.interstitialAdd.show();
                            viewH.interstitialAdd.setAdListener(new AdListener() {
                                @Override
                                public void onAdClosed() {
                                    super.onAdClosed();
                                    AdRequest adRequest = new AdRequest.Builder().build();
                                    viewH.interstitialAdd.loadAd(adRequest);
                                    Intent i = new Intent(mContext, SadActivity.class);
                                    mContext.startActivity(i);


                                }
                            });


                        } else {


                            Intent i = new Intent(mContext, SadActivity.class);
                            mContext.startActivity(i);

                        }
                    }
                    break;
                    case 5: {
                        if (viewH.interstitialAdd.isLoaded()) {
                            viewH.interstitialAdd.show();
                            viewH.interstitialAdd.setAdListener(new AdListener() {
                                @Override
                                public void onAdClosed() {
                                    super.onAdClosed();
                                    AdRequest adRequest = new AdRequest.Builder().build();
                                    viewH.interstitialAdd.loadAd(adRequest);
                                    Intent i = new Intent(mContext, FaliureActivity.class);
                                    mContext.startActivity(i);


                                }
                            });


                        } else {


                            Intent i = new Intent(mContext, FaliureActivity.class);
                            mContext.startActivity(i);

                        }
                    }
                    break;
                    case 6: {
                        if (viewH.interstitialAdd.isLoaded()) {
                            viewH.interstitialAdd.show();
                            viewH.interstitialAdd.setAdListener(new AdListener() {
                                @Override
                                public void onAdClosed() {
                                    super.onAdClosed();
                                    AdRequest adRequest = new AdRequest.Builder().build();
                                    viewH.interstitialAdd.loadAd(adRequest);
                                    Intent i = new Intent(mContext, MotivationalActivity.class);
                                    mContext.startActivity(i);


                                }
                            });


                        } else {


                            Intent i = new Intent(mContext, MotivationalActivity.class);
                            mContext.startActivity(i);

                        }
                    }
                    break;
                    case 7: {
                        if (viewH.interstitialAdd.isLoaded()) {
                            viewH.interstitialAdd.show();
                            viewH.interstitialAdd.setAdListener(new AdListener() {
                                @Override
                                public void onAdClosed() {
                                    super.onAdClosed();
                                    AdRequest adRequest = new AdRequest.Builder().build();
                                    viewH.interstitialAdd.loadAd(adRequest);
                                    Intent i = new Intent(mContext, HappinessActivity.class);
                                    mContext.startActivity(i);


                                }
                            });


                        } else {


                            Intent i = new Intent(mContext, HappinessActivity.class);
                            mContext.startActivity(i);

                        }
                    }


                }


            }
        });


    }

    @Override
    public int getItemCount() {
        return menuGetterSetters.size();
    }


    class ViewH extends RecyclerView.ViewHolder {
        TextView txt;
        ImageView img;
        RelativeLayout RL;
        InterstitialAd interstitialAdd;

        public ViewH(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            interstitialAdd = new InterstitialAd(mContext);
            interstitialAdd.setAdUnitId("ca-app-pub-4580368318288335/8308111539");
            interstitialAdd.loadAd(new AdRequest.Builder().build());
            MobileAds.initialize(mContext, "ca-app-pub-4580368318288335~5075443539");


            txt = (TextView) itemView.findViewById(R.id.TextView_list_id);
            img = (ImageView) itemView.findViewById(R.id.ImgaeView_List_id);
            RL = (RelativeLayout) itemView.findViewById(R.id.menulayout_id);


        }
    }

    ;

}




