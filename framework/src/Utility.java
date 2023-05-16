package etu1903.framework.servlet;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Vector;

import etu1903.annotation.Url;
import etu1903.framework.Mapping;

// import etu1903.framework.Mapping;
public class Utility {
    public Utility() {
    }

    public static void takeallfile(File file, HashMap<String, String> vecfile) {
        File[] files = file.listFiles();
        // System.out.println(files.length);
        try {
            if (files.length != 0) {
                for (File file2 : files) {
                    takeallfile(file2, vecfile);
                }
            }
        } catch (Exception e) {
            if (file.getName().endsWith(".class")) {
                // vecfile.add(file);
                vecfile.put(file.getName(), getpath("etu1903", file));
            }
        }
    }

    public static String getpath(String fileog, File file) {
        String p = file.getAbsolutePath();
        p = p.replace("\\", ".");
        String[] p1 = p.split("\\.");
        StringBuilder path = new StringBuilder();
        for (int i = 0; i < p1.length; i++) {
            if (p1[i].compareTo(fileog) == 0) {
                for (int j = i; j < p1.length - 1; j++) {
                    if (j + 1 != p1.length - 1) {
                        path.append(p1[j] + ".");
                    } else {
                        path.append(p1[j]);
                    }
                }
            }
        }
        // System.out.println(path.toString());
        return path.toString();

    }

    public static HashMap<String, Mapping> getnameclasse(HashMap<String, Mapping> MappingUrls, String path)
            throws ClassNotFoundException, IllegalAccessException,
            InvocationTargetException, NoSuchMethodException, SecurityException {
        File file = new File(path);
        File[] files = file.listFiles();

        // vecfile ['classname']=path
        HashMap<String, String> vecfile = new HashMap<String, String>();
        if (files.length == 0) {
            System.out.println("nothing in " + path);
            return MappingUrls;
        }

        // // recursive maka fichier rehetre anaty 'path'
        for (File f : files) {
            takeallfile(f, vecfile);
        }

        for (String i : vecfile.keySet()) {
            System.out.println("file : " + vecfile.get(i));
        }

        for (String i : vecfile.keySet()) {
            System.out.println("file : " + vecfile.get(i));
            Class<?> class1 = Class.forName(vecfile.get(i));
            Method[] methods = class1.getDeclaredMethods();
            for (Method method : methods) {
                // System.out.println(method.getName());
                if (method.isAnnotationPresent((Class<? extends Annotation>) Url.class)) {
                    Annotation annotation = method.getDeclaredAnnotation(Url.class);
                    String value = (String) annotation.getClass().getDeclaredMethod("meth").invoke(annotation);
                    System.out.println(value);
                    MappingUrls.put(value, new Mapping(i, method.getName()));
                }

            }
        }
        return MappingUrls;

    }

    // public static void getnameclasse2(String path)
    // throws ClassNotFoundException, IllegalAccessException,
    // InvocationTargetException, NoSuchMethodException, SecurityException {
    // File file = new File(path);
    // File[] files = file.listFiles();

    // // vecfile ['classname']=path
    // HashMap<String, String> vecfile = new HashMap<String, String>();
    // if (files.length == 0) {
    // System.out.println("nothing in " + path);
    // return;
    // }

    // // // recursive maka fichier rehetre anaty 'path'
    // for (File f : files) {
    // takeallfile(f, vecfile);
    // }

    // // for (String i : vecfile.keySet()) {
    // // System.out.println("file : " + vecfile.get(i));
    // // }

    // for (String i : vecfile.keySet()) {
    // System.out.println("file : " + vecfile.get(i));
    // Class<?> class1 = Class.forName(vecfile.get(i));
    // Method[] methods = class1.getDeclaredMethods();
    // for (Method method : methods) {
    // // System.out.println(method.getName());
    // if (method.isAnnotationPresent((Class<? extends Annotation>) Url.class)) {
    // Annotation annotation = method.getDeclaredAnnotation(Url.class);
    // String value = (String)
    // annotation.getClass().getDeclaredMethod("meth").invoke(annotation);
    // System.out.println(value);
    // // MappingUrls.put(value, new Mapping(i, value));
    // }

    // }
    // }

    // // for (String string : nameclasse) {
    // // Class<?> classe = Class.forName(string);
    // // Method[] methods = classe.getDeclaredMethods();
    // // for (Method method : methods) {
    // // if (method.isAnnotationPresent((Class<? extends Annotation>) Url.class)) {
    // // Annotation annotation = method.getDeclaredAnnotation(Url.class);
    // // String value = (String)
    // // annotation.getClass().getDeclaredMethod("value").invoke(annotation);

    // // }

    // // }
    // // System.out.println(string);
    // // }

    // return;

    // }

}
