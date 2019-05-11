package com.example.trab2_lddm;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
                AlertDialog.Builder alert = new AlertDialog.Builder(ctx);
                alert.setTitle("Conteúdo:");
                alert.setMessage("Digite novo o conteúdo do nó");

                // Create EditView
                final EditText input = new EditText(ctx);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alert.setView(input);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String nova = input.getText().toString();
                        filhosAtuais.get(i).setContent(nova);
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Toast.makeText(ctx, "CANCELADO", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();
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
