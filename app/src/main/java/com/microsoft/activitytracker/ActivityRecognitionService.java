package com.microsoft.activitytracker;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

import java.util.List;

public class ActivityRecognitionService extends IntentService {

    public ActivityRecognitionService() {
        super("ActivityRecognition");
    }

    public ActivityRecognitionService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if(ActivityRecognitionResult.hasResult(intent)) {
            ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);
            handleDetectedActivity(result.getProbableActivities());
            handleMostProbableActivity(result.getMostProbableActivity());
        }

    }

    private void handleDetectedActivity(List<DetectedActivity> probableActivities) {
        for (DetectedActivity activity: probableActivities) {
            switch (activity.getType()) {
                case DetectedActivity.IN_VEHICLE:
                    Log.d("ActivityRecognition", "IN_VEHICLE " + activity.getConfidence());
                    MainActivity.CURRENT_ACTIVITY = "IN_VEHICLE";
                    break;
                case DetectedActivity.STILL:
                    Log.d("ActivityRecognition", "STILL " + activity.getConfidence());
                    break;
                case DetectedActivity.ON_FOOT:
                    Log.d("ActivityRecognition", "ON_FOOT " + activity.getConfidence());
                    break;
                case DetectedActivity.RUNNING:
                    Log.d("ActivityRecognition", "RUNNING " + activity.getConfidence());
                    break;
                case DetectedActivity.TILTING:
                    Log.d("ActivityRecognition", "TILTING " + activity.getConfidence());
                    break;
                case DetectedActivity.WALKING:
                    Log.d("ActivityRecognition", "WALKING " + activity.getConfidence());
                    break;
                case DetectedActivity.ON_BICYCLE:
                    Log.d("ActivityRecognition", "ON_BICYCLE " + activity.getConfidence());
                    break;
                case DetectedActivity.UNKNOWN:
                    Log.d("ActivityRecognition", "UNKNOWN " + activity.getConfidence());
                    break;
                default:
                    Log.d("ActivityRecognition", "DEFAULT ");
                    break;
            }
        }
    }

    private void handleMostProbableActivity(DetectedActivity activity){
        switch (activity.getType()) {
            case DetectedActivity.IN_VEHICLE:
                Log.d("ActivityRecognitionM", "IN_VEHICLE " + activity.getConfidence());
                MainActivity.CURRENT_ACTIVITY = "IN_VEHICLE";
                break;
            case DetectedActivity.STILL:
                Log.d("ActivityRecognitionM", "STILL " + activity.getConfidence());
                MainActivity.CURRENT_ACTIVITY = "STILL";
                break;
            case DetectedActivity.ON_FOOT:
                Log.d("ActivityRecognitionM", "ON_FOOT " + activity.getConfidence());
                MainActivity.CURRENT_ACTIVITY = "ON_FOOT";
                break;
            case DetectedActivity.RUNNING:
                Log.d("ActivityRecognitionM", "RUNNING " + activity.getConfidence());
                MainActivity.CURRENT_ACTIVITY = "RUNNING";
                break;
            case DetectedActivity.TILTING:
                Log.d("ActivityRecognitionM", "TILTING " + activity.getConfidence());
                MainActivity.CURRENT_ACTIVITY = "TILTING";
                break;
            case DetectedActivity.WALKING:
                Log.d("ActivityRecognition", "WALKING " + activity.getConfidence());
                MainActivity.CURRENT_ACTIVITY = "WALKING";
                break;
            case DetectedActivity.ON_BICYCLE:
                Log.d("ActivityRecognition", "ON_BICYCLE " + activity.getConfidence());
                MainActivity.CURRENT_ACTIVITY = "ON_BICYCLE";
                break;
            case DetectedActivity.UNKNOWN:
                Log.d("ActivityRecognition", "UNKNOWN " + activity.getConfidence());
                MainActivity.CURRENT_ACTIVITY = "UNKNOWN";
                break;
            default:
                Log.d("ActivityRecognition", "DEFAULT ");
                break;
        }
    }
}
