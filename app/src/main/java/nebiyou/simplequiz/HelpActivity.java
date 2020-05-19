package nebiyou.simplequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class HelpActivity extends AppCompatActivity {
    Button btnHowTo,btnAboutUs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
    }

    public void  BtnHowTo (android.view.View button){
        Intent intent = new Intent(this,HowToActivity.class);
        startActivity(intent);
    }


    public void  btnAboutUs(android.view.View button){
        Intent intent = new Intent(this,AboutAsActivity.class);
        startActivity(intent);
    }

    }
