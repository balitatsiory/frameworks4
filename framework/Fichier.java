package traitment;
import java.io.File;
import java.util.Vector;

public class Fichier extends File{
    String name;

    public Fichier(String name){
        super("");
        this.name=name;
    }

    public Vector<String> listeclasse(){
        File file=new File(this.name);
        File[] liste = file.listFiles();
        Vector<String> listeclasse=new Vector<String>();
        for(File item : liste){
            if(item.isFile())
                { 
                    String[] sp=item.getName().split(".class");
                    listeclasse.add(sp[0]);
                } 
        }   
        return listeclasse;
    }

}