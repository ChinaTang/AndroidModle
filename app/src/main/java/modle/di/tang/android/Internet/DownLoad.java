package modle.di.tang.android.Internet;

import org.apache.http.HttpConnection;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by tangdi on 2016/1/15.
 */
public class DownLoad {

    private String Url;

    private HttpURLConnection httpConnection;

    private URL url;
    public DownLoad(String URL){
        this.Url = URL;
    }

    public void connect(){
        try {
            url = new URL(Url);
            httpConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpConnection.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

}
