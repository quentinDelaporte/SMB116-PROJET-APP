
package com.delaporte.projet;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
public class PartieApiService {
    static OkHttpClient client = new OkHttpClient();
    static String url = "http://192.168.1.2:3000/partie/";
    static String token = "3rG#TEdTO2NlzUEnEGAt#qr?2KD4BIt4n8TW1ZJSt8!bt!kRxaHQbP6F@moHkK8A50vfCCoAFqNvOR1r5O5ASWVOmw0p4UFX!p36aX@Eu#6m#D#v@PUfhH3Pdt7AYcejb8nV@JhNoUs3d#$l97Gf7lU!Hi4Gu5#U";

    public static String create(Integer ut_id, Integer ca_id) {
        String json = "{\"ut_id\":\""+ut_id+"\",\"ca_id\":\""+ca_id+"\"}";

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), json);

        Request request = new Request.Builder()
                .url(url+"new")
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
                Log.d("partieApiService", "Request failed: " + e.getMessage());
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Log.d("partieApiService", "Interrupted while waiting for the response: " + e.getMessage());
        }

        return result[0];
    }

    public static String createDefi(Integer ut_id, Integer ut_id_2, Integer ca_id) {
        String json = "{\"ut_id\":\""+ut_id+"\",\"ut_id_2\":\""+ut_id_2+"\",\"ca_id\":\""+ca_id+"\"}";

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), json);

        Request request = new Request.Builder()
                .url(url+"defi")
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
                Log.d("partieApiService", "Request failed: " + e.getMessage());
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Log.d("partieApiService", "Interrupted while waiting for the response: " + e.getMessage());
        }

        return result[0];
    }

    public static String updateScore(Integer pa_id, Integer score) {
        String json = "{\"pa_id\":"+pa_id+",\"score\":"+score+"}";

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), json);

        Request request = new Request.Builder()
                .url(url+"score")
                .put(body)
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
                Log.d("partieApiService", "Request failed: " + e.getMessage());
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Log.d("partieApiService", "Interrupted while waiting for the response: " + e.getMessage());
        }

        return result[0];
    }

    public static String getLastScore(Integer ut_id, Integer limit) {
        Request request = new Request.Builder()
                .url(url+"score/user/"+ut_id+"?limit="+limit)
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
                Log.d("partieApiService", "Request failed: " + e.getMessage());
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Log.d("partieApiService", "Interrupted while waiting for the response: " + e.getMessage());
        }

        return result[0];
    }


}
