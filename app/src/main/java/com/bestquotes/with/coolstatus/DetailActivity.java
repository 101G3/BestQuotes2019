
package com.bestquotes.with.coolstatus;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DetailActivity extends AppCompatActivity {


    ImageView Mainimg, Downloadimg, Likeimg, Shareimg;
    TextView DetailDownloadtxt, DetailLiketxt, DetailSharetxt;
    BitmapDrawable drawable;
    Bitmap bitmap1;
    String MainImage;
    BitmapDrawable drawable1;
    ImageView backimage;
    InterstitialAd interstitialAd;
    AdView adView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Mainimg = (ImageView) findViewById(R.id.DetailMainImage);
        Downloadimg = (ImageView) findViewById(R.id.DetailDownload_id);
        Likeimg = (ImageView) findViewById(R.id.DetailLike_id);
        Shareimg = (ImageView) findViewById(R.id.DetailShare_id);
        DetailDownloadtxt = (TextView) findViewById(R.id.DetailDonwloadDis_id);
        DetailLiketxt = (TextView) findViewById(R.id.DetailLikeDis_id);
        DetailSharetxt = (TextView) findViewById(R.id.DetailShareDis_id);
        backimage = (ImageView) findViewById(R.id.backimage_id);

        // InerstitialAd Coding for Back Button
        interstitialAd = new InterstitialAd(DetailActivity.this);
        interstitialAd.setAdUnitId("ca-app-pub-4580368318288335/8308111539");
        interstitialAd.loadAd(new AdRequest.Builder().build());
        MobileAds.initialize(DetailActivity.this, "ca-app-pub-4580368318288335~5075443539");
        // Back Button Ccode
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                    interstitialAd.setAdListener(new AdListener() {

                        @Override
                        public void onAdClosed() {
                            super.onAdClosed();

                            AdRequest adRequest = new AdRequest.Builder().build();
                            interstitialAd.loadAd(adRequest);
                            finish();
                            overridePendingTransition(0, 0);

                        }
                    });

                } else {

                    finish();
                    overridePendingTransition(0, 0);

                }

            }
        });


        // BannerAdView Coding
        // Adview Code
        adView = (AdView)findViewById(R.id.DetailBannerAdView_id);
        MobileAds.initialize(this, String.valueOf(R.string.AppAdID));
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);



        // Compress Image Size
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;

        Bundle bundle = this.getIntent().getExtras();
        MainImage = bundle.getString("MainImage");
        int DownloadImage = bundle.getInt("DownloadImage");
        int LikeImage = bundle.getInt("LikeImage");
        int ShareImage = bundle.getInt("ShareImage");
        MainImage = getIntent().getStringExtra("Mainimage");
        Picasso.get().load(MainImage).placeholder(R.drawable.loading).into(Mainimg);

        Downloadimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileOutputStream outputStream = null;
                drawable1 = (BitmapDrawable) Mainimg.getDrawable();
                bitmap1 = drawable1.getBitmap();
                File Sdcard = Environment.getExternalStorageDirectory();
                File Directory = new File(Sdcard.getAbsolutePath(), "/BestQuotes");
                Directory.mkdir();
                String filename = String.format("%d.jpg", System.currentTimeMillis());
                File outfile = new File(Directory, filename);
                Toast.makeText(DetailActivity.this, "Image Downloaded", Toast.LENGTH_LONG).show();
                try {
                    outputStream = new FileOutputStream(outfile);
                    bitmap1.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                    outputStream.flush();
                    outputStream.close();
                    Intent i = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    i.setData(Uri.fromFile(outfile));
                    sendBroadcast(i);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            ;
        });

        Likeimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    WallpaperManager wm = WallpaperManager.getInstance(DetailActivity.this);

                    wm.setBitmap(bitmap1);
                    Toast.makeText(DetailActivity.this, "Wallpapper Set",
                            Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(DetailActivity.this, "Please Download Image First",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


        Shareimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Picasso.get().load(MainImage).placeholder(R.drawable.loading).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("image/*");
                        intent.putExtra(Intent.EXTRA_STREAM, getLocalBitmapUri(bitmap));
                        startActivity(Intent.createChooser(intent, "Share Image"));
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });


            }
        });
    }

    private Uri getLocalBitmapUri(Bitmap bmp) {
        Uri uribmp = null;
        try {
            File file = new File(getBaseContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), "Share Image" + System.currentTimeMillis() + ".jpeg");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
            fileOutputStream.close();
            uribmp = Uri.fromFile(file);


        } catch (Exception e) {


        }


        return uribmp;
    }

    ;


}


