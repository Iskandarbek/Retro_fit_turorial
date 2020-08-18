package com.example.retrofit_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.Holder> {

    List<Repo> repoList;
    Context context;

    public RepoAdapter(Context context, List<Repo>repoList){
        this.repoList = repoList;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.repo_item,
                parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.name.setText(repoList.get(position).getName());
        holder.link.setText(repoList.get(position).getLink());
    }

    @Override
    public int getItemCount() {
        return repoList.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {

        TextView name, link;

        public Holder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name_text);
            link = itemView.findViewById(R.id.link_text);

        }
    }
}
