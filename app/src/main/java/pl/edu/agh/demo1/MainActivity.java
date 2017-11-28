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
        mItemsLV.setOnItemClickListener((adapterView, view, i, l) ->
                startActivityForResult(DetailActivity.newInstance(this, new ArrayList<>(items), i), DetailActivity.REQUEST_CODE));
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK:
                if (requestCode == DetailActivity.REQUEST_CODE) {
                    ArrayList<Item> result = data.getParcelableArrayListExtra(DetailActivity.ITEM_KEY);
                    if (result != null) {
                        items.clear();
                        addItems(result);
                    }
                }
                break;
        }
    }
}
