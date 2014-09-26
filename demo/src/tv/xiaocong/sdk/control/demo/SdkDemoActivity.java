package tv.xiaocong.sdk.control.demo;

import tv.xiaocong.sdk.control.ControlManager;
import tv.xiaocong.sdk.control.ControlManagerInitCallback;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.widget.Toast;

public class SdkDemoActivity extends Activity implements ControlManagerInitCallback {

    private KeyEventMonitor keyCodeShower;

    private ControlManager controlManager = new ControlManager(this, this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sdkdemo);

        keyCodeShower = (KeyEventMonitor) findViewById(R.id.final_keycode_monitor);
        keyCodeShower.setFocusable(true);
        keyCodeShower.setFocusableInTouchMode(true);
        keyCodeShower.requestFocus();
        keyCodeShower.requestFocusFromTouch();

        controlManager.init();
    }

    @Override
    public void doneInitControlManager(boolean success) {
        if (success) {
            Toast.makeText(this, "加载手柄配置文件完成", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "加载手柄配置文件失败！！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (controlManager.dispatchKeyEvent(event, this)) {
            return true;
        } else {
            String descr = KeyEvent.keyCodeToString(event.getKeyCode()) + "-" + event.getAction() + " | "
                    + buildConfigId(event.getDevice());

            keyCodeShower.setText(descr + "\n" + keyCodeShower.getText());

            return super.dispatchKeyEvent(event);
        }
    }

    @SuppressLint("NewApi")
    private static String buildConfigId(InputDevice device) {
        if (Build.VERSION.SDK_INT >= 19) {
            return device.getName() + device.getVendorId() + device.getProductId();
        } else {
            return device.getName();
        }
    }

}
