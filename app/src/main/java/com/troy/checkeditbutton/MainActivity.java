package com.troy.checkeditbutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tvTitle;
    private AppCompatEditText etContentOne;
    private AppCompatEditText etContentTwo;
    private CheckEditButton cbtConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitle = (TextView) findViewById(R.id.tv_title);
        etContentOne = (AppCompatEditText) findViewById(R.id.et_content_one);
        etContentTwo = (AppCompatEditText) findViewById(R.id.et_content_two);
        cbtConfirm = (CheckEditButton) findViewById(R.id.cbt_confirm);

        cbtConfirm.setCheckEditTexts(tvTitle,etContentOne,etContentTwo);
        cbtConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"所以输入框都输入了内容",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
