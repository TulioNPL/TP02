package com.example.trab2_lddm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerInterface {

    RecyclerView meuRecyclerView;
    LinearLayoutManager meuLayoutManager;
    MeuAdapter adapter;
    private List<Node> nodes = new ArrayList<>();
    MeuFragment meuFragment = new MeuFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        meuRecyclerView = (RecyclerView) findViewById(R.id.mainRV);
        meuLayoutManager = new LinearLayoutManager(this);
        meuRecyclerView.setLayoutManager(meuLayoutManager);
        adapter = new MeuAdapter(this,nodes,this);
        meuRecyclerView.setAdapter(adapter);
    }

    public void btnAdd(View V) {
        criaDialog();
    }

    public void btnRet(View V) {
        Node node = nodes.get(0);
        if(node.getFather() != null && node.getFather().getFather() != null) {
            MeuAdapter novoAdapter = new MeuAdapter(this, node.getFather().getFather().getChildren(), this); //Pega a lista de filhos do avo
            meuRecyclerView.setAdapter(novoAdapter);
        } else {
            Toast.makeText(this, "Impossível retornar mais!", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnClose(View V) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.detach(meuFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onItemClick(Object object) {
        Node node = (Node) object;
        meuFragment = MeuFragment.newFrag(node.getContent());
        if(node.isLeaf()){
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.fragment_container,meuFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else {
            //Atualiza a view
            MeuAdapter novoAdapter = new MeuAdapter(this, node.getChildren(), this);
            meuRecyclerView.setAdapter(novoAdapter);
        }
    }

    public void criaDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Conteúdo:");
        alert.setMessage("Digite o conteúdo do nó");

        // Create EditView
        final EditText input = new EditText(MainActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Node node = new Node(input.getText().toString(), nodes.size());
                nodes.add(node);
                adapter.notifyDataSetChanged();
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(MainActivity.this, "CANCELADO", Toast.LENGTH_SHORT).show();
            }
        });
        alert.show();
    }
}
