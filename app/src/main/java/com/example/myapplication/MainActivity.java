package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.internal.$Gson$Preconditions;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    public static final String KEY_NAME = "name";
    public static final String KEY_SURNAME = "surname";

    Button save;
    EditText et_name,et_surname;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_name = findViewById(R.id.et_name);
        et_surname = findViewById(R.id.et_surname);
        save = findViewById(R.id.save);

        save.setOnClickListener(v -> {
            String name = et_name.getText().toString();
            String surname = et_surname.getText().toString();
            Map<String, Object> user = new HashMap<>();
            user.put(KEY_NAME,name);
            user.put(KEY_SURNAME,surname);

            db.collection("Users").document("User").set(user)
                    .addOnSuccessListener(aVoid -> Toast.makeText(MainActivity.this, "User added!", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e -> {
                        Toast.makeText(MainActivity.this, "Error: " + e.toString(), Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    });
        });
    }
}