package com.example.tic_tac_toe;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btn_start;
    private TextView textView;
    private EditText ed_p1,ed_p2;
    private Spinner language;
    private RadioGroup rg1,rg2;
    private RadioButton rb1,rb2,rb3,rb4;

    private int p1 = 1; // 1 is X,0 is O
    private int p2 = 0;
    private String p1_name;
    private String p2_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        ed_p1 = findViewById(R.id.ed_p1);
        ed_p2 = findViewById(R.id.ed_p2);

        language = findViewById(R.id.language);

        rg1 = findViewById(R.id.radioGroup);
        rg2 = findViewById(R.id.radioGroup1);
        rb1 = findViewById(R.id.radioButton1);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        rb4 = findViewById(R.id.radioButton4);

        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.radioButton1:
                        rb4.setChecked(true);
                        p1 = 1;
                        break;
                    case R.id.radioButton2:
                        rb3.setChecked(true);
                        p1 = 0;
                        break;
                }
            }
        });


        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.radioButton3:
                        rb2.setChecked(true);
                        p1 = 1;
                        break;
                    case R.id.radioButton4:
                        rb1.setChecked(true);
                        p1 = 0;
                        break;

                }
            }
        });


        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.language_array, android.R.layout.simple_spinner_item);
        language.setAdapter(adapter);
        language.setSelection(0, false);



        btn_start = findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p1_name = ed_p1.getText().toString();
                p2_name = ed_p2.getText().toString();
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                Bundle bundle = new Bundle();
                bundle.putInt("p1", p1);
                bundle.putInt("p2", p2);
                bundle.putString("p1_name", p1_name);
                bundle.putString("p2_name", p2_name);
                intent.putExtras(bundle);
                startActivityForResult(intent,1);
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
                //tv_p1.setText(String.format("%s",player1));
                //tv_p2.setText(String.format("%s",player2));
            }
        }

    }
}