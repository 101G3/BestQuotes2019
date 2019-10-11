
package com.bestquotes.with.coolstatus;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SliderScreen extends AppCompatActivity {

    public static int SPLASH_TIME = 4000;

    TabLayout slidertabLayout;
    ViewPager viewPager;
    SliderScreenViewPagerAdapter screenViewPagerAdapter;
    int position = 0;
    Button ButtonNext;
    Button buttonGetStarted;
Button SkipButton;
    // Touch Listener Screen




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider_screen);
        SkipButton = (Button)findViewById(R.id.btn_skip);
        slidertabLayout = (TabLayout)findViewById(R.id.sliderscreentablayout);
        viewPager = (ViewPager)findViewById(R.id.SliderscreenViewPager_id);
        buttonGetStarted = (Button)findViewById(R.id.btn_getstarted);
        ButtonNext = (Button)findViewById(R.id.btn_next);
        final List<SliderScreenItems> itemsList = new ArrayList<>();
        itemsList.add(new SliderScreenItems(R.drawable.download,"Free Download","Download Free Your Favorite Quotes"));
        itemsList.add(new SliderScreenItems(R.drawable.share,"Quick Share","Easily Share Your Favorite Quotes "));
        itemsList.add(new SliderScreenItems(R.drawable.window,"Set Wallpaper","Set Your Favorite Quote on Screen"));
        screenViewPagerAdapter = new SliderScreenViewPagerAdapter(SliderScreen.this,itemsList);
        viewPager.setAdapter(screenViewPagerAdapter);
        slidertabLayout.setupWithViewPager(viewPager);

        // skip button coding

        SkipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"Please Wait.......",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(SliderScreen.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Button Get Started

        buttonGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"Please Wait.......",Toast.LENGTH_LONG).show();
                Intent MainActivity = new Intent(SliderScreen.this,com.bestquotes.with.coolstatus.MainActivity.class);
                startActivity(MainActivity);
                finish();
            }
        });

        // Button Next

        ButtonNext.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public void onClick(View v) {
                position = viewPager.getCurrentItem();
                if (position<itemsList.size())
                {

                    position++;
                    viewPager.setCurrentItem(position);
                }
                if(position == itemsList.size()-1)
                {

                   ButtonNext.setVisibility(View.INVISIBLE);
                   buttonGetStarted.setVisibility(View.VISIBLE);
                    slidertabLayout.setVisibility(View.INVISIBLE);
                   SkipButton.setVisibility(View.INVISIBLE);
                    // Method For : Stop Scrolling of View Pager at the last pager
                    viewPager.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            return true;
                        }
                    });





                }



            }
        });

        slidertabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == itemsList.size()-1)
                {

                    ButtonNext.setVisibility(View.INVISIBLE);
                    buttonGetStarted.setVisibility(View.VISIBLE);
                    slidertabLayout.setVisibility(View.INVISIBLE);
                    SkipButton.setVisibility(View.INVISIBLE);

                    // Method For : Stop Scrolling of View Pager at the last pager
                    viewPager.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            return true;
                        }
                    });







                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });












    }

};


