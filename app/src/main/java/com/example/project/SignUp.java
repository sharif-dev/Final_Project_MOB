package com.example.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sign_up);
        Button sign_up_button = findViewById(R.id.sign_up_sign_up_button);

        sign_up_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent myIntent = new Intent(SignUp.this, SignIn.class);
                //myIntent.putExtra("key", value); //Optional parameters
                SignUp.this.startActivity(myIntent);
            }
        });
    }
}
