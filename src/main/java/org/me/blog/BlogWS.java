/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.blog;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author JadyMartins
 */
@WebService(serviceName = "BlogWS")
@Stateless()
public class BlogWS {
     static Set<String> list = new HashSet<>();

    /**
     * Web service operation
     */
    @WebMethod(operationName = "convert")
    public String convert(@WebParam(name = "parameter") String parameter) throws URISyntaxException {
         try {
             testScanner();
         } catch (IOException ex) {
             Logger.getLogger(BlogWS.class.getName()).log(Level.SEVERE, null, ex);
         }
        String comment = parameter;
         // String comment = "jady! hey whats up you and ?Hugh, are<Lisa> other 9Winna trying ~Dorace";
        String lowerComment = comment.toLowerCase();
        //"[$&+,:;=\\\\?@#|/'<>.^*()%!-]"
        //"\\s+|(?=\\W)"
        //"[, !<>~?.@]+"
        //"\\W+"
        //"\\P{L}+"
        //^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{0,}
        
        
        
        String[] splitArray = comment.split("\\P{L}+");
        System.out.println(Arrays.toString(splitArray));   
        
        for(int i = 0; i < splitArray.length; i++){
            Pattern re = Pattern.compile("\\b"+splitArray[i]+"\\b",Pattern.UNICODE_CASE);
            Matcher m = re.matcher(list.toString());
            if(m.find()){
                splitArray[i] = "***";
            }
            System.out.println(splitArray[i]);
        }
        String joinedString = String.join(" ", splitArray);
        System.out.println(joinedString);
        parameter = joinedString;
        return parameter;
    }
    
    
private static Set testScanner() throws IOException, URISyntaxException {
        //File path
    //   File f = new File("src/main/resources/files/names.txt");
     //   String absolutePath = f.getAbsolutePath();

        
            //  File f = new File("C:/Users/JadyMartins/Google Drive/CCT/Y3 2019_2020/S2/BigData/names.txt");
       
            URL resource = BlogWS.class.getResource("/names.txt");
         try {
             File file = Paths.get(resource.toURI()).toFile(); // return a file
         } catch (URISyntaxException ex) {
             Logger.getLogger(BlogWS.class.getName()).log(Level.SEVERE, null, ex);
         }
String f = Paths.get(resource.toURI()).toFile().getAbsolutePath(); 
        try {
            //Read the file using scanner
            FileInputStream inputStream = new FileInputStream(f);
            Scanner sc = new Scanner(inputStream, "UTF-8");

            //While the file still have a next name
            while (sc.hasNextLine()) {
                //Attach ame from file in lower case to String line
                String line = sc.nextLine();
                   //    String line = sc.nextLine().toLowerCase();
                
                //Add name to the list
                list.add(line);
            }
            

        } catch (Exception e) {

        }

        return list;

    }        
}


