package com.ab.common;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ClassFinder {

    private static final char PKG_SEPARATOR = '.';

    private static final char DIR_SEPARATOR = '/';

    private static final String CLASS_FILE_SUFFIX = ".class";

    private static final String BAD_PACKAGE_ERROR = "Unable to get resources from path '%s'. Are you sure the package '%s' exists?";

    public static List<Class<?>> find(String scannedPackage) {
        String scannedPath = scannedPackage.replace(PKG_SEPARATOR, DIR_SEPARATOR);
        URL scannedUrl = Thread.currentThread().getContextClassLoader().getResource(scannedPath);
        if (scannedUrl == null) {
            throw new IllegalArgumentException(String.format(BAD_PACKAGE_ERROR, scannedPath, scannedPackage));
        }
        File scannedDir = new File(scannedUrl.getFile());
        
        if(scannedDir.getAbsolutePath().contains(".jar")) {
        	ZipInputStream zip;
        	List<String> classes = new ArrayList<>();
        	try {
        		String jarLocation = scannedDir.getAbsolutePath();
        		jarLocation = jarLocation.substring(jarLocation.indexOf("file:")+5, jarLocation.indexOf(".jar")+4);
        		zip = new ZipInputStream(new FileInputStream(jarLocation));
        		for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
        			if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
        				// This ZipEntry represents a class. Now, what class does it represent?
        				String className = entry.getName().replace('/', '.'); // including ".class"
        				className = className.substring(0, className.length() - ".class".length());
        				if(className.startsWith(scannedPackage)) {
        					classes.add(className);
        				}
        			}
        		}
        	} catch (Exception e) {
        		e.printStackTrace();
        	}
        	return convertAllIntoClass(classes);
        }
        
        List<Class<?>> classes = new ArrayList<Class<?>>();
        for (File file : scannedDir.listFiles()) {
            classes.addAll(find(file, scannedPackage));
        }
        return classes;
    }

    private static List<Class<?>> find(File file, String scannedPackage) {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        String resource = scannedPackage + PKG_SEPARATOR + file.getName();
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                classes.addAll(find(child, resource));
            }
        } else if (resource.endsWith(CLASS_FILE_SUFFIX)) {
            int endIndex = resource.length() - CLASS_FILE_SUFFIX.length();
            String className = resource.substring(0, endIndex);
            try {
                classes.add(Class.forName(className));
            } catch (ClassNotFoundException ignore) {
            }
        }
        return classes;
    }
    
    private static List<Class<?>> convertAllIntoClass(List<String> classes) {
        List<Class<?>> classList = new ArrayList<Class<?>>();
    	for(String cls : classes) {
            try {
            	classList.add(Class.forName(cls));
            } catch (ClassNotFoundException ignore) {}
    	}
    	return classList;
    }

}