package tv.xiaocong.sdk.control.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void enterNativeEvents(View view) {
        Intent i = new Intent(this, NativeKeyEventActivity.class);
        startActivity(i);
    }

    public void enterSdkDemo(View view) {
        Intent i = new Intent(this, SdkDemoActivity.class);
        startActivity(i);
    }

}
