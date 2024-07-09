package com.delaporte.projet;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.mindrot.jbcrypt.BCrypt;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private EditText emailAddressInput;
    private EditText passwordInput;
    private Button logInButton;
    private Button signUpButton;
    private UtilisateurApiService utilisateurApi = new UtilisateurApiService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        emailAddressInput = findViewById(R.id.emailAddressInput);
        passwordInput = findViewById(R.id.passwordInput);
        logInButton = findViewById(R.id.logInButton);
        signUpButton = findViewById(R.id.SignUpbutton);

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loginUser() {
        String email = emailAddressInput.getText().toString();
        String password = passwordInput.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }
        String utilisateurConnectedJson = utilisateurApi.login(email, password);

        Gson gson = new Gson();
        Type listType = new TypeToken<List<Utilisateur>>() {}.getType();
        List<Utilisateur> utilisateurs = gson.fromJson(utilisateurConnectedJson, listType);

        if(!utilisateurs.isEmpty()){
            redirectToHomePage(utilisateurs.get(0).getUt_id());
        } else {
            Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void redirectToHomePage(Integer loggedUserId){
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        intent.putExtra("USER_ID", loggedUserId);
        Log.d("loggedUserIdHome", loggedUserId.toString());
        startActivity(intent);
    }
}
