package com.delaporte.projet;

import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ScoreHistoryFragment extends Fragment {
    public ScoreHistoryFragment() {}

    TextView winstreakTextview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_score_history, container, false);

        String res = ScoreApiService.byCategorie();
        Log.d("SCORE", res);

        Type listType = new TypeToken<ArrayList<Score>>() {}.getType();
        List<Score> scores = new Gson().fromJson(res, listType);

        BarChart barChart = rootView.findViewById(R.id.barChart);

        ArrayList<BarEntry> entries1 = new ArrayList<>();
        ArrayList<BarEntry> entries2 = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();

        for (int i = 0; i < scores.size(); i++) {
            Score score = scores.get(i);
            if (score.getCa_libelle() != null && score.getPartie_joue() > 0) {
                float winRate = (float) score.getScore() / score.getPartie_joue();
                float loseRate = 1 - winRate;

                entries1.add(new BarEntry(i, winRate));
                entries2.add(new BarEntry(i, loseRate));
                labels.add(score.getCa_libelle());
            }
        }

        BarDataSet set1 = new BarDataSet(entries1, "Bonne réponse");
        set1.setColor(Color.rgb(60, 220, 78));
        set1.setValueTextColor(Color.rgb(60, 220, 78));
        set1.setValueTextSize(10f);

        BarDataSet set2 = new BarDataSet(entries2, "Mauvaise réponse");
        set2.setColor(Color.rgb(220, 60, 78));
        set2.setValueTextColor(Color.rgb(220, 60, 78));
        set2.setValueTextSize(10f);

        BarData data = new BarData(set1, set2);
        float groupSpace = 0.06f;
        float barSpace = 0.02f;
        float barWidth = 0.45f;
        data.setBarWidth(barWidth);

        barChart.setData(data);
        barChart.getXAxis().setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                int index = (int) value;
                if (index >= 0 && index < labels.size()) {
                    return labels.get(index);
                } else {
                    return "";
                }
            }
        });

        barChart.groupBars(0, groupSpace, barSpace); // start at x = 0
        barChart.invalidate(); // refresh

        //winstreakTextview = getView().findViewById(R.id.winstreak_textview);

        return rootView;
    }
}
