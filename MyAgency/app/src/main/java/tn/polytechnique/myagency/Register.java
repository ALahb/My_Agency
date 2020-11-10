package tn.polytechnique.myagency;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    EditText edt_name, edt_uname, edt_email, edt_pass, edt_cpass;
    Button btn_Rg;
    TextView apnnr, lg_txt;
    AlertDialog.Builder builder;
    String register_url = "http://10.0.2.2/ala/register.php";
    String strname, struname, stremail, strpass, strcpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

       // lg_txt = (TextView) findViewById(R.id.lgtxt);
        edt_name = (EditText) findViewById(R.id.edtname);
        edt_uname = (EditText) findViewById(R.id.edtuname);
        edt_email = (EditText) findViewById(R.id.edtemail);
        edt_pass = (EditText) findViewById(R.id.edtpass);
        edt_cpass = (EditText) findViewById(R.id.edtcpass);
        btn_Rg = (Button) findViewById(R.id.btnrg);
        builder = new AlertDialog.Builder(Register.this);

        btn_Rg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strname = edt_name.getText().toString();
                struname = edt_uname.getText().toString();
                stremail = edt_email.getText().toString();
                strpass = edt_pass.getText().toString();
                strcpass = edt_cpass.getText().toString();
                if (strname.equals("") || struname.equals("") || stremail.equals("") || strpass.equals("") || strcpass.equals("")) {
                    builder.setTitle("Something went wrong...");
                    builder.setMessage("Please fill all the fields...");
                    displayAlert("input_error0");
                }
                else if (!stremail.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")){
                    builder.setTitle("Something went wrong...");
                    builder.setMessage("Invalid email ...");
                    displayAlert("input_error_email");

                }

                else {
                    if (!(strpass.equals(strcpass))) {
                        builder.setTitle("Something went wrong...");
                        builder.setMessage("Your passwords are not matching...");
                        displayAlert("input_error");

                    } else {
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, register_url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONArray jsonArray = new JSONArray(response);
                                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                                    String code = jsonObject.getString("code");
                                    String message = jsonObject.getString("message");
                                    builder.setTitle("server response...");
                                    builder.setMessage(message);
                                    displayAlert(code);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() {
                                Map<String, String> params = new HashMap<String, String>();
                                params.put("firstname", strname);
                                params.put("email", stremail);
                                params.put("lastname", struname);
                                params.put("password", strpass);
                                return params;
                            }
                        };
                        MySingleton.getInstance(Register.this).addToRequestqueue(stringRequest);

                    }
                }


            }
        });



    }

    public void displayAlert(final String code) {
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (code.equals("input_error")) {
                    edt_pass.setText("");
                    edt_cpass.setText("");

                } else if (code.equals("input_error0")) {


                } else if (code.equals("reg_success")) {
                    startActivity(new Intent(Register.this,Authentification.class));


                } else if (code.equals("reg_failed")) {
                    edt_name.setText("");
                    edt_email.setText("");
                    edt_uname.setText("");
                    edt_pass.setText("");
                    edt_cpass.setText("");

                }
                else if (code.equals("input_error_email")) {

                    edt_email.setText("");
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void tologin(View view) {
        startActivity(new Intent(Register.this,Authentification.class));
    }
}



