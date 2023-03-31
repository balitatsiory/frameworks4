package etu1903.main;

import etu1903.framework.Mapping;
import etu1903.framework.servlet.Utility;
import java.util.Enumeration;
import java.util.HashMap;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        System.out.println("Main.main()");
        try {
            HashMap<String, Mapping> hashMap = new HashMap<String, Mapping>();
            // String path = "C:/Program Files/Apache Software
            // Foundation/Tomcat8.5/webapps/frameworkS4/WEB-INF/classes/etu1903/models";
            String path = "C:/Program Files/Apache Software Foundation/Tomcat 8.5/webapps/frameworkS4/WEB-INF/classes/etu1903/models";

            Utility.getnameclasse(hashMap, path);
            // "C:/Program%20Files/Apache%20Software%20Foundation/Tomcat%208.5/webapps/frameworkS4/WEB-INF/classes/etu1903/models");

            // String packageName = "models";
            // ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            // String path2 = packageName.replace('.', '/');
            // Enumeration<URL> resources = classLoader.getResources(path2);
            // URL resource = resources.nextElement();
            // String path1 = resource.getFile();

            // System.out.println("*****" + path1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
