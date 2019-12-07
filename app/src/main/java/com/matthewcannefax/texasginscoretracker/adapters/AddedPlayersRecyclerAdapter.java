package com.matthewcannefax.texasginscoretracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.matthewcannefax.texasginscoretracker.R;
import com.matthewcannefax.texasginscoretracker.model.Player;

import java.util.List;

public class AddedPlayersRecyclerAdapter extends RecyclerView.Adapter<AddedPlayersRecyclerAdapter.PlayerViewHolder> {

    private List<Player> mPlayerList;
    private Context mContext;
    private LayoutInflater mInflater;

    public AddedPlayersRecyclerAdapter(Context context, List<Player> playerList){
        mPlayerList = playerList;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.add_player_item, parent, false);

        return new PlayerViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(PlayerViewHolder holder, int position) {
        Player currentPlayer = mPlayerList.get(position);
        holder.tvPlayerName.setText(currentPlayer.getName());
    }

    @Override
    public int getItemCount() {
        return mPlayerList.size();
    }

    class PlayerViewHolder extends RecyclerView.ViewHolder{

        TextView tvPlayerName;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);

            tvPlayerName = itemView.findViewById(R.id.player_name_textview);
        }
    }
}
