package com.delaporte.projet;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class SignupActivity extends AppCompatActivity {

    private EditText nameInput;
    private EditText surnameInput;
    private EditText emailInput;
    private EditText usernameInput;
    private EditText passwordInput;
    private Button signupButton;
    private Button redirectToLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        nameInput = findViewById(R.id.nameInput);
        surnameInput = findViewById(R.id.surnameInput);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        usernameInput = findViewById(R.id.usernameInput);
        signupButton = findViewById(R.id.signupButton);
        redirectToLoginBtn = findViewById(R.id.redirectToLoginBtn);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("register", "click");
                signUpUser();
            }
        });

        redirectToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToLogin();
            }
        });
    }

    private void returnToLogin(){
        finish();
    }

    private void signUpUser() {
        String name = nameInput.getText().toString();
        String surname = surnameInput.getText().toString();
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        String username = usernameInput.getText().toString();

        if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty() || username.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        //String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());


        try {
            Log.d("register", "register");
            UtilisateurApiService.register(email, password,username,name, surname);
            Toast.makeText(this, "Inscription réussie", Toast.LENGTH_SHORT).show();
            finish();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Erreur lors de l'inscription", Toast.LENGTH_SHORT).show();
        }
    }
}
