/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ModelItem;
import Model.ModelUsuario;
import guardiaodesenhas.AlertBox;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Schmidt
 */
public class ControllerItem {

    private ResultSet res;
    private final ConnectionDataBase conexao;
    private StringBuilder query;
    ModelUsuario user = new ModelUsuario();

    public ControllerItem() {
        conexao = new ConnectionDataBase();
    }
    Crypt crypt = new Crypt();

    public void inserirItens(ModelItem item) {
        try {

            query = new StringBuilder();
            query.append("insert into ITENS (categoria, item, senha, observacao, usuario)");
            query.append("values('");
            query.append(item.getCategoria());
            query.append("','");
            query.append(item.getItem());
            query.append("','");
            query.append(crypt.encrypt(user.getSenha(), item.getSenha()));
            query.append("','");
            query.append(item.getObservacao());
            query.append("','");
            query.append(item.getUsuario());
            query.append("')");
            conexao.openConnection();
            conexao.executeUpdate(query);
            AlertBox.display("Aviso", "\n\tItem " + item.getItem() + " inserido com sucesso!  ");
        } catch (Exception erro) {
            AlertBox.display("Erro", "\n\tErro ao tentar gravar o registro : " + erro);
        } finally {
            conexao.closeConnection();
        }
    }

    public void deletarItem(ModelItem mi, ModelUsuario mu) {
        try {
            String info = "Deseja Deletar '" + mi.getItem() + "' ?";
            int opcao_escolhida = JOptionPane.showConfirmDialog(null, info,
                    "Exclusao ", JOptionPane.YES_NO_OPTION);
            if (opcao_escolhida == JOptionPane.YES_OPTION) {
                query = new StringBuilder();
                query.append("delete from ITENS where id_item ='");
                query.append(mi.getId());
                query.append("' AND USUARIO = '");
                query.append(mu.getId());
                query.append("';");
                conexao.openConnection();
                conexao.executeUpdate(query);
                //if (conseguiu_excluir == 1) {
                //valoresDefault();
                AlertBox.display("Aviso", "\n\tExcluido com sucesso! ");
                //}
            }
        } catch (Exception erro) {
            AlertBox.display("Erro", "\n\tErro ao tentar excluir o registro : " + erro);
        } finally {
            conexao.closeConnection();
        }
    }

    public List<ModelItem> getItem(ModelUsuario mu, String par) {
        ArrayList retorno = new ArrayList<>();
        try {
            query = new StringBuilder();
            query.append("SELECT * FROM ITENS WHERE USUARIO='");
            query.append(mu.getId());
            query.append("' and ITEM ='").append(par).append("' limit 0,500;");
            conexao.openConnection();
            res = conexao.executeQuery(query);
            while (res.next()) {
                ModelItem item = new ModelItem();
                item.setItem(res.getString("item"));
                item.setId(res.getInt("id_item"));
                retorno.add(item);
            }
        } catch (SQLException exc) {
            AlertBox.display("AVISO", "ERRO : " + exc);
        } finally {
            conexao.closeConnection();
            for (int i = 0; i < retorno.size(); i++) {
                System.out.printf("Posição %d- %s \n", i, retorno.get(i));
            }
        }
        return retorno;
    }

    public List getItem(ModelUsuario mu) {
        ArrayList retorno = new ArrayList<>();
        try {
            query = new StringBuilder();
            query.append("SELECT * FROM ITENS WHERE USUARIO='");
            query.append(mu.getId());
            query.append("';");
            conexao.openConnection();
            res = conexao.executeQuery(query);
            while (res.next()) {
                ModelItem item = new ModelItem();
                item.setItem(res.getString("item"));
                item.setId(res.getInt("id_item"));
                retorno.add(item);
            }
        } catch (SQLException exc) {
            AlertBox.display("AVISO", "ERRO : " + exc);
        } finally {
            conexao.closeConnection();
            for (int i = 0; i < retorno.size(); i++) {
                System.out.printf("Posição %d- %s \n", i, retorno.get(i));
            }
        }
        return retorno;
    }

