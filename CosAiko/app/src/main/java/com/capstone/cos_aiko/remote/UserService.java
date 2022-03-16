package com.capstone.cos_aiko.remote;

import com.capstone.cos_aiko.model.User;
import com.capstone.cos_aiko.model.UserResponse;


import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    // GET method to retrieve user by email - for shared preferences
    @GET("users/email/{email}")
    Call<UserResponse> findByEmail(@Path("email") String email);

    // GET method to perform HTTP requests
    @GET("users/{email}/{password}")
    Call<UserResponse> login(@Path("email") String email, @Path("password") String password);

    @POST("users/register")
    Call<UserResponse> createUser(@Body User user);

    @GET("/users")
    Call<List<User>> getAllUsers();

}
