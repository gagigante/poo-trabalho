/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifnet.Classes;

/**
 *
 * @author gabriel
 */
public class PaginaWeb extends Material {
    private String url;

    public PaginaWeb(String url, String titulo, String categoria, Usuario usuario) {
        super(titulo, categoria, usuario);
        
        this.url = url;
    }
     
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
}
