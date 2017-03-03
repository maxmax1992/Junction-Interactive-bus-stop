package junction.busstop_android;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.w3c.dom.Text;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import static com.android.volley.Request.Method.POST;

//import com.hmkcode.android.vo.Person;
public class MainActivity extends AppCompatActivity {

    private Button sendButton;
    private EditText messageText, codeText;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    //private GoogleApiClient client;
    //private Message message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendButton = (Button) findViewById(R.id.send_button);
        messageText = (EditText) findViewById(R.id.message);
        codeText = (EditText) findViewById(R.id.busstop_code);

        sendButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataToServer();
            }
        });
    }

    // Creating JSON.
    private String formatDataAsJSON() {
        final JSONObject root = new JSONObject();

        try {
            root.put("message", messageText.getText());
            root.put("stopid", codeText.getText());
            /*
            JSONArray animals = new JSONArray();
            animals.put("koira");
            root.put("animals", animals);
            */
            return root.toString(1);

        } catch (JSONException e1) {
            Log.d("JWP", "cant format JSON");
            e1.printStackTrace();
        }
        return null;
    }


    private void sendDataToServer() {

        final String json = formatDataAsJSON(); // JSON data

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {

                return getServerResponse(json);
            }

            @Override
            protected void onPostExecute(String result) {
                TextView textView = (TextView) findViewById(R.id.message);

                //Toast.makeText(, result, Toast.LENGTH_SHORT).show();
                Toast toast = Toast.makeText(textView.getContext(),result,Toast.LENGTH_LONG);
                toast.show();
                Log.d("POST Execute", textView.toString());
                //textView.setText(result);
            }

        }.execute();
    }

    // Tries to connect server and gives error when it fails.
    private String getServerResponse(String json) {

        HttpPost post = new HttpPost("http://busstop2016.herokuapp.com/requests/"); // http://requestb.in/19bwlsk1
        try {
            StringEntity entity = new StringEntity(json);

            post.setEntity(entity);
            post.setHeader("Content-type", "application/json");

            Log.d("test", "inside try");
            DefaultHttpClient client = new DefaultHttpClient();
            BasicResponseHandler handler = new BasicResponseHandler();

            Log.d("response", client.toString() + " " + handler);
            String response = client.execute(post, handler);

            Log.d("response", response.toString());
            return response;
        } catch (UnsupportedEncodingException e) {
            Log.d("JWP", e.toString());
        } catch (ClientProtocolException e) {
            Log.d("JWP", e.toString());
        } catch (IOException e) {
            Log.d("JWP", e.toString());
        }
        return "Unable to contact server!";
    }
}














