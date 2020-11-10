package tn.polytechnique.myagency;



import android.app.Activity;
import android.os.Bundle;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.Window;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class camping extends Activity {
    private String camping_url = "http://10.0.2.2/ala/getcamping.php";
    private JsonArrayRequest jsonArrayRequest;
    private RequestQueue requestQueue;
    getcamping getcamping;
    private List<getcamping> campingList = new ArrayList<>();
    private RecyclerView recyclerviewcamping;

    campingAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_camping);
        recyclerviewcamping = (RecyclerView) findViewById(R.id.recyclerviewcamping);
        jsonCall();
    }

    private void jsonCall() {
        jsonArrayRequest = new JsonArrayRequest(camping_url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        getcamping = new getcamping();
                        getcamping.setIdComping(jsonObject.getInt("id_c"));
                        getcamping.setDestination(jsonObject.getString("destination_c"));
                        getcamping.setDate(jsonObject.getString("date_c"));
                        getcamping.setImage(jsonObject.getString("image"));
                        getcamping.setDescription(jsonObject.getString("description_c"));
                        getcamping.setPrix(jsonObject.getDouble("prix_c"));

                        campingList.add(getcamping);
                    } catch (Exception e) {

                    }

                }
                adapter = new campingAdapter(camping.this, campingList);
                recyclerviewcamping.setAdapter(adapter);
                recyclerviewcamping.setLayoutManager(new LinearLayoutManager(camping.this));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue = Volley.newRequestQueue(camping.this);
        requestQueue.add(jsonArrayRequest);
    }


}
