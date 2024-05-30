package com.example.mobiledevelopment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobiledevelopment.Data.Property;
import com.example.mobiledevelopment.Data.PropertyListingView;
import com.example.mobiledevelopment.Data.UserInf;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {
    //  BUTTON
    private ImageButton btnExpand;
    private Button btnUserProfile;
    private Button btnUpload;
    private ImageButton Search;
    private TextView txtSearch;
    private ImageButton House;
    private ImageButton Rentals;
    private ImageButton Hotels;
    private ImageButton Complex;
    private ImageButton Resort;
    // FRAME
    private FrameLayout frmExpand;
    // SCROLL VIEW
    private ScrollView linHouse;
    private ScrollView linRental;
    private ScrollView linHotels;
    private ScrollView linComplex;
    private ScrollView linResort;
    // LINEAR LAYOUT
    private LinearLayout linearResort;
    private LinearLayout linearHouse;
    //  CUSTOMIZE
    private String email;
    private String propertyName;
    private ArrayList<Property> properties = new ArrayList<>();

    private void Initialize(){
        //  FRAME
        frmExpand = findViewById(R.id.frmExpand);
        //  SCROLL VIEW
        linHouse = findViewById(R.id.scrlHouses);
        linRental = findViewById(R.id.scrlRentals);
        linHotels = findViewById(R.id.scrlHotels);
        linComplex = findViewById(R.id.scrlComplex);
        linResort = findViewById(R.id.scrlResort);
        //  BUTTON
        Search = findViewById(R.id.btnSearch);
        btnExpand = findViewById(R.id.btnExpand);
        btnUserProfile = findViewById(R.id.btnUserProfile);
        btnUpload = findViewById(R.id.btnUpload);
        // iMAGE BUTTON
        House = (ImageButton) findViewById(R.id.btnHouse);
        Rentals = (ImageButton) findViewById(R.id.btnRentals);
//        Hotels = (ImageButton) findViewById(R.id.btnHotels);
        Complex = (ImageButton) findViewById(R.id.btnComplex);
        Resort = (ImageButton) findViewById(R.id.btnResort);
        // Linear Layout
        linearResort = findViewById(R.id.linResort);
        linearHouse = findViewById(R.id.linHouses);
        //  TEXT VIEW
        txtSearch = findViewById(R.id.txtSearch);
    }

    public void btnSearch_Click(View view) {
        String searchText = txtSearch.getText().toString();
        ScrollView scrollView = findViewById(R.id.scrlHouses);

        for (int i = 0; i < scrollView.getChildCount(); i++) {
            View child = scrollView.getChildAt(i);
            if (child instanceof FrameLayout) {
                TextView addressTextView = child.findViewById(R.id.txtHomeAddress);
                if (addressTextView != null && addressTextView.getText().toString().equals(searchText)) {
                    scrollView.smoothScrollTo(0, child.getTop());
                    return;
                }
            }
        }
        Toast.makeText(this, "Address not found", Toast.LENGTH_SHORT).show();
    }


    private void intent(){
        Intent intent = getIntent();
        if(intent.getExtras() != null){
            email = intent.getStringExtra("email");
            propertyName = intent.getStringExtra("propertyName");
        }
    }

    private void Sort(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference propertiesRef = db.collection("Properties");

        propertiesRef.get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<Property> properties = new ArrayList<>();
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        Property property = document.get("propertyDoc", Property.class);
                        properties.add(property);
                    }
                    
                    for (Property property : properties) {
                        PropertyListingView listingView = new PropertyListingView(this, null);
                        listingView.setPropertyData(property, this.email);
                        linearHouse.addView(listingView);
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e("Sort", "Error getting properties: ", e);
                });
    }

    private void Buttons(){
        Search.setOnClickListener(view -> btnSearch_Click(view));
        btnExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(frmExpand.getVisibility() == View.VISIBLE){
                    frmExpand.setVisibility(View.GONE);
                } else {
                    frmExpand.setVisibility(View.VISIBLE);
                }
            }
        });
        btnUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, UserProfile.class);
                intent.putExtra("email", email);
                intent.putExtra("propertyName", propertyName);
                startActivity(intent);
            }
        });
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage.this, UploadPage.class));
            }
        });
        House.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linHouse.setVisibility(View.VISIBLE);
                linRental.setVisibility(View.GONE);
                linHotels.setVisibility(View.GONE);
                linComplex.setVisibility(View.GONE);
                linResort.setVisibility(View.GONE);
            }
        });
        Rentals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linHouse.setVisibility(View.GONE);
                linRental.setVisibility(View.VISIBLE);
                linHotels.setVisibility(View.GONE);
                linComplex.setVisibility(View.GONE);
                linResort.setVisibility(View.GONE);
            }
        });
//        Hotels.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                linHouse.setVisibility(View.GONE);
//                linRental.setVisibility(View.GONE);
//                linHotels.setVisibility(View.VISIBLE);
//                linComplex.setVisibility(View.GONE);
//                linResort.setVisibility(View.GONE);
//            }
//        });
        Complex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linHouse.setVisibility(View.GONE);
                linRental.setVisibility(View.GONE);
                linHotels.setVisibility(View.GONE);
                linComplex.setVisibility(View.VISIBLE);
                linResort.setVisibility(View.GONE);
            }
        });
        Resort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linHouse.setVisibility(View.GONE);
                linRental.setVisibility(View.GONE);
                linHotels.setVisibility(View.GONE);
                linComplex.setVisibility(View.GONE);
                linResort.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Initialize();
        Sort();
        intent();
        Buttons();

    }
}