package com.example.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignIn extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sign_in);

        EditText username=findViewById(R.id.username_id);
        EditText password=findViewById(R.id.password_id);
        Button sign_in=findViewById(R.id.sign_in_button);
        Button sign_up=findViewById(R.id.sign_up_button);

        sign_in.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent myIntent = new Intent(SignIn.this, MainActivity.class);
                //myIntent.putExtra("key", value); //Optional parameters
                SignIn.this.startActivity(myIntent);
            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent myIntent = new Intent(SignIn.this, SignUp.class);
                //myIntent.putExtra("key", value); //Optional parameters
                SignIn.this.startActivity(myIntent);
            }
        });

    }
}
