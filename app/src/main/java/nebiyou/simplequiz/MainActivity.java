package nebiyou.simplequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button BtnQuestions,BtnResult,btnhelp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void  BtnQuestions(android.view.View button){
        Intent intent = new Intent(this,NumberOfQuestionActivity.class);
        startActivity(intent);

    }
    public void  BtnResult(android.view.View button){
        Intent intent = new Intent(this,ResultActivity.class);
        startActivity(intent);

    }
    public void  btnhelp(android.view.View button){
        Intent intent = new Intent(this,HelpActivity.class);
        startActivity(intent);

    }

}
