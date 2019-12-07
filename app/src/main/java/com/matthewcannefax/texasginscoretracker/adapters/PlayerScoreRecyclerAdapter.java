package com.matthewcannefax.texasginscoretracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.matthewcannefax.texasginscoretracker.R;
import com.matthewcannefax.texasginscoretracker.model.Player;

import java.util.List;

public class PlayerScoreRecyclerAdapter extends RecyclerView.Adapter<PlayerScoreRecyclerAdapter.PlayerViewHolder> {

    private List<Player> mPlayerList;
    private Context mContext;
    private LayoutInflater mInflater;

    public PlayerScoreRecyclerAdapter(Context context, List<Player> playerList){
        mPlayerList = playerList;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.player_score_item, parent, false);

        return new PlayerViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        Player currentPlayer = mPlayerList.get(position);
        holder.tvPlayerName.setText(currentPlayer.getName());
        holder.btWinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mPlayerList.size();
    }

    class PlayerViewHolder extends RecyclerView.ViewHolder{

        TextView tvPlayerName;
        EditText etRoundScore;
        Button btWinner;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);

            tvPlayerName = itemView.findViewById(R.id.player_name_textview);
            etRoundScore = itemView.findViewById(R.id.player_score_edittext);
            btWinner = itemView.findViewById(R.id.winner_button);
        }
    }
}
