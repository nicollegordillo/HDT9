public class CrearMapeo extends MapeoFactory{

    @Override
    public EstructuraArbol createMap(int tipo) {
            EstructuraArbol Arbol;
            
            switch(tipo) {
            case 1:{
                Arbol = new SplayTree();
            }break;
            
            case 2:{
                Arbol = new AVLTree();
            }break;
            default:{
                Arbol = null;
            }break;
            }
            
            return Arbol;
            
        
    }
    
}
