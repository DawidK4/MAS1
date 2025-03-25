import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Drug {
    private static final List<Drug> extension = new ArrayList<>();
    private String name;

    public Drug(String name) {
        this.name = name;
        extension.add(this);
    }

    public static List<Drug> getExtension(){
        return Collections.unmodifiableList(new ArrayList<>(extension));
    }

     public static void removeFromExtension(Drug drug) {
         extension.remove(drug);
    }

    public String getName() {
        return name;
    }

    public static void displayExtension(){
        for (Drug drug : extension){
            System.out.println(drug.getName());
        }
    }
}