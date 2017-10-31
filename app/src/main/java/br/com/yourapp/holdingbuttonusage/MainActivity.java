package br.com.yourapp.holdingbuttonusage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import br.com.yourapp.holdingbutton.HoldingButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HoldingButton holdingButton = findViewById(R.id.holdingButton);
        holdingButton.setOnFinishEventListener(() -> {
            Toast.makeText(MainActivity.this, "Finished", Toast.LENGTH_LONG).show();
        });
    }
}
