package com.example.usr0200393.retrofit18test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


// Test用APIはこちらを使用
// http://weather.livedoor.com/weather_hacks/webservice

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startButton = (Button)findViewById(R.id.startButton);
        textView = (TextView) findViewById(R.id.responseText);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("");
                fetch();
            }
        });
    }

    private void fetch() {
        TestApi api = ServiceGenerator.getService(TestApi.class);
        api.getPinpointLocations(400040, new Callback<Weather>() {
            @Override
            public void success(Weather weather, Response response) {
                if (weather != null) {
                    for (PinpointLocation pinpointLocation : weather.pinpointLocations) {
                        Log.d("rtTest", pinpointLocation.name);
                        String text = String.format("%s\n%s", textView.getText(), pinpointLocation.name);
                        textView.setText(text);
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("rtTest", "error");
                if (error.getResponse() == null) {
                    Log.d("rtTest", error.getMessage());
                } else {
                    int statusCode = error.getResponse().getStatus();
                }
            }
        });
    }
}
