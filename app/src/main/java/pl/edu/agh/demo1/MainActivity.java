package pl.edu.agh.demo1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mItemsLV;
    private EditText mContentED;
    private Button mSaveBtn;
    private List<Item> items;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }

    private List<String> getItemHeaders(List<Item> items) {
        List<String> itemDescription = new ArrayList<>();
        for (Item item : items) {
            itemDescription.add(item.getDescription());
        }
        return itemDescription;
    }
}
