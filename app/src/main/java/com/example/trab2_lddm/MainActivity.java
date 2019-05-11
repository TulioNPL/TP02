package com.example.trab2_lddm;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerInterface {

    RecyclerView meuRecyclerView;
    LinearLayoutManager meuLayoutManager;
    MeuAdapter adapter;
    private List<Node> nodes = new ArrayList<>();

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
        Node node = new Node("leitura");
        nodes.add(node);
        adapter.notifyDataSetChanged();
    }

    public void btnRet(View V) {
        Toast.makeText(this, "kakakakakakak lolololo", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(Object object) {
        Node node = (Node) object;
        if(node.isLeaf()){
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.fragment_container,new MeuFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        } else {
            //Atualiza a view
        }
    }
}