    public List<Integer> getId(ModelUsuario mu) {
        ArrayList<Integer> retorno = new ArrayList<>();
        try {
            query = new StringBuilder();
            query.append("SELECT ID_ITEM FROM ITENS WHERE USUARIO='");
            query.append(mu.getId());
            query.append("' limit 0,500;");
            conexao.openConnection();
            res = conexao.executeQuery(query);
            while (res.next()) {
                ModelItem item = new ModelItem();
                item.setId(res.getInt("id_item"));
                int id = item.getId();
                retorno.add(id);
                System.out.println(id);
            }
        } catch (SQLException exc) {
            AlertBox.display("AVISO", "ERRO : " + exc);
        } finally {
            conexao.closeConnection();
            for (int i = 0; i < retorno.size(); i++) {
                System.out.printf("Posição %d- %s \n", i, retorno.get(i));
            }
        }
        return retorno;
    }

    public List getAll(ModelUsuario mu) throws BadPaddingException, 
            NoSuchPaddingException, IllegalBlockSizeException, InvalidKeyException, 
            NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        ArrayList retorno = new ArrayList<>();
        try {
            query = new StringBuilder();
            query.append("SELECT * FROM ITENS WHERE USUARIO =' ");//170000
            query.append(mu.getId());
            query.append("';");
            conexao.openConnection();
            res = conexao.executeQuery(query);
            while (res.next()) {
                ModelItem mi = new ModelItem();
                mi.setId(res.getInt("id_item"));
                mi.setCategoria(res.getString("categoria"));
                mi.setItem(res.getString("item"));
                mi.setSenha(crypt.decrypt(user.getSenha(), res.getString("senha")));
                mi.setObservacao(res.getString("observacao"));
                retorno.add(mi);
            }
        } catch (SQLException e) {
            AlertBox.display("AVISO", "ERRO : " + e);
        } finally {
            conexao.closeConnection();
            for (int i = 0; i < retorno.size(); i++) {
                System.out.printf("Posição %d- %s \n", i, retorno.get(i));
            }
        }
        return retorno;
    }

    public List<ModelItem> listarItemPorLinha(ModelUsuario mu, String par) 
            throws BadPaddingException, NoSuchPaddingException, 
            IllegalBlockSizeException, InvalidKeyException, 
            NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        ArrayList retorno = new ArrayList<>();
        try {
            ModelItem mi = new ModelItem();
            query = new StringBuilder();
            query.append("SELECT categoria,item,senha,observacao FROM ITENS WHERE USUARIO =' ");//170000
            query.append(mu.getId());
            query.append("' and ID_ITEM ='").append(par).append("' limit 0,500;");
            conexao.openConnection();
            res = conexao.executeQuery(query);
            while (res.next()) {
                // mi.setId(res.getInt("id_item"));
                mi.setCategoria(res.getString("categoria"));
                mi.setItem(res.getString("item"));
                mi.setSenha(crypt.decrypt(user.getSenha(), res.getString("senha")));
                mi.setObservacao(res.getString("observacao"));
                retorno.add(mi);
                System.out.println(mi);
            }
        } catch (SQLException e) {
            AlertBox.display("AVISO", "ERRO : " + e);
        } finally {
            conexao.closeConnection();
            for (int i = 0; i < retorno.size(); i++) {
                System.out.printf("Posição %d- %s \n", i, retorno.get(i));
            }
        }

        return retorno;
    }

    public void AtualizaItens(ModelItem mi, ModelUsuario mu) {
        try {
            query = new StringBuilder();
            query.append("UPDATE ITENS SET categoria = '");
            query.append(mi.getCategoria());
            query.append("',item ='");
            query.append(mi.getItem());
            query.append("',senha ='");
            query.append(crypt.encrypt(user.getSenha(),mi.getSenha()));
            query.append("',observacao ='");
            query.append(mi.getObservacao());
            query.append("' WHERE id_item = '");
            query.append(mi.getId());
            query.append("' AND USUARIO ='");
            query.append(mu.getId());
            query.append("'");
            conexao.openConnection();
            conexao.executeUpdate(query);
            AlertBox.display("Aviso", "\n\tAlteração realizada com sucesso!");
        } catch (Exception erro) {
            AlertBox.display("Erro", "ERRO ao tentar concluir a alteração : " + erro);
        } finally {
            conexao.closeConnection();
        }
    }
}
