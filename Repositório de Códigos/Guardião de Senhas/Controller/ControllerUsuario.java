/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ModelItem;
import Model.ModelUsuario;
import View.AlertBox;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Schmidt
 */
public class ControllerUsuario extends ConnectionDataBaseH2 {

    private StringBuilder query;
    private ModelUsuario user;
    private ResultSet res = null;

    Crypt crypt = new Crypt();

    public void inserirModelUsuario(ModelUsuario mu) {
        try {
            query = new StringBuilder();
            query.append("insert into USUARIOS (nome, sobrenome,usuario,senha)");
            query.append("values('");
            query.append(mu.getNome());
            query.append("','");
            query.append(mu.getSobrenome());
            query.append("','");
            query.append(mu.getUsuario());
            query.append("','");
            query.append(mu.getSenha());
            query.append("')");
            executeUpdate(query);
            AlertBox.display("Aviso", "\n\tUsuario " + mu.getUsuario() + " inserido com sucesso");
        } catch (Exception erro) {
            AlertBox.display("Erro", "\n\tErro ao tentar gravar o registro: " + erro + "\n tente novamente.");
        }
    }
    
    public void deletarUsuario(ModelUsuario mu){
         query = new StringBuilder();
                query.append("delete from USUARIOS where ID_USUARIO ='");
                query.append(mu.getId());
                query.append("';");
                executeUpdate(query);
    }

    public boolean checkAuthenticationModel(ModelUsuario mu) {
        boolean check = Boolean.FALSE;
        try {
            query = new StringBuilder();
            query.append("SELECT * FROM USUARIOS");
            query.append(" WHERE USUARIO = '");
            query.append(mu.getUsuario()).append("'");
            query.append(" AND SENHA = '");
            query.append(mu.getSenha()).append("'");
            res = executeQuery(query);
            while (res.next()) {
                user = new ModelUsuario((int) res.getInt("id_usuario"),
                        res.getString("usuario"), res.getString("senha"));
            }
            if (user.getUsuario() == null || user.getUsuario().equals("")) {
            } else if (user.getUsuario().equals(mu.getUsuario())
                    && user.getSenha().equals(mu.getSenha())) {
                check = Boolean.TRUE;
                mu.setId(user.getId());
            }
        } catch (Exception ex) {
            AlertBox.display("AVISO", "Usuario ou senha incorreto");
        }
        return check;
    }

    public boolean getIdLoginByLogin(ModelUsuario mu) {
        boolean result = false;
        String check = "";
        try {
            query = new StringBuilder();
            query.append("SELECT USUARIO FROM USUARIOS WHERE USUARIO= '");
            query.append(mu.getUsuario());
            query.append("'");
            res = executeQuery(query);
            res.next();
            check = res.getString("usuario");
            if (mu.getUsuario().equals(check)) {
                result = true;
            }
        } catch (SQLException exc) {
            //AlertBox.display("Erro", "\tExeção : " + exc);
        }
        return result;
    }

    public String getNome(ModelUsuario mu) {
        String check = "";
        try {
            query = new StringBuilder();
            query.append("SELECT NOME FROM USUARIOS WHERE USUARIO= '");
            query.append(mu.getUsuario());
            query.append("'");
            res = executeQuery(query);
            res.next();
            check = res.getString("nome");
        } catch (SQLException exc) {
            AlertBox.display("Erro", "\tExeção : " + exc);
        }
        return check;
    }

    public void alterarSenha(ModelUsuario mu, String senhaAntiga, String senhaNova)
            throws BadPaddingException, NoSuchPaddingException,
            IllegalBlockSizeException, InvalidKeyException,
            NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        ArrayList retorno = new ArrayList<>();
        try {
            query = new StringBuilder();
            query.append("SELECT * FROM ITENS WHERE USUARIO =' ");//170000
            query.append(mu.getId());
            query.append("';");
            res = executeQuery(query);
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
            for (int i = 0; i < retorno.size(); i++) {
                System.out.printf("Posição %d- %s \n", i, retorno.get(i));
            }
        }
        
    }
}
