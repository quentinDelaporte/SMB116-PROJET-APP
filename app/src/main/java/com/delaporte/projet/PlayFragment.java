package com.delaporte.projet;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Collections;
import java.util.List;

public class PlayFragment extends Fragment {
    Button choix1Btn;
    Button choix2Btn;
    Button choix3Btn;
    Button choix4Btn;
    Button stopBtn;
    TextView questionTextView;
    String bonneReponse;
    Partie partie;
    static int categoryId =0;
    static int partyId =0;
    boolean answered=false;

    DownloadAndPlayAudioTask downloadAndPlayAudioTask =new DownloadAndPlayAudioTask();

    public PlayFragment(){

    }

    public static PlayFragment newInstance(int catId) {
        PlayFragment fragment = new PlayFragment();
        categoryId = catId;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play, container, false);
        String res = PartieApiService.create(1,categoryId);

        Gson gson = new Gson();
        partie = gson.fromJson(res, Partie.class);
        Log.d("partie", partie.toString());
        partyId = partie.getPa_id();
        choix1Btn = view.findViewById(R.id.choix1Btn);
        choix2Btn = view.findViewById(R.id.choix2Btn);
        choix3Btn = view.findViewById(R.id.choix3Btn);
        choix4Btn = view.findViewById(R.id.choix4Btn);
        stopBtn = view.findViewById(R.id.stopBtn);
        questionTextView = view.findViewById(R.id.questionTextView);

        List<Musique> listMusiques = partie.getMusiques();
        String audioFileName = partie.getMusiques().get(0).mu_nom_fichier;


        if (partie != null && listMusiques != null && listMusiques.size() >= 4) {
            bonneReponse = partie.getMusiques().get(0).ar_nom;
            Log.d("good answer", partie.getMusiques().get(0).ar_nom);

            Collections.shuffle(listMusiques);
            choix1Btn.setText(listMusiques.get(0).ar_nom);
            choix2Btn.setText(listMusiques.get(1).ar_nom);
            choix3Btn.setText(listMusiques.get(2).ar_nom);
            choix4Btn.setText(listMusiques.get(3).ar_nom);
        }

        downloadAndPlayAudioTask.execute("http://192.168.1.2:3000/musique/file/" + audioFileName);

        choix1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChoix1Clicked();
            }
        });

        choix2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChoix2Clicked();
            }
        });

        choix3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChoix3Clicked();
            }
        });

        choix4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChoix4Clicked();
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stopBtn.getText()=="stop"){
                    downloadAndPlayAudioTask.pauseAudio();
                    stopBtn.setText("start");
                } else {
                    downloadAndPlayAudioTask.resumeAudio();
                    stopBtn.setText("stop");
                }
            }
        });

        return view;
    }

    private void setBorder(Button b){
        /**GradientDrawable border = new GradientDrawable();
        border.setColor(Color.TRANSPARENT);
        border.setStroke(4, Color.CYAN);
        border.setCornerRadius(8);
        b.setBackground(border);*/
    }

    private void setBtnColor(){
        choix1Btn.setBackgroundColor(Color.RED);
        choix2Btn.setBackgroundColor(Color.RED);
        choix3Btn.setBackgroundColor(Color.RED);
        choix4Btn.setBackgroundColor(Color.RED);

        if(bonneReponse == choix1Btn.getText()){
            choix1Btn.setBackgroundColor(Color.GREEN);
        } else if(bonneReponse == choix2Btn.getText()){
            choix2Btn.setBackgroundColor(Color.GREEN);
        } else if(bonneReponse == choix3Btn.getText()){
            choix3Btn.setBackgroundColor(Color.GREEN);
        } else if(bonneReponse == choix4Btn.getText()){
            choix4Btn.setBackgroundColor(Color.GREEN);
        }
    }



    private void onChoix1Clicked() {
        if(!answered) {
            answered=true;
            setBorder(choix1Btn);
            setBtnColor();

            Log.d("rep", choix1Btn.getText() + " - " + bonneReponse);
            if (bonneReponse == choix1Btn.getText()) {
                addPointToScore();
                Toast.makeText(this.getContext(), "Bonne réponse", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this.getContext(), "Mauvaise réponse, la réponse était " + bonneReponse, Toast.LENGTH_SHORT).show();
            }
            downloadAndPlayAudioTask.stopAudio();
            new Handler().postDelayed(this::goToCategorie, 2000);
        }
    }

    private void onChoix2Clicked() {
        if(!answered) {
            answered=true;
            setBorder(choix2Btn);
            setBtnColor();

            Log.d("rep", choix2Btn.getText() + " - " + bonneReponse);
            if (bonneReponse == choix2Btn.getText()) {
                addPointToScore();
                Toast.makeText(this.getContext(), "Bonne réponse", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this.getContext(), "Mauvaise réponse, la réponse était " + bonneReponse, Toast.LENGTH_SHORT).show();
            }
            downloadAndPlayAudioTask.stopAudio();
            new Handler().postDelayed(this::goToCategorie, 2000);
        }
    }

    private void onChoix3Clicked() {
        if(!answered) {
            answered=true;
            setBorder(choix3Btn);
            setBtnColor();

            Log.d("rep", choix3Btn.getText() + " - " + bonneReponse);
            if (bonneReponse == choix3Btn.getText()) {
                addPointToScore();
                Toast.makeText(this.getContext(), "Bonne réponse", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this.getContext(), "Mauvaise réponse, la réponse était " + bonneReponse, Toast.LENGTH_SHORT).show();
            }
            downloadAndPlayAudioTask.stopAudio();
            new Handler().postDelayed(this::goToCategorie, 2000);
        }
    }

    private void onChoix4Clicked() {
        if(!answered) {
            answered=true;
            setBorder(choix4Btn);
            setBtnColor();

            Log.d("rep", choix4Btn.getText() + " - " + bonneReponse);
            if(bonneReponse==choix4Btn.getText()) {
                addPointToScore();
                Toast.makeText(this.getContext(), "Bonne réponse", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this.getContext(), "Mauvaise réponse, la réponse était " + bonneReponse, Toast.LENGTH_SHORT).show();
            }

            downloadAndPlayAudioTask.stopAudio();
            new Handler().postDelayed(this::goToCategorie, 2000);
        }
    }

    public void goToCategorie() {

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        CategorieListFragment categorieListFragment = CategorieListFragment.newInstance();
        choix1Btn.setVisibility(View.INVISIBLE);
        choix2Btn.setVisibility(View.INVISIBLE);
        choix3Btn.setVisibility(View.INVISIBLE);
        choix4Btn.setVisibility(View.INVISIBLE);
        questionTextView.setVisibility(View.INVISIBLE);
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, categorieListFragment)
                //.detach(this)
                //.attach(playFragment)
                .addToBackStack(null)
                .commit();
    }

    public void addPointToScore(){
        PartieApiService.updateScore(partyId, 1);
    }
}