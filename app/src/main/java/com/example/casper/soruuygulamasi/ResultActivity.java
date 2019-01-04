package com.example.casper.soruuygulamasi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    DataHelper dataHelper;
    TextView olanpuan,eniyi,tv;
    Button anasay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        dataHelper = new DataHelper(this);

        olanpuan = (TextView) findViewById(R.id.yapilanpuan);
        eniyi = (TextView) findViewById(R.id.eniyiskor);
        tv = (TextView) findViewById(R.id.tv_user);
        anasay = (Button) findViewById(R.id.home);


        int puan = dataHelper.receiveDataInt("PUAN Kolay",0);
        int eni = dataHelper.receiveDataInt("En İyi Kolay",0);

        olanpuan = (TextView) findViewById(R.id.yapilanpuan);
        eniyi = (TextView) findViewById(R.id.eniyiskor);
        tv.setText("İyi Şanslar Bir Dahaki Sefere,  "+dataHelper.receiveDataString("İsim","Kullanici"));
        olanpuan.setText(""+puan);

        if (puan > eni) {
            eni = puan;
            dataHelper.saveDataInt("En İyi Kolay", eni);

        }

        eniyi.setText(""+eni);
        anasay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}