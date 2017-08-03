package com.example.nikhil.notebook;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NoteDetailsActivity extends AppCompatActivity  implements View.OnClickListener, AdapterView.OnItemClickListener{

    EditText etxtName;
    EditText etxtDescription;
    Button btnSubmit ;
    Note note, rcvNote;
    ContentResolver resolver ;
    boolean updateMode;
    SimpleDateFormat dateFormat;

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
        note = new Note();
        resolver = getContentResolver();
    }

    String FindCurrentDate(){
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String currentdate = dateFormat.format(new Date());
        Log.i("test", currentdate);

        return currentdate;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.buttonSubmit)
            note.setCurrentDate(FindCurrentDate());
            note.setName(etxtName.getText().toString().trim());
            note.setDescription(etxtDescription.getText().toString().trim());
            insertUser();
    }
    void insertUser() {
        ContentValues values = new ContentValues();
        values.put(Util.COL_NAME, note.getName());
        values.put(Util.COL_DESCRIPTION, note.getDescription());
        values.put(Util.COL_CURRENT_DATE, note.getCurrentDate());

        if(!updateMode) {
            Uri uri = resolver.insert(Util.USER_URI, values);
            Toast.makeText(this, note.getName() + " registered successfully with id " + uri.getLastPathSegment(), Toast.LENGTH_LONG).show();
            clearFields();
        }else{
            String where = Util.COL_ID+" = "+ rcvNote.getId();
            int i = resolver.update(Util.USER_URI,values,where,null);
            if(i>0){
                Toast.makeText(this, rcvNote.getName()+ " updated...",Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
    void clearFields(){
        etxtName.setText("");
        etxtDescription.setText("");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.allUsers){
            Intent intent = new Intent(NoteDetailsActivity.this,AllNoteActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
