package nebiyou.simplequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GeneretorActivity extends AppCompatActivity {
    Button btnGenarte;
    EditText txtPassword,txtUserId, txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generetor);
        txtPassword=(EditText)findViewById(R.id.txtPassword);
        txtUserId=(EditText)findViewById(R.id.txtUserId);
        txtResult=(EditText)findViewById(R.id.txtResult);
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
    public void Generate(android.view.View btn){
        String combinedPassword = txtUserId.getText().toString() + txtPassword.getText().toString();
        String hashedPassword = GenerateMD5(combinedPassword);

        txtResult.setText(hashedPassword);
    }


}
