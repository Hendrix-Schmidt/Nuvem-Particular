/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import br.com.util.Conexao;
import br.com.bean.EmailMarketing;
import java.util.ArrayList;

/**
 *
 * @author Schmidt
 */
public class EmailMarketingModel {

    private ResultSet res;
    private final Conexao conexao;
    private StringBuilder query;
    private final ArrayList<EmailMarketing> lista;
    private static EmailMarketingModel InstanciaRep;

    public EmailMarketingModel() {
        conexao = new Conexao();
        this.lista = new ArrayList<>();
    }
    public void inserirEmailMarketing(EmailMarketing mail) {
        try {
            query = new StringBuilder();
            query.append("insert into cliente (id, email, listageekle, origemcentral, "
                    + "situacao, divisoes, observacoes, contatoweb, f/j, nac/int, providencia, "
                    + "atividade, cidade, uf, bairro, regiao)");
            query.append("values('");
            query.append(mail.getId());
            query.append("','");
            query.append(mail.getE_Mail());
            query.append("','");
            query.append(mail.getListaGeekle());
            query.append("','");
            query.append(mail.getOrigemCentral());
            query.append("','");
            query.append(mail.getSituacao());
            query.append("','");
            query.append(mail.getDivisao());
            query.append("','");
            query.append(mail.getObservacoes());
            query.append("','");
            query.append(mail.getContatoWeb());
            query.append("','");
            query.append(mail.getF_j());
            query.append("','");
            query.append(mail.getNacInt());
            query.append("','");
            query.append(mail.getProvidencia());
            query.append("','");
            query.append(mail.getAtividade());
            query.append("','");
            query.append(mail.getCidade());
            query.append("','");
            query.append(mail.getUF());
            query.append("','");
            query.append(mail.getBairro());
            query.append("','");
            query.append(mail.getRegiao());
            query.append("')");
            conexao.openConnection();
            conexao.executeUpdate(query);
            JOptionPane.showMessageDialog(null,
                    "Email inserido com sucesso!");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null,
                    "Erro a tentar Gravar o registro..." + erro);
        } finally {
            conexao.closeConnection();
        }
    }
    public void deletarEmail(EmailMarketing mail) {
        try {
            String info = "Deseja Deletar '" + mail.getE_Mail() + "', Cuja ID = '"
                    + mail.getId() + "' ?";
            int opcao_escolhida = JOptionPane.showConfirmDialog(null, info,
                    "Exclusao ", JOptionPane.YES_NO_OPTION);
            if (opcao_escolhida == JOptionPane.YES_OPTION) {
                query = new StringBuilder();
                query.append("delete from arquivo_mail2 where email = '");
                query.append(mail.getE_Mail());
                query.append("' and id = '");
                query.append(mail.getId());
                query.append("';");
                conexao.openConnection();
                conexao.executeUpdate(query);
                //if (conseguiu_excluir == 1) {
                //valoresDefault();
                JOptionPane.showMessageDialog(null,
                        "Exclusão realizada com sucesso");
                //}
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null,
                    "Erro a tentar deletar o registro..." + erro);
        } finally {
            conexao.closeConnection();
        }
    }
    public ArrayList<EmailMarketing> listarTodos(int limI) {
        //qtde de registro a retornar
        int limF = 400; 
        @SuppressWarnings("Convert2Diamond")
        ArrayList<EmailMarketing> retorno = new ArrayList<EmailMarketing>();
        query = new StringBuilder();
        query.append("SELECT * FROM arquivo_mail2 LIMIT "+(limI-1)*limF+", "+limF+"");//170000
        try {
            conexao.openConnection();
            res = conexao.executeQuery(query);
            while (res.next()) {
                EmailMarketing emailMarketing = new EmailMarketing();
                emailMarketing.setId(res.getInt("id"));
                emailMarketing.setE_Mail(res.getString("email"));
                emailMarketing.setListaGeekle(res.getString("listageekle"));
                emailMarketing.setOrigemCentral(res.getString("origemcentral"));
                emailMarketing.setSituacao(res.getString("situacao"));
                emailMarketing.setDivisao(res.getString("divisoes"));
                emailMarketing.setObservacoes(res.getString("observacoes"));
                emailMarketing.setContatoWeb(res.getString("contatoweb"));
                emailMarketing.setF_j(res.getString("f/j"));
                emailMarketing.setNacInt(res.getString("nac/int"));
                emailMarketing.setProvidencia(res.getString("providencia"));
                emailMarketing.setAtividade(res.getString("atividade"));
                emailMarketing.setCidade(res.getString("cidade"));
                emailMarketing.setUF(res.getString("uf"));
                emailMarketing.setBairro(res.getString("bairro"));
                emailMarketing.setRegiao(res.getString("regiao"));
                retorno.add(emailMarketing);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            //
        }
        conexao.closeConnection();
        return retorno;
    }
    public ArrayList<EmailMarketing> listarTodosEmailASC(int limI) {
        //qtde de registro a retornar
        int limF = 400;
        @SuppressWarnings("Convert2Diamond")
        ArrayList<EmailMarketing> retorno = new ArrayList<EmailMarketing>();
        query = new StringBuilder();
        query.append("SELECT * FROM arquivo_mail2 order by email asc LIMIT "+(limI-1)*limF+", "+limF+"");
        try {
            conexao.openConnection();
            res = conexao.executeQuery(query);
            while (res.next()) {
                EmailMarketing emailMarketing = new EmailMarketing();
                emailMarketing.setId(res.getInt("id"));
                emailMarketing.setE_Mail(res.getString("email"));
                emailMarketing.setListaGeekle(res.getString("listageekle"));
                emailMarketing.setOrigemCentral(res.getString("origemcentral"));
                emailMarketing.setSituacao(res.getString("situacao"));
                emailMarketing.setDivisao(res.getString("divisoes"));
                emailMarketing.setObservacoes(res.getString("observacoes"));
                emailMarketing.setContatoWeb(res.getString("contatoweb"));
                emailMarketing.setF_j(res.getString("f/j"));
                emailMarketing.setNacInt(res.getString("nac/int"));
                emailMarketing.setProvidencia(res.getString("providencia"));
                emailMarketing.setAtividade(res.getString("atividade"));
                emailMarketing.setCidade(res.getString("cidade"));
                emailMarketing.setUF(res.getString("uf"));
                emailMarketing.setBairro(res.getString("bairro"));
                emailMarketing.setRegiao(res.getString("regiao"));
                retorno.add(emailMarketing);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        conexao.closeConnection();
        return retorno;
    }
    public ArrayList<EmailMarketing> listarTodosEmailDESC(int limI) {
        //qtde de registro a retornar
        int limF = 400;
        @SuppressWarnings("Convert2Diamond")
        ArrayList<EmailMarketing> retorno = new ArrayList<EmailMarketing>();
        query = new StringBuilder();
        query.append("SELECT * FROM arquivo_mail2 order by email desc LIMIT "+(limI-1)*limF+", "+limF+"");
        try {
            conexao.openConnection();
            res = conexao.executeQuery(query);
            while (res.next()) {
                EmailMarketing emailMarketing = new EmailMarketing();
                emailMarketing.setId(res.getInt("id"));
                emailMarketing.setE_Mail(res.getString("email"));
                emailMarketing.setListaGeekle(res.getString("listageekle"));
                emailMarketing.setOrigemCentral(res.getString("origemcentral"));
                emailMarketing.setSituacao(res.getString("situacao"));
                emailMarketing.setDivisao(res.getString("divisoes"));
                emailMarketing.setObservacoes(res.getString("observacoes"));
                emailMarketing.setContatoWeb(res.getString("contatoweb"));
                emailMarketing.setF_j(res.getString("f/j"));
                emailMarketing.setNacInt(res.getString("nac/int"));
                emailMarketing.setProvidencia(res.getString("providencia"));
                emailMarketing.setAtividade(res.getString("atividade"));
                emailMarketing.setCidade(res.getString("cidade"));
                emailMarketing.setUF(res.getString("uf"));
                emailMarketing.setBairro(res.getString("bairro"));
                emailMarketing.setRegiao(res.getString("regiao"));
                retorno.add(emailMarketing);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        conexao.closeConnection();
        return retorno;
    }
    public int VerificaExistencia(EmailMarketing emailMarketing) {
        int retorno = -1;
        for (int i = 0; i < this.lista.size(); i++) {
            if (emailMarketing.getE_Mail().trim().equals(this.lista.get(i).getE_Mail().trim())) {
                retorno = i;
                break;
            }
        }
        return retorno;
    }
    public static EmailMarketingModel obterInstancia() {
        if (InstanciaRep == null) {
            InstanciaRep = new EmailMarketingModel();
        }
        return InstanciaRep;
    }
    public void AtualizaEmailMarketing(EmailMarketing mail) {
        query.append("insert into cliente (id, email, listageekle, origemcentral, "
                + "situacao, divisoes, observacoes, contatoweb, f/j, nac/int, providencia, "
                + "atividade, cidade, uf, bairro, regiao)");
        try {
            query = new StringBuilder();
            query.append("UPDATE arquivo_mail2 SET email = '");
            query.append(mail.getE_Mail());
            query.append("',listageekle ='");
            query.append(mail.getListaGeekle());
            query.append("', origemcentral ='");
            query.append(mail.getOrigemCentral());
            query.append("', situacao ='");
            query.append(mail.getSituacao());
            query.append("', divisoes ='");
            query.append(mail.getDivisao());
            query.append("',observacoes ='");
            query.append(mail.getObservacoes());
            query.append("',contatoweb ='");
            query.append(mail.getContatoWeb());
            query.append("', f/j ='");
            query.append(mail.getF_j());
            query.append("', nac/int ='");
            query.append(mail.getNacInt());
            query.append("', providencia ='");
            query.append(mail.getProvidencia());
            query.append("', atividade ='");
            query.append(mail.getAtividade());
            query.append("', cidade ='");
            query.append(mail.getCidade());
            query.append("', uf ='");
            query.append(mail.getUF());
            query.append("', bairro ='");
            query.append(mail.getBairro());
            query.append("', regiao ='");
            query.append(mail.getRegiao());
            query.append("'");
            query.append(" WHERE id = '");
            query.append(mail.getId());
            query.append("'");
            conexao.openConnection();
            conexao.executeUpdate(query);
            JOptionPane.showMessageDialog(null,
                    "Alteracao realizada com sucesso!");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null,
                    "Erro a tentar Alterar o registro..." + erro);
        } finally {
            conexao.closeConnection();
        }
    }
}