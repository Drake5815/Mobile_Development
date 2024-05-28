package com.example.mobiledevelopment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private Group loginGroup;
    private Group registerGroup;
    private Button btnScrnLogin;
    private Button btnScrnRegister;
    private Button btnCreate;
    private Button btnOTP;
    private Button Back;
    private Button Register;
    private Button Login;
    private EditText txtLgnEmail;
    private EditText txtPassword;
    private EditText txtEmail;
    private EditText txtUsernameReg;
    private EditText txtPasswordReg;
    private EditText txtConPasswordReg;
    private LinearLayout linOTP;
    private LinearLayout linButtons;
    private FirebaseAuth mAuth;
    private void Initialize(){
        //FireBase
        mAuth = FirebaseAuth.getInstance();
        //Group
        loginGroup = findViewById(R.id.grpLogin);
        registerGroup = findViewById(R.id.grpRegister);
        //Buttons
        btnScrnLogin = findViewById(R.id.btnScrnLogin);
        btnScrnRegister = findViewById(R.id.btnScrnRegister);
        btnCreate = findViewById(R.id.btnCreate);
        btnOTP = findViewById(R.id.btnForgetPassword);
        Back = findViewById(R.id.btnBack);
        Register = findViewById(R.id.btnTabReg_Register);
        Login = findViewById(R.id.btnLogin);
        //TextBox
        txtLgnEmail = (EditText)findViewById(R.id.txtEmailLgn);
        txtPassword = (EditText)findViewById(R.id.txtPassword);
        txtEmail = (EditText)findViewById(R.id.txtEmail);
        txtUsernameReg = (EditText)findViewById(R.id.txtUsernameReg);
        txtPasswordReg = (EditText)findViewById(R.id.txtPasswordReg);
        txtConPasswordReg = (EditText)findViewById(R.id.txtConPasswordReg);
        //Linear Layout
        linOTP = findViewById(R.id.linForgetPass);
        linButtons = findViewById(R.id.Buttons);
    }

    @SuppressLint("NotConstructor")
    private void Login(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Logged-In", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, HomePage.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(this, "Credentials does not match with Database", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void Register(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Account has been Registered", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Something went wrong please retry", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void Button(){
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtLgnEmail.getText().toString() == null || txtPassword.getText().toString() == null){
                    Toast.makeText(Login.this, "Please put insert missing information", Toast.LENGTH_SHORT).show();
                } else {
                    Login(txtLgnEmail.getText().toString(), txtPassword.getText().toString());
                }
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtEmail.getText().toString() == null || txtPasswordReg.getText().toString() == null || txtConPasswordReg.getText().toString() == null){
                    Toast.makeText(Login.this, "Please fill-in any lacking Information", Toast.LENGTH_SHORT).show();
                } else {
                    if(!txtPasswordReg.getText().toString().equals(txtConPasswordReg.getText().toString())){
                        Toast.makeText(Login.this, "Password does not match!", Toast.LENGTH_SHORT).show();
                    } else {
                        Register(txtLgnEmail.getText().toString(), txtPassword.getText().toString());
                    }
                }
            }
        });
        btnScrnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginGroup.setVisibility(View.VISIBLE);
                btnScrnLogin.setTextColor(getResources().getColor(R.color.WhiteText));
                registerGroup.setVisibility(View.GONE);
                btnScrnRegister.setTextColor(getResources().getColor(R.color.GreyText));
            }
        });
        btnScrnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginGroup.setVisibility(View.GONE);
                btnScrnLogin.setTextColor(getResources().getColor(R.color.GreyText));
                registerGroup.setVisibility(View.VISIBLE);
                btnScrnRegister.setTextColor(getResources().getColor(R.color.WhiteText));
            }
        });
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginGroup.setVisibility(View.GONE);
                btnScrnLogin.setTextColor(getResources().getColor(R.color.GreyText));
                registerGroup.setVisibility(View.VISIBLE);
                btnScrnRegister.setTextColor(getResources().getColor(R.color.WhiteText));
            }
        });

        btnOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginGroup.setVisibility(View.GONE);
                linButtons.setVisibility(View.GONE);
                linOTP.setVisibility(View.VISIBLE);
            }
        });
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginGroup.setVisibility(View.VISIBLE);
                linButtons.setVisibility(View.VISIBLE);
                linOTP.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Initialize();
        Button();



    }
}