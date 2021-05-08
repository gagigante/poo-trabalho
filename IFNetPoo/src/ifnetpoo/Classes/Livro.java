/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifnetpoo.Classes;

import ifnetpoo.Interfaces.IMaterial;

/**
 *
 * @author gabriel
 */
public class Livro implements IMaterial {
    private final String titulo;
    private final String categoria;
    private final Usuario criador;
    private final String autor;
    private final int numeroDePaginas;
    private final int edicao;

    public Livro(
        String titulo, 
        String categoria, 
        Usuario criador, 
        String autor, 
        int numeroDePaginas,
        int edicao
    ) {
        
        this.titulo = titulo;
        this.categoria = categoria;
        this.criador = criador;
        this.autor = autor;
        this.numeroDePaginas = numeroDePaginas;
        this.edicao = edicao;
    }
    
    public String getAutor() {
        return this.autor;
    }
    
    public int getNumeroDePaginas() {
        return this.numeroDePaginas;
    }
    
    public int getEdicao() {
        return this.edicao;
    }

    @Override
    public String getTitulo() {
        return this.titulo;
    }

    @Override
    public String getCategoria() {
        return this.categoria;
    }

    @Override
    public Usuario getCriador() {
        return this.criador;
    }

    @Override
    public String getOverviewMaterial() {
        return "Título: " + this.getTitulo() + "; Categoria: " + this.getCategoria() + "; Autor: " + this.getAutor()+ "; Número de páginas: " 
            + this.getNumeroDePaginas() + "; Edição: " + this.getEdicao() + "; Criador: " + this.getCriador();
    }

    @Override
    public String getTipoMaterial() {
        return "livro";
    }
}
