package estudo.com.br;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import com.android.volley.Request;
import com.google.gson.Gson;
import com.android.volley.VolleyError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

import static android.widget.AdapterView.OnItemClickListener;

public class DibreActivity extends ListActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shot_listview);
        chamaDibres();
    }
    @Override
    protected void onDestroy() {
        Crouton.cancelAllCroutons();
        super.onDestroy();
    }

    Activity getActivity(){
        return this;
    }

    void chamaDibres(){
        String url = "http://api.dribbble.com/shots/popular?page=1";

        RequestQueue queue = Volley.newRequestQueue(this);
        final Pagina[] pagina = {new Pagina()};
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        pagina[0] = gson.fromJson(response, Pagina.class);
                        final ShhhotListAdapter shhhotListAdapter = new ShhhotListAdapter(getActivity(),pagina[0].getShhhot());
                        setListAdapter(shhhotListAdapter);

                        getListView().setOnItemClickListener(new OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Shhhot shot = shhhotListAdapter.getItem(position);
                                Intent i = new Intent(getApplication(),DetalheShot.class);
                                i.putExtra("shot", shot);
                                startActivity(i);
                            }
                        });
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        gerarToast("Falha na comunicação com a rede");
                    }
                }
        );
        queue.add(stringRequest);
    }

    void gerarToast(CharSequence message) {
        Crouton.makeText(getActivity(), message, Style.INFO).show();
    }
    void minimizaTeclado(){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
