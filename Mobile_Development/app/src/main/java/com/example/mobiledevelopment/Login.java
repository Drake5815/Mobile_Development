package com.example.mobiledevelopment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mongodb.client.FindIterable;

import org.bson.Document;
import org.w3c.dom.Text;

import java.util.HashMap;

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
    private EditText txtUsername;
    private EditText txtPassword;
    private EditText txtEmail;
    private EditText txtUsernameReg;
    private EditText txtPasswordReg;
    private EditText txtConPasswordReg;
    private LinearLayout linOTP;
    private LinearLayout linButtons;
    private DatabaseManager dbManager;
    private void Initialize(){
        //Managers
        dbManager = new DatabaseManager();
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
        txtUsername = (EditText)findViewById(R.id.txtUsername);
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
    private boolean Login(){
        String email = txtUsername.getText().toString();
        String password = txtPassword.getText().toString(); // Assuming you have an input for password

        // Check for empty fields
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(Login.this, "Please enter both email and password", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Query MongoDB for the account with the given email
        Document query = new Document("Email", email);
        FindIterable<Document> documents = dbManager.getCollection("Acc").find(query);
        Document accountDocument = documents.first();

        if (accountDocument != null) {
            // Check if the entered password matches the stored password
            String storedPassword = accountDocument.getString("Password");
            if (password.equals(storedPassword)) {
                // Successful login
                Toast.makeText(Login.this, "Login successful!", Toast.LENGTH_SHORT).show();
                return true;
            } else {
                // Incorrect password
                Toast.makeText(Login.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            // Account with the given email doesn't exist
            Toast.makeText(Login.this, "Account not found", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean Register(){
        HashMap<String, String> Account = new HashMap<>();
        String email = txtEmail.getText().toString();
        String username = txtUsernameReg.getText().toString();
        String password = txtPasswordReg.getText().toString();
        String ConPassword = txtConPasswordReg.getText().toString();



        if (email.isEmpty()){
            Toast.makeText(Login.this, "Email does not have entry", Toast.LENGTH_SHORT).show();
        }
        if (username.isEmpty()){
            Toast.makeText(Login.this, "Username does not have entry", Toast.LENGTH_SHORT).show();
        }
        if (password.isEmpty()){
            Toast.makeText(Login.this, "Password does not have entry", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (ConPassword.isEmpty()){
            Toast.makeText(Login.this, "Confirm Password does not have entry", Toast.LENGTH_SHORT).show();
        }

        if(password.equals(ConPassword)){
            Account.put("Email", email);
            Account.put("Username", username);
            Account.put("Password", password);

            dbManager.createAccount("Acc", Account);
            Toast.makeText(Login.this, "Register", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            txtPasswordReg.setText("");
            txtConPasswordReg.setText("");
            Toast.makeText(Login.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
        }

        return false;
    }

    private void Button(){
//        Login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Login();
//            }
//        });
//        Register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Register();
//            }
//        });
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