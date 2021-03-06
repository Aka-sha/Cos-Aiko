package com.capstone.cos_aiko.remote;

/*
    This class sets the BASE_URL for the asynchronous REST Api calls
 */

import com.capstone.cos_aiko.config.Constants;

public class ApiUtils {
    // url to RESTful API

    //public static final String BASE_URL = "http://152.13.76.10:8080/";
    public static final String BASE_URL = "http://" + Constants.address + ":8080/";
    //public static final String  BASE_URL = "http://192.168.56.1:8080";
    //public static final String BASE_URL = "http://152.13.76.10:8080/";
    //public static final String BASE_URL = "http://192.168.1.2:8080/"; //akasha


    /**
     * This function initializes an RetofitClient object with the BASE_URL
     *
     * @return the initialized RetrofitClient
     */
    public static UserService getUserService() {
        // create retrofit client
        return RetrofitClient.getClient(BASE_URL).create(UserService.class);
    }

}
