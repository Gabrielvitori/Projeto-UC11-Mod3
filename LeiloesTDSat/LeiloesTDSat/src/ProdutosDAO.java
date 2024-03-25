
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto, Connection conexao) {
        
        conn = new conectaDAO().connectDB();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost/atividade1", "root", "Lolgabriel725@");
            System.out.println("SUCESSO DE CONEXÃO");
        } catch (ClassNotFoundException e) {
            System.out.println("Falha ao carregar a classe de conexão. Classe não encontrada");
        } catch (SQLException e) {
            System.out.println("Falha ao conectar com o banco. Erro de SQL");
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {

        return listagem;
    }

    void cadastrarProduto(ProdutosDTO produto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
