package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_SELECT_PHONE_NUMBER = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText searchBar = findViewById(R.id.searchBar);
        Button setDate = findViewById(R.id.setDate);
        Button accessLink = findViewById((R.id.accessLink));
        Button search = findViewById(R.id.search);
        setDate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(Settings.ACTION_DATE_SETTINGS);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        accessLink.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Uri webpage = Uri.parse(searchBar.getText().toString());
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                try {
                    startActivity(intent);
                }
                catch(ActivityNotFoundException e) {

                }
            }
        });
        search.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, searchBar.getText().toString());
                try {
                    startActivity(intent);
                }
                catch(ActivityNotFoundException e) {

                }
            }
        });
    }
}