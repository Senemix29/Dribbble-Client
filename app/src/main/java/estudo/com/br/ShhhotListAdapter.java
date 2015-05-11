package estudo.com.br;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Natan on 10/05/2015.
 */
public class ShhhotListAdapter extends ArrayAdapter<Shhhot> {
    private Context context;
    private List<Shhhot> shots;

    public ShhhotListAdapter(Context context,  List<Shhhot> shots) {
        super(context,0, shots);
        this.context=context;
        this.shots=shots;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent){
        Shhhot shot = shots.get(position);

        if(view == null)
            view = LayoutInflater.from(context).inflate(R.layout.item_shot_list, null);

        ImageView imagePost = (ImageView) view.findViewById(R.id.lsImagePost);
        baixaImagem(shot.getImage_url(),imagePost,350,200);

        TextView txTitle = (TextView) view.findViewById(R.id.lsTxTitle);
        txTitle.setText(shot.getTitle());

        return view;
    }
    void baixaImagem(String url, ImageView imageView, int width, int height){
        Picasso.with(context)
                .load(url)
                .resize(width, height)
                .into(imageView);
    }
}
