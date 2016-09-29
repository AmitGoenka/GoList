package org.agoenka.golist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

    private int currentPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        Intent intent = getIntent();
        if (intent != null) {
            currentPosition = intent.getIntExtra("itemPosition", -1);
            String text = intent.getStringExtra("itemText");
            if (currentPosition > -1) {
                EditText etEditItem = (EditText) findViewById(R.id.etEditItem);
                etEditItem.setText(text);
                etEditItem.requestFocus(View.FOCUS_RIGHT);
            }
        }
    }

    public void onSave(View view) {
        EditText etEditItem = (EditText) findViewById(R.id.etEditItem);
        Intent data = new Intent();
        data.putExtra("itemPosition", currentPosition);
        data.putExtra("itemText", etEditItem.getText().toString());
        setResult(RESULT_OK, data);
        finish();
    }
}