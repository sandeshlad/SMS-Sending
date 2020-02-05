package com.example.sms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText number, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number = findViewById(R.id.txt_number);
        message = findViewById(R.id.txt_message);
    }

    public void sendButton(View view) {

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            MyMessage();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 0);
        }

    }

    private void MyMessage() {

        String phone = number.getText().toString().trim();
        String msg = message.getText().toString().trim();


        if (!number.getText().toString().equals("") || message.getText().toString().equals("")) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone, null, msg, null, null);

            Toast.makeText(this, "Message Send", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please Enter Number And Message", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        switch (requestCode) {
            case 0:

                if (grantResults.length >= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)

            {

                MyMessage();
            }
           else
            {

                Toast.makeText(this, "You Dont have permission to make this Action", Toast.LENGTH_SHORT).show();
            }

            break;
        }

    }
}
