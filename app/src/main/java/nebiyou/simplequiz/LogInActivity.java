package nebiyou.simplequiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class LogInActivity extends AppCompatActivity {
    Button BtnLogin;
    EditText txtPassword, txtUserName;
    TextView lblUserName, lblPassword;
    CheckBox     checkBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtUserName = (EditText) findViewById(R.id.txtUserName);
        checkBox=(CheckBox)findViewById(R.id.checkBox);

        SharedPreferences prefs = this.getSharedPreferences("LoginSetting",MODE_PRIVATE);

        String Password =prefs.getString("Password","");

        txtPassword.setText(Password);



        SharedPreferences sharedPreferences = this.getSharedPreferences("GeneralSettinng", MODE_PRIVATE);
        String uniqueCode = sharedPreferences.getString("UniqueCode", "");

        if(uniqueCode.equals("")){
            uniqueCode = UUID.randomUUID().toString();
            txtUserName.setText(uniqueCode);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("UniqueCode", uniqueCode);
            editor.apply();
        }
        else{
            txtUserName.setText(uniqueCode);
        }
    }

    public void BtnLogin(android.view.View button) {


        if (txtPassword.getText().toString().equals("")) {
            Toast.makeText(this, "plz Feel The Password. ", Toast.LENGTH_LONG).show();
        }
        else {
            String combinedPassword = txtUserName.getText().toString() + "abcd";
            String hashedPassword = GenerateMD5(combinedPassword);


        if(checkBox.isChecked()){
            SharedPreferences prefs= this.getSharedPreferences("LoginSetting",MODE_PRIVATE);
            SharedPreferences.Editor editor=prefs.edit();


            editor.putString("Password",txtPassword.getText().toString());
            editor.apply();

            Toast.makeText(this,"Your ID Password Is Saved.",Toast.LENGTH_LONG ).show();

            if(hashedPassword.equals(txtPassword.getText().toString())){
                Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                startActivity(intent);
            }

            else{
                //show error toast
                Toast.makeText(this,"Incorect Password",Toast.LENGTH_LONG).show();
            }
        }
        else{

            if(hashedPassword.equals(txtPassword.getText().toString())){
                Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                startActivity(intent);
            }

            else{
                //show error toast
                Toast.makeText(this,"Incorect Password",Toast.LENGTH_LONG).show();
            }
        }




        }
    }


    public void BtnShowGenerator(android.view.View button) {
        Intent intent = new Intent(LogInActivity.this, GeneretorActivity.class);
        startActivity(intent);
    }

    public String GenerateMD5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}

