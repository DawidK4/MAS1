import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class ObjectPlus implements Serializable {
    private static Map<Class<? extends ObjectPlus>, List<ObjectPlus>> allExtents = new HashMap<>();

    public ObjectPlus() {
        List<ObjectPlus> extent = null;
        Class theClass = this.getClass();
        if(allExtents.containsKey(theClass)) {
            // An extent of this class already exist
            extent = allExtents.get(theClass);
        }
        else {
            // An extent does not exist - create a new one
            extent = new ArrayList();
            allExtents.put(theClass, extent);
        }
        extent.add(this);
    }

    public static void writeExtents(ObjectOutputStream stream) throws IOException {
        stream.writeObject(allExtents);
    }

    public static void readExtents(ObjectInputStream stream) throws IOException,
            ClassNotFoundException {
        allExtents = (HashMap) stream.readObject();
    }
}
