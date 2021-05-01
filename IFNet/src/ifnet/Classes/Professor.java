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
public class Professor extends Usuario {
    private String area;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
    
    public Professor(String nome, String prontuario, String email, String area) {
        super(nome, prontuario, email);
        
        this.setArea(area);
    }
}
