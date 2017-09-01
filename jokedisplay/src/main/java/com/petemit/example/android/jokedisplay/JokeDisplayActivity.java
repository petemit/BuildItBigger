package com.petemit.example.android.jokedisplay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {

    private TextView tv_joke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tv_joke=(TextView)findViewById(R.id.jokeTextView);
        Intent intent = getIntent();
        if (intent!=null&&intent.hasExtra(getString(R.string.joke_key))){
            String joke=intent.getStringExtra(getString(R.string.joke_key));
            tv_joke.setText(joke);
        }
        Button bt_morejokes=(Button)findViewById(R.id.button_morejokes);
        bt_morejokes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(getString(R.string.coming_back),true);
        setResult(RESULT_OK,intent);
        super.onBackPressed();
    }
}
