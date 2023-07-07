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
    int id;
    String nom;

    public Emp(){}
     
    public Emp(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    @Myannotation(value="empall")
    public ModelView getallemployer(){
        HashMap<String,Object> map=new HashMap<String,Object>();
        map.put("empall",34);
        ModelView view=new ModelView("AffichageEmp.jsp",map);
        return view;
    }
    
}
