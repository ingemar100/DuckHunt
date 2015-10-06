package duckhunt.Control;

import duckhunt.Model.Unit;
import java.util.HashMap;

public class UnitFactory {

    HashMap<String, String> idMap = new HashMap();
    
    public UnitFactory(){
        vul();
    }

    public Unit create(String id) {
        String name = (String) idMap.get(id);
        Unit result = null;

        if (name != null) {
            try {
                Class className = Class.forName(name);
                result = (Unit) className.newInstance();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException exception) {
                exception.printStackTrace();
                result = null;
            }
        }
        return result;
    }

    private void vul() {
        idMap.put("Bird", "duckhunt.Model.Duck");
        idMap.put("Chase", "duckhunt.Model.Dog");
        idMap.put("Chaser", "duckhunt.Model.Goku");
        idMap.put("Special", "duckhunt.Model.Psyduck");
    }
}
