package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import javax.xml.namespace.QName;

public class SignUpActivity extends AppCompatActivity {

    EditText name,userName,password,repeatPassword,email,PhoneNumber;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);


        name=(EditText)findViewById(R.id.name_id);
        userName=(EditText)findViewById(R.id.user_id);
        password=(EditText)findViewById(R.id.password);
        repeatPassword=(EditText)findViewById(R.id.repass);
        email=(EditText)findViewById(R.id.email);
        PhoneNumber=(EditText)findViewById(R.id.phone_id);


        Button sign_up_button = findViewById(R.id.sign_up_sign_up_button);

        sign_up_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                if (TextUtils.isEmpty(name.getText())) {
                    name.setError("First name is required!");
                }
                else if (TextUtils.isEmpty(userName.getText())){
                    userName.setError("user name  is required!");
                }
                else if (TextUtils.isEmpty(password.getText())) {
                    password.setError("password  is required!");
                }
                else if (TextUtils.isEmpty(repeatPassword.getText())) {
                    repeatPassword.setError("confirm password  is required!");
                }
                else if (TextUtils.isEmpty(email.getText())) {
                    email.setError("email  is required!");
                }
                else if (TextUtils.isEmpty(PhoneNumber.getText())) {
                    PhoneNumber.setError("phone number  is required!");
                }
//                else if (!password.getText().toString().equals(repeatPassword.getText().toString())){
//                    PhoneNumber.setError("password and repeat not same!");
//                }
                else{

                    ParseUser user = new ParseUser();
                    user.setEmail(email.getText().toString().trim());
                    user.setUsername(userName.getText().toString().trim());
                    user.setPassword(password.getText().toString());
//                    user.put("phone number",PhoneNumber.getText().toString().trim());
                    user.put("name",name.getText().toString().trim());
                    user.saveInBackground();
                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                Toast.makeText(SignUpActivity.this,"welcome", Toast.LENGTH_LONG).show();
                                System.out.println("Yessssssssss");
                                Intent myIntent = new Intent(SignUpActivity.this, SignInActivity.class);
                                //myIntent.putExtra("key", value); //Optional parameters
                                SignUpActivity.this.startActivity(myIntent);


                            } else {
                                ParseUser.logOut();
                                System.out.println("Noooooooooooooooo");
                                Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }

            }
        });
    }





}
