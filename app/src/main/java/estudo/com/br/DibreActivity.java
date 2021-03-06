package estudo.com.br;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.gson.Gson;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

import static android.widget.AdapterView.OnItemClickListener;

public class DibreActivity extends ListActivity {

    FloatingActionMenu menu1;
    private FloatingActionButton btNext,btPrevious;
    int cont;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shot_listview);
        cont=0;
        inicializaObjetos();
        objListeners();
        cont++;
        chamaDibre(cont);
    }
    @Override
    protected void onDestroy() {
        Crouton.cancelAllCroutons();
        super.onDestroy();
    }

    Activity getActivity(){
        return this;
    }

    void chamaDibre(int id){
        String url = "http://api.dribbble.com/shots/popular?page=";

        RequestQueue queue = Volley.newRequestQueue(this);
        final Pagina[] pagina = {new Pagina()};
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url+id,
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
                                Intent i = new Intent(getApplication(),DetalheShotActivity.class);
                                i.putExtra("shot", shot);
                                startActivity(i);
                            }
                        });
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        gerarCrouton("Falha na comunicação com a rede");
                    }
                }
        );
        queue.add(stringRequest);
    }
    void inicializaObjetos(){
        menu1 = (FloatingActionMenu) findViewById(R.id.menu1);
        menu1.setClosedOnTouchOutside(true);
        btPrevious = (FloatingActionButton) findViewById(R.id.fab1);
        btNext = (FloatingActionButton) findViewById(R.id.fab2);

    }
    void objListeners(){
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cont<50) {
                    cont++;
                    chamaDibre(cont);
                }
            }
        });
        btPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cont > 0) {
                    cont--;
                    chamaDibre(cont);
                }
            }
        });
    }
    void gerarCrouton(CharSequence message) {
        Crouton.makeText(getActivity(), message, Style.INFO).show();
    }
}
