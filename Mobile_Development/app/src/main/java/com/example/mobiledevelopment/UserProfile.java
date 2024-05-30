package com.example.mobiledevelopment;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserProfile extends AppCompatActivity {
    private Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        TextView txt = findViewById(R.id.txtUserEmail);
        TextView txtR = findViewById(R.id.txtReserve);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String propertyName = intent.getStringExtra("propertyName");
        txt.setText(email);
        txtR.setText(propertyName);

        btnBack = findViewById(R.id.btnBackUser);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserProfile.this, HomePage.class));
            }
        });
    }
}