package com.example.mobiledevelopment.Data;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.mobiledevelopment.HomePage;
import com.example.mobiledevelopment.R;
import com.example.mobiledevelopment.ViewerPage;
import com.google.android.material.button.MaterialButton;

public class PropertyListingView extends FrameLayout {
    private ImageView userImageView;
    private TextView userNameTextView;
    private ImageView propertyImageView;
    private MaterialButton viewButton;
    private TextView publishDateTextView;
    private TextView priceTextView;
    private TextView titleTextView;
    private TextView locationTextView;
    private Property propertyData;

    public PropertyListingView(Context context, AttributeSet attrs){
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.custom_layout, this, true);
        userImageView = findViewById(R.id.imgUser);
        userNameTextView = findViewById(R.id.txtHomeUsername);
        propertyImageView = findViewById(R.id.imgView);
        viewButton = findViewById(R.id.btnViewPage);
        publishDateTextView = findViewById(R.id.txtHomeDate);
        priceTextView = findViewById(R.id.txtHomePrice);
        titleTextView = findViewById(R.id.txtHomePropertyName);
        locationTextView = findViewById(R.id.txtHomeAddress);
    }

    public void setPropertyData(Property property, String email) {
        this.propertyData = property;

        titleTextView.setText(property.getPropertyType() + ": " + property.getPropertyName());
        locationTextView.setText(property.getAddress());
        priceTextView.setText(String.format("For SALE: %.2f", property.getPrice()));
        publishDateTextView.setText("Publish on: " + property.getDate());

        viewButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ViewerPage.class);
                intent.putExtra("email",email);
                intent.putExtra("price", property.getPrice());
                intent.putExtra("propertyName", property.getPropertyName());
                getContext().startActivity(intent);
            }
        });
    }
}
