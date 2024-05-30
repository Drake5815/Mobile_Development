package com.example.mobiledevelopment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ViewerPage extends AppCompatActivity {


    private Button Input;
    private Button Reserve;
    private Button Back;
    private TextView Days;
    private TextView Tax;
    private TextView Total;
    private TextView txtPriceView;
    private String propertyName;
    private String email;

    private void Initialize(){
        Input = (Button) findViewById(R.id.btnDays);
        Reserve = findViewById(R.id.btnReserve);
        Days = findViewById(R.id.txtDays);
        Tax = findViewById(R.id.txtTax);
        Total = findViewById(R.id.txtTotal);
        txtPriceView = findViewById(R.id.txtPriceView);
        Back = findViewById(R.id.btnViewBack);
    }

    private double calculateTaxes(double days, double price){
        double amount = price * days;
        if(amount>=250000&&amount<=400000){
            return amount * 0.15;
        }
        if(amount>400000&&amount<=800000){
            return amount*0.2;
        }
        if(amount>800000&&amount<=2000000){
            return amount*0.25;
        }
        if(amount>2000000&&amount<=8000000){
            return  amount*0.3;
        }
        if(amount>8000000){
            return amount*0.35;
        }
        return amount*0.05;
    }

    private void Buttons(){
        Reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ViewerPage.this, "Reserved", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ViewerPage.this, HomePage.class);
                intent.putExtra("email", email);
                intent.putExtra("propertyName", propertyName);
                startActivity(intent);
            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewerPage.this, HomePage.class));
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewer_page);

        Initialize();
        // GET INTETN
        Intent intent = getIntent();
        Double price = intent.getDoubleExtra("price", 0.0);
        propertyName = intent.getStringExtra("propertyName");
        email = intent.getStringExtra("email");
        txtPriceView.setText(String.format("PHP %.2f", price));
        // BUTTON INPUT
        Input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double tax = calculateTaxes(Double.parseDouble(Days.getText().toString()), price);
                double total = tax + (price * Double.parseDouble(Days.getText().toString()));

                Tax.setText(String.format("%.2f", tax));
                Total.setText(String.format("%.2f", total));

            }
        });

        Buttons();
    }
}