package com.capstone.cos_aiko.remote;

public class ApiUtils {
    // url to RESTful API
    public static final String BASE_URL = "http://192.168.1.4:8080/users/";

    public static UserService getUserService(){
        return RetrofitClient.getClient(BASE_URL).create(UserService.class);
    }

}
