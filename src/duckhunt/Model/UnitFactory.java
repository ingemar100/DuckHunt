package duckhunt.Model;

import java.util.HashMap;

public class UnitFactory {

    private static HashMap<String, String> idMap = null;

    public static Unit create(String id) {
        HashMap<String, String> map = getMap();
        
        String name = (String) map.get(id);
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
    
    private static HashMap<String, String> getMap(){
        if (idMap == null){
            idMap = new HashMap();
            vul();
        }
        return idMap;
    }

    private static void vul() {
        idMap.put("Bird", "duckhunt.Model.Duck");
        idMap.put("Chase", "duckhunt.Model.Dog");
        idMap.put("Chaser", "duckhunt.Model.Goku");
        idMap.put("Special", "duckhunt.Model.Psyduck");
    }
}
