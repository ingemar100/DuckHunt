package duckhunt;

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
        idMap.put("Bird", "duckhunt.Duck");
        idMap.put("Chase", "duckhunt.Dog");
        idMap.put("Chaser", "duckhunt.Goku");
    }
}
