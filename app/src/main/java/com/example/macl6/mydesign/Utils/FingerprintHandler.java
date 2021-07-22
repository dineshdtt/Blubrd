package com.example.macl6.mydesign.Utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.macl6.mydesign.Activity.Main2Activity;
import com.example.macl6.mydesign.R;


/**
 * Created by whit3hawks on 11/16/16.
 */
@RequiresApi(api = Build.VERSION_CODES.M)
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {
    private FingerprintHelperListener listener;

    private Context context;

    // Constructor
    public FingerprintHandler(Context mContext) {
        context = mContext;
    }
    public FingerprintHandler(FingerprintHelperListener listener) {
        this.listener = listener;
    }

    public void startAuth(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject) {
        CancellationSignal cancellationSignal = new CancellationSignal();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }

    @Override
    public void onAuthenticationError(int errMsgId, CharSequence errString) {
        this.update("Fingerprint Authentication error\n" + errString, false);
    }

    @Override
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        this.update("Fingerprint Authentication help\n" + helpString, false);
    }

    @Override
    public void onAuthenticationFailed() {
        this.update("Fingerprint Authentication failed.", false);
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        ((Activity) context).finish();
        Intent intent = new Intent(context, Main2Activity.class);
        context.startActivity(intent);
        Toast.makeText(context,"Fingerprint Enable",Toast.LENGTH_LONG).show();
    }

    private void update(String e, Boolean success) {
        TextView textView = (TextView) ((Activity) context).findViewById(R.id.errorText);
        textView.setText(e);
//        ImageView imageView = (ImageView) ((Activity) context).findViewById(R.id.img_fingerprint);
        if (success) {
            Toast.makeText(context, e, Toast.LENGTH_LONG).show();
//            imageView.setImageResource(R.drawable.ic_finger_success);
        } else {
            Toast.makeText(context, e, Toast.LENGTH_LONG).show();
//            imageView.setImageResource(R.drawable.ic_finger_fail);
        }
    }

    //interface for the listner
    public interface FingerprintHelperListener {
        void authenticationFailed(String error);
        void authenticationSuccess(FingerprintManager.AuthenticationResult result);
    }
}
