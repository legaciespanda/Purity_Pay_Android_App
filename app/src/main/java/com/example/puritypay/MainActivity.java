package com.example.puritypay;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import com.example.puritypay.network.ConnectivityReceiver;
import com.example.puritypay.network.MyApplication;
import com.example.puritypay.network.NoNetworkDialog;

public class MainActivity extends AppCompatActivity implements
        ConnectivityReceiver.ConnectivityReceiverListener{

    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkConnectionInit();
        checkConnection();
    }

    @Override
    protected void onResume(){
        super.onResume();

        // register connection status listener
        MyApplication.getInstance().setConnectivityListener(this);
    }

    @Override
    public void onBackPressed() {
        showExitAlertDialog();
    }

    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showDialogInit(isConnected);
    }
    /**
     * Checks for No Internet Access on Start on MainActivity or PURITY PAY APP
     * */
    private void showDialogInit (boolean isConnected) {

        if (isConnected) {
                //let user proceed to app
        } else {
            NoNetworkDialog.showNoInternetDialog();
        }
    }

    /**
     * Checks for No Internet Access on Start on MainActivity or PURITY PAY APP
     * automatically isConnected method of the CoonectivityReceiver class
     * */
    private void checkConnectionInit() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showDialogInit(isConnected);
    }

    /**
     * Callback will be triggered when there is change in
     * network connection
     */
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showDialogInit(isConnected);
    }

    /**
     * Callback will be triggered when there is change in
     * network connection
     */
    public void showExitAlertDialog(){
        if ((System.currentTimeMillis() - exitTime) > 2000){
            exitTime = System.currentTimeMillis();
        }
        else{
            //init alert dialog
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setTitle("Close App!");
            builder.setIcon(R.mipmap.ic_launcher);
            builder.setMessage("Are you sure you want to exit Purity Pay?");
            //set listeners for dialog buttons
            builder.setPositiveButton("SURE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });

            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            //create alert dialog box and shows it
            builder.create().show();
        }
    }

}
