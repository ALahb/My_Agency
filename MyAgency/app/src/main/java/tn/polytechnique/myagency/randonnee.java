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


public class randonnee extends Activity {
    private String randonnee_url = "http://10.0.2.2/ala/getrandonnee.php";
    private JsonArrayRequest jsonArrayRequest;
    private RequestQueue requestQueue;
    getrandonnee getrandonnee;
    private List<getrandonnee> randonneeList = new ArrayList<>();
    private RecyclerView recyclerviewrandonnee;

    randonneeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_randonnee);
        recyclerviewrandonnee = (RecyclerView) findViewById(R.id.recyclerviewR);
        jsonCall();
    }

    private void jsonCall() {
        jsonArrayRequest = new JsonArrayRequest(randonnee_url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        getrandonnee = new getrandonnee();
                        getrandonnee.setIdComping(jsonObject.getInt("id_r"));
                        getrandonnee.setDestination(jsonObject.getString("destination_r"));
                        getrandonnee.setDate(jsonObject.getString("date_r"));
                        getrandonnee.setDescription(jsonObject.getString("description_r"));
                        getrandonnee.setImage(jsonObject.getString("image"));
                        getrandonnee.setPrix(jsonObject.getDouble("prix_r"));

                        randonneeList.add(getrandonnee);
                    } catch (Exception e) {

                    }

                }
                adapter = new randonneeAdapter(randonnee.this, randonneeList);
                recyclerviewrandonnee.setAdapter(adapter);
                recyclerviewrandonnee.setLayoutManager(new LinearLayoutManager(randonnee.this));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue = Volley.newRequestQueue(randonnee.this);
        requestQueue.add(jsonArrayRequest);
    }


}
