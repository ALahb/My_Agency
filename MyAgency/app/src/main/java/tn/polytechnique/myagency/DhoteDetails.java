package tn.polytechnique.myagency;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DhoteDetails extends AppCompatActivity {
    TextView nom,emp,prix;
    ImageView imageView;
    Button  res;
    SharedPreferences sharedPreferences;
    String idh;
    String url = "http://10.0.2.2/ala/reservation.php";
    private TextView datedebtxt,datefintxt;
    private Button datedebbtn,datefinbtn;

    private int year;
    private int month;
    private int day;

    static final int DATE_PICKER_ID = 1111;
    static final int DATE1_PICKER_ID = 2222;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dhote_details);
        datedebbtn = findViewById(R.id.btndb);
        datefinbtn =  findViewById(R.id.btndf);
        datedebtxt =   findViewById(R.id.datdeb);
        datefintxt =  findViewById(R.id.datdef);
        emp = findViewById(R.id.dhemp);
        nom = findViewById(R.id.dhnom);
        prix = findViewById(R.id.dhprix);
        emp.setText(getIntent().getStringExtra("emp"));
        nom.setText(getIntent().getStringExtra("nom"));
        prix.setText(getIntent().getStringExtra("prix"));
        imageView =  findViewById(R.id.dhimg);
        res = findViewById(R.id.btnreservation);

        final Calendar c = Calendar.getInstance();
        year  = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day   = c.get(Calendar.DAY_OF_MONTH);



       /* datefintxt.setText(new StringBuilder()

                .append(month + 1).append("-").append(day).append("-")
                .append(year).append(" "));*/



        datedebbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                showDialog(DATE_PICKER_ID);

            }

        });
        datefinbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                showDialog(DATE1_PICKER_ID);

            }

        });
        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences = getSharedPreferences("myprefs",MODE_PRIVATE);
                final String user_id = sharedPreferences.getString("user_id","");
                idh = getIntent().getStringExtra("id");

                StringRequest stringRequest = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String code = jsonObject.getString("code");
                            String message = jsonObject.getString("message");
                            Toast.makeText(DhoteDetails.this, message, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DhoteDetails.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();

                        params.put("id_user",user_id);
                        params.put("df",datefintxt.getText().toString());
                        params.put("dd",datedebtxt.getText().toString());
                        params.put("id_h",getIntent().getStringExtra("id"));
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(DhoteDetails.this);
                requestQueue.add(stringRequest);
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(DhoteDetails.this);
        ImageRequest imageRequest = new ImageRequest(
                getIntent().getStringExtra("image"), // Image URL
                new Response.Listener<Bitmap>() { // Bitmap listener
                    @Override
                    public void onResponse(Bitmap response) {

                        imageView.setImageBitmap(response);


                    }
                },
                0,
                0,
                ImageView.ScaleType.CENTER_CROP,
                Bitmap.Config.RGB_565,
                new Response.ErrorListener() { // Error listener
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        error.printStackTrace();

                    }
                }
        );


        requestQueue.add(imageRequest);
    }


    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE1_PICKER_ID:


                return new DatePickerDialog(this, pickerListener, year, month,day);

            case DATE_PICKER_ID:
                return new DatePickerDialog(this, pickerListener1, year, month,day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {


        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            year  = selectedYear;
            month = selectedMonth;
            day   = selectedDay;


            datefintxt.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));

        }
    };

    private DatePickerDialog.OnDateSetListener pickerListener1 = new DatePickerDialog.OnDateSetListener() {


        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            year  = selectedYear;
            month = selectedMonth;
            day   = selectedDay;


            datedebtxt.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));

        }
    };
}
