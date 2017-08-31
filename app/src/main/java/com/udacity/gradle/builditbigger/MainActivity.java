package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pmit.backend.myApi.MyApi;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.petemit.example.android.jokedisplay.JokeDisplayActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;

import org.w3c.dom.Text;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    TextView errorTextView;
    ProgressBar progressBar;
    View fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        errorTextView=(TextView)findViewById(R.id.tv_error);
        progressBar=(ProgressBar)findViewById(R.id.pb_loading_indicator);
        fragment=findViewById(R.id.fragment);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_purchase_app) {
            Toast.makeText(this, R.string.buy_toast, Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showError(){
        if(errorTextView!=null&&fragment!=null&&progressBar!=null) {
            progressBar.setVisibility(View.GONE);
            errorTextView.setVisibility(View.VISIBLE);
            fragment.setVisibility(View.GONE);

        }
    }

    public void showLoading(){

        if (progressBar!=null&&fragment!=null) {
            progressBar.setVisibility(View.VISIBLE);
            fragment.setVisibility(View.GONE);
        }
    }
    public void tellJoke(View view) {
        showLoading();
        new EndpointsAsyncTask().execute(this);

        //calls the java library directly
      //  Toast.makeText(this, joke, Toast.LENGTH_SHORT).show();
    }

    /**
     * async task to execute the API's call
     */
    private class EndpointsAsyncTask extends AsyncTask<Context, Void, String> {
        private MyApi myApiService = null;
        private Context context;

        @Override
        protected String doInBackground(Context... params) {
            if(myApiService == null) {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        // options for running against local devappserver
                        // - 10.0.2.2 is localhost's IP address in Android emulator
                        // - turn off compression when running against local devappserver
                        //.setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                // end options for devappserver

                myApiService = builder.build();
            }
            //get context from params
            context = params[0];

            try {
                return myApiService.getJoke().execute().getData();
            } catch (IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showError();
                    }
                });

                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            startActivity((new Intent(context,JokeDisplayActivity.class))
                      .putExtra(getString(R.string.joke_key),result));
            //   Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        }
    }
}
