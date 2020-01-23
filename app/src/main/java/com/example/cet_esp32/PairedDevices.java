package com.example.cet_esp32;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.Set;
import androidx.annotation.Nullable;







    public class PairedDevices extends ListActivity {

        ArrayAdapter<String>arrayAdapter;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // INTERFACE
            ListView lv = getListView();
            LayoutInflater inflater = getLayoutInflater();
            View header = inflater.inflate(R.layout.text_header, lv, false);

            ((TextView) header.findViewById(R.id.textView)).setText("\nPaired Devices\n");
            lv.addHeaderView(header, null, false);

            // BLUETOOTH
            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
            setListAdapter(adapter);

            if(pairedDevices.size()>0){
                for(BluetoothDevice device : pairedDevices){
                    adapter.add(device.getName() + "\n" + device.getAddress());
                }
            }
        }


        @Override
        protected void onListItemClick(ListView l, View v, int position, long id) {
            super.onListItemClick(l, v, position, id);


            // Note: position-1 is used because a title has been added and the position value is shifted one unit  String item = (String) getListAdapter().getItem(position-1);
            String item = (String) getListAdapter().getItem(position-1);
            String devName = item.substring(0, item.indexOf("\n"));
            String devAddress = item.substring(item.indexOf("\n")+1, item.length());

            Log.d("SelectedDevice", devAddress);

            Intent returnIntent = new Intent();
            returnIntent.putExtra("btDevName", devName);
            returnIntent.putExtra("btDevAddress", devAddress);
            setResult(RESULT_OK, returnIntent);
            finish();
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            super.onCreateOptionsMenu(menu);
            getMenuInflater().inflate(R.menu.menu_paired_devices, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();

            if(id == R.id.action_settings){
                return true;
            }

            return super.onOptionsItemSelected(item);
        }
    }


