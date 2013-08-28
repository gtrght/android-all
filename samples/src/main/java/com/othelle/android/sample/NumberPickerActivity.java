package com.othelle.android.sample;

import android.app.Activity;
import android.os.Bundle;
import com.othelle.android.widget.numberpicker.NumberPicker;

/**
 * author: v.vlasov
 */
public class NumberPickerActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_number_picker);
        NumberPicker picker = (NumberPicker) findViewById(R.id.number_picker);
        picker.setMaxValue(1000);
        picker.setMinValue(0);
        picker.setValue(5);
        picker.setTextAppearance(android.R.style.TextAppearance_Large);
    }
}
