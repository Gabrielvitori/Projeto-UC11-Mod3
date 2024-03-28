
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;


public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {
        
    try{
         conn = new conectaDAO().connectDB();
         
         String sql = "INSERT INTO tabela_produtos (id, nome, valor, status) VALUES (?, ?, ?, ?)";
         PreparedStatement pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, produto.getId()); 
         pstmt.setString(2, produto.getNome());
         pstmt.setDouble(3, produto.getValor()); 
         pstmt.setString(4, produto.getStatus()); 
         
         pstmt.executeUpdate();
         pstmt.close();
         conn.close();
            
         System.out.println("Produto cadastrado com sucesso.");
         
    }catch (SQLException e) {
        
         System.out.println("Erro ao cadastrar produto: " + e.getMessage());
         
        } 
    
    } 

    public ArrayList<ProdutosDTO> listarProdutos() {

        return listagem;
    }

    


}