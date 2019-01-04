package com.example.casper.soruuygulamasi;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RelativeLayout kolay,orta,zor;
    TextView isim,b_kolay,b_orta,b_zor;
    ImageView isimduz;
    DataHelper dataHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataHelper = new DataHelper(this);

        kolay = (RelativeLayout) findViewById(R.id.kolay);
        orta = (RelativeLayout) findViewById(R.id.orta);
        zor = (RelativeLayout) findViewById(R.id.zor);
        isimduz = (ImageView) findViewById(R.id.kadi_duzenle);
        isim = (TextView) findViewById(R.id.kullaniciadi);
        b_kolay = (TextView) findViewById(R.id.b_kolay);
        b_orta = (TextView) findViewById(R.id.b_orta);
        b_zor = (TextView) findViewById(R.id.b_zor);

        b_kolay.setText(""+dataHelper.receiveDataInt("En İyi Kolay",0));
        b_orta.setText(""+dataHelper.receiveDataInt("En İyi Orta",0));
        b_zor.setText(""+dataHelper.receiveDataInt("En İyi Zor",0));

        isimduz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog();
            }
        });

        kolay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, KolayActivity.class));
            }
        });

        orta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OrtaActivity.class));
            }
        });

        zor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ZorActivity.class));
            }
        });

    }

    public void alertDialog(){
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.uyaripenceresi,null);
        final EditText name = (EditText) view.findViewById(R.id.name);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setMessage("Kullanıcı Adınızı Giriniz")
                .setView(view)
                .setPositiveButton("Kaydet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String s = name.getText().toString();
                        dataHelper.saveDataString("İsim",s);
                        isim.setText(dataHelper.receiveDataString("İsim","Kullanıcı"));
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}


