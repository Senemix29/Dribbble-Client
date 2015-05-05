package estudo.com.br;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.google.gson.Gson;
import com.android.volley.VolleyError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

public class DibreActivity extends ActionBarActivity {

    TextView txName, txDescription;
    Button btBuscarPorId,btTodosShots;
    EditText etShotId;
    ImageView imagePost,imageAvatar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chamaMain();
    }

    void inicializaMainObj(){
        btBuscarPorId = (Button) findViewById(R.id.btBuscarPorId);
        btTodosShots = (Button) findViewById(R.id.btTodosShots);
        etShotId = (EditText) findViewById(R.id.editTextShotId);
    }
    void inicializaObjDibre(){
        txName = (TextView) findViewById(R.id.TxName);
        txDescription = (TextView) findViewById(R.id.TxDescription);
        imageAvatar = (ImageView) findViewById(R.id.imageAvatar);
        imagePost = (ImageView) findViewById(R.id.imagePost);
    }

    void chamaDibreIsolado(){
        setContentView(R.layout.dibre_isolado);
        inicializaObjDibre();
    }
    void chamaMain(){
        setContentView(R.layout.main_dibre);
        inicializaMainObj();
    }

    public void ActionBuscarPorId(View view){
        chamaDibreIsolado();
        DibreShot(Integer.valueOf(etShotId.getText().toString()));
    }

    void DibreShot(int id) {
        String url = "http://api.dribbble.com/shots/";

        RequestQueue queue = Volley.newRequestQueue(this);
        final Shhhot[] shot2 = {new Shhhot()};
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url + id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        shot2[0] = gson.fromJson(response, Shhhot.class);
                        txName.setText(shot2[0].getPlayer().getname());
                        txDescription.setText(shot2[0].getDescription());
                        baixaImagemAvatar(shot2[0].getPlayer().getAvatar_url(),imageAvatar,50,50);
                        baixaImagem(shot2[0].getImage_url(),imagePost,350,199);
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                      txDescription.setText("DEU RUIM");
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
    void baixaImagemAvatar(String url, ImageView imageView, int width, int height){
        Picasso.with(this)
                .load(url)
                .resize(width,height)
                .transform(new CircleTransform())
                .into(imageView);
    }
    void baixaImagem(String url, ImageView imageView, int width, int height){
        Picasso.with(this)
                .load(url)
                .resize(width, height)
                .into(imageView);
    }
}
