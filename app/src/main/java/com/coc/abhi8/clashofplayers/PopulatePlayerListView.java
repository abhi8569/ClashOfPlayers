package com.coc.abhi8.clashofplayers;

/**
 * Created by abhi8 on 2/8/2016.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PopulatePlayerListView extends ArrayAdapter<Player> {

    private String[] trophies;
    private String[] names;
    private String[] expLevel;
    private Player[] _playerList;
    private Activity context;

    public PopulatePlayerListView(Activity context, Player _playerList[]) {
        super(context, R.layout.player_listview_layout, _playerList);
        this.context = context;
//        this.trophies = trophies;
//        this.names = names;
//        this.expLevel = expLevel;
        this._playerList=_playerList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.player_listview_layout, null, true);
        TextView textViewId = (TextView) listViewItem.findViewById(R.id.textViewTrophies);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewEmail = (TextView) listViewItem.findViewById(R.id.textViewExpLevel);

        textViewId.setText(_playerList[position].getTrophies());
        textViewName.setText(_playerList[position].getName());
        textViewEmail.setText(_playerList[position].getLeagueBadgeImg());

        return listViewItem;
    }


}
