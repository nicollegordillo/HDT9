import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws FileNotFoundException {
        lectura l=new lectura();
        CrearMapeo TreeType = new CrearMapeo();
        EstructuraArbol Arbol;
        int tipo =0;
        Scanner teclado = new Scanner(System.in);
        Boolean continuar=true;
        String traduccion="";
        ArrayList<String[]> Palabras = new ArrayList(); 
        System.out.println("Bienvenido al diccionario inglés-español");
        while(continuar){
            traduccion="";
            Palabras=l.leer();
            System.out.println("¿Qué Tree quiere usar?");
            System.out.println("1. SplayTree \n2. AVLTree \n3. Salir");
            tipo=teclado.nextInt();
            teclado.nextLine();
            if(tipo==1|| tipo==2){
                Arbol= TreeType.createMap(tipo);
                for (String[] palabra : Palabras) {
                    Arbol.add(palabra[0], palabra[1]);
                }
                Scanner sentence = new Scanner(new File("texto.txt"));
                while(sentence.hasNextLine()){
                    String sentence1 = sentence.nextLine();
                    if (sentence1.endsWith(".")) {
                        sentence1 = sentence1.substring(0, sentence1.length() - 1);
                    }
                    String[] words = sentence1.split(" ");
                    for(int i=0;i<words.length;i++){
                        if(Arbol.get(words[i].toLowerCase())==null){
                            traduccion+="*"+words[i]+"* ";
                        }
                        else{
                            traduccion+=Arbol.get(words[i])+" ";
                        }
                    }
                    System.out.println(traduccion);
                }
            }
            else if(tipo==3){
                continuar=false;
            }
            else{
                System.out.println("Ingrese algo válido");
            }
                
                
        }
    }
    
}
