package com.example.trab2_lddm;

import java.util.ArrayList;

public class Node {
    private Node father;
    private ArrayList<Node> children = new ArrayList<Node>();
    private int cod;
    private String content;

    /**
     * Constructor
     * @param content
     */
    public Node(String content) {
        this.content = content;
    }

    /**
     * Constructor
     * @param father
     * @param content
     */
    public Node(Node father, String content) {
        this.father = father;
        this.content = content;
    }

    public Node getFather() {
        return father;
    }

    public void setFather(Node father) {
        this.father = father;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Node> children) {
        this.children = children;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     *
     * @return
     */
    public boolean isLeaf() {
        return children.isEmpty();
    }

    /**
     * Retorna o nome do no
     * @return nome
     */
    public String getNome() {
        String nome = "";
        if(father != null){
            nome = father.getNome() + ".";
        }
        nome = nome + cod;
        return nome;
    }
}
