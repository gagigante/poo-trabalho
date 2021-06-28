package ifnetpoo.DAO;

import ifnetpoo.CustomExceptions.ExcessaoItemNaoEncontrado;
import ifnetpoo.Interfaces.IDatabaseConnection;
import ifnetpoo.Interfaces.IMaterial;
import ifnetpoo.Models.Aluno;
import ifnetpoo.Models.Apostila;
import ifnetpoo.Models.Professor;
import ifnetpoo.Models.Usuario;
import ifnetpoo.Models.Disciplina;
import ifnetpoo.Models.Livro;
import ifnetpoo.Models.PaginaWeb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialDAO {
    private final IDatabaseConnection conn;

    public MaterialDAO(IDatabaseConnection conn) {
        this.conn = conn;
    }

    public ArrayList<IMaterial> getMateriais() {
        PreparedStatement stmt;
        ResultSet rs=null;
        var materiais = new ArrayList<IMaterial>();
        
        try {
            stmt = this.conn.getConn().prepareStatement("select m.*, d.*, u.nome as nome_criador, u.prontuario, u.email, u.area as area_professor,"
                 + " u.prontuario, u.tipo as tipo_criador from materiais as m inner join usuarios as u on"
                 + " m.criador_id = u.id inner join disciplinas d on m.disciplina_id  = d.id;");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String categoria = rs.getString("categoria");
                Usuario criador = null;
                var disciplina = new Disciplina(rs.getString("nome"), rs.getString("sigla"));
                
                if (rs.getString("tipo_criador").equals("aluno")) {
                    criador = new Aluno(rs.getString("nome_criador"), rs.getString("prontuario"), rs.getString("email"));
                } else {
                    criador = new Professor(rs.getString("nome_criador"), rs.getString("prontuario"), rs.getString("email"), rs.getString("area_professor"));  
                }

                if (rs.getString("tipo").equals("apostila")) {
                    materiais.add(new Apostila(id, titulo, categoria, criador, rs.getString("area"), disciplina));
                }
                
                if (rs.getString("tipo").equals("web")) {
                    materiais.add(new PaginaWeb(id, titulo, categoria, criador, rs.getString("url"), disciplina));
                }
                
                if (rs.getString("tipo").equals("livro")) {
                    materiais.add(new Livro(id, titulo, categoria, criador, disciplina, rs.getString("autor"), rs.getInt("numero_de_paginas"), rs.getInt("edicao")));
                }
            }
        } catch (SQLException e) {}
           
        return materiais;
    }
    
    // ADICIONA APOSTILA
    public void adicionarMaterial(String titulo, String categoria, int idCriador, String area, int idDisciplina) {
        PreparedStatement stmt;
        
        try {
            stmt = this.conn.getConn().prepareStatement("insert into materiais (titulo, categoria, criador_id, area, disciplina_id, tipo)"
                 + " values(?, ?, ?, ?, ?, 'apostila')");
            stmt.setString(1, titulo);
            stmt.setString(2, categoria);
            stmt.setInt(3, idCriador);
            stmt.setString(4, area);
            stmt.setInt(5, idDisciplina);
            stmt.execute();
        } catch (SQLException e) {}
    }
    
    // ADICIONA WEB
    public void adicionarMaterial(String titulo, String categoria, String url, int idCriador, int idDisciplina) {
        PreparedStatement stmt;
        
        try {
            stmt = this.conn.getConn().prepareStatement("insert into materiais (titulo, categoria, url, criador_id, disciplina_id, tipo)"
                 + " values(?, ?, ?, ?, ?, 'web')");
            stmt.setString(1, titulo);
            stmt.setString(2, categoria);
            stmt.setString(3, url);
            stmt.setInt(4, idCriador);
            stmt.setInt(5, idDisciplina);
            stmt.execute();
        } catch (SQLException e) {}
    }
    
    // ADICIONA LIVRO
    public void adicionarMaterial(String titulo, String categoria, String autor, int edicao, int numeroDePaginas, int idCriador, int idDisciplina) {
        PreparedStatement stmt;
        
        try {
            stmt = this.conn.getConn().prepareStatement("insert into materiais (titulo, categoria, autor, edicao, numero_de_paginas, criador_id, disciplina_id, tipo)"
                + " values(?, ?, ?, ?, ?, ?, ?, 'livro')");
            stmt.setString(1, titulo);
            stmt.setString(2, categoria);
            stmt.setString(3, autor);
            stmt.setInt(4, edicao);
            stmt.setInt(5, numeroDePaginas);
            stmt.setInt(6, idCriador);
            stmt.setInt(7, idDisciplina);
            stmt.execute();
        } catch (SQLException e) {}
    }
    
    public void removerMaterial(int index) {
        var materiais = new ArrayList<IMaterial>();
        PreparedStatement stmt;
        
        materiais = this.getMateriais();
        int size = materiais.size();
        
        if (index < 0 || index > size - 1) {
            throw new ExcessaoItemNaoEncontrado("Material não foi encontrado");
        }
        
        var materialSelecionado = materiais.get(index);
        
        try {
            stmt = this.conn.getConn().prepareStatement("delete from materiais where id = ?");
            stmt.setInt(1, materialSelecionado.getId());
            stmt.execute();
        } catch (SQLException e) {}
    }
}
