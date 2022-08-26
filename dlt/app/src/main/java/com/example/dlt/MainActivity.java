package com.example.dlt;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import com.google.gson.GsonBuilder;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import android.os.Bundle;

import javax.net.SocketFactory;

public class MainActivity extends AppCompatActivity {
    PrintWriter out;
    String prop=null;
    String clothes=null;
    String foreground=null;
    String mask=null;
    String background=null;


    String ip = "192.168.0.30";
    int port = 8002;
    Socket s ;

    Button btnProp;
    Button btnClothes;
    Button btnForeground;
    Button btnMask;
    Button btnBackground;

    int count = 0 ;
    int countProp = 0 ;
    int countClothes = 0 ;
    int countForeground = 0 ;
    int countMask = 0 ;

    //Socket serverSocket = null;

    //private static ServerSocket serverSocket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewElement();
        connect();

        btnBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count == 0) {
                    background = "down.jpg";
                    count=(count+1)%4;
                }
                else if (count == 1) {
                    background = "gym.jpg";
                    count=(count+1)%4;
                }
                else if (count == 2) {
                    background = "playground.jpg";
                    count=(count+1)%4;
                }
                else if (count == 3) {
                    background = "room.jpg";
                    count=(count+1)%4;
                }


                SendMessage(setMaterialMsg()); // 連線成功更換背景
            }
        });

        btnProp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (countProp == 0) {
                    prop = "babyball.png";
                    countProp=(countProp+1)%4;
                }
                else if (countProp == 1) {
                    prop = "banana.png";
                    countProp=(countProp+1)%4;
                }
                else if (countProp == 2) {
                    prop = "baseball.png";
                    countProp=(countProp+1)%4;
                }
                else if (countProp == 3) {
                    prop = "gun.png";
                    countProp=(countProp+1)%4;
                }

                SendMessage(setMaterialMsg()); // 連線成功更換背景
            }
        });

        btnClothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (countClothes == 0) {
                    clothes = "Hakuho_body.png";
                    countClothes=(countClothes+1)%4;
                }
                else if (countClothes == 1) {
                    clothes = "howl.png";
                    countClothes=(countClothes+1)%4;
                }
                else if (countClothes == 2) {
                    clothes = "kingdress.png";
                    countClothes=(countClothes+1)%4;
                }
                else if (countClothes == 3) {
                    clothes = "mom.png";
                    countClothes=(countClothes+1)%4;
                }


                SendMessage(setMaterialMsg());
            }
        });

        btnForeground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (countForeground == 0) {
                    foreground = "anywheredoor.png";
                    countForeground=(countForeground+1)%4;
                }
                else if (countForeground == 1) {
                    foreground = "box.png";
                    countForeground=(countForeground+1)%4;
                }
                else if (countForeground == 2) {
                    foreground = "catbus.png";
                    countForeground=(countForeground+1)%4;
                }
                else if (countForeground == 3) {
                    foreground = "fatcat.png";
                    countForeground=(countForeground+1)%4;
                }

                SendMessage(setMaterialMsg());
            }
        });

        btnMask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (countMask == 0) {
                    mask = "5566.png";
                    countMask=(countMask+1)%4;
                }
                else if (countMask == 1) {
                    mask = "deer.png";
                    countMask=(countMask+1)%4;
                }
                else if (countMask == 2) {
                    mask = "gdchen.png";
                    countMask=(countMask+1)%4;
                }
                else if (countMask == 3) {
                    mask = "kitty.png";
                    countMask=(countMask+1)%4;
                }
                SendMessage(setMaterialMsg());
            }
        });
    }

    public void connect()
    {
        Runnable runnable1=new Runnable() {
            @Override
            public void run() {
                try{
                    s = new Socket(ip, port);
                }
                catch ( IOException e)
                {
                    e.printStackTrace();
                }
            }
        };
        Thread thread1 = new Thread(runnable1);
        thread1.start();
    }


    public void SendMessage(final String msg) {

        Runnable runnable2 = new Runnable () {
            @Override
            public void run() {

                try {
                    out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())), true);
                    out.println(msg);
                    Log.d("socket", msg);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }

        };

        Thread thread2 = new Thread(runnable2);

        thread2.start();

    }

    private String setMaterialMsg() {
        HashMap<String, Object> attribute = new HashMap<String, Object>();
        attribute.put("instruction", "Scene");
        HashMap<String, Object> materials = new HashMap<String, Object>();
        materials.put("background", background==null?null:background); // 背景
        materials.put("foreground", foreground==null?null:foreground); // 前景
        materials.put("prop", prop==null?null:prop); // 道具
        materials.put("clothes", clothes==null?null:clothes); // 衣服
        materials.put("mask", mask==null?null:mask); // 面具
        materials.put("shift_x", 80.0);
        materials.put("shift_y", 80.0);
        materials.put("scale", 1.0);
        materials.put("puppet", null);
        attribute.put("material", materials);
        Log.e("Bluesnowtest", new GsonBuilder().create().toJson(attribute));
        return new GsonBuilder().create().toJson(attribute);

    }

    private void initViewElement() {
        btnProp = (Button) findViewById(R.id.btnProp);
        btnClothes = (Button) findViewById(R.id.btnClothes);
        btnForeground = (Button) findViewById(R.id.btnForeground);
        btnMask = (Button) findViewById(R.id.btnMask);
        btnBackground = (Button) findViewById(R.id.btnBackground);

    }
}