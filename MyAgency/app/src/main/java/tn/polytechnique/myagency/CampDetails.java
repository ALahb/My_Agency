package tn.polytechnique.myagency;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

public class CampDetails extends AppCompatActivity {
    TextView rddesc,rdprix,rddest,rddate;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camp_details);
        rddate =  findViewById(R.id.cpdate);
        rddesc =  findViewById(R.id.cpdesc);
        rddest =  findViewById(R.id.cpdest);
        rdprix = findViewById(R.id.cpprix);
        imageView =  findViewById(R.id.cpimg);
        rddate.setText("Date : " + getIntent().getStringExtra("date"));
        rdprix.setText("Prix : " + getIntent().getStringExtra("prix"));
        rddest.setText("Destination : " + getIntent().getStringExtra("destination"));
        rddesc.setText("Description : \n" + getIntent().getStringExtra("description"));
        RequestQueue requestQueue = Volley.newRequestQueue(CampDetails.this);
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
}
