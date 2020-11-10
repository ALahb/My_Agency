package tn.polytechnique.myagency;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.support.v7.widget.Toolbar;

public class MesReservation extends Activity  {
    private String JSON_URL = "http://10.0.2.2/ala/mesproduits.php";


    MesRes mesRes;
    private List<MesRes> mesResList = new ArrayList<>();
    private RecyclerView recyclerView;

    MesResAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_reservation);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewmes);
        loadRecyclerViewData();
    }

    private void loadRecyclerViewData() {
        SharedPreferences sharedPreferences = getSharedPreferences("myprefs",MODE_PRIVATE);
        final String user_id = sharedPreferences.getString("user_id","");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);
                                mesRes = new MesRes();
                                mesRes.setDate_debut(o.getString("date_debut"));
                                mesRes.setDate_fin(o.getString("date_fin"));
                                mesRes.setEmplacement(o.getString("nom_h"));



                                mesResList.add(mesRes);
                            }
                            adapter = new MesResAdapter(MesReservation.this, mesResList);
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(MesReservation.this));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {




                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String,String> params = new HashMap<String, String>();
                params.put("user_id",user_id);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }










}

