package com.bestquotes.with.coolstatus;

public class MenuGetterSetter
{
    private String Text;
    private int img;
    private int layout;


    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public MenuGetterSetter(String text, int img, int layout) {

        Text = text;
        this.img = img;
        this.layout = layout;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
