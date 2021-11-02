package com.example.socket_io_java;

import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private Socket mSocket;{
        try {
            mSocket = IO.socket("https://appio-chat.herokuapp.com/clients");
        } catch (URISyntaxException e) {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSocket.on("client chat message", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    System.out.println(Arrays.stream(args).iterator().next().toString());
                }
                System.out.println("getting!");
            }
        });
        mSocket.connect();
        mSocket.emit("joinRoom", "clients-chat");
        mSocket.emit("client chat message","ahmed");
    }
}
