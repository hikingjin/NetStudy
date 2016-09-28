package com.wangjin.net;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.b_request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread() {
                    @Override
                    public void run() {
                        super.run();

                        OkHttpClient client = new OkHttpClient.Builder()
                                .readTimeout(3000, TimeUnit.SECONDS)//设置读取超时时间
                                .writeTimeout(3000, TimeUnit.SECONDS)//设置写的超时时间
                                .connectTimeout(3000, TimeUnit.SECONDS)//设置连接超时时间
                                .build();

                        String url = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=218.4.255.255";
                        Request request = new Request.Builder().url(url).build();
                        Response response = null;
                        try {

                            response = client.newCall(request).execute();
                            Log.e("UUU", response.body().string());

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
    }
}
