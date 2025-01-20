package clients.customer;

import java.util.HashMap;

public class NameToNumber extends HashMap<String, String> {

    // Changed from package-private to public
    public NameToNumber(){
        put("0001", "TV");
        put("0002", "Radio");
        put("0003", "Toaster");
        put("0004", "Watch");
        put("0005", "Camera");
        put("0006", "Music Player");
        put("0007", "USB Driver");
    }

    // Adjusted getNumberByName method to work within this instance
    public String getNumberByName(String value) throws NullPointerException {
        for (Entry<String, String> entry : this.entrySet()) {
            if (value.equalsIgnoreCase(entry.getValue())){ // Case-insensitive comparison
                return entry.getKey();
            }
        }
        throw new NullPointerException(value); // Value not found
    }
}