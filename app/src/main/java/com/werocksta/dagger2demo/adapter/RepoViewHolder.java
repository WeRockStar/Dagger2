package com.werocksta.dagger2demo.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.werocksta.dagger2demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepoViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.tvName)
    public TextView tvName;

    @BindView(R.id.card)
    public CardView cvRepo;

    @BindView(R.id.tvLanguage)
    public TextView tvLanguage;

    public RepoViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

}
