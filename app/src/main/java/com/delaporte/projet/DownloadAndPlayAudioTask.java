package com.delaporte.projet;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadAndPlayAudioTask extends AsyncTask<String, Void, File> {
    private MediaPlayer mediaPlayer;
    private OkHttpClient client = new OkHttpClient();

    @Override
    protected File doInBackground(String... urls) {
        String url = urls[0];
        Log.d("DownloadAudioTask", "Downloading audio from URL: " + url);
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                Log.e("DownloadAudioTask", "Unexpected code " + response);
                throw new IOException("Unexpected code " + response);
            }

            File outputDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "MyApp");
            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }

            File outputFile = new File(outputDir, "downloaded_audio.mp3");

            try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                fos.write(response.body().bytes());
                Log.d("DownloadAudioTask", "Audio downloaded to " + outputFile.getAbsolutePath());
            }

            return outputFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(File result) {
        if (result != null && result.exists() && result.length() > 0) {
            Log.d("DownloadAudioTask", "Downloaded file exists and is not empty: " + result.getAbsolutePath());
            playAudio(result);
        } else {
            Log.e("DownloadAudioTask", "Failed to download audio or file is empty");
        }
    }

    private void playAudio(File audioFile) {
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(audioFile.getAbsolutePath());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopAudio() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void pauseAudio() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public void resumeAudio() {
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }
}
