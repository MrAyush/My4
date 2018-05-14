package com.example.ayushgupta.my4;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;

public class ListTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_test);
        int status = ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE);
        if (status == PackageManager.PERMISSION_GRANTED)
            readFiles();
        else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
            readFiles();
        }
        else {
            Toast.makeText(this,"please allow permission to proceed",Toast.LENGTH_LONG).show();
        }
    }

    public void readFiles(){
        ListView listView = findViewById(R.id.List_View);
        String path = "/storage/sdcard0/";
        File file = new File(path);
        if(!file.exists()){
            path = "/storage/emulated/0/";
            file = new File(path);
        }
        String[] files = file.list();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(ListTest.this, android.R.layout.simple_list_item_1,files);
        listView.setAdapter(adapter);
    }
}