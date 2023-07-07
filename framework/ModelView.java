/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traitment;

import java.util.HashMap;

/**
 *
 * @author ITU
 */
public class ModelView {
    String view;
    HashMap<String,Object> data;

    public ModelView(String view,HashMap<String,Object> data) {
        this.view = view;
        this.data = data;
    }

    public ModelView(){
        
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }
    
    public HashMap<String,Object> getData(){
        return this.data;
    }
    
    public void setData(HashMap<String,Object> data){
        this.data=data;
    }
    
    public void addItem(String cle,Object objet){
        //HashMap<String,Object> map=new HashMap<String,Object>();
        this.data.put(cle,objet);
        //this.setData(map);
    }
}
