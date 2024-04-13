package com.example.thitracnghiem;

import androidx.appcompat.app.AppCompatActivity;

import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

public class AboutActivity extends AppCompatActivity {
    CardView youtube,instagram,facebook,telegram;
    ImageView img_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initView();
        img_back.setOnClickListener(v->{
            Intent intent=new Intent(AboutActivity.this,TrangChuActivity.class);
            startActivity(intent);
            finish();
        });
    }
    private void initView(){
        img_back=findViewById(R.id.img_back);
    }
}