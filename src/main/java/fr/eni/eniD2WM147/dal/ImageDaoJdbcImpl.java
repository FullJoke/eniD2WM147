package fr.eni.eniD2WM147.dal;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.Part;

public class ImageDaoJdbcImpl {
	public static final String SAVE_DIRECTORY = "uploads";
	
	
	
	
	private String saveFile(String appPath, Part part) throws IOException  {
	    appPath = appPath.replace('\\', '/');
	    
        // The directory to save uploaded file
        String fullSavePath = null;
        if (appPath.endsWith("/")) {
            fullSavePath = appPath + SAVE_DIRECTORY;
        } else {
            fullSavePath = appPath + "/" + SAVE_DIRECTORY;
        }
      
        
        // Creates the save directory if it does not exists
        File fileSaveDir = new File(fullSavePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        String filePath=null;

        String fileName = extractFileName(part);
        System.out.println(fileName);
        String[] fn = fileName.split("(\\.)");
        fileName = fn[0];
        String ext = fn[(fn.length-1)];
        if(!ext.isEmpty()) {
        	//generate a unique file name
        	UUID uuid = UUID.randomUUID();
        	fileName = fileName + "_" + uuid.toString() + "." + ext ;
        	if (fileName != null && fileName.length() > 0) {
        		filePath = fullSavePath + File.separator + fileName;
        		System.out.println("Write attachment to file: " + filePath);
        		// Write to file
        		part.write(filePath);
        	}
        }
        return fileName;
       
	}
	
	  private String extractFileName(Part part) {
	        String contentDisp = part.getHeader("content-disposition");
	        String[] items = contentDisp.split(";");
	        for (String s : items) {
	            if (s.trim().startsWith("fileName")) {
	                String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
	                clientFileName = clientFileName.replace("\\", "/");
	                int i = clientFileName.lastIndexOf('/');
	                return clientFileName.substring(i + 1);
	            }
	        }
	        return null;
}

}
