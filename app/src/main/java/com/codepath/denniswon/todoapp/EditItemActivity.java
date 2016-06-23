package com.codepath.denniswon.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        pos = getIntent().getIntExtra("pos", -1);
        if (pos == -1) {
            throw new ExceptionInInitializerError("pos param for EditItemActivity not found.");
        }
        String initialVal = getIntent().getStringExtra("value");
        EditText etEditItem = (EditText) findViewById(R.id.etEditItem);
        etEditItem.setText(initialVal);
        etEditItem.setSelection(initialVal.length());
    }

    public void onSave(View view) {
        EditText etEditItem = (EditText) findViewById(R.id.etEditItem);
        String editedVal = etEditItem.getText().toString();

        // Prepare data intent
        Intent data = new Intent();
        data.putExtra("newValue", editedVal);
        data.putExtra("pos", pos);
        // Activity finished ok, return the data
        setResult(RESULT_OK, data); // set result code and bundle data for response

        // closes the activity and returns to SimpleTodoActivity
        this.finish();
    }
}
