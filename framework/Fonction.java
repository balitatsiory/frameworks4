/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traitment;

import annotation.Myannotation;
import etu1903.frameworki.Mapping;
import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

/**
 *
 * @author ITU
 */
public class Fonction {
    
    
    public HashMap<String,Mapping> listemethode(String nomClasse) throws ClassNotFoundException{
        Class classe=Class.forName(nomClasse);
        Method[] listemethode=classe.getDeclaredMethods();
        String annotation="Myannotation";
        HashMap<String,Mapping> map=new HashMap<String,Mapping>();
        for(int i=0;i<listemethode.length;i++){
            if(listemethode[i].isAnnotationPresent(Myannotation.class)){
                Myannotation an=listemethode[i].getAnnotation(Myannotation.class);
                Mapping mapping=new Mapping(classe.getName(),listemethode[i].getName());
                map.put(an.value(),mapping);
            }
        }
        return map;
    }
    
    public void getsousdossier(String pathProjet,String pack,Vector<String> tableau){
        String[] noslash=pack.split("\\.");
        File folder=new File(pathProjet+"\\"+noslash[noslash.length-1]);
        File[] listedossier=folder.listFiles();
        String enrepack=pack;
        String newPath="";
        String fileName="";
        for(File file : listedossier){
            if(file.isDirectory()){
                enrepack=enrepack+"."+file.getName();
                newPath=pathProjet+"\\"+pack;
                getsousdossier(newPath,enrepack,tableau);
            }else{
                fileName=file.getName();
                enrepack=enrepack+"."+fileName.substring(0,fileName.lastIndexOf('.'));
                tableau.add(enrepack);
            }
            enrepack=pack;
        }
    }
        
    public Vector<String> listeClasse(String pathProjet){
        String path=pathProjet+"\\WEB-INF\\classes";
        File folder=new File(path);
        File[] listedossier=folder.listFiles();
        Vector<String> enregistrement=new Vector<String>();
        for(File file : listedossier){
            if(file.isDirectory()){   //si file est un dossier
                Vector<String> mini=new Vector<String>();
                getsousdossier(path,file.getName(),mini);
                enregistrement.addAll(mini);
            }
        }
        return enregistrement;
    }
    
    public HashMap<String,Mapping> listeHashMapAllClass(String pathProjet) throws ClassNotFoundException{
        Vector<String> allClasse=listeClasse(pathProjet);
        HashMap<String,Mapping> newmap=new HashMap<String,Mapping>(); 
        String key="";
        for(int i=0;i<allClasse.size();i++){
            HashMap<String,Mapping> map=listemethode(allClasse.get(i));
            Set<String> keys=map.keySet();
            for(String j : keys){
               newmap.put(j,map.get(j));
            }
        }
        return newmap;
    }
    
    
   public Mapping getMapping(String annotation,HashMap<String,Mapping> hashmap) throws Exception{
       Mapping mapping=hashmap.get(annotation);
       if(mapping==null)
           throw new Exception("annotation "+annotation+" non trouver");
       return mapping;
   }
   
    public ModelView invocationMethode(String annotation,HashMap<String,Mapping> hashmap) throws Exception{
       Mapping mapping=getMapping(annotation,hashmap);
       Class classe=Class.forName(mapping.getClassname());
       Object instance=classe.newInstance();
       Method methode=instance.getClass().getMethod(mapping.getMethod());
       ModelView resultat=(ModelView)methode.invoke(instance);
      return resultat;
   }
    
    public static void main(String[] args)throws Exception{
       Fonction fonction=new Fonction();
        
      
        HashMap<String,Mapping> map=fonction.listeHashMapAllClass("C:\\Users\\ITU\\Documents\\NetBeansProjects\\sprint5\\build\\web\\");
        ModelView val=fonction.invocationMethode("empall",map);

        System.out.println(val.getView());
    }
}
