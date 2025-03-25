import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Drug implements Serializable {
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

    public static void saveExtensionToFile(String filename){
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))){
            outputStream.writeObject(extension);
        } catch (IOException e){
            System.out.println("An error occured: " + e.getMessage());
        }
    }

    public static void loadExtensionFromFile(String filename){
        try (ObjectInputStream outputStream = new ObjectInputStream(new FileInputStream(filename))){
            List<Drug> loadedList = (List<Drug>) outputStream.readObject();
            extension.clear();
            extension.addAll(loadedList);
        } catch (IOException | ClassNotFoundException e){
            System.out.println("An error occured: " + e.getMessage());
        }
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