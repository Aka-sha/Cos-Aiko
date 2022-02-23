package com.capstone.cos_aiko.remote;

import com.capstone.cos_aiko.model.User;
import com.capstone.cos_aiko.model.UserResponse;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    // GET method to perform HTTP requests
    @GET("users/{email}/{password}")
    Call<ResponseBody> login(@Path("email") String email, @Path("password") String password);

    // POST method to create new user
    @POST("users/register")
    Call<UserResponse> createUser(@Body User user);
}
