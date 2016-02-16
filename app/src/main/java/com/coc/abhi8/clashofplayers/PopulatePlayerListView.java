package com.coc.abhi8.clashofplayers;

/**
 * Created by abhi8 on 2/8/2016.
 */

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class PopulatePlayerListView extends ArrayAdapter<Player> {

    private Player[] _playerList;
    private Activity context;
    public static ImageLoader imageLoader;
    public static DisplayImageOptions options;
    public static Typeface myTypeface;
    public PopulatePlayerListView(Activity context, Player _playerList[]) {
        super(context, R.layout.player_listview_layout, _playerList);
        this.context = context;
        this._playerList=_playerList;
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        myTypeface = Typeface.createFromAsset(getContext().getAssets(), "font/coc_font.ttf");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.player_listview_layout, null, true);

        //set Name
        TextView nameTextViewId = (TextView) listViewItem.findViewById(R.id.playerNameTextView);
        nameTextViewId.setTypeface(myTypeface);
        nameTextViewId.setText(_playerList[position].getName());

        //Set role
        TextView roleTextViewId = (TextView) listViewItem.findViewById(R.id.playerRoleTextView);
        roleTextViewId.setText(_playerList[position].getRole());

        //setExpLevel
        TextView expLevelTextViewId = (TextView) listViewItem.findViewById(R.id.playerexpLevelTextView  );
        expLevelTextViewId.setTypeface(myTypeface);
        expLevelTextViewId.setText(_playerList[position].getExpLevel());

        //set trophies
        TextView trophiesTextViewId = (TextView) listViewItem.findViewById(R.id.playerTrophiesTextView);
        trophiesTextViewId.setTypeface(myTypeface);
        trophiesTextViewId.setText(_playerList[position].getTrophies());

//        //setDonation
//        TextView donationTextViewId = (TextView) listViewItem.findViewById(R.id.playerDonationtextView);
//        donationTextViewId.setText("\u25B2"+" " +_playerList[position].getDonations());

        //set DonationReceived
        TextView donationReceivedTextViewId = (TextView) listViewItem.findViewById(R.id.playerDonationReceivedtextView);
        donationReceivedTextViewId.setText("\u25B2"+" " +_playerList[position].getDonations()+ "  "+"\u25BC"+" " +_playerList[position].getDonationsReceived());

        //set Player clan Level
        TextView ClanLevelTextViewId = (TextView) listViewItem.findViewById(R.id.playerClanLevelTextView);
        ClanLevelTextViewId.setTypeface(myTypeface);
        ClanLevelTextViewId.setText(_playerList[position].getClanRank());


        ImageView imageView = (ImageView) listViewItem.findViewById(R.id.playerBadgeImgView);
        //imageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisc(true).resetViewBeforeLoading(true)
                .showImageForEmptyUri(R.drawable.no_badge)
                .showImageOnFail(R.drawable.no_badge)
                .showImageOnLoading(R.drawable.no_badge).build();
        imageLoader.displayImage(_playerList[position].getLeagueBadgeImg(), imageView, options);

        return listViewItem;
    }



}


