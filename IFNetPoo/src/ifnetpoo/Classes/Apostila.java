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
public class Apostila implements IMaterial {    
    private final String titulo;
    private final String categoria;
    private final Usuario criador;
    private final String area;
//    private final Disciplina disciplina;
    
    public Apostila(String titulo, String categoria, Usuario criador, String area) {
        this.titulo = titulo;
        this.categoria = categoria;
        this.criador = criador; 
        this.area = area;
    }

    public String getArea() {
        return area;
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
        return "Título: " + this.getTitulo() + "; Categoria: " + this.getCategoria() + "; Área: " + this.getArea() + "; Criador: " + this.getCriador();
    }

    @Override
    public String getTipoMaterial() {
        return "apostila";
    }
}
