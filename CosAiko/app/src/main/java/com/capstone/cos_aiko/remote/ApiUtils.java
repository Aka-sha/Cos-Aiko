package com.capstone.cos_aiko.remote;

public class ApiUtils {
    // url to RESTful API
    public static final String BASE_URL = "http://75.191.240.81:8080/";

    public static UserService getUserService(){
        // create retrofit client
        return RetrofitClient.getClient(BASE_URL).create(UserService.class);
    }

}
