package com.example.mobiledevelopment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.LinEmpty);
        addButton = findViewById(R.id.btnInsert);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView newTxt = new TextView(MainActivity.this);
                newTxt.setText("New Text View" + (linearLayout.getChildCount()+1));
                linearLayout.addView(newTxt);
            }
        });


    }
}