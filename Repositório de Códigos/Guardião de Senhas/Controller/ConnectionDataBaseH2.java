package Controller;

import View.AlertBox;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDataBaseH2 {

    //private final String urlODBC; // Recebe string de conexao com o banco
    private static final String DB_URL = "jdbc:h2:~/GDB";
    private static final String DB_USERNAME = "IX@35432";
    private static final String DB_PASSWORD = "@@#$iuheH";
    private Connection con; // Recebe string que conecta com o banco
    private Statement stmt; // Recebe string que permitir� a execu��o SQL
    private ResultSet res = null; // Recebe um conjunto de resultado da tabela

    private final  String CREATE_TABLE_GDB = "CREATE TABLE USUARIOS( "
            + "	id_usuario integer auto_increment not null, "
            + "nome varchar(15) not null, "
            + "sobrenome varchar(50) not null, "
            + "usuario varchar(40) not null,"
            + " senha varchar(50) not null,"
            + "CONSTRAINT PK_USUARIOS PRIMARY KEY (id_usuario));"
            + "CREATE TABLE ITENS("
            + "id_item integer auto_increment not null,"
            + "categoria varchar(20) not null,"
            + "item varchar(50)not null,"
            + "senha varchar(50)not null,"
            + "observacao varchar(80)not null,"
            + "usuario integer not null,"
            + "CONSTRAINT PK_ITENS PRIMARY KEY(id_item),"
            + "CONSTRAINT FK_ITENS FOREIGN KEY (usuario) REFERENCES USUARIOS (id_usuario));"
            + "CREATE TABLE CATEGORIA("
            + "id_categoria integer auto_increment not null,"
            + "categoria varchar(20) not null,"
            + "CONSTRAINT PK_ITENS PRIMARY KEY(id_categoria);";
    public  ConnectionDataBaseH2() {
        openConnection();
    }
    public void openConnection() {
        try {
            Class.forName("org.h2.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            stmt = con.createStatement();
            System.out.printf("Conexão estabelecida...");
            stmt.execute(CREATE_TABLE_GDB);
        } catch (Exception ex) {
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
            stmt = con.createStatement();
            res = stmt.executeQuery(query.toString());
            System.out.printf("EXECULTEQUERY");
        } catch (SQLException sqlex) {
            // TODO: use um sistema de log apropriado.
            //AlertBox.display("Erro", "\n\tErro na conexao com a fonte de dados (EXQUERY) : " + sqlex +"\n");
        }

        return res;
    }
    public void executeUpdate(StringBuilder query) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query.toString());
            preparedStatement.execute();
        } catch (SQLException sqlex) {
            // TODO: use um sistema de log apropriado.
            AlertBox.display("Erro", "\n\tErro na conexao com a fonte de dados (UPD) : " + sqlex+"\n\n");
        }
    }

}