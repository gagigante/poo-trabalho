package ifnetpoo.DAO;

import ifnetpoo.CustomExceptions.ExcessaoItemNaoEncontrado;
import ifnetpoo.Interfaces.IDatabaseConnection;
import ifnetpoo.Models.Pesquisa;
import ifnetpoo.Models.Professor;
import ifnetpoo.Models.Usuario;
import ifnetpoo.Models.Aluno;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PesquisaDAO {
    private IDatabaseConnection conn;
    
    // PROVISORIO
    private final ArrayList<Pesquisa> gruposPesquisa = new ArrayList<>();
    
    public PesquisaDAO(IDatabaseConnection conn) {
        this.conn = conn;
    }
    
    public ArrayList<Pesquisa> getGrupos() {
        PreparedStatement stmt;
        ResultSet rs=null;
        var grupos = new ArrayList<Pesquisa>();
        
        try {
            stmt = this.conn.getConn().prepareStatement("select g.*, u.id as id_usuario, u.nome, u.prontuario, u.email, u.area"
                 + " from grupos as g"
                 + " inner join usuarios as u on g.criador_id = u.id"
                 + " where g.tipo = 'pesquisa';");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                var criador = new Professor(rs.getInt("id_usuario"), rs.getString("nome"), rs.getString("prontuario"), rs.getString("email"), rs.getString("area"));
            
                var grupo = new Pesquisa(rs.getInt("id"), criador, rs.getString("nome"));
                grupos.add(grupo);
            }
        } catch (SQLException e) {}
        
        return grupos;
    }
    
    public ArrayList<Pesquisa> getTresGruposMaisPopulares() {
        PreparedStatement stmt;
        ResultSet rs=null;
        var grupos = new ArrayList<Pesquisa>();
        
        try {
            stmt = this.conn.getConn().prepareStatement("select g.*, u.id as id_usuario, u.nome, u.prontuario, u.email, u.area,"
                 + " COUNT(ag.aluno_id) as qtd_alunos from grupos as g"
                 + " left join alunos_grupos as ag on g.id = ag.grupo_id"
                 + " inner join usuarios as u on g.criador_id = u.id"
                 + " where g.tipo = 'pesquisa'"
                 + " group by g.id limit 3;");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                var criador = new Professor(rs.getInt("id_usuario"), rs.getString("nome"), rs.getString("prontuario"), rs.getString("email"), rs.getString("area"));
            
                var grupo = new Pesquisa(rs.getInt("id"), criador, rs.getString("nome"));
                grupo.setQuantidadeAlunos(rs.getInt("qtd_alunos"));
                grupos.add(grupo);
            }
        } catch (SQLException e) {}
        
        return grupos;
    }
    
    public Pesquisa getGrupoPorIndex(int index) {
        var grupos = this.getGrupos();
        var size = grupos.size();
        
        if (index < 0 || index > size - 1) {
            throw new Error("Grupo não encontrado");
        }
        
        return grupos.get(index); 
    }
     
    public Pesquisa criarGrupo(Usuario orientador, String nome) {
        PreparedStatement stmt;
         
        try {
            stmt = this.conn.getConn().prepareStatement("insert into grupos (titulo, criador_id, tipo) values(?, ?, 'pesquisa')");
            stmt.setString(1, nome);
            stmt.setInt(2, orientador.getId());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        Pesquisa novoGrupo = new Pesquisa(orientador, nome);
        
        return novoGrupo;
    }
    
    public void removerGrupoPorIndex(int index) {
        var grupos = new ArrayList<Pesquisa>();
        PreparedStatement stmt;
        
        grupos = this.getGrupos();
        int size = grupos.size();
        
        if (index < 0 || index > size - 1) {
            throw new ExcessaoItemNaoEncontrado("Grupo não foi encontrado");
        }
        
        var grupoSelecionado = grupos.get(index);
        
        try {
            stmt = this.conn.getConn().prepareStatement("delete from grupos where id = ?");
            stmt.setInt(1, grupoSelecionado.getId());
            stmt.execute();
        } catch (SQLException e) {}    
    }
    
    public void cadastraAlunoEmGrupo(Aluno aluno, Pesquisa grupo) {
        PreparedStatement stmt;
         
        try {
            stmt = this.conn.getConn().prepareStatement("insert into alunos_grupos (aluno_id, grupo_id) values(?, ?)");
            stmt.setInt(1, aluno.getId());
            stmt.setInt(2, grupo.getId());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
