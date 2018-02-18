package ca.prieto.hepro_tech.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.onesignal.OneSignal;

import Controller.HeprotechApi;
import Model.HeprotechDevice;
import ca.prieto.hepro_tech.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Switch enableSwitch;
    HeprotechApi service;
    TextView deviceTitle;
    TextView lastUpdated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        deviceTitle = findViewById(R.id.deviceTitle);
        lastUpdated = findViewById(R.id.lastUpdated);

        enableSwitch = findViewById(R.id.enableSwitch);
        enableSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    service.armHeprotechDevice().enqueue(new Callback<HeprotechDevice>() {
                        @Override
                        public void onResponse(Call<HeprotechDevice> call, Response<HeprotechDevice> response) {
                        }

                        @Override
                        public void onFailure(Call<HeprotechDevice> call, Throwable t) {

                        }
                    });
                } else {
                    service.disarmHeprotechDevice().enqueue(new Callback<HeprotechDevice>() {
                        @Override
                        public void onResponse(Call<HeprotechDevice> call, Response<HeprotechDevice> response) {

                        }

                        @Override
                        public void onFailure(Call<HeprotechDevice> call, Throwable t) {

                        }
                    });
                }

            }
        });

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
        callHeprotechApi();
    }

    public void callHeprotechApi() {
        buildHeprotechApi();
        final HeprotechDevice[] device = new HeprotechDevice[1];

        service.getHeprotechDevice().enqueue(new Callback<HeprotechDevice>() {
            @Override
            public void onResponse(Call<HeprotechDevice> call, Response<HeprotechDevice> response) {
                device[0] = response.body();

                deviceTitle.setText(device[0].getName());
                lastUpdated.setText(device[0].getLatestEventTimestamp().toString());

                if (device[0].isArmed()) {
                    enableSwitch.setChecked(true);
                } else {
                    enableSwitch.setChecked(false);
                }
            }

            @Override
            public void onFailure(Call<HeprotechDevice> call, Throwable t) {
                Log.d("Failed at calling API", String.valueOf(t));
                Toast toast = Toast.makeText(MainActivity.this, "failed to load API", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    public void buildHeprotechApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.hepro.tech:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(HeprotechApi.class);
    }
}
