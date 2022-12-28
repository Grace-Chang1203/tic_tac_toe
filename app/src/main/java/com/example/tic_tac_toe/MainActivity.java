package com.example.tic_tac_toe;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btn_start;
    private TextView textView,tv_p1,tv_p2;
    private Spinner language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        tv_p1 = findViewById(R.id.tv_p1);
        tv_p2 = findViewById(R.id.tv_p2);

        language = findViewById(R.id.language);

        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.language_array, android.R.layout.simple_spinner_item);
        language.setAdapter(adapter);
        language.setSelection(0, false);

        btn_start = findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this,MainActivity2.class),1);
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data ){
        super.onActivityResult(requestCode, resultCode, data);
        if(data == null) return;

        if (requestCode == 1){
            if (resultCode == 101){
                Bundle b = data.getExtras();
                String player1 = b.getString("玩家1");
                String player2 = b.getString("玩家2");
                textView.setText("PK結果");
                tv_p1.setText(String.format("%s",player1));
                tv_p2.setText(String.format("%s",player2));
            }
        }

    }
}