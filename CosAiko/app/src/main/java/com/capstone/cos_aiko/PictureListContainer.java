package com.capstone.cos_aiko;

import android.widget.ImageView;

public class PictureListContainer {
    private ImageView image;
    private ImageView xButton;
    private int index;
    public PictureListContainer(ImageView image, ImageView xButton, int index) {
        this.image = image;
        this.xButton = xButton;
        this.index = index;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public ImageView getxButton() {
        return xButton;
    }

    public void setxButton(ImageView xButton) {
        this.xButton = xButton;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
