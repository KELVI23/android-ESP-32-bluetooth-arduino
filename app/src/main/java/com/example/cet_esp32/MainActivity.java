package com.example.cet_esp32;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Message;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.bluetooth.BluetoothAdapter;
import android.os.Handler;
import android.util.Log;
import android.content.Intent;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
/*APP created by Kelvin Musodza*/




    /*Constants*/
    public final int  REQUEST_ENABLE_BT =1;
    public final int SELECT_PAIRED_DEVICE = 2;
    public final int SELECT_DISCOVERED_DEVICE = 2;

    /*Variables*/
    public String Creator = "Kelvin Musodza \n  Contact: 778-318-7940";
    public static BluetoothAdapter bluetoothAdapter;
    private ArrayList<String> arrayAdapter;
    private String macConnectedDevice;   //mac number of device

    //UI
    static TextView tvStatusMessage;
    static TextView tvTextSpace;

    //Threads
    ConnectionThread connect;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //email toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        // UI
        tvStatusMessage = findViewById(R.id.statusMessage);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // Check for Bluetooth
        if(bluetoothAdapter == null){
            Toast.makeText(this, "Bluetooth not available", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }


        // If it exists, enable
        if(!bluetoothAdapter.isEnabled()){
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }



}

//edit
//pair or select device
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_ENABLE_BT){
            if(resultCode == RESULT_OK){
                Toast.makeText(this, "Bluetooth enabled", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Bluetooth not available", Toast.LENGTH_SHORT).show();
            }
            finish();
        }
        else if(requestCode == SELECT_PAIRED_DEVICE || requestCode == SELECT_DISCOVERED_DEVICE){
            if(resultCode == RESULT_OK){
                assert data != null;
                tvStatusMessage.setText(String.format("Selected: %s → %s",
                        data.getStringExtra("btDevName"), data.getStringExtra("btDevAddress")));
                macConnectedDevice = data.getStringExtra("btDevAddress");
                connect = new ConnectionThread(data.getStringExtra("btDevAddress"));

            }else{
                tvStatusMessage.setText(R.string.any_connected_devices);
            }
        }

        // MISSING TO CONFIGURE WHAT HAPPENS IF USER Deactivates BLUETOOTH
    }

    public void searchPairedDevices(View view){
        Intent searchPairedDevicesIntent = new Intent(this, PairedDevices.class);
        startActivityForResult(searchPairedDevicesIntent, SELECT_PAIRED_DEVICE);
    }

    public void discoverDevices(View view){
        Intent searchPairesDevicesIntent = new Intent(this, DiscoveredDevices.class);
        startActivityForResult(searchPairesDevicesIntent, SELECT_DISCOVERED_DEVICE);
    }


    public void enableVisibility(View view) {
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 30);
        startActivity(discoverableIntent);
    }

    public void waitConnection(View view){
        connect = new ConnectionThread();
        connect.start();
    }

    public void connect(View view){
        Log.d("MAC", macConnectedDevice);
        connect = new ConnectionThread(macConnectedDevice);
        connect.start();
    }

    static public Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {

            Bundle bundle = msg.getData();
            byte[] data = bundle.getByteArray("data");
            String dataString = new String(data);

            if(dataString.equals("---N")) tvStatusMessage.setText("A connection error has occurred");
            else if(dataString.equals("---S")) tvStatusMessage.setText("Connected");
            else if(dataString.equals("---TO")) tvStatusMessage.setText("Connection Timeout");
            else tvStatusMessage.setText(new String(data)); //BLUETOOTH MESSAGE RECEIPT

        }

    };

    public void sendMessage(View view){
        EditText messageBox = (EditText) findViewById(R.id.editText_MessageBox);
        String messageString = messageBox.getText().toString();
        byte[] data = messageString.getBytes();
        connect.write(data);
    }






//Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem logIn) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//
//        //String id = logIn.getItemId(R.id.LogIn);
//
//
//
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.LogIn){
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
////handle signin
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
//        if (requestCode == RC_SIGN_IN) {
//            // The Task returned from this call is always completed, no need to attach
//            // a listener.
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            handleSignInResult(task);
//        }
//    }
//
//    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
//        try {
//            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
//
//            // Signed in successfully, show authenticated UI.
//            updateUI(account);
//        } catch (ApiException e) {
//            // The ApiException status code indicates the detailed failure reason.
//            // Please refer to the GoogleSignInStatusCodes class reference for more information.
//            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
//            updateUI(null);
//        }
//    }
}