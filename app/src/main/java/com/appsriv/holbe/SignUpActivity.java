package com.appsriv.holbe;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
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

public class SignUpActivity extends Activity {

    EditText email;
    EditText password;
    String emailId,pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        TextView signin = (TextView)findViewById(R.id.signin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this,Login.class));
            }
        });

        email=(EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);
        Button login =(Button)findViewById(R.id.signup);

        //Sign up button click
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                emailId=email.getText().toString();
                pwd =password.getText().toString();
                if (emailId.isEmpty()||pwd.isEmpty())
                {
                    Toast.makeText(SignUpActivity.this,"Please Enter Valid Credentials ", Toast.LENGTH_LONG).show();
                }
                else if (!Splash.isValidEmaillId(emailId))
                {

                    final String URL=" http://192.185.26.69/~holbe/api/patient/createuser.php?user_email_address="+emailId+"&password="+pwd;
                    new SignUp().execute(URL);

                }
                else
                {
                    Toast.makeText(SignUpActivity.this,"Please Enter Valid Email ", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    class SignUp extends AsyncTask<String, Void, Integer>
    {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute()
        {
            progressDialog = ProgressDialog.show(SignUpActivity.this, "Please wait!", "Loading...");
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

            } catch (Exception e) {
                // Log.d(TAG, e.getLocalizedMessage());
            }

            return result; //"Failed to fetch data!";
        }

        @Override
        protected void onPostExecute(Integer result)
        {

            //setProgressBarIndeterminateVisibility(false);


            if (progressDialog!=null&&progressDialog.isShowing())
            {
                progressDialog.dismiss();
            }
            if (result == 1)
            {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(SignUpActivity.this);

                // Setting Dialog Title
                alertDialog.setTitle("Alert");

                // Setting Dialog Message
                alertDialog.setMessage("You are logged in successfully please varify your mail");

                // Setting Icon to Dialog
                //alertDialog.setIcon(R.drawable.delete);

                // Setting Positive "Yes" Button
                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which) {

                        /*// Write your code here to invoke YES event
                        Toast.makeText(getApplicationContext(), "You clicked on YES", Toast.LENGTH_SHORT).show();*/
                        startActivity(new Intent(SignUpActivity.this, Splash.class));
                    }
                });

                // Setting Negative "NO" Button
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to invoke NO event
                        //Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignUpActivity.this, Splash.class));
                        dialog.cancel();
                    }
                });

                // Showing Alert Message
                alertDialog.show();




            }
            else
            {
                Toast.makeText(SignUpActivity.this,"Please enter valid Email and Password", Toast.LENGTH_LONG).show();
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
