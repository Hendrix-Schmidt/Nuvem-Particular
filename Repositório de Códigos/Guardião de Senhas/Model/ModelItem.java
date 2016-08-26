/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Schmidt
 */
public class ModelItem {
    private int id;
    private String categoria;
    private String item;
    private String senha;
    private String observacao;
    private int usuario;
    
   public ModelItem(int id, String categoria, String item, String senha, String observacao, int usuario) {
        this.id = id;
        this.categoria = categoria;
        this.item = item;
        this.senha = senha;
        this.observacao = observacao;
        this.usuario = usuario;
    }
    public ModelItem(){}
   

    public String toString() {
        return this.getItem();
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }
}
