import java.io.*;
import java.util.*;

public class Manufacturer implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final List<Manufacturer> extension = new ArrayList<>();
    private String name;
    private String address;
    private String contactNumber;
    private String website;

    public Manufacturer(String name, String address, String contactNumber, String website) {
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
        this.website = website;
        extension.add(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        if (website != null && !website.isEmpty()) {
            if (website.startsWith("http://") || website.startsWith("https://")) {
                this.website = website;
            } else {
                throw new IllegalArgumentException("Website must start with 'http://' or 'https://'");
            }
        } else {
            this.website = null;
        }
    }

    public static List<Manufacturer> getExtension() {
        return Collections.unmodifiableList(new ArrayList<>(extension));
    }

    public static void saveExtensionToFile(String filename) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(extension);
        } catch (IOException e) {
            System.out.println("An error occurred while saving manufacturers: " + e.getMessage());
        }
    }

    public static void loadExtensionFromFile(String filename) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            List<Manufacturer> loadedList = (List<Manufacturer>) inputStream.readObject();
            extension.clear();
            extension.addAll(loadedList);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An error occurred while loading manufacturers: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Manufacturer{name='" + name + "', address='" + address + "', contactNumber='" + contactNumber + "', website='" + website + "'}";
    }
}
