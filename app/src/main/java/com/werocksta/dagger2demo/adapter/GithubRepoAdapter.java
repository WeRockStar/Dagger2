package com.werocksta.dagger2demo.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.werocksta.dagger2demo.R;
import com.werocksta.dagger2demo.model.RepoCollection;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GithubRepoAdapter extends RecyclerView.Adapter<GithubRepoAdapter.RepoViewHolder> {

    private List<RepoCollection> repo;
    private OnClickRepo listener;

    public void setRepoList(List<RepoCollection> list) {
        this.repo = list;
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_item_row, parent, false);
        return new RepoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {
        RepoCollection repoCollection = repo.get(position);
        holder.tvName.setText(repoCollection.getNameRepo());
        holder.tvLanguage.setText(repoCollection.getLanguage());
    }

    @Override
    public int getItemCount() {
        return repo == null ? 0 : repo.size();
    }

    class RepoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tvName)
        TextView tvName;

        @BindView(R.id.card)
        CardView cvRepo;

        @BindView(R.id.tvLanguage)
        TextView tvLanguage;

        public RepoViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            cvRepo.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onClickRepoItem(repo.get(getAdapterPosition()));
            }
        }
    }

    public interface OnClickRepo {
        void onClickRepoItem(RepoCollection repo);
    }

    public void setOnClickRepo(OnClickRepo listener) {
        this.listener = listener;
    }
}
