package svenboom.smsbooommm;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int numberOfMessages = 5;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnSendSMS = (Button) findViewById(R.id.SendSMS);
        TextView displayMessage = (TextView) findViewById(R.id.DisplayText);


        btnSendSMS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    sendSMS();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void sendSMS() throws InterruptedException {
//        final SendMessageManager sendMessageManager = new SendMessageManager(getApplicationContext());
        final SendMessageManager sendMessageManager = new SendMessageManager();
        MessageManager messageManager = new MessageManager();


        TextView messageTime = (TextView) findViewById(R.id.MessageTime);
        numberOfMessages = Integer.parseInt(messageTime.getText().toString());
        EditText phoneNumberEdit = (EditText) findViewById(R.id.PhoneNumber);
        final TextView displayMessage = (TextView) findViewById(R.id.DisplayText);

        String phoneNumberStr = phoneNumberEdit.getText().toString();
        sendMessageManager.setPhoneNumber(phoneNumberStr);
        String message = messageManager.getMessage();
        sendMessageManager.setMessage(message);

        for (i = 1; i <= numberOfMessages; i++) {
            sendMessageManager.setMessageConunt(i+"");

            Handler handler=  new Handler();
            Runnable myRunnable = new Runnable() {
                public void run() {
                    sendMessageManager.messageSending();
                    displayMessage.setText("Message had sent: " + i);
                }
            };
            handler.postDelayed(myRunnable,5000*i);
            //displayMessage.setText("Message had sent: " + i);
        }



    }
}
