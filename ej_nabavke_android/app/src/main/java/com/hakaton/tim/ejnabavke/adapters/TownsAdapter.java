package com.hakaton.tim.ejnabavke.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hakaton.tim.ejnabavke.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by bojankopanja on 12/5/15.
 */
public class TownsAdapter extends RecyclerView.Adapter<TownsAdapter.ViewHolder> {

    private JSONArray mDataSet;
    private Context context;

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView townName;
        public TextView townOffers;

        public ViewHolder(View v) {
            super(v);

            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.setBackground(ResourcesCompat.getDrawable(TownsAdapter.this.context.getResources(), R.drawable.corners_selected, null));
                    townName.setTextColor(Color.WHITE);
                    townOffers.setTextColor(Color.WHITE);
                }
            });
            townName = (TextView) v.findViewById(R.id.townName);
            townOffers = (TextView) v.findViewById(R.id.townOffers);

            v.setBackground(ResourcesCompat.getDrawable(TownsAdapter.this.context.getResources(), R.drawable.corners, null));
            townName.setTextColor(Color.BLACK);
            townOffers.setTextColor(Color.BLACK);
        }

        public TextView getTextView() {
            return townName;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param data String[] containing the data to populate views to be used by RecyclerView.
     */
    public TownsAdapter(Context context, JSONArray data) {
        this.context = context;
        this.mDataSet = data;
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
        JSONObject town = null;
        try {
            town = mDataSet.getJSONObject(position);
            viewHolder.townName.setText(town.getString("naziv"));
            viewHolder.townOffers.setText("Nabavki: " + town.getString("broj_nabavki"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.length();
    }

}
