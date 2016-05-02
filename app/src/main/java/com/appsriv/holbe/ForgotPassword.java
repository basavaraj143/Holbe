package com.appsriv.holbe;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ForgotPassword extends Activity implements OnTaskCompleted {

    ProgressDialog pd;
    EditText email;
    Config config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot);

        email=(EditText) findViewById(R.id.email);
    }
    public void alert(String title, String msg){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                ForgotPassword.this);

        // set title
        alertDialogBuilder.setTitle(title);

        // set dialog message
        alertDialogBuilder
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        //Donor.this.finish();
                        dialog.cancel();
                    }
                });


        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    @Override
    public void onTaskCompleted(String stringi, String string) {
        pd.dismiss();
        if(string.equalsIgnoreCase("true"))
        {
            Toast.makeText(this,"Your password has been sent on your email id", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"Email id not exist in our database", Toast.LENGTH_SHORT).show();
        }
    }

    public void submit(View v)
    {

        if(email.getText().toString().isEmpty())
        {
            alert("Alert","Please enter your email address");
        }
        else
        {
        pd = new ProgressDialog(ForgotPassword.this);
        pd.setMessage("Contacting to server...");
        pd.setCancelable(false);
        pd.show();

        HashMap<String, String> dat = new HashMap<String, String>();
        JSONObject data=new JSONObject();

        try {
            data.put("emailaddress", email.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AsyncHttpGet asyncHttpGet = new AsyncHttpGet("login",this,dat);
            asyncHttpGet.data=data;
            asyncHttpGet.execute(config.forgot+"?emailaddress="+email.getText().toString());
    }
    }
}
