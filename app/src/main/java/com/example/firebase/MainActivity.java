package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edtPhone = findViewById(R.id.phone);
        EditText edtEmail = findViewById(R.id.email);
        EditText edtPass = findViewById(R.id.password);
        Button btn = findViewById(R.id.signup);

        btn.setOnClickListener(view -> {
            String phone = edtPhone.getText().toString();
            String email = edtEmail.getText().toString();
            String password = edtPass.getText().toString();

            database = FirebaseDatabase.getInstance("https://console.firebase.google.com/project/fireba-23845/overview")
                    .getReference().child("Users");

            User user = new User(phone, email, password);
            database.child(phone).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    edtPhone.getText().clear();
                    edtEmail.getText().clear();
                    edtPass.getText().clear();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), "Ошибка", Toast.LENGTH_LONG).show();
                }
            });
        });
    }
}