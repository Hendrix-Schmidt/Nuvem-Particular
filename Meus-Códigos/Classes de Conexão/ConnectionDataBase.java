package Controller;

import Model.ModelBanco;
import guardiaodesenhas.AlertBox;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDataBase {

    private final String urlODBC; // Recebe string de conexao com o banco
    private Connection con; // Recebe string que conecta com o banco
    private Statement stmt; // Recebe string que permitir� a execu��o SQL
    private ResultSet res; // Recebe um conjunto de resultado da tabela
    ModelBanco mb = new ModelBanco();
    public ConnectionDataBase() {
        urlODBC = "jdbc:mysql://localhost:3306/gdb?user=root&password=SUASENHA"; // Armazena a string de conexao
    }

    public void openConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");// Carrega Driver ConnectionDataBase   
            con = DriverManager.getConnection(urlODBC);// Estabelece ConnectionDataBase con = DriverManager.getConnection(urlODBC, "root", "senha");
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY); // Cria a conexao com o banco
        } catch (ClassNotFoundException ex) {
            // TODO: use um sistema de log apropriado.
            AlertBox.display("Erro", "\n\tDriver JDBC-ODBC nao encontrado : " + ex);
        } catch (SQLException ex) {
            // TODO: use um sistema de log apropriado.
            AlertBox.display("Erro", "\n\tProblemas na conexao com a fonte de dados : " + ex);
        }
    }

    public void closeConnection() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                // TODO: use um sistema de log apropriado.
                AlertBox.display("Erro", "\n\tErro na conexao com a fonte de dados (CON) : " + ex);
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                // TODO: use um sistema de log apropriado.
                AlertBox.display("Erro", "\n\tErro na conexao com a fonte de dados (STMT) : " + ex);
            }
        }

        if (res != null) {
            try {
                res.close();
            } catch (SQLException ex) {
                // TODO: use um sistema de log apropriado.
                AlertBox.display("Erro", "\n\tErro na conexao com a fonte de dados (RES) : " + ex);
            }
        }
    }

    public ResultSet executeQuery(StringBuilder query) {
        try {
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            res = stmt.executeQuery(query.toString());

        } catch (SQLException sqlex) {
            // TODO: use um sistema de log apropriado.
            AlertBox.display("Erro", "\n\tErro na conexao com a fonte de dados (EXQUERY) : " + sqlex);
        }

        return res;
    }

    public void executeUpdate(StringBuilder query) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query
                    .toString());
            preparedStatement.executeUpdate();

        } catch (SQLException sqlex) {
            // TODO: use um sistema de log apropriado.
            AlertBox.display("Erro", "\n\tErro na conexao com a fonte de dados (UPD) : " + sqlex);
        }
    }
}
