package ingredient;

import java.util.ArrayList;

public class Bag {
    private ArrayList<Ingredient> items = new ArrayList<>();
    private final int maxSlots = 12;

    public void add(Ingredient ingredient){
        if (items.size() < maxSlots){
            items.add(ingredient);
        }
    }
    public Ingredient get(int index){
        return items.get(index);
    }
    public void remove(int index){
        if (index >= 0 && index < items.size()) {
            items.remove(index);
        }
    }
    public int size(){
        return items.size();
    }

    public ArrayList<Ingredient> getItems() {
         return items;

    }
}
