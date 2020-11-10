package tn.polytechnique.myagency;



import android.app.Activity;
import android.os.Bundle;
//import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.Menu;
//import android.view.MenuInflater;
import android.view.Window;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class dhote extends Activity {
    private String dhote_url = "http://10.0.2.2/ala/getdhote.php";
    private JsonArrayRequest jsonArrayRequest;
    private RequestQueue requestQueue;
    getdhote getdhote;
    private List<getdhote> dhoteList = new ArrayList<>();
    private RecyclerView recyclerviewdhote;

    dhoteAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dhote);
        recyclerviewdhote = (RecyclerView) findViewById(R.id.recyclerviewdhote);
        jsonCall();
    }

    private void jsonCall() {
        jsonArrayRequest = new JsonArrayRequest(dhote_url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        getdhote = new getdhote();
                        getdhote.setIddhote(jsonObject.getInt("id_h"));
                        getdhote.setNom(jsonObject.getString("nom_h"));
                        getdhote.setEmplacement(jsonObject.getString("empmacement_h"));
                        getdhote.setPrix(jsonObject.getDouble("prix_h"));
                        getdhote.setImage(jsonObject.getString("image"));
                        getdhote.setLati(jsonObject.getString("lati"));
                        getdhote.setLongi(jsonObject.getString("longi"));
                        dhoteList.add(getdhote);
                    } catch (Exception e) {

                    }

                }
                adapter = new dhoteAdapter(dhote.this, dhoteList);
                recyclerviewdhote.setAdapter(adapter);
                recyclerviewdhote.setLayoutManager(new LinearLayoutManager(dhote.this));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(dhote.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(dhote.this);
        requestQueue.add(jsonArrayRequest);
    }


}
