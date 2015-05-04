package estudo.com.br;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.google.gson.Gson;
import com.android.volley.VolleyError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;



public class DibreActivity extends ActionBarActivity {

    TextView txName, txDescription;
    Button btBuscarPorId;
    EditText etShotId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chamaMain();
    }

    void inicializaMainObj(){
        btBuscarPorId = (Button) findViewById(R.id.buttonBuscarPorId);
        etShotId = (EditText) findViewById(R.id.editTextShotId);
    }
    void inicializaObjDibre(){
        txName = (TextView) findViewById(R.id.TxName);
        txDescription = (TextView) findViewById(R.id.TxDescription);
    }

    void chamaDibreIsolado(){
        setContentView(R.layout.dibreIsolado);
        inicializaObjDibre();
    }
    void chamaMain(){
        setContentView(R.layout.mainDibre);
        inicializaMainObj();
    }

    void ActionBuscarPorId(View view){
        chamaDibreIsolado();
        DibreShot(Integer.valueOf(etShotId.getText().toString()));
    }

    void DibreShot(int id) {
        String url = "http://api.dribbble.com/shots/";

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url + id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        Shhhot shot = gson.fromJson(response, Shhhot.class);
                        txName.setText(shot.getPlayer().getname());
                        txDescription.setText(shot.getDescription());
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //shot=new Shhhot();
                        //shot.setTitle("DEU RUIM");
                    }
                }
        );
        queue.add(stringRequest);
    }

    void gerarToast(CharSequence message) {
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast
                .makeText(DibreActivity.this, message, duration);
        toast.show();
    }


}
