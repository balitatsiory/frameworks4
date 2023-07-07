/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personne;

import annotation.Myannotation;
import java.util.HashMap;
import traitment.ModelView;

/**
 *
 * @author ITU
 */
public class Emp {
    int Id;
    String Nom;

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
    
    public void setNom(String nom) {
        this.Nom = nom;
    }
    
    @Myannotation(value="empall")
    public ModelView getallemployer(){
        HashMap<String,Object> 
        map=new HashMap<String,Object>();
        ModelView view=new ModelView("AffichageEmp.jsp",map);
        view.addItem("attribut",34);
        return view;
    }
    
    @Myannotation(value="add_Emp")
    public ModelView save(){
        HashMap<String,Object> map=new HashMap<String,Object>();
        ModelView view=new ModelView("AffichageEmp.jsp",map);
        view.addItem("attribut",this.getNom());
        return view;
    }
}
