package com.example.beverly.registrationdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    DatabaseHelper db;

    EditText regTextUsername, regTextPassw, regTextConfPassw;
    Button regBtnRegister;
    TextView txtLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);

        regTextUsername = (EditText) findViewById(R.id.regUsername);
        regTextPassw = (EditText) findViewById(R.id.regPassword);
        regTextConfPassw = (EditText) findViewById(R.id.regConfPassword);
        regBtnRegister = (Button) findViewById(R.id.buttonRegister);
        txtLogin = (TextView) findViewById(R.id.textSignin);

        onRegLogin();
        onRegister();
    }

    public void onRegLogin(){
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(Register.this,MainActivity.class);
                startActivity(loginIntent);
            }
        });
    }

    public void onRegister(){
        regBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reg_username = regTextUsername.getText().toString();
                String reg_passw = regTextPassw.getText().toString();
                String reg_confpassw = regTextConfPassw.getText().toString();

                if(reg_username.equals("")||reg_passw.equals("")||reg_confpassw.equals("")){
                    Toast.makeText(getApplicationContext(),"Fields can not be empty!",Toast.LENGTH_SHORT).show();
                }//end if
                else{
                    if (reg_passw.equals(reg_confpassw)){
                        Boolean chkUsername = db.chkUsername(reg_username);
                        if(chkUsername==true){
                            Boolean insert = db.insert(reg_username,reg_passw);
                            if(insert==true){
                                Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_SHORT).show();
                                Intent homeIntent = new Intent(Register.this,Homepage.class);
                                startActivity(homeIntent);
                            }//end if
                        }//end if
                        else{
                            Toast.makeText(getApplicationContext(),"Username already exists",Toast.LENGTH_SHORT).show();
                        }//end else
                    }//end if
                    else {
                        Toast.makeText(getApplicationContext(), "Password do not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
