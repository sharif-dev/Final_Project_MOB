package com.example.project;


import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class SignInActivity extends AppCompatActivity {

    EditText username,password;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sign_in);


        username=findViewById(R.id.username_id);
        password=findViewById(R.id.password_id);
        Button sign_in=findViewById(R.id.sign_in_button);
        Button sign_up=findViewById(R.id.sign_up_button);



        sign_in.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                if (TextUtils.isEmpty(username.getText())) {
                    username.setError("First name is required!");
                }
                else if (TextUtils.isEmpty(password.getText())){
                    password.setError("password  is required!");

                }
                else{
                    ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
                        @Override
                        public void done(ParseUser parseUser, ParseException e) {
                            if (parseUser != null) {
                                Toast.makeText(SignInActivity.this,"welcome back!", Toast.LENGTH_LONG).show();
                                Intent myIntent = new Intent(SignInActivity.this, MainActivity.class);
                                //myIntent.putExtra("key", value); //Optional parameters
                                SignInActivity.this.startActivity(myIntent);
                            } else {
                                ParseUser.logOut();
                                Toast.makeText(SignInActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }



                    });
                }

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
