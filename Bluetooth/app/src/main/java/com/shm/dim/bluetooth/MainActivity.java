package com.shm.dim.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private BluetoothAdapter bluetoothAdapter;
    private Set<BluetoothDevice> pairedDevices;
    private TextView mDeviceName;
    private ListView mDevicesList;
    private ArrayList devicesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mDeviceName = findViewById(R.id.device_name);
        mDevicesList = findViewById(R.id.devices_list);
        setDeviceName();
    }

    protected void setDeviceName() {
        mDeviceName.setText("Device Name: " + bluetoothAdapter.getName());
    }

    public void onClickBlutoothOn(View v) {
        if (!bluetoothAdapter.isEnabled()) {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, 0);
        }
    }

    public void onClickBlutoothOff(View v) {
        bluetoothAdapter.disable();
    }

    public  void onClickVisible(View v) {
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(intent, 0);
    }

    public void onClickFindDevices(View v) {
        pairedDevices = bluetoothAdapter.getBondedDevices();
        devicesList = new ArrayList();
        for(BluetoothDevice bluetoothDevice : pairedDevices)
            devicesList.add(bluetoothDevice.getName());
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, devicesList);
        mDevicesList.setAdapter(adapter);
    }
}