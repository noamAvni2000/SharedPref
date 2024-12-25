package com.example.sharedpref;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView greetingTextView;
    private EditText nameEditText;
    private Button submitButton;

    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "userPrefs";
    private static final String KEY_NAME = "userName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        greetingTextView = findViewById(R.id.greetingTextView);
        nameEditText = findViewById(R.id.nameEditText);
        submitButton = findViewById(R.id.submitButton);

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        String savedName = sharedPreferences.getString(KEY_NAME, "###");

        greetingTextView.setText("Hello " + savedName);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = nameEditText.getText().toString().trim();

                if (!userName.isEmpty()) {
                    saveName(userName);
                    greetingTextView.setText("Hello " + userName);
                } else {
                    greetingTextView.setText("Hello ###");
                }
            }
        });
    }

    private void saveName(String name) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_NAME, name);
        editor.apply();
    }
}