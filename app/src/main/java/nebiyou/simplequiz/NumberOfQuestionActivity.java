package nebiyou.simplequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NumberOfQuestionActivity extends AppCompatActivity {
    Button btnNext;
    EditText txtNumberOfQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_of_question);

        btnNext = (Button) findViewById(R.id.btnNext);
        txtNumberOfQuestions = (EditText) findViewById(R.id.txtNumberOfQuestions);

    }

    public void BtnNext(android.view.View Button) {
        if (txtNumberOfQuestions.getText().toString().equals("")) {
            Toast.makeText(this, "How Many Questions Do You Need. ", Toast.LENGTH_LONG).show();
        }
        else if(txtNumberOfQuestions.getText().toString().equals(">=9")){
                Toast.makeText(this, "There Are Onel 8 Questions", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(this, QuestionActivity.class);
            intent.putExtra("NumberOfQuestion", Integer.valueOf(txtNumberOfQuestions.getText().toString()));
            startActivity(intent);
        }
    }
}

