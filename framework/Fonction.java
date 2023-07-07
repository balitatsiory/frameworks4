/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traitment;

import annotation.Myannotation;
import etu1903.frameworki.Mapping;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
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
   
   public Class getClass(String annotation,HashMap<String,Mapping> hashmap) throws ClassNotFoundException, Exception{
       Mapping mapping=getMapping(annotation,hashmap);
       Class classe=Class.forName(mapping.getClassname());
       return classe;
   }
   
   public Field[] listeAttribut(String annotation,HashMap<String,Mapping> hashmap) throws Exception{
       Class classe=getClass(annotation,hashmap);
       Field[] listeAttribut=classe.getDeclaredFields();
       return listeAttribut;
   }

   /*
    eviterna an ilay maka methode avec argument dia aleo tetezina tsirairay ilay methode rehetra ao
    anaty classe dia alaina iza mitovy anarana amn ilay methode tina tadiavina
   */ 
   public Method getMethode(String nomMethode,String nomClasse)throws Exception{
        Class classe=Class.forName(nomClasse);
        Method methode=null;
        for(Method m : classe.getMethods()){
            if(m.getName().equals(nomMethode)){
                methode=m;
            }
        }
        if(methode==null)           //si la methode n'existe pas 
            throw new Exception("la methode "+nomMethode+" n'existe pas");
        return methode;
   }  


    /*
        regle de l'appel de fonction:
            1-tsy maintsy mitovy ny filaharan ilay arguments sy ireo arguments apetraka eny amn lien
                ex : fonction= addEmp(int numero,String nom)
                     lien= /do/add_EmpAnnotation?numero=1&nom=thoy
            2-asina ? alohany itanisana argument eo amn lien
            3-ny separateur ny chaque argument dia &
    */
   
    public ModelView invocationMethode(String annotation,HashMap<String,Mapping> hashmap,Object instance,String[] listeArgument) throws Exception{
       Mapping mapping=getMapping(annotation,hashmap);
       Method methode=this.getMethode(mapping.getMethod(),mapping.getClassname());
       //obtenir les types des arguments du methode//
       ModelView resultat=null;
        Parameter[] parametre=methode.getParameters();
       if(listeArgument!=null && parametre.length!=0){         //si la fonction a de l'argument 
            Object[] argument=new Object[listeArgument.length];
            for(int i=0;i<listeArgument.length;i++){                    
                    Class paraType = parametre[i].getType();
                    String[] getValueArgument=listeArgument[i].split("\\=");
                    if(paraType.getSimpleName()=="int"){
                        argument[i]=Integer.parseInt(getValueArgument[1]);
                    }
                    else if(paraType.getSimpleName().equals("String")){
                        argument[i]=getValueArgument[1];
                    }
                    else if(paraType.getSimpleName().equals("double")){
                        argument[i]=Double.parseDouble(getValueArgument[1]);
                    }
            }
            resultat=(ModelView)methode.invoke(instance,argument);
       }else{
            resultat=(ModelView)methode.invoke(instance);       //si la fonction n'a pas d'argument
       }
       return resultat;
   }
}
