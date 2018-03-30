package ro.pub.cs.systems.eim.practicaltest01var04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var04MainActivity extends AppCompatActivity {

    Button navigateButton = null;
    Button topLeftButton = null;
    Button topRightButton = null;
    Button centerButton = null;
    Button bottomLeftButton = null;
    Button bottomRightButton = null;
    TextView labelText = null;
    public int contor = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var04_main);

        navigateButton = findViewById(R.id.navigate);
        topLeftButton = findViewById(R.id.top_left);
        topRightButton = findViewById(R.id.top_right);
        centerButton = findViewById(R.id.center);
        bottomLeftButton = findViewById(R.id.bottom_left);
        bottomRightButton = findViewById(R.id.bottom_right);
        labelText = findViewById(R.id.label);

        navigateButton.setOnClickListener(buttonClickListener);
        topLeftButton.setOnClickListener(buttonClickListener);
        topRightButton.setOnClickListener(buttonClickListener);
        centerButton.setOnClickListener(buttonClickListener);
        bottomLeftButton.setOnClickListener(buttonClickListener);
        bottomRightButton.setOnClickListener(buttonClickListener);
    }

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String str = labelText.getText().toString();
            switch (view.getId()) {
                case R.id.top_left:
                    str += ", Top Left";
                    labelText.setText(str);
                    contor++;
                    Toast.makeText(getApplicationContext(), String.valueOf(contor), Toast.LENGTH_LONG).show();
                    break;
                case R.id.top_right:
                    str += ", Top Right";
                    labelText.setText(str);
                    contor++;
                    Toast.makeText(getApplicationContext(), String.valueOf(contor), Toast.LENGTH_LONG).show();
                    break;
                case R.id.center:
                    str += ", Center";
                    labelText.setText(str);
                    contor++;
                    Toast.makeText(getApplicationContext(), String.valueOf(contor), Toast.LENGTH_LONG).show();
                    break;
                case R.id.bottom_left:
                    str += ", Bottom Left";
                    labelText.setText(str);
                    contor++;
                    Toast.makeText(getApplicationContext(), String.valueOf(contor), Toast.LENGTH_LONG).show();
                    break;
                case R.id.bottom_right:
                    str += ", Bottom Right";
                    labelText.setText(str);
                    contor++;
                    Toast.makeText(getApplicationContext(), String.valueOf(contor), Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("contor", labelText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey("contor")) {
            Toast.makeText(getApplicationContext(), savedInstanceState.getString("contor"), Toast.LENGTH_LONG).show();
        }
    }
}
