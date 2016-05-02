package com.appsriv.holbe;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Login extends Activity implements OnTaskCompleted {

    ProgressDialog pd;
    EditText email;
    EditText password;
    Config config;
    String emailId,pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        TextView txt=(TextView)findViewById(R.id.forgot);
        String styledText = "<u>FORGOT PASSWORD?</u>";
        txt.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);

        email=(EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);
        Button login =(Button)findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               emailId=email.getText().toString();
               pwd =password.getText().toString();
              if (email.getText().toString().isEmpty()||password.getText().toString().isEmpty())
              {
                  Toast.makeText(Login.this,"Please Enter Valid Credentials ", Toast.LENGTH_LONG).show();
              }
                else if (!Splash.isValidEmaillId(emailId))
              {
                  Toast.makeText(Login.this,"Please Enter Valid Email ", Toast.LENGTH_LONG).show();
              }
                else
              {
                  final String url = "http://192.185.26.69/~holbe/api/patient/login.php?emailaddress="+email.getText().toString()+"&"+"password="+password.getText().toString();
                  new AsyncHttpTask().execute(url);
              }
            }
        });
    }
    public void forgot(View v)
    {
        Intent i=new Intent(Login.this,ForgotPassword.class);
        startActivity(i);
    }
    public void alert(String title, String msg)
    {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                Login.this);

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
    public void onTaskCompleted(String stringi, String string)
    {
        pd.dismiss();
        if(string.equalsIgnoreCase("true"))
        {
            Toast.makeText(this,"Login Successfully", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"Invalid Login Details", Toast.LENGTH_SHORT).show();
        }
    }

   /* public void login(View v)
    {

        if(email.getText().toString().isEmpty())
        {
            alert("Alert","Please enter your email address");
        }
        else if(password.getText().toString().isEmpty()){
            alert("Alert","Please enter your password");
        }
        else
        {
        pd = new ProgressDialog(Login.this);
        pd.setMessage("Contacting to server...");
        pd.setCancelable(false);
        pd.show();

        HashMap<String, String> dat = new HashMap<String, String>();
        JSONObject data=new JSONObject();

        try
        {
            data.put("emailaddress", email.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try
        {
            data.put("password", password.getText().toString());
        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        AsyncHttpGet asyncHttpGet = new AsyncHttpGet("login",this,dat);
            asyncHttpGet.data=data;
            asyncHttpGet.execute(config.login+"?emailaddress="+email.getText().toString()+"&password="+password.getText().toString());
    }
    }
*/



    class AsyncHttpTask extends AsyncTask<String, Void, Integer>
    {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(Login.this, "Please wait!", "Loading...");
        }

        @Override
        protected Integer doInBackground(String... params) {
            //InputStream inputStream = null;
            Integer result = 0;
            HttpURLConnection urlConnection = null;

            try {

                URL url = new URL(params[0]);

                urlConnection = (HttpURLConnection) url.openConnection();


                urlConnection.setRequestMethod("GET");

                int statusCode = urlConnection.getResponseCode();


                if (statusCode == 200)
                {
                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        response.append(line);
                    }

                    String status = parseResult(response.toString());
                    if (status.equals("200"))
                    {
                        result = 1; // Successful
                    }
                    else
                    {
                        return 0;
                    }


                    //result = 1; // Successful
                } else
                {
                    result = 0; //"Failed to fetch data!";
                }

            } catch (Exception e)
            {
                // Log.d(TAG, e.getLocalizedMessage());
                e.printStackTrace();
            }

            return result; //"Failed to fetch data!";
        }

        @Override
        protected void onPostExecute(Integer result) {

            //setProgressBarIndeterminateVisibility(false);


            if (progressDialog!=null&&progressDialog.isShowing())
            {
                progressDialog.dismiss();
            }
            if (result == 1)
            {
                startActivity(new Intent(Login.this, DrawerActivity.class));

            }
            else
            {
                Toast.makeText(Login.this,"Please enter valid Email and Password", Toast.LENGTH_LONG).show();
            }

        }

        private String parseResult(String result)
        {
            String status=null;
            try {
                JSONObject object = new JSONObject(result);
                 status = object.getString("status");


            } catch (JSONException e) {
                e.printStackTrace();
            }
            return status;
        }
    }

}
