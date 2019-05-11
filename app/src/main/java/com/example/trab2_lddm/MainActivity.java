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
        Node node = new Node("leitura", nodes.size());
        nodes.add(node);
        adapter.notifyDataSetChanged();
    }

    public void btnRet(View V) {
        Toast.makeText(this, "kakakakakakak lolololo", Toast.LENGTH_SHORT).show();
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
        meuFragment = new MeuFragment();
        if(node.isLeaf()){
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.fragment_container,meuFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else {
            //Atualiza a view
        }
    }
}
