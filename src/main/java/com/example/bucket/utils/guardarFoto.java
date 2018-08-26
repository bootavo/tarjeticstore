package com.example.bucket.utils;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class guardarFoto {
	public static boolean guardar(String ruta, Part foto) {
		try {
			
			OutputStream out = new FileOutputStream(new File(ruta));
			InputStream filecontent = foto.getInputStream();
			System.out.println("util ruta:"+ruta);
			System.out.println("util out:"+out);
			System.out.println("util filecontent:"+filecontent);
			int read = 0;
			final byte[] bytes = new byte[1024];
			while ((read = filecontent.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			
			return true;
		} catch (Exception e) {
			System.out.print(e.getMessage());
			
			return false;
		}
	}
	
	public static String obtenerNombreFoto(Part part) {
	    final String partHeader = part.getHeader("content-disposition");

	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
}
