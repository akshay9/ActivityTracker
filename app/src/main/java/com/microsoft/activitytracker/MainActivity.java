package com.microsoft.activitytracker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.ActivityRecognition;
import com.google.android.gms.location.ActivityRecognitionClient;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    GoogleApiClient mApiClient;
    static String CURRENT_ACTIVITY = "UNKNOWN";
    ActivityRecognitionClient mActivityRecognitionClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mActivityRecognitionClient = new ActivityRecognitionClient(this);
//        mApiClient = mActivityRecognitionClient.asGoogleApiClient();
//        mApiClient.registerConnectionCallbacks(this);
//        mApiClient.registerConnectionFailedListener(this);
        mApiClient = new GoogleApiClient.Builder(this)
                .addApi(ActivityRecognition.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        mApiClient.connect();

        final Handler handler = new Handler();
        final int delay = 1000; //milliseconds

        handler.postDelayed(new Runnable(){
            public void run(){
                //do something
                TextView tv = MainActivity.this.findViewById(R.id.textV);
                tv.setText(MainActivity.CURRENT_ACTIVITY);
                handler.postDelayed(this, delay);
            }
        }, delay);
}

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d("ActivityRecognition", "Connected");
        Intent intent = new Intent(MainActivity.this, ActivityRecognitionService.class);
        PendingIntent pendingIntent = PendingIntent.getService(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        ActivityRecognition.ActivityRecognitionApi.requestActivityUpdates(mApiClient, 1000, pendingIntent);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d("ActivityRecognition", "Suspended ?" + i);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("ActivityRecognition", "Error:" + connectionResult.getErrorMessage());
    }
}
