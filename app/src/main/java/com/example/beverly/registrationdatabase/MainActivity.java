package com.example.beverly.registrationdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;

    EditText textUsername, textPassword;
    Button btnLogin;
    TextView txtRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);

        textUsername = (EditText) findViewById(R.id.editTextUser);
        textPassword = (EditText) findViewById(R.id.editTextPassword);
        btnLogin = (Button) findViewById(R.id.buttonLogin);
        txtRegister = (TextView) findViewById(R.id.textViewRegister);

        onLogin();
        onRegister();
    }

    public void onRegister(){
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent registerIntent = new Intent(MainActivity.this,Register.class);
                startActivity(registerIntent);
            }
        });
    }

    public void onLogin(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = textUsername.getText().toString();
                String passw = textPassword.getText().toString();

                Boolean ChkUserPassw = db.userpassw(username,passw);

                if(ChkUserPassw==true){
                    Toast.makeText(getApplicationContext(), "Successfully Logged in", Toast.LENGTH_SHORT).show();
                    Intent home = new Intent(MainActivity.this,Homepage.class);
                    startActivity(home);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Wrong email or password", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }



}
