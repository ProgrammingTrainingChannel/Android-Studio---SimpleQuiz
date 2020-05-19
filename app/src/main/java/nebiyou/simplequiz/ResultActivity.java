package nebiyou.simplequiz;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    TextView lbltext,lblResult;
    Button btnMobilData,btnWiFi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        btnMobilData=(Button)findViewById(R.id.btnMobilData);
        btnWiFi=(Button)findViewById(R.id.btnWiFi);

        lblResult=(TextView) findViewById(R.id.lblResult);

        int totalQuestion = getIntent().getIntExtra("totalQuestion", 0);
        int mark = getIntent().getIntExtra("mark", 0);
        lblResult.setText(mark + " out of " + totalQuestion);
    }
    public void btnMobilData(android.view.View Button){
        try{
            ConnectivityManager connectivity = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

            //If connectivity object is not null
            if (connectivity != null) {
                //Get network info - Data Connection internet access
                NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                if (info != null) {
                    //Look for whether device is currently connected to Data Connection network
                    if (info.isConnected()) {
                        Toast.makeText(this, "Data Connection Available", Toast.LENGTH_LONG).show();
                        return;
                    }
                }
            }

            Toast.makeText(this, "Data Connection NOT Available", Toast.LENGTH_LONG).show();
        }
        catch(Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    public void btnWiFi(android.view.View Button){
        try{
            ConnectivityManager connectivity = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

            //If connectivity object is not null
            if (connectivity != null) {
                //Get network info - WIFI internet access
                NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                if (info != null) {
                    //Look for whether device is currently connected to WIFI network
                    if (info.isConnected()) {
                        Toast.makeText(this, "WiFi Available", Toast.LENGTH_LONG).show();
                        return;
                    }
                }
            }

            Toast.makeText(this, "WiFi NOT Available", Toast.LENGTH_LONG).show();
        }
        catch(Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}
