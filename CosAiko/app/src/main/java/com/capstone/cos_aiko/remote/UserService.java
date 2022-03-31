package com.capstone.cos_aiko.remote;

/*
 * This interface has HTTP request methods used to send/retrieve data from/to
 * a REST API that has endpoints into the users table
 * in our MySQL database
 */

import com.capstone.cos_aiko.model.User;
import com.capstone.cos_aiko.model.UserResponse;


import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface UserService {

    /**
     * GET method to retrieve user by email - for shared preferences
     *
     * @param email email key to retrieve user by unique email
     * @return Http Response from REST API
     */
    @GET("users/email/{email}")
    Call<UserResponse> findByEmail(@Path("email") String email);

    /**
     * GET method to match email-password combination
     *
     * @param email email
     * @param password password
     * @return Http Response from REST API
     */
    @GET("users/{email}/{password}")
    Call<UserResponse> login(@Path("email") String email, @Path("password") String password);

    /**
     * POST method to  create new user account
     *
     * @param user new user
     * @return Http Response from REST API
     */
    @POST("users/register")
    Call<UserResponse> createUser(@Body User user);

    /**
     * Get all current users of CosAiko
     *
     * @return Http Response from REST API
     */
    @GET("/users")
    Call<List<User>> getAllUsers();
    
    @GET("/users")
    Call<List<UserResponse>> getAllUsersReponse();

    // PUT method to update user profile image

    /**
     * PUT method to update user profile image
     *
     * @param img
     * @param email
     * @return Http Response from REST API
     */
    @PUT("users/updateProfileImage/{email}")
    @Multipart
    Call<UserResponse> updateImage(@Part MultipartBody.Part img, @Path("email") String email);

}
