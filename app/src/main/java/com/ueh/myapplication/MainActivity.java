package com.ueh.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    TextView txtUser,txtPhone;
    EditText editUser,editPhone;
    Button btnUpdate;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUser=findViewById(R.id.txtUser);
        txtPhone=findViewById(R.id.txtPhone);
        editUser=findViewById(R.id.editUser);
        editPhone=findViewById(R.id.editPhone);
        btnUpdate=findViewById(R.id.btnUpdate);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child("account");
        databaseReference.child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    txtUser.setText(snapshot.getValue(String.class));
                }
                else {
                    txtUser.setText("Not found");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference.child("phone").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    txtPhone.setText(snapshot.getValue(int.class).toString());
                }
                else {
                    txtPhone.setText("Not found");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName=editUser.getText().toString();
                String phoneNumber=editPhone.getText().toString();
                int phone=Integer.parseInt(phoneNumber);

                databaseReference.child("name").setValue(userName);
                databaseReference.child("phone").setValue(phone);
            }
        });
    }
}