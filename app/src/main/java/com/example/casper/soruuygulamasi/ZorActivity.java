package com.example.casper.soruuygulamasi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ZorActivity extends AppCompatActivity {

    DataHelper dataHelper;
    TextView sorularr,puann,isim_oyun,gecsayi;
    ImageButton dogrug, yanlısg,anasayfag;

    RelativeLayout gec;
    int gecc;


    Random r = new Random();
    int n;
    int points=0;
    int SKIP_NUMBER=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zor);

        dataHelper = new DataHelper(this);
        gecsayi = (TextView) findViewById(R.id.gecsayi);
        sorularr = (TextView) findViewById(R.id.sorular);
        isim_oyun = (TextView) findViewById(R.id.isim_oyun);
        puann = (TextView) findViewById(R.id.puan);
        dogrug = (ImageButton) findViewById(R.id.dogrug);
        yanlısg = (ImageButton) findViewById(R.id.yanlısg);
        anasayfag = (ImageButton) findViewById(R.id.anasayfayagit);
        gec=(RelativeLayout) findViewById(R.id.gec);

        anasayfag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ZorActivity.this,MainActivity.class));
                finish();
            }
        });

        gecsayi.setText(""+dataHelper.receiveDataInt("Geç", SKIP_NUMBER));
        isim_oyun.setText(dataHelper.receiveDataString("İsim","Kullanici"));

        //Sorular
        final String[] arrayQ = {getString(R.string.z1),getString(R.string.z2),getString(R.string.z3),getString(R.string.z4),getString(R.string.z5),getString(R.string.z6),
                getString(R.string.z7),getString(R.string.z8),getString(R.string.z9),getString(R.string.z10)};

        final Boolean[] arrayA ={true,false,false,true,true,true,false,false,true,true};


        final ArrayList<String> sorular = new ArrayList<String>(Arrays.asList(arrayQ));
        final ArrayList<Boolean>cevaplar = new ArrayList<Boolean>(Arrays.asList(arrayA));

        n = r.nextInt(sorular.size());
        sorularr.setText(sorular.get(n)); // soruların karışık olmasını sağlıyor

        gec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gecsayi.setText(""+dataHelper.receiveDataInt("Geç", SKIP_NUMBER));
                gecc = dataHelper.receiveDataInt("Geç",SKIP_NUMBER);
                if (dataHelper.receiveDataInt("Geç",SKIP_NUMBER) == 0){
                    Toast.makeText(ZorActivity.this,"0 Geçme Hakkınız Var",Toast.LENGTH_SHORT).show();
                } else {
                    gecc--;
                    sorular.remove(n);
                    cevaplar.remove(n);
                    if (sorular.size()==0){
                        result();
                    } else {
                        n = r.nextInt(sorular.size());
                        sorularr.setText(sorular.get(n));
                        dataHelper.saveDataInt("Geç",gecc);

                    }
                }
            }
        });


        dogrug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cevaplar.get(n)) {
                    points++;
                    sorular.remove(n);
                    cevaplar.remove(n);
                    puann.setText("Skor:  "+points);
                    if (sorular.size() == 0 ) {
                        result();
                    } else {
                        n = r.nextInt(sorular.size());
                        sorularr.setText(sorular.get(n));
                    }
                } else {
                    result();
                }
            }
        });

        yanlısg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cevaplar.get(n)) {
                    points++;
                    sorular.remove(n);
                    cevaplar.remove(n);
                    puann.setText("Skor:  "+points);
                    if (sorular.size() == 0 ) {
                        result();
                    } else {
                        n = r.nextInt(sorular.size());
                        sorularr.setText(sorular.get(n));
                    }
                } else {
                    result();
                }
            }
        });
    }

    private void result() {
        dataHelper.saveDataInt("PUAN Zor",points);
        startActivity(new Intent(ZorActivity.this, ZorResultActivity.class));
        finish();
    }
}
