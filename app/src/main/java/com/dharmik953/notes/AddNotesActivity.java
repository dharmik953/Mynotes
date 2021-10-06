package com.dharmik953.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddNotesActivity extends AppCompatActivity {

    EditText description, title;
    Button addNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        title = findViewById(R.id.et_title);
        description = findViewById(R.id.et_notes);
        addNote = findViewById(R.id.add_notes);

        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!TextUtils.isEmpty(title.getText().toString()) && !TextUtils.isEmpty(description.getText().toString())) {
                    DatabaseClass db = new DatabaseClass(AddNotesActivity.this);
                    db.addNotes(title.getText().toString(), description.getText().toString());

                    Intent intent = new Intent(AddNotesActivity.this,MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(AddNotesActivity.this, "Both Fields Required", Toast.LENGTH_SHORT).show();
                }

//                if (!TextUtils.isEmpty(title.getText().toString()) && !TextUtils.isEmpty(description.getText().toString())) {
//                    DatabaseClass db = new DatabaseClass(AddNotesActivity.this);
//                    db.addNotes(title.getText().toString(), description.getText().toString());
//
//                    Intent intent = new Intent(AddNotesActivity.this,MainActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent);
//                    finish();
//
//                } else {
//                    Toast.makeText(AddNotesActivity.this, "Both Fields Required", Toast.LENGTH_SHORT).show();
//                }


//                if (!TextUtils.isEmpty(title.getText().toString()) && !TextUtils.isEmpty(description.getText().toString())){
//
//                    DatabaseClass db = new DatabaseClass(AddNotesActivity.this);
//                    db.AddNotesActivity(title.getText().toString(), description.getText().toString());
//
//                    Intent intent = new Intent(AddNotesActivity.this, MainActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent);
//                    finish();
//                }
//                else Toast.makeText(AddNotesActivity.this, "Both fields Required", Toast.LENGTH_SHORT).show();
            }
        });

//        addNote.setOnClickListener(v -> {
//
//            if (!TextUtils.isEmpty(title.getText()) && !TextUtils.isEmpty(description.getText())){
//
//                DatabaseClass db = new DatabaseClass(AddNotesActivity.this);
//                db.AddNotesActivity(title.getText().toString(),description.getText().toString());
//
//                Intent intent = new Intent(AddNotesActivity.this, MainActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//                finish();
//            }
//            else Toast.makeText(AddNotesActivity.this, "Both fields Required", Toast.LENGTH_SHORT).show();
//        });
    }
}