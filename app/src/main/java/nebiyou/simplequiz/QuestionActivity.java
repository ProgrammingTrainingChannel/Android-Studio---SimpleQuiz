package nebiyou.simplequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {
    Button btnNext;
    RadioButton rdbtnC, rdbtnB, rdbtnA;

    ArrayList<String> questions = new ArrayList<>();
    int index = 0;

    TextView lblQuestion;
    int answerCode = 0;

    int mark = 0;
    int NumberOfQuestions= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        lblQuestion = (TextView) findViewById(R.id.lblQuestion);
        btnNext = (Button) findViewById(R.id.btnNext);
        rdbtnA = (RadioButton) findViewById(R.id.rdbtnA);
        rdbtnB = (RadioButton) findViewById(R.id.rdbtnB);
        rdbtnC = (RadioButton) findViewById(R.id.rdbtnC);

        try {
            ReadQuestions("questions.txt");
            NumberOfQuestions = getIntent().getIntExtra("NumberOfQuestion", 0);

            if(NumberOfQuestions > 0){
                String[] singleQuestion = questions.get(index).split(",");

                lblQuestion.setText(singleQuestion[1]);

                String choice = singleQuestion[2].replace("-1", "");
                rdbtnA.setText(choice);

                choice = singleQuestion[3].replace("-1", "");
                rdbtnB.setText(choice);

                choice = singleQuestion[4].replace("-1", "");
                rdbtnC.setText(choice);

                if (singleQuestion[2].contains("-1") == true) {
                    answerCode = 1;
                } else if (singleQuestion[3].contains("-1") == true) {
                    answerCode = 2;
                } else if (singleQuestion[4].contains("-1") == true) {
                    answerCode = 3;
                }
            }
            else {Toast.makeText(this,"Feel How Many Questions You Need.",Toast.LENGTH_LONG).show();}
        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void ReadQuestions(String filename) {
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(this.getAssets().open(filename), "UTF-8"));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                questions.add(mLine);
            }
        } catch (IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
    }

    public void BtnNext(android.view.View Button) {


        if (rdbtnA.isChecked() == false && rdbtnB.isChecked() == false && rdbtnC.isChecked() == false) {
            Toast.makeText(this, "feel the answer plz", Toast.LENGTH_LONG).show();
        } else {
            if (rdbtnA.isChecked() == true && answerCode == 1) {
                Toast.makeText(this, "Correct Answer", Toast.LENGTH_LONG).show();
                mark++;
            } else if (rdbtnB.isChecked() == true && answerCode == 2) {
                Toast.makeText(this, "Correct Answer", Toast.LENGTH_LONG).show();
                mark++;
            } else if (rdbtnC.isChecked() == true && answerCode == 3) {
                Toast.makeText(this, "Correct Answer", Toast.LENGTH_LONG).show();
                mark++;
            } else {
                Toast.makeText(this, "Incorrect Answer", Toast.LENGTH_LONG).show();
            }

            index = index + 1;

            if (index >= NumberOfQuestions) {
                Toast.makeText(this, "the end", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(this,ResultActivity.class);
                intent.putExtra("totalQuestion",NumberOfQuestions);
                intent.putExtra("mark", mark);

                startActivity(intent);
            } else {
                String[] singleQuestion = questions.get(index).split(",");

                lblQuestion.setText(singleQuestion[1]);
                String choice = singleQuestion[2].replace("-1", "");
                rdbtnA.setText(choice);

                choice = singleQuestion[3].replace("-1", "");
                rdbtnB.setText(choice);

                choice = singleQuestion[4].replace("-1", "");
                rdbtnC.setText(choice);

                if (singleQuestion[2].contains("-1") == true) {
                    answerCode = 1;
                } else if (singleQuestion[3].contains("-1") == true) {
                    answerCode = 2;
                } else if (singleQuestion[4].contains("-1") == true) {
                    answerCode = 3;
                }

            }
        }
    }


}


