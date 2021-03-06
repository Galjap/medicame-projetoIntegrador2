package com.example.android.pi2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = findViewById(R.id.btnLogin);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        Button c = findViewById(R.id.btnCadastro);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastro();
            }
        });

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        if (user != null){
            Intent it = new Intent(MainActivity.this, Redirect.class);
            startActivity(it);
            finish();
        }
    }

    private void cadastro(){
        startActivity(new Intent(MainActivity.this, Cadastro.class));
    }



    private void login(){

        auth = FirebaseAuth.getInstance();



        EditText edtEmail = findViewById(R.id.edtEmail);
        EditText edtPassword = findViewById(R.id.edtPassword);

        Task<AuthResult> processo = auth.signInWithEmailAndPassword(edtEmail.getText().toString(), edtPassword.getText().toString());
        processo.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Intent it = new Intent(MainActivity.this, Redirect.class);
                    startActivity(it);
                }else {
                    Toast.makeText(MainActivity.this, "Email ou senha invpalidos", Toast.LENGTH_LONG).show();
                }


            }
        });

        //teste


    }


    }

