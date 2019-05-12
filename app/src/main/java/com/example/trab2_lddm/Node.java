package com.example.trab2_lddm;

import java.util.ArrayList;

/**
 * Classe Node
 */
public class Node {
    private Node father;
    private ArrayList<Node> children = new ArrayList<Node>();
    private int cod;
    private String content;

    /**
     * Construtor vazio
     */
    public Node(){
        this.father = null;
        this.content = "raiz";
        setCod(-1);
    }

    /** Constructor
     * @param father
     * @param content
     * @param brothers
     */
    public Node(Node father, String content, int brothers) {
        this.father = father;
        this.content = content;
        setCod(brothers +1);
    }

    /** Constructor
     * @param content
     * @param brothers
     */
    public Node(String content, int brothers) {
        setContent(content);
        setCod(brothers+1);
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
     * Retorna se o no eh uma folha
     * @return boolean
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
