package com.shzlabs.payukickstarter.ui.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shzlabs.payukickstarter.R;
import com.shzlabs.payukickstarter.data.model.Kickstarter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shaz on 13/8/17.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.CustomViewHolder>{

    private Context ctx;
    private List<Kickstarter> items;

    @Inject
    public ListAdapter(Context context) {
        this.ctx = context;
        items = new ArrayList<>();
    }

    public void setItems(List<Kickstarter> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.item_main_list, parent, false);

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Kickstarter item = items.get(position);
        holder.titleTv.setText(item.getTitle());
        holder.pledgeTv.setText(String.valueOf(item.getAmtPledged()));
        holder.backersTv.setText(item.getNumBackers());
        holder.daysToGo.setText(item.getEndTime());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.title)
        TextView titleTv;
        @BindView(R.id.pledge)
        TextView pledgeTv;
        @BindView(R.id.backers)
        TextView backersTv;
        @BindView(R.id.days_to_go)
        TextView daysToGo;

        public CustomViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
