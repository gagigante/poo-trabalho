/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifnetpoo.Interfaces;

import ifnetpoo.Classes.Usuario;

/**
 *
 * @author gabri
 */
public interface IMaterial {
    public String getTitulo();
    
    public String getCategoria();
    
    public Usuario getCriador();
    
    public String getOverviewMaterial();
}
