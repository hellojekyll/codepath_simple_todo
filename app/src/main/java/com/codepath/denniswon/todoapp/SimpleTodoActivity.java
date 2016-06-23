package com.codepath.denniswon.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.codepath.denniswon.todoapp.utils.ItemUtils;

import java.util.ArrayList;

public class SimpleTodoActivity extends AppCompatActivity {

    // REQUEST_CODE can be any value we like, used to determine the result type later
    private final int REQUEST_CODE = 20;

    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;
    ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_todo);

        items = ItemUtils.readItems(getFilesDir());
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        lvItems = (ListView) findViewById(R.id.lvItems);
        lvItems.setAdapter(itemsAdapter);

        setupListViewLister();
    }

    private void setupListViewLister() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {

                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        items.remove(position);
                        itemsAdapter.notifyDataSetChanged();
                        ItemUtils.writeItems(getFilesDir(), items);
                        return true;
                    }

                }
        );

        lvItems.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(SimpleTodoActivity.this, EditItemActivity.class);
                        i.putExtra("pos", position);
                        i.putExtra("value", items.get(position));
                        startActivityForResult(i, REQUEST_CODE);   // Go to EditItemActivity
                    }

                }
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract newValue and pos from result extras
            String newValue = data.getStringExtra("newValue");
            int pos = data.getIntExtra("pos", -1);
            if (pos == -1) {
                throw new ExceptionInInitializerError("pos param for SimpleTodoActivity not found.");
            }
            items.set(pos, newValue);
            itemsAdapter.notifyDataSetChanged();
            ItemUtils.writeItems(getFilesDir(), items);
        }
    }

    public void onAddItem(View view) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        if (itemText.length() > 0) {
            items.add(itemText);
            etNewItem.setText("");
            ItemUtils.writeItems(getFilesDir(), items);
        }
    }

}
