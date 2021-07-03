package ifnetpoo.DAO;

import ifnetpoo.Models.Aluno;
import ifnetpoo.Models.Apostila;
import ifnetpoo.Models.Professor;
import ifnetpoo.Models.Usuario;
import ifnetpoo.Models.Disciplina;
import ifnetpoo.Models.Livro;
import ifnetpoo.Models.PaginaWeb;

import ifnetpoo.Interfaces.IDatabaseConnection;
import ifnetpoo.Interfaces.IMaterial;

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
    public Apostila adicionarMaterial(String titulo, String categoria, Usuario criador, String area, Disciplina disciplina) {
        PreparedStatement stmt;
        
        try {
            stmt = this.conn.getConn().prepareStatement("insert into materiais (titulo, categoria, criador_id, area, disciplina_id, tipo)"
                 + " values(?, ?, ?, ?, ?, 'apostila')");
            stmt.setString(1, titulo);
            stmt.setString(2, categoria);
            stmt.setInt(3, criador.getId());
            stmt.setString(4, area);
            stmt.setInt(5, disciplina.getId());
            stmt.execute();
        } catch (SQLException e) {}
        
        return new Apostila(titulo, categoria, criador, area, disciplina);
    }
    
    // ADICIONA WEB
    public PaginaWeb adicionarMaterial(String titulo, String categoria, String url, Usuario criador, Disciplina disciplina) {
        PreparedStatement stmt;
        
        try {
            stmt = this.conn.getConn().prepareStatement("insert into materiais (titulo, categoria, url, criador_id, disciplina_id, tipo)"
                 + " values(?, ?, ?, ?, ?, 'web')");
            stmt.setString(1, titulo);
            stmt.setString(2, categoria);
            stmt.setString(3, url);
            stmt.setInt(4, criador.getId());
            stmt.setInt(5, disciplina.getId());
            stmt.execute();
        } catch (SQLException e) {}
        
        return new PaginaWeb(titulo, categoria, criador, url, disciplina);
    }
    
    // ADICIONA LIVRO
    public Livro adicionarMaterial(String titulo, String categoria, String autor, int edicao, int numeroDePaginas, Usuario criador, Disciplina disciplina) {
        PreparedStatement stmt;
        
        try {
            stmt = this.conn.getConn().prepareStatement("insert into materiais (titulo, categoria, autor, edicao, numero_de_paginas, criador_id, disciplina_id, tipo)"
                + " values(?, ?, ?, ?, ?, ?, ?, 'livro')");
            stmt.setString(1, titulo);
            stmt.setString(2, categoria);
            stmt.setString(3, autor);
            stmt.setInt(4, edicao);
            stmt.setInt(5, numeroDePaginas);
            stmt.setInt(6, criador.getId());
            stmt.setInt(7, disciplina.getId());
            stmt.execute();
        } catch (SQLException e) {}
        
        return new Livro(titulo, categoria, criador, disciplina, autor, numeroDePaginas, edicao);
    }
    
    public void removerMaterial(int id) {
        PreparedStatement stmt;
        
        try {
            stmt = this.conn.getConn().prepareStatement("delete from materiais where id = ?");
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {}
    }
}
