package com.coc.abhi8.clashofplayers;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class Setting extends AppCompatActivity {

    public ImageButton saveClanTagButton;
    public EditText clan_tag_input_text_java;
    ListView clanTagListView;
    ArrayAdapter<String> listAdapter;
    ArrayList<String> clanList;
    StringBuilder clanListBuilder;
    int position1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.setting_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final SharedPreferences settings = getSharedPreferences(MainActivity.PREFS_NAME, 0);

        clanListBuilder = new StringBuilder();
        clanTagListView=(ListView)findViewById(R.id.clan_list_list_view);
        clan_tag_input_text_java = (EditText) findViewById(R.id.clan_tag_input_Text);
        saveClanTagButton=(ImageButton)findViewById(R.id.add_to_clan_list_img_button);

        if (settings.contains("CLAN_TAG")) {
           // clan_tag_input_text_java.setText(settings.getString("CLAN_TAG", null).toString());
        }

        if(settings.contains("CLAN_TAG_LIST"))
        {
            clanListBuilder.append(settings.getString("CLAN_TAG_LIST",null ));
            clanList=new ArrayList<String>(Arrays.asList(settings.getString("CLAN_TAG_LIST",null).toString().split(",")));
            refreshClanList();
        }

        saveClanTagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Setting.this,"Added to list",Toast.LENGTH_SHORT).show();
                clanListBuilder.append(clan_tag_input_text_java.getText()+",");
                clanList=new ArrayList<String>(Arrays.asList(clanListBuilder.toString().split(",")));
                SharedPreferences.Editor editor=settings.edit();
                editor.putString("CLAN_TAG_LIST", clanListBuilder.toString());
                editor.commit();
                refreshClanList();
                clan_tag_input_text_java.setText("");
            }
        });

        clanTagListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(Setting.this,clanList.get(position).toString()+" Set as Default",Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("CLAN_TAG", clanList.get(position).toString());
                editor.commit();
                Intent i=new Intent(Setting.this,MainActivity.class);
                Setting.this.startActivity(i);
            }
        });

        clanTagListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                position1=position;

                AlertDialog.Builder adb = new AlertDialog.Builder(Setting.this);
                adb.setTitle("Delete "+clanList.get(position1).toString()+" ?");
                adb.setIcon(android.R.drawable.ic_menu_delete);
                adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(Setting.this,clanList.get(position1).toString()+" Deleted",Toast.LENGTH_SHORT).show();
                        clanList.remove(position1);
                        clanListBuilder.delete(0,clanListBuilder.length());
                        for (String s : clanList)
                        {
                            clanListBuilder.append(s+",");
                        }
                        SharedPreferences.Editor editor=settings.edit();
                        editor.putString("CLAN_TAG_LIST", clanListBuilder.toString());
                        editor.commit();
                        refreshClanList();
                    }
                });
                adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                adb.show();
                return true;
            }
        });

    }

    public void refreshClanList()
    {
        listAdapter = new ArrayAdapter<String>(Setting.this,R.layout.clan_list_layout, clanList);
        clanTagListView.setAdapter(listAdapter);
    }


}
