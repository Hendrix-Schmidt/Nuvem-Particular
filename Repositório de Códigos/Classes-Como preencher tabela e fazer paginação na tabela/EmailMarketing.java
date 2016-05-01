/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bean;

/**
 *
 * @author h.schmidt
 */
public class EmailMarketing {

    private int id = 0;
    private String e_Mail;
    private String listaGeekle;
    private String origemCentral;
    private String situacao;/* ANT - Antispan
     CXC - Caixa - Cheia - 
     DNSD - DNS Desconhecido - 
     INA - Inativo - 
     INVA - invalido - 
     MAY - maybe - 
     REJEDES - Rejeitado pelo  Destinatario - 
     REJESER - Rejeitado pelo Servidor
     REPTOT - Repetido Já Cad na Rel Total 
     VAL - valido - 
     VER - VERIFICAR 
     ENAO - Não EncontradoANT - Antispan
     CXC - Caixa - Cheia - 
     DNSD - DNS Desconhecido - 
     INA - Inativo - 
     INVA - invalido - 
     MAY - maybe - 
     REJEDES - Rejeitado pelo  Destinatario - 
     REJESER - Rejeitado pelo Servidor
     REPTOT - Repetido Já Cad na Rel Total 
     VAL - valido - 
     VER - VERIFICAR 
     ENAO - Não Encontrado*/
    private String divisao;
    private String nacInt;// nacional ou internacional
    private String observacoes;
    private String contatoWeb;
    private String f_j;
    private String providencia;
    private String atividade;
    private String cidade;
    private String UF;
    private String bairro;
    private String regiao;
    
    

    public EmailMarketing(int id, String e_Mail, String listaGeekle, String origemCentral,
            String situacao, String nacInt, String observacoes, String contatoWeb, String f_j,
            String providencia, String atividade, String cidade, String UF, String bairro,
            String regiao) {

        this.id = id;
        this.e_Mail = e_Mail;
        this.listaGeekle = listaGeekle;
        this.origemCentral = origemCentral;
        this.situacao = situacao;
        this.nacInt = nacInt;
        this.observacoes = observacoes;
        this.contatoWeb = contatoWeb;
        this.f_j = f_j;
        this.providencia = providencia;
        this.atividade = atividade;
        this.cidade = cidade;
        this.UF = UF;
        this.bairro = bairro;
        this.regiao = regiao;

    }

    public EmailMarketing() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getE_Mail() {
        return e_Mail;
    }

    public void setE_Mail(String e_Mail) {
        this.e_Mail = e_Mail;
    }

    public String getListaGeekle() {
        return listaGeekle;
    }

    public void setListaGeekle(String listaGeekle) {
        this.listaGeekle = listaGeekle;
    }

    public String getOrigemCentral() {
        return origemCentral;
    }

    public void setOrigemCentral(String origemCentral) {
        this.origemCentral = origemCentral;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getContatoWeb() {
        return contatoWeb;
    }

    public void setContatoWeb(String contatoWeb) {
        this.contatoWeb = contatoWeb;
    }

    public String getF_j() {
        return f_j;
    }

    public void setF_j(String f_j) {
        this.f_j = f_j;
    }

    public String getProvidencia() {
        return providencia;
    }

    public void setProvidencia(String providencia) {
        this.providencia = providencia;
    }


    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public String getDivisao() {
        return divisao;
    }

    public void setDivisao(String divisao) {
        this.divisao = divisao;
    }

    public String getNacInt() {
        return nacInt;
    }

    public void setNacInt(String nacInt) {
        this.nacInt = nacInt;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

}
