package com.example.trab2_lddm;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MeuAdapter extends RecyclerView.Adapter<MeuAdapter.MeuViewHolder> {

    public static RecyclerInterface recyclerInterface;
    Context ctx;
    private List<Node> filhosAtuais;

    public MeuAdapter (Context ctx, List<Node> lista, RecyclerInterface clickRecyclerInterface) {
        this.ctx = ctx;
        this.filhosAtuais = lista;
        this.recyclerInterface = clickRecyclerInterface;
    }

    @Override
    public MeuViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.node,viewGroup,false);
        return new MeuViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MeuViewHolder viewHolder, final int i) {
        Node node = filhosAtuais.get(i);
        viewHolder.txt.setText(node.getNome());

        viewHolder.delBtn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Node deletedNode = filhosAtuais.get(i);
                filhosAtuais.remove(deletedNode);
                notifyItemRemoved(i);
                notifyDataSetChanged();
            }
        }));

        viewHolder.editBtn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nova = "falta ler";
                //leitura de uma String :)
                filhosAtuais.get(i).setContent(nova);
            }
        }));
    }

    @Override
    public int getItemCount() {
        return filhosAtuais.size();
    }

    protected class MeuViewHolder extends RecyclerView.ViewHolder {
        protected TextView txt;
        protected Button editBtn;
        protected Button delBtn;

        public MeuViewHolder(final View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.txt);
            editBtn = itemView.findViewById(R.id.edit);
            delBtn = itemView.findViewById(R.id.rmChild);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerInterface.onItemClick(filhosAtuais.get(getLayoutPosition()));
                }
            });
        }
    }
}
