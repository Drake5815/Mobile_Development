package com.example.mobiledevelopment.Data;

public class Property {
    private String propertyType;
    private String address;
    private double price;
    private String date;
    private String coverImgRef;

    public Property(){}
    public Property(String propertyType, String address, double price, String date, String coverImgRef){
        this.propertyType = propertyType;
        this.address = address;
        this.price = price;
        this.date = date;
        this.coverImgRef = coverImgRef;
    }
    //  SETTERS AND GETTERS
    public void setPropertyType(String propertyType){this.propertyType = propertyType;}
    public void setAddress(String address){this.address = address;}
    public void setPrice(double price){this.price = price;}
    public void setDate(String date){this.date = date;}
    public void setCoverImgRef(String coverImgRef){this.coverImgRef = coverImgRef;}

    public String getPropertyType(){return this.propertyType;}
    public String getAddress(){return this.address;}
    public double getPrice(){return this.price;}
    public String getDate(){return this.date;}
    public String getCoverImgRef(){return this.coverImgRef;}
    //  FUNCTION
    public double calculateTaxes(double days){
        double amount = this.price * days;

        if(amount>=250000&&amount<=400000){
            return amount + (amount * 0.15);
        }
        if(amount>400000&&amount<=800000){
            return amount +(amount*0.2);
        }
        if(amount>800000&&amount<=2000000){
            return amount + (amount*0.25);
        }
        if(amount>2000000&&amount<=8000000){
            return  amount + (amount*0.3);
        }
        if(amount>8000000){
            return amount + (amount*0.35);
        }
        return amount + (amount*0.05);
    }
    public double calculateMonthlyPayment(double loanAmount, double annualInterestRate, int loanTermYears) {
        int numberOfPayments = loanTermYears * 12;
        double monthlyInterestRate = annualInterestRate / 100 / 12;

        double numerator = loanAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfPayments);
        double denominator = Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1;

        return numerator / denominator;
    }
}
