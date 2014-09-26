package tv.xiaocong.sdk.control.demo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.widget.TextView;

public class NativeKeyEventActivity extends Activity {

    private TextView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.keyevents);

        listView = (TextView) findViewById(R.id.keyevents_listview);
    }

    @SuppressLint("NewApi")
    private static String buildConfigId(InputDevice device) {
        if (Build.VERSION.SDK_INT >= 19) {
            return device.getName() + device.getVendorId() + device.getProductId();
        } else {
            return device.getName();
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        String descr = KeyEvent.keyCodeToString(event.getKeyCode()) + "-" + event.getAction() + " | "
                + buildConfigId(event.getDevice());
        listView.setText(descr + "\n" + listView.getText());
        return super.dispatchKeyEvent(event);
    }

}
