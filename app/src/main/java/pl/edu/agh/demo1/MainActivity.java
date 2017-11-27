package pl.edu.agh.demo1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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
        mSaveBtn.setOnClickListener(l -> {
            String content = (mContentED.getText() != null ? mContentED.getText().toString() : "").trim();
            if (content.isEmpty()) {
                Toast.makeText(this, "Empty item", Toast.LENGTH_SHORT).show();
            } else {
                mContentED.setText("");
                addItem(content);
            }
        });
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
    //todo override onResume
    //todo override onStart
    //todo override onStop
    //todo override onPause
    //todo override onDestroy
    //todo override onRestoreInstanceState
    //todo override onSaveInstanceState
    //todo add logger in ALL overriden methods including onCreate
    //todo run emulator, goto logcat, view logged TAG, try rotate screen, switch app to foreground, turn on/off screen

}
