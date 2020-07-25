package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sign_up);
        Button sign_up_button = findViewById(R.id.sign_up_sign_up_button);

        sign_up_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent myIntent = new Intent(SignUpActivity.this, SignInActivity.class);
                //myIntent.putExtra("key", value); //Optional parameters
                SignUpActivity.this.startActivity(myIntent);
            }
        });
    }
}
