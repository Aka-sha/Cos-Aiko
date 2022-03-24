package com.capstone.cos_aiko.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.capstone.cos_aiko.R;
import com.capstone.cos_aiko.model.User;
import com.capstone.cos_aiko.model.UserResponse;
import com.capstone.cos_aiko.remote.ApiUtils;
import com.capstone.cos_aiko.remote.UserService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageCardAdapter extends BaseAdapter {
    private ArrayList<UserResponse> userData;
    private Context context;
    UserService userService = ApiUtils.getUserService();
    public ImageCardAdapter(ArrayList<UserResponse> userList, Context context){
        this.context = context;
        this.userData = userList;
    }

    @Override
    public int getCount() {
        return userData.size();
    }

    @Override
    public Object getItem(int i) {
        return userData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = view;
        if (v == null){
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        }
        ImageView swipeCardProfileImage = v.findViewById(R.id.image);
        TextView t = v.findViewById(R.id.helloText);
        t.setText(userData.get(i).getFName());

        if (userData.get(i).getImage() != null){
            // decode string
            byte[] imageBytes = Base64.decode(userData.get(i).getImage(), Base64.DEFAULT);
            // create bitmap for image
            Bitmap bmp = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

            swipeCardProfileImage.setImageBitmap(bmp);
        }
        return v;
    }
}
