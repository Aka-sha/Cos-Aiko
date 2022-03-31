package com.capstone.cos_aiko;

import org.junit.Test;

import static org.junit.Assert.*;

import android.content.Intent;
import android.widget.Toast;

import com.capstone.cos_aiko.model.User;
import com.capstone.cos_aiko.remote.ApiUtils;
import com.capstone.cos_aiko.remote.UserService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        String usernameText = "garrett@123.com";
        String passwordText = "password1";
        String emailText = "garr@gmail.com";
        String fnameText = "garrett";
        String lnameText = "Dittrich";
        User user = new User(fnameText, lnameText, emailText, passwordText);

        UserService userService = ApiUtils.getUserService();
//        Call<ResponseBody> call = userService.login("bob", "jim");
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                // check if credentials matched (response code of 200 = success)
//                if (response.isSuccessful()) {
//                    // login successful
//                    System.out.println("SUCCESS");
//                } else { // response code 404 (no matching credentials)
//                    System.out.println("SUCCESS BUT WHATEVER");
//                }
//            }
//
//            @Override
//            public void onFailure(Call call, Throwable t) {
//                System.out.println("FAILURE");
//            }
//        });
        //assertEquals(4, 2 + 5);
    }
}