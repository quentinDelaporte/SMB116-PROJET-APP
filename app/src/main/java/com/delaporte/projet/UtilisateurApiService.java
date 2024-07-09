package com.delaporte.projet;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UtilisateurApiService {
    static OkHttpClient client = new OkHttpClient();
    static String url = "http://192.168.1.2:3000/utilisateur/";
    static String token = "3rG#TEdTO2NlzUEnEGAt#qr?2KD4BIt4n8TW1ZJSt8!bt!kRxaHQbP6F@moHkK8A50vfCCoAFqNvOR1r5O5ASWVOmw0p4UFX!p36aX@Eu#6m#D#v@PUfhH3Pdt7AYcejb8nV@JhNoUs3d#$l97Gf7lU!Hi4Gu5#U";

    public String login(String email, String password) {
        String json = "{\"ut_email\":\""+email+"\",\"ut_mot_passe\":\""+password+"\"}";

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), json);

        Request request = new Request.Builder()
                .url(url+"login")
                .post(body)
                .addHeader("token", token)
                .build();

        final String[] result = {"false"};
        final CountDownLatch latch = new CountDownLatch(1);

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    if (response.isSuccessful() && response.body() != null) {
                        String responseData = response.body().string();
                        result[0] = responseData;
                    } else {
                        result[0] = "false";
                    }
                } finally {
                    latch.countDown();
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("UtilisateurApiService", "Request failed: " + e.getMessage());
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Log.d("UtilisateurApiService", "Interrupted while waiting for the response: " + e.getMessage());
        }

        return result[0];
    }

    public static boolean register(String ut_email, String ut_mot_passe, String ut_nom_utilisateur, String ut_nom, String ut_prenom) {
        String json = "{\"ut_nom\":\""+ut_nom+"\",\"ut_prenom\":\""+ut_prenom+"\", \"ut_nom_utilisateur\":\""+ut_nom_utilisateur+"\", \"ut_email\":\""+ut_email+"\", \"ut_mot_passe\":\""+ut_mot_passe+"\"}";

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), json);

        Request request = new Request.Builder()
                .url(url+"register")
                .post(body)
                .addHeader("token", token)
                .build();

        final boolean[] result = {false};
        final CountDownLatch latch = new CountDownLatch(1);

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    if (response.isSuccessful() && response.body() != null) {
                        String responseData = response.body().string();
                        result[0] = !responseData.equals("false");
                    } else {
                        result[0] = false;
                    }
                } finally {
                    latch.countDown();
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("UtilisateurApiService", "Request failed: " + e.getMessage());
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Log.d("UtilisateurApiService", "Interrupted while waiting for the response: " + e.getMessage());
        }

        return result[0];
    }

    public static String getById(Integer ut_id) {
        Request request = new Request.Builder()
                .url(url+ut_id)
                .get()
                .addHeader("token", token)
                .build();
    Log.d("ut_id",ut_id+"");
        final String[] result = {"false"};
        final CountDownLatch latch = new CountDownLatch(1);

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    if (response.isSuccessful() && response.body() != null) {
                        result[0] = response.body().string();
                    } else {
                        result[0] = "false";
                    }
                } finally {
                    latch.countDown();
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("UtilisateurApiService", "Request failed: " + e.getMessage());
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Log.d("UtilisateurApiService", "Interrupted while waiting for the response: " + e.getMessage());
        }

        return result[0];
    }

    public static String getByUsername(String ut_username) {
        Request request = new Request.Builder()
                .url(url+"username/"+ut_username)
                .get()
                .addHeader("token", token)
                .build();
        Log.d("ut_username",ut_username);
        final String[] result = {"false"};
        final CountDownLatch latch = new CountDownLatch(1);

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    if (response.isSuccessful() && response.body() != null) {
                        result[0] = response.body().string();
                    } else {
                        result[0] = "false";
                    }
                } finally {
                    latch.countDown();
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("UtilisateurApiService", "Request failed: " + e.getMessage());
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Log.d("UtilisateurApiService", "Interrupted while waiting for the response: " + e.getMessage());
        }

        return result[0];
    }
}
