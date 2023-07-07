package traitment;

import annotation.Myannotation;
import traitment.ModelView;
import java.util.HashMap;

import java.io.FileOutputStream;

public class FileUpload {
    String Name;
    byte[] Bits;

    public FileUpload(String name,byte[] bits){
        this.Name=name;
        this.Bits=bits;
    }

    public String getName(){
        return this.Name;
    }

    public byte[] getBits(){
        return this.Bits;
    }

    public void setNamePhoto(String name){
        this.Name=name;
    }

    public void setBits(byte[] bits){
        this.Bits=bits;
    }

    public void upload(String emplacement){
        try(FileOutputStream fileOutputStream=new FileOutputStream(emplacement+"/"+this.getName())){
            fileOutputStream.write(this.getBits());
            fileOutputStream.close();
        }catch(Exception ef){
            System.out.println(ef);
        }
        System.out.println("tafatafatafatatafatafataftataft");
    }

}