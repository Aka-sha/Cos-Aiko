package com.capstone.cos_aiko.remote;

import com.capstone.cos_aiko.model.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    // GET method to perform HTTP requests
    @GET("{email}/{password}")
    Call<ResponseBody> login(@Path("email") String email, @Path("password") String password);

    @POST("/newUser")
    Call<ResponseBody> createUser(@Body User user);
}
