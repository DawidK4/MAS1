import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Drug implements Serializable {
    private static final List<Drug> extension = new ArrayList<>();
    private String name;
    private Manufacturer manufacturer;

    // Optional attribute
    private String expirationDate;

    public Drug(String name, Manufacturer manufacturer, String expirationDate) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.expirationDate = expirationDate;
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
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public static void loadExtensionFromFile(String filename){
        try (ObjectInputStream outputStream = new ObjectInputStream(new FileInputStream(filename))){
            List<Drug> loadedList = (List<Drug>) outputStream.readObject();
            extension.clear();
            extension.addAll(loadedList);
        } catch (IOException | ClassNotFoundException e){
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public String getName() {
        return name;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        if (expirationDate != null && !expirationDate.isEmpty()) {
            this.expirationDate = expirationDate;
        } else {
            this.expirationDate = null;
        }
    }

    public static void displayExtension(){
        for (Drug drug : extension){
            System.out.println(drug.getName());
        }
    }

    @Override
    public String toString() {
        return "Drug{name='" + name + "', manufacturer=" + manufacturer + ", expirationDate='" + expirationDate + "'}";
    }
}
