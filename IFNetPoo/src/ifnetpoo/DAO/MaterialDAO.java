/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifnetpoo.DAO;

import ifnetpoo.Interfaces.IMaterial;
import java.util.ArrayList;

/**
 *
 * @author gabri
 */
public class MaterialDAO {
    private final ArrayList<IMaterial> materiais = new ArrayList<>();
    
    public ArrayList<IMaterial> getMateriais() {
        return this.materiais;
    }
    
    public IMaterial getMaterialPorIndex(int index) {
        int size = this.materiais.size();
        
        if (index < 0 || index > size - 1) {
            throw new Error("Material não encontrada");
        }
        
        return this.materiais.get(index);
    }
    
    public IMaterial adicionarMaterial(IMaterial material) {
        this.materiais.add(material);
        
        return material;
    }
    
    public void removerMaterial(int index) {
        int size = this.materiais.size();
        
        if (index < 0 || index > size - 1) {
            throw new Error("Material não encontrada");
        }
        
        this.materiais.remove(index);                
    }
}
