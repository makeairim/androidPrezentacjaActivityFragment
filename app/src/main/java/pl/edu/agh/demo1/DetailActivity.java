package pl.edu.agh.demo1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    public static final String ITEM_KEY = "ITEM_KEY";
    public static final String ITEM_POSITION = "ITEM_POS";
    public static final int REQUEST_CODE = 101;
    private EditText mItemED;
    private Button mSaveBtn;
    private ArrayList<Item> items;
    private int itemPosition;

    public static Intent newInstance(Context context, ArrayList<Item> item, int position) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putParcelableArrayListExtra(ITEM_KEY, item);
        intent.putExtra(ITEM_POSITION, position);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mItemED = findViewById(R.id.ed_item_desc);
        mSaveBtn = findViewById(R.id.btn_save_item);
        items = getIntent().getParcelableArrayListExtra(ITEM_KEY);
        itemPosition = getIntent().getIntExtra(ITEM_POSITION, 0);
        mItemED.setText(items.get(itemPosition).getDescription());

        mSaveBtn.setOnClickListener(l -> {
            items.get(itemPosition).setDescription((mItemED.getText() != null ? mItemED.getText().toString() : "").trim());
            Intent data = new Intent();
            data.putParcelableArrayListExtra(ITEM_KEY, items);
            setResult(RESULT_OK, data);
            finish();
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent data = new Intent();
                data.putParcelableArrayListExtra(ITEM_KEY, items);
                setResult(RESULT_OK, data);
                super.onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
