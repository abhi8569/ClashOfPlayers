package com.coc.abhi8.clashofplayers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
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
    ImageView clanBadgeImage;

    TextView clan_name,clan_description,clan_type,clan_location,clan_frequency,clan_wins,clan_point,clan_reqtrop;

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
            JSON_URL="https://set7z18fgf.execute-api.us-east-1.amazonaws.com/prod/?route=getClanDetails&clanTag=%23"+clanTag.trim().toUpperCase();
            //Toast.makeText(this,JSON_URL,Toast.LENGTH_SHORT).show();
            sendRequest();
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
        clan_name=(TextView)findViewById(R.id.clan_name_text_view_header);

        //setClanHeader
        //=====================================//
        //setName
        clan_name=(TextView)findViewById(R.id.clan_name_text_view_header);
        clan_name.setTypeface(PopulatePlayerListView.myTypeface);
        clan_name.setText(ParsePlayerList.thisClan.getC_name());

        //setClan Description
        clan_description=(TextView)findViewById(R.id.clan_description_text_view_header);
        clan_description.setTypeface(PopulatePlayerListView.myTypeface);
        clan_description.setText(ParsePlayerList.thisClan.getC_description());

        //clan type
        clan_type=(TextView)findViewById(R.id.clan_type_text_view_header);
        clan_type.setText("type       : "+ParsePlayerList.thisClan.getC_type());

        //clan location
        clan_location=(TextView)findViewById(R.id.clan_location_text_view_header);
        clan_location.setText("location: " + ParsePlayerList.thisClan.getC_locationName());

        //war frequency
        clan_frequency=(TextView)findViewById(R.id.clan_frequency_text_view_header);
        clan_frequency.setText("war        : " + ParsePlayerList.thisClan.getC_warFrequency());

        //war wins
        clan_wins=(TextView)findViewById(R.id.clan_wins_text_view_header);
        clan_wins.setText("wins         : " + ParsePlayerList.thisClan.getC_warWins());

        //clan points
        clan_point=(TextView)findViewById(R.id.clan_point_text_view_header);
        clan_point.setText("points       : " + ParsePlayerList.thisClan.getC_clanPoints());

        //clan req  Trophies
        clan_reqtrop=(TextView)findViewById(R.id.clan_req_trophies_text_view_header);
        clan_reqtrop.setText("req. trophies : " + ParsePlayerList.thisClan.getC_requiredTrophies());


        //set clanBadge Image
        clanBadgeImage=(ImageView)findViewById(R.id.clan_badge_image_header);
        PopulatePlayerListView.imageLoader.displayImage(ParsePlayerList.thisClan.getC_clanBadge(),clanBadgeImage,PopulatePlayerListView.options);

        listView.setAdapter(cl);

    }
}
