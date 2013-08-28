package com.othelle.android.sample;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;
import com.othelle.android.action.BaseUIAction;
import com.othelle.android.action.ContextMenuAction;
import com.othelle.android.widget.color.views.ColorPanelView;
import com.othelle.android.widget.color.views.ColorPickerView;

/**
 * author: v.vlasov
 */
public class ColorPickerActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_color_picker);

        final ColorPanelView colorPanel = (ColorPanelView) findViewById(R.id.color_panel);
        final ColorPickerView colorPicker = (ColorPickerView) findViewById(R.id.color_picker);
        colorPicker.setAlphaSliderVisible(true);
        colorPicker.setAlphaSliderText("Alpha");
        colorPicker.setOnColorChangedListener(new ColorPickerView.OnColorChangedListener() {
            @Override
            public void onColorChanged(int color) {
                colorPanel.setColor(color);
            }
        });

        colorPanel.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new ContextMenuAction().setActivity(ColorPickerActivity.this).performAction(
                        new BaseUIAction("Action1", "Subtitle long1", getResources().getDrawable(R.drawable.ic_hardware_computer)) {
                            @Override
                            public Object performAction(Object... param) {
                                Toast.makeText(ColorPickerActivity.this, "Action1", Toast.LENGTH_SHORT).show();
                                return null;
                            }
                        }, new BaseUIAction("Action2", null, getResources().getDrawable(R.drawable.ic_action_settings)) {
                            @Override
                            public Object performAction(Object... param) {
                                Toast.makeText(ColorPickerActivity.this, "Action2", Toast.LENGTH_SHORT).show();
                                return null;
                            }
                        }, new BaseUIAction("Action3", "Subtitle long3", null) {
                            @Override
                            public Object performAction(Object... param) {
                                Toast.makeText(ColorPickerActivity.this, "Action3", Toast.LENGTH_SHORT).show();
                                return null;
                            }
                        }
                );
                return true;
            }
        });
    }
}
