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
    MeuAdapter novoAdapter;
    private List<Node> nodes = new ArrayList<>();
    private List<Node> pais = new ArrayList<>();
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

    /**
     * OnClick do botao Add
     * @param V, View
     */
    public void btnAdd(View V) {
        criaDialog();
    }

    /**
     * OnClick do botao Return
     * @param V, View
     */
    public void btnRet(View V) {
        nodes = pais;
        novoAdapter = new MeuAdapter(this, pais, this);
        meuRecyclerView.setAdapter(novoAdapter);
    }

    /**
     * OnClick do botao Close
     * @param V, View
     */
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
            pais = nodes;
            nodes = node.getChildren();
            novoAdapter = new MeuAdapter(this, node.getChildren(), this);
            meuRecyclerView.setAdapter(novoAdapter);
        }
    }

    /**
     * Cria uma Dialog para receber os dados de um novo no
     */
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
                if(novoAdapter != null) novoAdapter.notifyDataSetChanged();
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
