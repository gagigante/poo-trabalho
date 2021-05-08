package ifnetpoo.DAO;

import ifnetpoo.Interfaces.IMaterial;
import java.util.ArrayList;

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
        this.getMaterialPorIndex(index);
        
        this.materiais.remove(index);                
    }
}
