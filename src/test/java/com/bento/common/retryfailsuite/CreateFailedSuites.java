/**
 * 
 * This class is to create Suite Files dynamically in order to use these suites for execution
 * 
 * It creates the above suite files dynamically for the project specified in 'helper.properties' file
 * It is executed from build.xml of the project and will fetch the Test class files from respective project folder (like 'system-tests') and its sub-directories
 * 
 */
package com.bento.common.retryfailsuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Properties;


public class CreateFailedSuites {
	
	public String testClasses[] = new String[2];
	public String fileNames ="";
	public String importStatements ="";
	public static String HELPER_FILE = "helper.properties";
	public static Properties m_helper_properties = new Properties();
	public static String gridProject = "";
	static Properties prop = new Properties();
	
	public String absolutePath="";
	
	public CreateFailedSuites() throws FileNotFoundException, IOException {
		m_helper_properties.load(new FileInputStream(new File("../common/src/"+HELPER_FILE)));
		gridProject = m_helper_properties.getProperty("gridProject");
	}
	
	/**
	 * 
	 * Creates the dynamic suite for tests execution on Selenium
	 * @param suiteName: Name of the Dynamic Suite to be created
	 * @throws IOException
	 * 
	 */
	public void createSuite(String gridProject, String suiteName, String includeCategory) throws IOException
	{
		fileNames ="";
		importStatements ="";
				
			FileOutputStream fout;   
  		    PrintStream prints;   
  		  
  		    String fileName= "./src/com/oracle/pgbu/unifier/tests/users/" +suiteName+ ".java";
  		    
  		    File file=new File(fileName);
  		    fout = new FileOutputStream(file);   
	        prints = new PrintStream(fout);   	              
	        prints.println("package com.oracle.pgbu.unifier.testsuites;");
	    	prints.println("import org.junit.runners.Suite;");
	    	prints.println("import org.junit.runners.Suite.SuiteClasses;");
	    	prints.println("import org.junit.runner.RunWith;\n");
	    	
	    	String className = "";
	    	Iterator<String> it=RetryFailSuiteAll.setOfPackages.iterator();
	    	while (it.hasNext()) {
				String string = (String) it.next();
				string= string.replace(".com.", "com.");
				if(string.contains(".temp.")){
					prints.println(string);
					String[] parts = string.split(".temp.");
					parts[1]=parts[1].substring(0, parts[1].length()-1);
					className =className+ parts[1] + ".class," ;
				}
			}
	    	
	    	if( !className.equals("")){
	    		className = className.substring(0, className.length()-1);
	    		System.out.println(className);
			}
	    	prints.println("\n");	    	
	    	prints.println("@RunWith(Suite.class)");
	    	prints.println("@Suite.SuiteClasses({ " +className+ "})");
	        prints.println("public class "+suiteName+" { \n }");   
	        fout.close();   
	}
	
	public void createJenkinsSuite(String suiteName) throws IOException{
		
		fileNames ="";
		importStatements ="";
				
		FileOutputStream fout;   
  		PrintStream prints;   

  		String fileName=  "./src/com/oracle/pgbu/unifier/tests/users/" +suiteName+ ".java";
  		File file=new File(fileName);
  		
  		
	    fout = new FileOutputStream(file);   
	    prints = new PrintStream(fout);   
	        
	    prints.println("package com.oracle.pgbu.unifier.testsuites;");
	    prints.println("import org.junit.runner.RunWith;");
	    prints.println("import org.jenkinsci.testinprogress.runner.ProgressSuite;");
	    prints.println("import org.junit.runner.RunWith;");
	    prints.println("import org.junit.runners.Suite.SuiteClasses;\n");
	    prints.println("import org.jenkinsci.testinprogress.runner.ProgressBatchSuite;");  
	    prints.println("import de.oschoen.junit.runner.BatchTestRunner;\n");  
	    prints.println("@RunWith(ProgressSuite.class)");
	    prints.println("@SuiteClasses({AllTestsFailedSuite.class})");
	    prints.println("public class "+suiteName+" { \n }");
	           
	    fout.close();   
	}
}