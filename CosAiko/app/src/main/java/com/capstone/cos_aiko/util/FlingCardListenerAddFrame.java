package com.capstone.cos_aiko.util;

import android.view.View;

import com.lorentzos.flingswipe.FlingCardListener;

public class FlingCardListenerAddFrame extends FlingCardListener {
    private View frame;
    public FlingCardListenerAddFrame(View frame, Object itemAtPosition, FlingListener flingListener) {
        super(frame, itemAtPosition, flingListener);
    }

    public FlingCardListenerAddFrame(View frame, Object itemAtPosition, float rotation_degrees, FlingListener flingListener) {
        super(frame, itemAtPosition, rotation_degrees, flingListener);
    }

    public View getFrame(){
        return this.frame;
    }

}
