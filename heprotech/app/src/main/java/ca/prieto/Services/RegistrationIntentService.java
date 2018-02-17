package ca.prieto.Services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

import ca.prieto.hepro_tech.R;

/**
 * Created by Jessica on 2018-02-17.
 */

public class RegistrationIntentService extends IntentService {

    private static final String TAG = "RegIntentService";
    private static final String topic = "/topics/hepro-tech";

    public RegistrationIntentService() {
        super("");
    }
    public RegistrationIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String token = "";
        InstanceID instanceID = InstanceID.getInstance(this);
        try {
            token = instanceID.getToken(getString(R.string.gcm_defaultSenderId),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
        } catch (IOException e) {
            Log.d(TAG, "Failed to complete token refresh", e);
        }

        GcmPubSub subscription = GcmPubSub.getInstance(this);
        try {
            subscription.subscribe(token, topic, null);
        } catch (IOException e) {
            Log.d(TAG, "Failed to subscribe to topic", e);
        }


    }
}
