package ro.pub.cs.systems.eim.practicaltest01var05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PracticalTest01Var05SecondaryActivity extends AppCompatActivity {

    EditText result = null;
    Button correctButton = null;
    Button incorrectButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var05_secondary);

        result = findViewById(R.id.result);
        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey("myText")) {
            String expression = intent.getStringExtra("myText");
            result.setText(expression);
        }

        correctButton = findViewById(R.id.correct);
        incorrectButton = findViewById(R.id.incorect);
        correctButton.setOnClickListener(buttonClickListener);
        incorrectButton.setOnClickListener(buttonClickListener);
    }

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.correct:
                    setResult(RESULT_OK, null);
                    break;
                case R.id.incorect:
                    setResult(RESULT_CANCELED, null);
                    break;
            }
            finish();
        }
    }
}
