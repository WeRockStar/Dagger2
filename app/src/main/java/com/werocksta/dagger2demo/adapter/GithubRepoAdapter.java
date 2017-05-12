package com.werocksta.dagger2demo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.werocksta.dagger2demo.R;
import com.werocksta.dagger2demo.model.RepoCollection;
import com.werocksta.dagger2demo.widget.CustomRecyclerView;

import java.util.List;

public class GithubRepoAdapter extends CustomRecyclerView {

    private List<RepoCollection> repo;
    private OnClickRepository listener;

    public void setGithubAdapter(List<RepoCollection> repo, OnClickRepository listener) {
        this.repo = repo;
        this.listener = listener;
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_item_row, parent, false);
        return new RepoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RepoCollection repository = repo.get(position);
        RepoViewHolder viewHolder = (RepoViewHolder) holder;
        viewHolder.tvName.setText(repository.getNameRepo());
        viewHolder.tvLanguage.setText(repository.getLanguage());

        viewHolder.cvRepo.setOnClickListener(v -> {
            listener.onClickRepoItem(repository);
        });
    }

    @Override
    public int getItemCount() {
        return repo == null ? 0 : repo.size();
    }

    public interface OnClickRepository {
        void onClickRepoItem(RepoCollection repo);
    }

}
