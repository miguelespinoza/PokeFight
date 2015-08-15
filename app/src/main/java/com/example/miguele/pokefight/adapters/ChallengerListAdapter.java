package com.example.miguele.pokefight.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miguele.pokefight.R;
import com.example.miguele.pokefight.utils.Spitter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by miguele on 8/14/15.
 */
public class ChallengerListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "adapter_challenger_list";
    private List<String> mChallengers;
    private final LayoutInflater mLayoutInflater;
    private Context mContext;

    public ChallengerListAdapter(Context context, ArrayList<String> challengers) {
        this.mContext = context;
        this.validateChallengerList(challengers);
        this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mChallengers = challengers;
    }

    @Override
    public int getItemCount() {
        return (this.mChallengers != null) ? this.mChallengers.size() : 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == EMPTY_VIEW) {
            view = this.mLayoutInflater.inflate(R.layout.empty_challenger, parent, false);
            EmptyViewHolder evh = new EmptyViewHolder(view);
            return evh;
        } else {
            view = this.mLayoutInflater.inflate(R.layout.item_challenger, parent, false);
            ChallengerViewHolder challengerViewHolder = new ChallengerViewHolder(mContext, view);
            return  challengerViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ChallengerViewHolder) {
            ChallengerViewHolder challengerViewHolder = (ChallengerViewHolder) holder;

            Spitter.Log(TAG, "onBindViewHolder", "bindView on position(" + String.valueOf(position) + ")");

            final String challengerName = this.mChallengers.get(position);
            String counter = "10";
            if (position + 1 < 10) {
                counter = "0" + String.valueOf(position + 1);
            }
            challengerViewHolder.mCounter.setText(counter);
            challengerViewHolder.mChallegerName.setText(challengerName);
        }
    }

    public void setChallengers(ArrayList<String> challengers) {
        this.validateChallengerList(challengers);
        this.mChallengers = (List<String>) challengers;
        this.notifyDataSetChanged();
    }

    private void validateChallengerList(ArrayList<String> challengers) {
        if (challengers == null) {
            throw new IllegalArgumentException("The pokemonList cannot be null, function(validatePokeList)");
        }
    }

    public class EmptyViewHolder extends RecyclerView.ViewHolder {
        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static class ChallengerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.counter_tv) TextView mCounter;
        @Bind(R.id.challenger_name_tv) TextView mChallegerName;
        @Bind(R.id.star_iv) ImageView mSelected;

        private Context mContext;

        public ChallengerViewHolder(Context context, final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mContext = context;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, mChallegerName.getText(), Toast.LENGTH_SHORT).show();

            if (mSelected.getVisibility() == View.VISIBLE) {
                mSelected.setVisibility(View.GONE);
            } else {
                mSelected.setVisibility(View.VISIBLE);
            }
        }
    }


    private static final int EMPTY_VIEW = 10;
}
