/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifnetpoo.Classes;

/**
 *
 * @author gabriel
 */
public class Livro extends Material {
    private String autor;
    private String numeroDePaginas;
    private int edicao;

    public Livro(
        String titulo, 
        String categoria, 
        Usuario usuario, 
        String autor, 
        String numeroDePaginas, 
        int edicao
    ) {
        super(titulo, categoria, usuario);
        
        this.autor = autor;
        this.numeroDePaginas = numeroDePaginas;
        this.edicao = edicao;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getNumeroDePaginas() {
        return numeroDePaginas;
    }

    public void setNumeroDePaginas(String numeroDePaginas) {
        this.numeroDePaginas = numeroDePaginas;
    }

    public int getEdicao() {
        return edicao;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }
}
