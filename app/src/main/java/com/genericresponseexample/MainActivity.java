package com.genericresponseexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;
import android.widget.Toast;

import com.genericresponseretrofit.GenericResponseManager;
import com.genericresponseretrofit.onGenericResponseListener;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.title);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Map<String, String> mapString = new HashMap<>();
        mapString.put("user_id", "29");
        mapString.put("lang_id", "1");
        mapString.put("type", "passenger");
        mapString.put("device_token", "");
        new GenericResponseManager("http://baseurl/api/").getRequest(mapString, "languages", new onGenericResponseListener() {
            @Override
            public void onComplete() {
                Timber.tag(TAG).d("Complete");
                Toast.makeText(MainActivity.this, "Completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(String response) {
                LanguageSelectionModel srt = new Gson().fromJson(response, LanguageSelectionModel.class);
                Timber.tag(TAG).d(response);
                textView.setText(srt.getData().toString());
            }

            @Override
            public void onErrorBody(String response) {
                Timber.tag(TAG).d("Error %s", response);
            }
        });
    }
}