import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class lectura {
    public ArrayList<String[]> leer(){
        String filename = "Spanish.txt"; 
        ArrayList<String[]> Palabras = new ArrayList(); 

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) { 
                String[] parts = line.split(",");
                String key = parts[0].toLowerCase().trim(); 
                String value = parts[1].toLowerCase().trim();
                String[] productArray = {key, value}; 
                Palabras.add(productArray); 
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return Palabras;

    }    
    
}
