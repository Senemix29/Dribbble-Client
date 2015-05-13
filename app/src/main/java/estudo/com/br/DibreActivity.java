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
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.gson.Gson;
import com.android.volley.VolleyError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import estudo.com.br.DetalheShotActivity;
import estudo.com.br.entitys.Pagina;
import estudo.com.br.R;
import estudo.com.br.entitys.Shhhot;
import estudo.com.br.controllers.ShhhotListAdapter;

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
        chamaDibres(cont);


    }
    @Override
    protected void onDestroy() {
        Crouton.cancelAllCroutons();
        super.onDestroy();
    }

    Activity getActivity(){
        return this;
    }

    void chamaDibres(int id){
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
                        gerarToast("Falha na comunicação com a rede");
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
                    chamaDibres(cont);
                }
            }
        });
        btPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cont>0){
                    cont--;
                    chamaDibres(cont);
                }
            }
        });
    }
    void gerarToast(CharSequence message) {
        Crouton.makeText(getActivity(), message, Style.INFO).show();
    }
    void minimizaTeclado(){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
