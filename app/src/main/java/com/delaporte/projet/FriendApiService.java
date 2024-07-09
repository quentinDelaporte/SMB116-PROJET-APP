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

public class FriendApiService {

    static OkHttpClient client = new OkHttpClient();
    static String url = "http://192.168.1.2:3000/ami/";
    static String token = "3rG#TEdTO2NlzUEnEGAt#qr?2KD4BIt4n8TW1ZJSt8!bt!kRxaHQbP6F@moHkK8A50vfCCoAFqNvOR1r5O5ASWVOmw0p4UFX!p36aX@Eu#6m#D#v@PUfhH3Pdt7AYcejb8nV@JhNoUs3d#$l97Gf7lU!Hi4Gu5#U";

    public static String getById(Integer id) {
        Log.d("cat", url+id);

        Request request = new Request.Builder()
                .url(url+id)
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
                } catch (IOException e) {
                    Log.e("FriendApiService", "Error reading response body: " + e.getMessage(), e);
                } finally {
                    response.close();
                    latch.countDown();
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("FriendApiService", "Request failed: " + e.getMessage());
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Log.d("FriendApiService", "Interrupted while waiting for the response: " + e.getMessage());
        }

        return result[0];
    }

    public static String insert(Integer ut_id, Integer ut_id_1) {
        String json = "{\"ut_id\":" + ut_id + ",\"ut_id_1\":" + ut_id_1 + "}";

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), json);

        Request request = new Request.Builder()
                .url(url)
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
                } catch (IOException e) {
                    Log.e("FriendApiService", "Error reading response body: " + e.getMessage(), e);
                } finally {
                    response.close();
                    latch.countDown();
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("FriendApiService", "Request failed: " + e.getMessage());
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Log.d("FriendApiService", "Interrupted while waiting for the response: " + e.getMessage());
        }

        return result[0];
    }

    public static String valider(Integer ut_id, Integer ut_id_1) {
        String json = "{\"ut_id\":" + ut_id + ",\"ut_id_1\":" + ut_id_1 + "}";

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), json);

        Request request = new Request.Builder()
                .url(url + "accepter")
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
                } catch (IOException e) {
                    Log.e("FriendApiService", "Error reading response body: " + e.getMessage(), e);
                } finally {
                    response.close();
                    latch.countDown();
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("FriendApiService", "Request failed: " + e.getMessage());
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Log.d("FriendApiService", "Interrupted while waiting for the response: " + e.getMessage());
        }

        return result[0];
    }

    public static Boolean delete(Integer ut_id, Integer ut_id_1) {
        String json = "{\"ut_id\":" + ut_id + ",\"ut_id_1\":" + ut_id_1 + "}";

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), json);

        Request request = new Request.Builder()
                .url(url + "")
                .delete(body)
                .addHeader("token", token)
                .build();

        final Boolean[] result = {false};
        final CountDownLatch latch = new CountDownLatch(1);

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful() && response.body() != null) {
                        result[0] = true;
                    } else {
                        result[0] = false;
                    }

                    response.close();
                    latch.countDown();
            }

            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("FriendApiService", "Request failed: " + e.getMessage());
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Log.d("FriendApiService", "Interrupted while waiting for the response: " + e.getMessage());
        }

        return result[0];
    }
}
