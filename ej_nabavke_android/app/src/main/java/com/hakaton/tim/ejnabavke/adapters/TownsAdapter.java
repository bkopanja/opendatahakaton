package com.hakaton.tim.ejnabavke.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hakaton.tim.ejnabavke.R;
import com.hakaton.tim.ejnabavke.model.CardViewItemInterface;
import com.hakaton.tim.ejnabavke.model.TownEntity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by bojankopanja on 12/5/15.
 */
public class TownsAdapter extends RecyclerView.Adapter<TownsAdapter.ViewHolder> {

    private List<TownEntity> mDataSet;
    private Context context;
    private CardViewItemInterface callback;

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param data String[] containing the data to populate views to be used by RecyclerView.
     */
    public TownsAdapter(Context context, List<TownEntity> data, CardViewItemInterface callback) {
        this.context = context;
        this.mDataSet = data;
        this.callback = callback;
    }

    public void setData(List<TownEntity> data) {
        this.mDataSet = data;
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.text_row_item, viewGroup, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        TownEntity town = mDataSet.get(position);
        viewHolder.tvTownName.setText(town.getNaziv());
        viewHolder.tvTownOffers.setText("Nabavki: " + town.getBroj_nabavki());

        if(!town.getSelected()) {
            viewHolder.cvRowHolder.setBackgroundColor(ContextCompat.getColor(context, R.color.my_card_background));
            viewHolder.tvTownName.setTextColor(Color.BLACK);
            viewHolder.tvTownOffers.setTextColor(Color.BLACK);
        } else {
            viewHolder.cvRowHolder.setBackgroundColor(ContextCompat.getColor(context, R.color.my_primary));
            viewHolder.tvTownName.setTextColor(Color.WHITE);
            viewHolder.tvTownOffers.setTextColor(Color.WHITE);
        }

        viewHolder.cvRowHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.OnClick(position);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.cvRowHolder)
        CardView cvRowHolder;
        @Bind(R.id.tvTownName)
        TextView tvTownName;
        @Bind(R.id.tvTownOffers)
        TextView tvTownOffers;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

}
