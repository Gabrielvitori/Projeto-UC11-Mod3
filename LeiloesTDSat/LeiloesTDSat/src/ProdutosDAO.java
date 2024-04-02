
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

        
        
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        
         try {
            
            conn = new conectaDAO().connectDB();
            
            String sql = "SELECT id, nome, valor, status FROM tabela_produtos";
            PreparedStatement pstmt = conn.prepareStatement(sql);
                      
            ResultSet rs = pstmt.executeQuery();
               
            while (rs.next()) {
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            double valor = rs.getDouble("valor");
            String status = rs.getString("status");
                
            ProdutosDTO produto = new ProdutosDTO(id, nome, valor, status);
            listagem.add(produto);
            }
                    
            rs.close();
            pstmt.close();
            conn.close();
            
        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }
        
        return listagem; 
    

    }
    public void venderProduto(int idProduto) {
             
        try {
            conn = new conectaDAO().connectDB();
            
            String sql = "UPDATE tabela_produtos SET status = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "Vendido");
            pstmt.setInt(2, idProduto);
         
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            
            System.out.println("Produto vendido com sucesso.");
         
        } catch (SQLException e) {
            System.out.println("Erro ao vender produto: " + e.getMessage());
        } 
    }
    
    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
        ArrayList<ProdutosDTO> produtosVendidos = new ArrayList<>();
        
        try {
            conn = new conectaDAO().connectDB();
            
            String sql = "SELECT id, nome, valor, status FROM tabela_produtos WHERE status = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "Vendido");
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                double valor = rs.getDouble("valor");
                String status = rs.getString("status");
                
                ProdutosDTO produto = new ProdutosDTO(id, nome, valor, status);
                produtosVendidos.add(produto);
            }
            
            rs.close();
            pstmt.close();
            conn.close();
            
        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos vendidos: " + e.getMessage());
        }
        
        return produtosVendidos;
    }
      
}