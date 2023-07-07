/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etu1903.frameworki;

import java.util.HashMap;

/**
 *
 * @author ITU
 */
public class Mapping {
    String Classname;
    String Method;

    public Mapping(String Classname, String Method) {
        this.Classname = Classname;
        this.Method = Method;
    }

    public String getClassname() {
        return Classname;
    }

    public void setClassname(String Classname) {
        this.Classname = Classname;
    }

    public String getMethod() {
        return Method;
    }

    public void setMethod(String Method) {
        this.Method = Method;
    }
}