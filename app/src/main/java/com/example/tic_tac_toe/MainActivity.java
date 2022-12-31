package com.example.tic_tac_toe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btn_start;
    private TextView textView;
    private Spinner spinner;

    private int language = 0;

    private int player1;
    private int player2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        spinner = findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.spinner_array, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0, false);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(position == 0) {
                    language = 0;
                    btn_start.setText("開始遊戲");
                    textView.setText("PK 結果");
                }
                else if(position == 1) {
                    language = 1;
                    btn_start.setText("Start Game");
                    textView.setText("PK Result");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn_start = findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                intent.putExtra("language", language);
                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data ){
        super.onActivityResult(requestCode, resultCode, data);
        if(data == null) return;

        if(requestCode == 1) {
            if (resultCode == 101) {
                Bundle b = data.getExtras();
                player1 = b.getInt("玩家1");
                player2 = b.getInt("玩家2");
                if (language == 0)
                    textView.setText("PK結果\n玩家1 得分:" + player1 + "\n玩家2 得分:" + player2);
                else if (language == 1)
                    textView.setText("PK Result\nPlayer1 Score:" + player1 + "\nPlayer2 Score:" + player2);
            }
        }
    }

}