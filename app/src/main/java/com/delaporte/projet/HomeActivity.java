package com.delaporte.projet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnItemSelectedListener {
    private int userId;

    BottomNavigationView bottomNavigationView;
    CategorieListFragment categorieListFragment = new CategorieListFragment();
    FriendFragment friendFragment = new FriendFragment();
    ScoreHistoryFragment scoreHistoryFragment = new ScoreHistoryFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("USER_ID")) {
            userId = intent.getIntExtra("USER_ID", 0);

            Bundle bundle = new Bundle();
            bundle.putInt("USER_ID", userId);
            friendFragment.setArguments(bundle);
        } else {
            Log.e("HomeActivity", "USER_ID not found in intent");
            finish();
            return;
        }

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.jouer);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.jouer) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, categorieListFragment)
                    .commit();
            return true;
        } else if(item.getItemId()==R.id.scores) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, scoreHistoryFragment)
                        .commit();
                return true;
        } else if(item.getItemId()==R.id.friends) {
            friendFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, friendFragment)
                        .commit();
                return true;
        }
        return false;
    }
}
