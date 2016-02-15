package com.coc.abhi8.clashofplayers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "MY_SETTING_PREF";
    public static String JSON_URL;
    public static String _response;
    public String clanTag;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        listView = (ListView) findViewById(R.id.player_listView);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        if(settings.contains("CLAN_TAG"))
        {
            clanTag=settings.getString("CLAN_TAG",null);
            Toast.makeText(this,clanTag,Toast.LENGTH_SHORT).show();
            JSON_URL="https://set7z18fgf.execute-api.us-east-1.amazonaws.com/prod/?route=getClanDetails&clanTag=%23"+clanTag;
        }
        else
        {
            Toast.makeText(this,"Save Clan Tag in Setting",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setting:
                Intent gotoSetting=new Intent(this,Setting.class);
                this.startActivity(gotoSetting);
                return true;
            case R.id.refresh_data:
                sendRequest();
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    private void sendRequest(){
        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                MainActivity._response=response;
                if(_response!=null) {
                    showJSON(response);
                    //Toast.makeText(MainActivity.this,MainActivity._response,Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(MainActivity.this,"Error Fetching Data",Toast.LENGTH_SHORT).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json){
        ParsePlayerList pj = new ParsePlayerList(json);
        pj.parseJSON();
        PopulatePlayerListView cl = new PopulatePlayerListView(this, ParsePlayerList.playerArrayList);
        listView.setAdapter(cl);
    }
}
