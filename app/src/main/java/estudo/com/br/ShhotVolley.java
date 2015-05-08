package estudo.com.br;
import android.content.Context;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.android.volley.VolleyError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by Natan on 03/05/2015.
 */
public class ShhotVolley {

    private static final String url = "http://api.dribbble.com/shots/";


    Shhhot[] getShhhotVolley(Context context, int id){
    final Shhhot[] shot2 = {new Shhhot()};
    RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET,url+id,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Gson gson = new Gson();
                    shot2[0] = gson.fromJson(response,Shhhot.class);
                }

            },
            new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    //shot=new Shhhot();
                    //shot.setTitle("DEU RUIM");
                }
            }
        );
        queue.add(stringRequest);
        return shot2;

    }
}
