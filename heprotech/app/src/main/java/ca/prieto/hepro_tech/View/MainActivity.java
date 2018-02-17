package ca.prieto.hepro_tech.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ca.prieto.Services.RegistrationIntentService;
import ca.prieto.hepro_tech.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
    }
}
