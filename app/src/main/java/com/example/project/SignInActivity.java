package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {

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
                Intent myIntent = new Intent(SignInActivity.this, MainActivity.class);
                //myIntent.putExtra("key", value); //Optional parameters
                SignInActivity.this.startActivity(myIntent);
            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent myIntent = new Intent(SignInActivity.this, SignUpActivity.class);
                //myIntent.putExtra("key", value); //Optional parameters
                SignInActivity.this.startActivity(myIntent);
            }
        });

    }
}
