package com.example.mobiledevelopment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.HashMap;

public class Login extends AppCompatActivity {

    private Group loginGroup;
    private Group registerGroup;
    private Button btnScrnLogin;
    private Button btnScrnRegister;
    private Button btnCreate;
    private Button btnOTP;
    private Button Back;
    private LinearLayout linOTP;
    private LinearLayout linButtons;

    private void Initialize(){
        //Group
        loginGroup = findViewById(R.id.grpLogin);
        registerGroup = findViewById(R.id.grpRegister);
        //Buttons
        btnScrnLogin = findViewById(R.id.btnScrnLogin);
        btnScrnRegister = findViewById(R.id.btnScrnRegister);
        btnCreate = findViewById(R.id.btnCreate);
        btnOTP = findViewById(R.id.btnForgetPassword);
        Back = findViewById(R.id.btnBack);
        //Linear Layout
        linOTP = findViewById(R.id.linForgetPass);
        linButtons = findViewById(R.id.Buttons);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Initialize();

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
}