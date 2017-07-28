package com.example.nikhil.notebook;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NoteDetailsActivity extends AppCompatActivity  implements View.OnClickListener{

    EditText etxtName;
    EditText etxtDescription;
    Button btnSubmit ;
    User user  ;
    ContentResolver resolver ;

    void initviews()
    {
        etxtName = (EditText)findViewById(R.id.editTextName);
        etxtDescription = (EditText)findViewById(R.id.editTextDescription);
        btnSubmit = (Button)findViewById(R.id.buttonSubmit);
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);
        initviews();
        btnSubmit.setOnClickListener(this);
        user = new User();
        resolver = getContentResolver();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.buttonSubmit)
        user.setName(etxtName.getText().toString().trim());
        user.setDescription(etxtDescription.getText().toString().trim());
        insertUser();
    }
    void insertUser()
    {
        ContentValues values = new ContentValues();
        values.put(Util.COL_NAME,user.getName());
        values.put(Util.COL_DESCRIPTION,user.getDescription());
        Uri uri = resolver.insert(Util.USER_URI, values);
        Toast.makeText(this, user.getName() + " registered successfully with id " + uri.getLastPathSegment(), Toast.LENGTH_LONG).show();
    }

}
