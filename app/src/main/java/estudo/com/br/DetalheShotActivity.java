package estudo.com.br;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import estudo.com.br.controllers.CircleTransform;
import estudo.com.br.entitys.Shhhot;


public class DetalheShotActivity extends Activity {


    TextView txName, txDescription, txTitle;
    ImageView imagePost,imageAvatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Shhhot shot = (Shhhot) getIntent().getSerializableExtra("shot");
        setContentView(R.layout.dibbbre_shot_detalhe);
        inicializaObjDibre();
        montaShot(shot);

    }
    void montaShot(Shhhot shot) {
        if (shot!=null) {
            txName.setText(shot.getPlayer().getname());
            if (shot.getDescription() != null) {
                Spanned spanned = Html.fromHtml(shot.getDescription());
                txDescription.setText(spanned);
            }
            txTitle.setText(shot.getTitle());
            baixaImagemAvatar(shot.getPlayer().getAvatar_url(), imageAvatar, 50, 50);
            baixaImagem(shot.getImage_url(), imagePost, 350, 200);
        }
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
    void inicializaObjDibre(){
        txName = (TextView) findViewById(R.id.TxName);
        txDescription = (TextView) findViewById(R.id.TxDescription);
        imageAvatar = (ImageView) findViewById(R.id.imageAvatar);
        imagePost = (ImageView) findViewById(R.id.imagePost);
        txTitle = (TextView) findViewById(R.id.txTitle);
    }
}
