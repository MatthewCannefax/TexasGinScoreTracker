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

public class PlayerScoreRecyclerAdapter extends RecyclerView.Adapter<PlayerScoreRecyclerAdapter.PlayerViewHolder> {

    private List<Player> mPlayerList;
    private Context mContext;
    private LayoutInflater mInflater;
    private boolean mEditTextsEnabled;

    public PlayerScoreRecyclerAdapter(Context context, List<Player> playerList, boolean enableEditTexts){
        mPlayerList = playerList;
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mEditTextsEnabled = enableEditTexts;
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
        holder.tvTotalScore.setText(mContext.getString(R.string.total_score, Integer.toString(currentPlayer.getTotalScore())));
    }

    @Override
    public int getItemCount() {
        return mPlayerList.size();
    }

    class PlayerViewHolder extends RecyclerView.ViewHolder{

        TextView tvPlayerName;
        TextView tvTotalScore;
//        Button btWinner;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);

            tvPlayerName = itemView.findViewById(R.id.player_name_textview);
            tvTotalScore = itemView.findViewById(R.id.player_score_textview);
//            btWinner = itemView.findViewById(R.id.winner_button);
        }
    }
}
