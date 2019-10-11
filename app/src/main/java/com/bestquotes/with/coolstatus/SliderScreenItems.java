package com.bestquotes.with.coolstatus;

public class SliderScreenItems
{
    int ScreenImg;
    String Title,Discription;

    public SliderScreenItems(int screenImg, String title, String discription) {
        ScreenImg = screenImg;
        Title = title;
        Discription = discription;
    }

    public int getScreenImg() {

        return ScreenImg;
    }

    public void setScreenImg(int screenImg) {
        ScreenImg = screenImg;
    }
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDiscription() {
        return Discription;
    }

    public void setDiscription(String discription) {
        Discription = discription;
    }
}
