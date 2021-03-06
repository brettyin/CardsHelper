package com.brettyin.cardshelper;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.brettyin.cardshelper.model.Avatar;
import com.brettyin.cardshelper.model.Player;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by XYin on 08.07.2015.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private Context mContext;
    private List<Player> contactList;
    public List<Long> idList;
    List<Integer> colors;
    public ContactAdapter(Context context ,List<Player> contactList) {
        this.idList=new ArrayList<Long>();
        this.contactList = contactList;
        this.mContext=context;
        colors=new ArrayList<Integer>();
        colors.add(0X7fef5350);
        colors.add(0X7fe91e63);
        colors.add(0X7f9c27b0);
        colors.add(0X7f7c4dff);
        colors.add(0X7f448aff);
        colors.add(0X7f00b0ff);
        colors.add(0X7f4caf50);
        colors.add(0X7fcddc39);
        colors.add(0X7fffa726);
        colors.add(0X7f8d6e63);
    }


    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {
        final Player mPlayer = contactList.get(i);
        contactViewHolder.vName.setText(mPlayer.getName());
        contactViewHolder.vName.setBackgroundColor(colors.get(i%10));
        contactViewHolder.vNickname.setText(mPlayer.getNickname());
        contactViewHolder.vPic.setImageResource(Avatar.values()[Integer.valueOf(mPlayer.getPic())].getDrawableId());

        contactViewHolder.chkSelected.setTag(contactList.get(i));
        //contactViewHolder.vSurname.setText(ci.surname);
        //contactViewHolder.vEmail.setText(ci.email);
        //contactViewHolder.vTitle.setText(ci.name + " " + ci.surname);
        contactViewHolder.chkSelected.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                Player contact = (Player) cb.getTag();
                if (cb.isChecked())
                {
                    idList.add(contact.getId());
                }else
                {
                    idList.remove(contact.getId());
                }

                MyApplication myApplication=(MyApplication) mContext.getApplicationContext();
                myApplication.setPlayerIDList(idList);
                if (idList.size()>=5 && idList.size()<=6)
                    myApplication.getMainActivity().changeBtn(true);
                else
                    myApplication.getMainActivity().changeBtn(false);
                //contact.setSelected(cb.isChecked());
                //stList.get(pos).setSelected(cb.isChecked());

                /*String players="";
                for (Player p:contactList) {
                    if (myApplication.getPlayerIDList().contains(p.getId()))
                    {
                        players+=p.getName()+"; ";
                    }
                }

                Toast.makeText(
                        v.getContext(),
                        "Selected players: "+players, Toast.LENGTH_LONG).show();*/
            }
        });
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_layout, viewGroup, false);

        return new ContactViewHolder(itemView);
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {

        protected TextView vName;
        protected TextView vNickname;
        protected CircleImageView vPic;
        protected CheckBox chkSelected;
        public ContactViewHolder(View v) {
            super(v);
            vName =  (TextView) v.findViewById(R.id.txtName);
            vNickname = (TextView)  v.findViewById(R.id.txtNickname);

            vPic = (CircleImageView) v.findViewById(R.id.profile_image);
            chkSelected=(CheckBox)v.findViewById(R.id.chkSelected);
        }
    }
}