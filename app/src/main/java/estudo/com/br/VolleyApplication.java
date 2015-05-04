package estudo.com.br;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.app.Application;
/**
 * Created by Natan on 02/05/2015.
 */
public class VolleyApplication extends Application {
    private static VolleyApplication sInstance;

    private RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        mRequestQueue = Volley.newRequestQueue(this);
        sInstance = this;
    }
    public synchronized static VolleyApplication getInstance() {
        return sInstance;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }
}
