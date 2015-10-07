/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duckhunt.Model;

import duckhunt.Boundary.InputContainer;
import duckhunt.Model.BaseLevelState;
import duckhunt.Model.Menu;
import duckhunt.Model.Level1;
import duckhunt.Model.Unit;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Ingemar
 */
public class LevelFactory {

    private static ArrayList<String> map;

    public static BaseLevelState nextLevel(BaseLevelState currentLevel) {
        ArrayList<String> map = getMap();
        
        if (currentLevel == null) {
            return firstLevel();
        }

        int index = map.indexOf(currentLevel.getClass().getName());

        if (index >= map.size() - 1) {
            return finished();
        } else {
            return getClassForName(map.get(index + 1));
        }
    }
    
    private static BaseLevelState firstLevel(){
        return getClassForName(getMap().get(0));
    }

    private static ArrayList<String> getMap() {
        if (map == null) {
//            Menu menu = new Menu();
//            Level1 level = new Level1();
            ArrayList<String> arr = new ArrayList<>();
            arr.add("duckhunt.Model.Menu");
            arr.add("duckhunt.Model.Level1");
//            arr.add(menu);
//            arr.add(level);
            map = arr;
        }

        return map;
    }
    
    private static BaseLevelState getClassForName(String name){
        BaseLevelState result = null;
        if (name != null) {
            try {
                Class className = Class.forName(name);
                result = (BaseLevelState) className.newInstance();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException exception) {
                exception.printStackTrace();
                result = null;
            }
        }
        return result;
    }

    private static BaseLevelState finished() {
        return firstLevel();
    }
}
