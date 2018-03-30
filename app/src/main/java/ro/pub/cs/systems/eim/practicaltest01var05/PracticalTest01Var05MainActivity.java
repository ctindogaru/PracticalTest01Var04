package ro.pub.cs.systems.eim.practicaltest01var05;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var05MainActivity extends AppCompatActivity {

    Button navigateButton = null;
    Button topLeftButton = null;
    Button topRightButton = null;
    Button centerButton = null;
    Button bottomLeftButton = null;
    Button bottomRightButton = null;
    TextView labelText = null;
    EditText editText = null;
    public int contor = 0;
    public static final int SERVICE_STARTED = 1;
    public static final int SERVICE_STOPPED = 0;
    public int serviceStatus = SERVICE_STOPPED;
    public static final int SECONDARY_ACTIVITY_REQUEST_CODE = 2;
    public static final int THRESHOLD = 3;
    private IntentFilter intentFilter = new IntentFilter();
    public static final String actionType = "firstAction";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var05_main);

        navigateButton = findViewById(R.id.navigate);
        topLeftButton = findViewById(R.id.top_left);
        topRightButton = findViewById(R.id.top_right);
        centerButton = findViewById(R.id.center);
        bottomLeftButton = findViewById(R.id.bottom_left);
        bottomRightButton = findViewById(R.id.bottom_right);
        editText = findViewById(R.id.edit_text);

        navigateButton.setOnClickListener(buttonClickListener);
        topLeftButton.setOnClickListener(buttonClickListener);
        topRightButton.setOnClickListener(buttonClickListener);
        centerButton.setOnClickListener(buttonClickListener);
        bottomLeftButton.setOnClickListener(buttonClickListener);
        bottomRightButton.setOnClickListener(buttonClickListener);

        intentFilter.addAction(actionType);
    }

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("[Message received]", intent.getStringExtra("message"));
        }
    }

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String str = editText.getText().toString();
            switch (view.getId()) {
                case R.id.top_left:
                    str += ", Top Left";
                    editText.setText(str);
                    contor++;
                    Toast.makeText(getApplicationContext(), String.valueOf(contor), Toast.LENGTH_LONG).show();
                    break;
                case R.id.top_right:
                    str += ", Top Right";
                    editText.setText(str);
                    contor++;
                    Toast.makeText(getApplicationContext(), String.valueOf(contor), Toast.LENGTH_LONG).show();
                    break;
                case R.id.center:
                    str += ", Center";
                    editText.setText(str);
                    contor++;
                    Toast.makeText(getApplicationContext(), String.valueOf(contor), Toast.LENGTH_LONG).show();
                    break;
                case R.id.bottom_left:
                    str += ", Bottom Left";
                    editText.setText(str);
                    contor++;
                    Toast.makeText(getApplicationContext(), String.valueOf(contor), Toast.LENGTH_LONG).show();
                    break;
                case R.id.bottom_right:
                    str += ", Bottom Right";
                    editText.setText(str);
                    contor++;
                    Toast.makeText(getApplicationContext(), String.valueOf(contor), Toast.LENGTH_LONG).show();
                    break;
                case R.id.navigate:
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var05SecondaryActivity.class);
                    intent.putExtra("myText", editText.getText().toString());
                    startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);
                    break;
            }
            if (serviceStatus == SERVICE_STOPPED && contor > THRESHOLD) {
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var05Service.class);
                getApplicationContext().startService(intent);
                serviceStatus = SERVICE_STARTED;
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("contor", editText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey("contor")) {
            Toast.makeText(getApplicationContext(), savedInstanceState.getString("contor"), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
            editText.setText("");
            contor = 0;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(this, PracticalTest01Var05Service.class);
        stopService(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }
}
