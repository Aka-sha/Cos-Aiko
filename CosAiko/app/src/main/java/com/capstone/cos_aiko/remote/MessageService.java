package com.capstone.cos_aiko.remote;

import com.capstone.cos_aiko.model.MessageResponse;
import com.capstone.cos_aiko.model.User;
import com.capstone.cos_aiko.model.UserResponse;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface MessageService {


    @POST("/messages/betweentwo/{sender}/{receiver}")
    Call<List<MessageResponse>> getPreviousMessagesBetweenUsers(@Path("sender") String sender, @Path("receiver") String receiver);
}
