package etu1903.models.tes1;

import etu1903.annotation.Url;

/**
 *
 * @author Alain
 */
public class Emp {
    @Url(meth = "/Emp-findall")
    public void findall() {
        System.out.println("emp meth findall");
    }

    @Url(meth = "/Emp-select")
    public void finone() {
        System.out.println("emp meth findall");
    }

}
