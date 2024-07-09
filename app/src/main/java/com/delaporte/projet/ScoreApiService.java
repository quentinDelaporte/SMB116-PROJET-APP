
package com.delaporte.projet;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ScoreApiService {
    static OkHttpClient client = new OkHttpClient();
    static String url = "http://192.168.1.2:3000/score/";
    static String token = "3rG#TEdTO2NlzUEnEGAt#qr?2KD4BIt4n8TW1ZJSt8!bt!kRxaHQbP6F@moHkK8A50vfCCoAFqNvOR1r5O5ASWVOmw0p4UFX!p36aX@Eu#6m#D#v@PUfhH3Pdt7AYcejb8nV@JhNoUs3d#$l97Gf7lU!Hi4Gu5#U";

    public static String byCategorie() {
        Request request = new Request.Builder()
                .url(url+"categorie")
                .get()
                .addHeader("token", token)
                .build();

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
                Log.d("ScoreApiService", "Request failed: " + e.getMessage());
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

    public static String byArtiste() {
        Request request = new Request.Builder()
                .url(url+"artiste")
                .get()
                .addHeader("token", token)
                .build();

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
                Log.d("ScoreApiService", "Request failed: " + e.getMessage());
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
