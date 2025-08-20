
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import model.domain.Ator;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.PersistenceUtil;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LEDS
 */
public class TesteConexao {
    public static void main(String[] args) {
        // Ajuste os dados conforme sua config do docker
        String url = "jdbc:postgresql://localhost:5432/locadoraDB";
        String user = "postgres";
        String password = "123456";

        try {
            // Tenta carregar o driver
            Class.forName("org.postgresql.Driver");
            
            // Tenta abrir conexão
            Connection conn = DriverManager.getConnection(url, user, password);

            if (conn != null) {
                System.out.println("Conexão estabelecida com sucesso!");
                conn.close();
            } else {
                System.out.println("Falha na conexão.");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC não encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro SQL ao conectar: " + e.getMessage());
        }
    }
}
