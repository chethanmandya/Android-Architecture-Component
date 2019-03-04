package com.chethan.architecturecomponents;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {

    private EditText editTextTile;
    private EditText editTextDescription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Note");


    }


    private void saveNote() {
        editTextTile = findViewById(R.id.editTextTitle);
        editTextDescription = findViewById(R.id.editTextDescription);

        String title = editTextTile.getText().toString();
        String description = editTextDescription.getText().toString();
        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please insert the title and description", Toast.LENGTH_SHORT).show();
        } else {
            Intent data = new Intent();
            data.putExtra(MainActivity.EXTRA_TITLE, title);
            data.putExtra(MainActivity.EXTRA_DESCRIPTION, description);
            setResult(RESULT_OK, data);
            finish();


        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }


    }
}


