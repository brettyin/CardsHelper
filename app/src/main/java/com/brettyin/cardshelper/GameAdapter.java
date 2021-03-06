package com.brettyin.cardshelper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.brettyin.cardshelper.model.Avatar;
import com.brettyin.cardshelper.model.Game;
import com.brettyin.cardshelper.model.GameToPlayer;
import com.brettyin.cardshelper.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by XYin on 08.07.2015.
 */
public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ContactViewHolder> {

    private Context mContext;
    private List<GameToPlayer> gameList;
    public List<Long> idList;
    public GameAdapter(Context context ,List<GameToPlayer> gameList) {
        this.idList=new ArrayList<Long>();
        this.gameList = gameList;
        this.mContext=context;
    }


    @Override
    public int getItemCount() {
        return gameList.size();
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {
        final GameToPlayer game = gameList.get(i);
        contactViewHolder.vName.setText(String.valueOf(game.getPlayer().getName()));
        contactViewHolder.vNickname.setText(String.valueOf(game.getPlayer().getName()));
        int level=game.getLevel();
        int plusnr=level/13;
        if(plusnr>0)
        {
            contactViewHolder.vPlus1.setVisibility(View.VISIBLE);
            if(plusnr>1)
            {
                contactViewHolder.vPlus2.setVisibility(View.VISIBLE);

            }
        }
        Random rn = new Random();
         level=(14-level)*4+ rn.nextInt(4)+1;
        contactViewHolder.vLevel.setImageResource( mContext.getResources().getIdentifier("c"+String.valueOf(level) , "drawable", mContext.getPackageName()));
        contactViewHolder.vAvatar.setImageResource(Avatar.values()[Integer.valueOf(game.getPlayer().getPic())].getDrawableId());
        //contactViewHolder.chkSelected.setTag(gameList.get(i));
        //contactViewHolder.vSurname.setText(ci.surname);
        //contactViewHolder.vEmail.setText(ci.email);
        //contactViewHolder.vTitle.setText(ci.name + " " + ci.surname);
//        contactViewHolder.chkSelected.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                CheckBox cb = (CheckBox) v;
//                Player contact = (Player) cb.getTag();
//                if (cb.isChecked())
//                {
//                    idList.add(contact.getId());
//                }else
//                {
//                    idList.remove(contact.getId());
//                }

//                ( (MyApplication) mContext.getApplicationContext()).setPlayerIDList(idList);
                //contact.setSelected(cb.isChecked());
                //stList.get(pos).setSelected(cb.isChecked());
//                String players="";
//                for (GameToPlayer p:gameList) {
//                    if (( (MyApplication) mContext.getApplicationContext()).getPlayerIDList().contains(p.getId()))
//                    {
//                        players+=p.getPlayer().getName()+"; ";
//                    }
//                }
//
//                Toast.makeText(
//                        v.getContext(),
//                        "Selected players: "+players, Toast.LENGTH_LONG).show();
//            }
//        });
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.game_layout, viewGroup, false);

        return new ContactViewHolder(itemView);
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {

        protected TextView vName;
        protected TextView vNickname;
        protected ImageView vLevel,vPlus1, vPlus2;
        protected CircleImageView vAvatar;
        public ContactViewHolder(View v) {
            super(v);
            vName =  (TextView) v.findViewById(R.id.gamePlayerName);
            vNickname = (TextView)  v.findViewById(R.id.gamePlayerNick);
            vLevel = (ImageView)  v.findViewById(R.id.game_levelimg);
            vAvatar=(CircleImageView)v.findViewById(R.id.game_profile_image);
            vPlus1 = (ImageView)  v.findViewById(R.id.game_plus1);

            vPlus2 = (ImageView)  v.findViewById(R.id.game_plus2);


        }
    }
}