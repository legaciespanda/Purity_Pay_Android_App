package com.example.puritypay.network;

import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.puritypay.R;

/**
 * Created by Ernest Obot on 12/02/20.
 */
public class NoNetworkDialog extends AppCompatActivity {
    private static Context context;

    public NoNetworkDialog(){

    }
    public NoNetworkDialog (Context context){
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showNoInternetDialog();
    }


    public static void showNoInternetDialog(){
        final Dialog dialog1 = new Dialog(context, R.style.df_dialog);
        dialog1.setContentView(R.layout.dialog_no_internet);
        dialog1.setCancelable(false);
        dialog1.setCanceledOnTouchOutside(false);
        dialog1.findViewById(R.id.btnExitApp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.dismiss();

            }
        });
        dialog1.show();
    }
}
