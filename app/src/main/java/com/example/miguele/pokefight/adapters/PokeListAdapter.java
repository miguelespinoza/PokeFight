package com.example.miguele.pokefight.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miguele.pokefight.R;
import com.example.miguele.pokefight.model.Pokemon;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Resources used:
 *  - RecyclerView: https://github.com/codepath/android_guides/wiki/Using-the-RecyclerView
 */
public class PokeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "adapter_poke_list";

    private List<Pokemon> mPokemonList;
    private final LayoutInflater mLayoutInflater;
    private Context mContext;

    public PokeListAdapter(Context context, ArrayList<Pokemon> pokemonList) {
        this.mContext = context;
        this.validatePokeList(pokemonList);
        this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mPokemonList = pokemonList;
    }

    @Override
    public int getItemCount() {
        return (this.mPokemonList != null) ? this.mPokemonList.size() : 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        if (viewType == EMPTY_VIEW) {
            view = this.mLayoutInflater.inflate(R.layout.empty_challenger, parent, false);
            EmptyViewHolder evh = new EmptyViewHolder(view);
            return evh;

        } else {
            view = this.mLayoutInflater.inflate(R.layout.item_poke, parent, false);
            PokeViewHolder pokeViewHolder = new PokeViewHolder(mContext, view);

            return pokeViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof PokeViewHolder) {

            PokeViewHolder pokeViewHolder = (PokeViewHolder) holder;
            Log.i(TAG, "bindView on position(" + String.valueOf(position) + ")");
            final Pokemon pokemon = this.mPokemonList.get(position);
            String counter = "10";
            if (position + 1 < 10) {
                counter = "0" + String.valueOf(position + 1);
            }
            pokeViewHolder.mCounter.setText(counter);
            Uri uri = generatePokeMediaUri(pokemon.getPkdx_id());
            Picasso.with(mContext).load(uri)
                    .into(pokeViewHolder.mPokemonImg);
            pokeViewHolder.mPokemonName.setText(pokemon.getName());
        }
    }

    private Uri generatePokeMediaUri(int pokeDexId) {
        return Uri.parse("http://pokeapi.co/media/img/" + String.valueOf(pokeDexId) + ".png");
    }

    private void validatePokeList(ArrayList<Pokemon> pokemonList) {
        if (pokemonList == null) {
            throw new IllegalArgumentException("The pokemonList cannot be null, function(validatePokeList)");
        }
    }

    public void setPokemonList(ArrayList<Pokemon> pokeList) {
        this.validatePokeList(pokeList);
        this.mPokemonList = (List<Pokemon>) pokeList;
        this.notifyDataSetChanged();
    }

    public class EmptyViewHolder extends RecyclerView.ViewHolder {
        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static class PokeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.counter_tv) TextView mCounter;
        @Bind(R.id.pokemon_iv) ImageView mPokemonImg;
        @Bind(R.id.pokemon_name_tv) TextView mPokemonName;
        @Bind(R.id.star_iv) ImageView mSelected;

        private Context mContext;


        public PokeViewHolder(Context context, final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mContext = context;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, mPokemonName.getText(), Toast.LENGTH_SHORT).show();

            if (mSelected.getVisibility() == View.VISIBLE) {
                mSelected.setVisibility(View.GONE);
            } else {
                mSelected.setVisibility(View.VISIBLE);
            }
        }
    }

    private static final int EMPTY_VIEW = 10;

}
