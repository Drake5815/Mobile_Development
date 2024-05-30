package com.example.mobiledevelopment;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobiledevelopment.Data.Property;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.Map;

public class UploadPage extends AppCompatActivity {
    private FirebaseFirestore db;
    private Property property = new Property();
    // pass
    private TextView propertyType;
    private TextView propertyName;
    private TextView address;
    private TextView price;

    private Button Uplaod;
    private Button UploadPhoto;
    private ImageView img;

    private String date;
    private Uri coverImgRef;

    ActivityResultLauncher<Intent> resultLauncher;

    private void Intialize(){
        propertyType = findViewById(R.id.txtPropertyType);
        propertyName = findViewById(R.id.txtPropertyName);
        address = findViewById(R.id.txtAddress);
        price = findViewById(R.id.txtPrice);

        img = findViewById(R.id.imgCoverPhoto);

        Uplaod = findViewById(R.id.btnUploadFinal);
        UploadPhoto = findViewById(R.id.btnUploadPhoto);
    }

    private void Buttons(){
        Uplaod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String propertyTypeString = propertyType.getText().toString().trim();
                String propertyNameString = propertyName.getText().toString().trim();
                String addressString = address.getText().toString().trim();
                String priceString = price.getText().toString().trim();

                if (propertyTypeString.isEmpty()) {
                    Toast.makeText(UploadPage.this, "Missing Variable propertyType", Toast.LENGTH_SHORT).show();
                    return; // Stop further processing if a required field is missing
                } else {
                    property.setPropertyType(propertyTypeString);
                }

                if (propertyNameString.isEmpty()) {
                    Toast.makeText(UploadPage.this, "Missing Variable propertyName", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    property.setPropertyName(propertyNameString);
                }

                if (addressString.isEmpty()) {
                    Toast.makeText(UploadPage.this, "Missing Variable Address", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    property.setAddress(addressString);
                }

                if (priceString.isEmpty()) {
                    Toast.makeText(UploadPage.this, "Missing Variable price", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    try {
                        double priceValue = Double.parseDouble(priceString); // Use double for flexibility
                        property.setPrice(priceValue);
                    } catch (NumberFormatException e) {
                        Toast.makeText(UploadPage.this, "Invalid price format", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                Property property = new Property();
                property.setPropertyType(propertyTypeString);
                property.setPropertyName(propertyNameString);
                property.setAddress(addressString);
                property.setPrice(Double.parseDouble(priceString));
                property.setDate(displayPhoneDate());

                Map<String, Object> propertyData = new HashMap<>();
                propertyData.put("propertyType", propertyTypeString);
                propertyData.put("propertyDoc", property);

                db.collection("Properties")
                        .add(propertyData)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("TAG", "Property added with ID: " + documentReference.getId());
                                Toast.makeText(UploadPage.this, "Property uploaded successfully!", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(UploadPage.this, HomePage.class);
                                startActivity(intent);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(UploadPage.this, "Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });

        UploadPhoto.setOnClickListener(view -> pickImage());
    }

    private String displayPhoneDate() {
        // Get system's default time zone to match phone settings
        ZoneId zoneId = ZoneId.systemDefault();

        // Get current date in the system's zone
        LocalDate today = LocalDate.now(zoneId);

        // Format date (automatically adapts to user's locale)
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
        String formattedDate = today.format(formatter);

        return formattedDate;
    }

    private void pickImage(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        resultLauncher.launch(intent);
    }

    private void getImage(){
        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        try{
                            if (result != null && result.getData() != null) {
                                try {
                                    coverImgRef = result.getData().getData();
                                    img.setImageURI(coverImgRef);
                                } catch (Exception e) {
                                    Toast.makeText(UploadPage.this, "Returned null", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(UploadPage.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                            }

                        } catch (Exception e){
                            Toast.makeText(UploadPage.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_page);

        Intialize();
        db = FirebaseFirestore.getInstance();
        getImage();
        Buttons();



    }
}