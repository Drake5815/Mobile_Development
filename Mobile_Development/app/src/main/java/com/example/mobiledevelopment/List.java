package com.example.mobiledevelopment;

import org.bson.Document;

import java.util.ArrayList;

public interface List {
    /*
        Setters and Getters as indicated
     */

    // Setters
        //Address
    void setAddress(String Municipal_City, String Province, String Country);
    void setCustomAddress(String Address);
    void setPropertyName(String PropName);
    void setCoverPhotoReferenceString(String CoverPhoto);
    void setInsertImageReference(String ImgRef);
        // Price
    void setPrice(Double Price);
    // Getters
        //Type
    String getType();
        //Address
    String getAddress();
    String getCustomAddress();
    String getPropertyName();
    String getCoverPhotoReferenceString();
    ArrayList<String> getInsertImageReference();
        //Price
    Double getPrice();

    /*
        Set Functions
     */
        //Setting Prices of TotalPayment
    Double setTaxes(double day, double interest);
    Double setAmortization(double date, double interest);
    Double setTotalAmount();
        // Turning Var into Json File
    Document createJson();
}
