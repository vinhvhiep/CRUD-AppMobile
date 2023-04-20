package com.example.crud_gk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.awt.font.TextAttribute;

public class DetailActivity extends AppCompatActivity {

    TextView detailID, detailName, detailBrand, detailPrice, detailDesc;
    ImageView detailImage;
    FloatingActionButton deleteButton, editButton;
    String key = "";
    String imageURL = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        detailName = findViewById(R.id.detailName);
        detailPrice = findViewById(R.id.detailPrice);
        detailBrand = findViewById(R.id.detailBrand);
        detailDesc = findViewById(R.id.detailDesc);
        detailImage = findViewById(R.id.detailImage);
        detailID =  findViewById(R.id.detailID);
        deleteButton = findViewById(R.id.deleteButton);
        editButton = findViewById(R.id.editButton);

        Bundle bundle = getIntent().getExtras();
        if (bundle !=null) {

            detailID.setText(bundle.getString("ID"));
            detailName.setText(bundle.getString("Name"));
            detailBrand.setText(bundle.getString("Brand"));
            detailPrice.setText(bundle.getString("Price"));
            detailDesc.setText(bundle.getString("Description"));
            key = bundle.getString("Key");
            imageURL = bundle.getString("Image");
            Glide.with(this).load(bundle.getString("Image")).into(detailImage);
        }
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Android CRUD");
                FirebaseStorage storage = FirebaseStorage.getInstance();

                StorageReference storageReference = storage.getReferenceFromUrl(imageURL);
                storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        reference.child(key).removeValue();
                        Toast.makeText(DetailActivity.this, "Đã xoá", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                });
            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, UpdateActivity.class)

                        .putExtra("ID", detailID.getText().toString())
                        .putExtra("Name", detailName.getText().toString())
                        .putExtra("Brand", detailBrand.getText().toString())
                        .putExtra("Price", detailPrice.getText().toString())
                        .putExtra("Description", detailDesc.getText().toString())
                        .putExtra("Key", key)
                        .putExtra("Image", imageURL);
                startActivity(intent);
            }
        });
    }
}