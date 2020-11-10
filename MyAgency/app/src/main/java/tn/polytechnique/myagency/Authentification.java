package tn.polytechnique.myagency;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Authentification extends AppCompatActivity {
    Button lg_btn;
    EditText uname_edt, pass_edt;
    TextView rg_txt, apnn;
    String strusername, strpass;
    //String lg_url = "http://192.168.176.2/login.php";
    String lg_url = "http://10.0.2.2/ala/login.php";
    AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_authentification);
        lg_btn = (Button) findViewById(R.id.lg);
        uname_edt = (EditText) findViewById(R.id.us);
        pass_edt = (EditText) findViewById(R.id.ps);




        builder = new AlertDialog.Builder(Authentification.this);
        lg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strusername = uname_edt.getText().toString();
                strpass = pass_edt.getText().toString();
                if (strusername.equals("") || strpass.equals("")) {
                    builder.setTitle("Something went wrong");
                    displayAlert("Enter a valid user name and password please...");
                }
                else
                {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, lg_url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                String code = jsonObject.getString("code");

                                if (code.equals("login_failed"))
                                {
                                    builder.setTitle("Login Error");
                                    displayAlert(jsonObject.getString("message"));
                                }
                                else
                                {
                                    SharedPreferences sharedPreferences = getSharedPreferences("myprefs",MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("user_id",jsonObject.getInt("user_id")+"");
                                    editor.apply();

                                    Intent i = new Intent(Authentification.this,home.class);
                                    startActivity(i);

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Authentification.this,error.getMessage(),Toast.LENGTH_LONG).show();

                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<String, String>();
                            params.put("email",strusername);
                            params.put("password",strpass);
                            return params;
                        }
                    };
                    MySingleton.getInstance(Authentification.this).addToRequestqueue(stringRequest);
                }
            }
        });
    }

    public void displayAlert(String message) {
        builder.setMessage(message);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                uname_edt.setText("");
                pass_edt.setText("");
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}