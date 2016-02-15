package com.coc.abhi8.clashofplayers;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Setting extends AppCompatActivity {

    public Button saveClanTagButton;
    public EditText clan_tag_input_text_java;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.setting_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final SharedPreferences settings = getSharedPreferences(MainActivity.PREFS_NAME, 0);

        clan_tag_input_text_java=(EditText)findViewById(R.id.clan_tag_input_Text);
        saveClanTagButton=(Button)findViewById(R.id.save_clan_tag_button);

        if(settings.contains("CLAN_TAG"))
        {
            clan_tag_input_text_java.setText(settings.getString("CLAN_TAG", null).toString());
        }

        saveClanTagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings.edit().putString("CLAN_TAG",clan_tag_input_text_java.getText().toString().toUpperCase()).apply();
                Toast.makeText(Setting.this,clan_tag_input_text_java.getText().toString().toUpperCase(),Toast.LENGTH_LONG).show();
            }
        });

    }



}
