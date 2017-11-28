package pl.edu.agh.demo1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
//todo add new activity
//todo add activity to AndroidManifest
//todo in AndroidManifest set activity parent to MainActivity

//todo in created activity add edittext to edit item data
//todo add save button to save item data and return to previous activity
//todo to maintain state when click back button in navigationbar override onOptionsItemSelected, check if item is android.R.id.home and set activity result data
public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String ITEM_KEY = "ITEM_KEY";
    private ListView mItemsLV;
    private EditText mContentED;
    private Button mSaveBtn;
    private List<Item> items;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
        setContentView(R.layout.activity_main);
        items = new LinkedList<>();
        initView();
    }

    private void initView() {
        mItemsLV = findViewById(R.id.lv_items);
        mContentED = findViewById(R.id.ed_item_content);
        mSaveBtn = findViewById(R.id.btn_save_item);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item, getItemHeaders(items));
        mItemsLV.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        mSaveBtn.setOnClickListener(l -> {
            String content = (mContentED.getText() != null ? mContentED.getText().toString() : "").trim();
            if (content.isEmpty()) {
                Toast.makeText(this, "Empty item", Toast.LENGTH_SHORT).show();
            } else {
                mContentED.setText("");
                addItem(content);
            }
        });
        //todo add onItemClickListener on listview
        //todo create new activity
        //todo pass data to new activity to show item
        //todo use startActivityForResult to receive result data from new activity
    }

    private List<String> getItemHeaders(List<Item> items) {
        List<String> itemDescription = new ArrayList<>();
        for (Item item : items) {
            itemDescription.add(item.getDescription());
        }
        return itemDescription;
    }

    private void addItem(String desription) {
        items.add(new Item(desription));
        adapter.clear();
        adapter.addAll(getItemHeaders(items));
        adapter.notifyDataSetChanged();
    }

    private void addItems(List<Item> item) {
        items.addAll(item);
        adapter.clear();
        adapter.addAll(getItemHeaders(items));
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState: ");
        Item[] items = (Item[]) savedInstanceState.getParcelableArray(ITEM_KEY);
        if (items != null) {
            addItems(Arrays.asList(items));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState: ");
        outState.putParcelableArray(ITEM_KEY, items.toArray(new Item[0]));
    }

    //todo override onActivityResult to receive data from startActivityForResult
    //todo check resultCode
    //todo check requestCode
    //todo update view in MainActivity
}
