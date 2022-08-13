package com.example.diarynotesuasakb;
/* Nama : Prasetyo Hade M. Winoto
   Kelas : IF-3
   NIM : 10119108
 */
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private EditText inputEmail, inputPassword;
    private Button btnRegister;
    private TextView btnLogin;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputEmail  = (EditText) findViewById(R.id.email);
        inputPassword = (EditText)  findViewById(R.id.password);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLogin = (TextView) findViewById(R.id.btnLogin);
        auth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)){
                    inputEmail.setError("Fill up email text field!");
                }else if(TextUtils.isEmpty(password)){
                    inputPassword.setError("Fill up password text field!");
                }else if (password.length() < 6){
                    inputPassword.setError("Password mush be up to 6 word!");
                }else {
                    auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this, "Register Success", Toast.LENGTH_SHORT).show();
                                finish();
                            }else {
                                Toast.makeText(RegisterActivity.this, "Register Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }
}