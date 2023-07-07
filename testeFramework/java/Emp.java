/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personne;

import annotation.Myannotation;
import java.util.HashMap;
import traitment.ModelView;
import traitment.FileUpload;

/**
 *
 * @author ITU
 */


/*
    les annotations doivent tjrs se terminer par .do
 */
public class Emp {
    int Id;
    String Nom;
    FileUpload File;

    public Emp(){}
     
    public Emp(int id, String nom) {
        this.Id = id;
        this.Nom = nom;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getNom() {
        return Nom;
    }

    public FileUpload getFile(){
        return this.File;
    }
    
    public void setNom(String nom) {
        this.Nom = nom;
    }

    public void setFile(FileUpload file){
        this.File=file;
    }
    
    @Myannotation(value="empall.do")
    public ModelView getallemployer(){
        HashMap<String,Object> 
        map=new HashMap<String,Object>();
        ModelView view=new ModelView("AffichageEmp.jsp",map);
        view.addItem("attribut",34);
        return view;
    }

    //regle: la classe emp doit comporter Un attribut FileUpload
    @Myannotation(value="add_Emp.do")
    public ModelView save(){
        HashMap<String,Object> map=new HashMap<String,Object>();
        ModelView view=new ModelView("AffichageEmp.jsp",map);
        this.getFile().upload("C:/perso/film");
        view.addItem("attribut",this.getFile().getName());
        return view;
    }

    @Myannotation(value="ajoute_numero.do")
    public ModelView ajouteNumero(int numero,String nom){
        HashMap<String,Object> map=new HashMap<String,Object>();
        ModelView view=new ModelView("AffichageEmp.jsp",map);
        String soratra="";
        for(int i=0;i<numero;i++){
            soratra=soratra+nom;
        }
        view.addItem("attribut",soratra);
        return view;
    }
}
