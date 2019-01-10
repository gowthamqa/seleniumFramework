/**
 * 
 * This class is to run Failed test cases dynamically in order to use these suites for execution
 * 
 */

package com.bento.common.retryfailsuite;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.io.FileUtils;


public class RetryFailSuiteAll 
{
	private String sfile;
	private HashMap<Object, Object> resultMap=null;
	private File resultFile=null;
	CreateFailedSuites suites;
	public String filePath;
	public File fileDst;
	public static Set<String> setOfPackages=new HashSet<String>();
	
	RetryFailSuiteAll(String sfile) throws FileNotFoundException, IOException{
		this.sfile=sfile;
		suites=new CreateFailedSuites();
	}
	
	/*
	 * This will store all classes with their failed test cases
	 */
	private HashMap<Object, Object> getList() throws FileNotFoundException{
		resultMap=new HashMap<Object, Object>();
		resultFile=new File(sfile);
		Scanner sc=null;
		
		if(resultFile.exists()){
			sc=new Scanner(resultFile);
			
			while(sc.hasNextLine()){
				String line=sc.nextLine();
				String classname;
				String testcase;
				
				if((line.contains("<testcase") && !line.contains("/>")))
				{
					classname=line.split("<testcase classname=")[1].split("name=")[0].trim().replace("\"", "");
					testcase=(line.split("<testcase classname=")[1].split("name=")[1]).split("time=")[0].trim().replace("\"", "");
					
					if(resultMap.keySet().contains(classname)){
						testcase=testcase.trim()+","+resultMap.get(classname).toString().trim();
						resultMap.put(classname, testcase);
					}
					else{
						resultMap.put(classname, testcase);
					}
				}
				
			}
			sc.close();
		}	
		return resultMap;	
	}
	
	/*
	 * This will generate a new file
	 */
	static File createTmpFile(String filename, String fileContent, String workDir) {
		BufferedWriter out = null;
		File tempFile = null;
		try {
			tempFile = new File(workDir, filename);
			out = new BufferedWriter(new FileWriter(tempFile));
			out.write(fileContent);
		} catch (IOException e) {

		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (Exception e) {
				
			}
		}

		return tempFile;
	}
	
	/*
	 * This function will delete existing temp packages created for retry 
	 */
	
	public static void findFolderToDel(File [] filesList)
    {
        if(filesList!=null)
        for (File fil : filesList)
        {
        	System.out.println(fil.getName());
            if (fil.isDirectory() && fil.getName().equals("temp"))
            {
            	File [] tempFiles=fil.listFiles();
            	
            	for(File temp : tempFiles){
            		temp.deleteOnExit();
            	}
            	
            	fil.deleteOnExit();
            	//System.out.print(fil.getName());
            	
            	//System.out.println("---"+ fil.delete());
            	
            }
            else{
            	findFolderToDel(fil.listFiles());
            	
            }
        }
    }
	
	/*
	 * This function will delete existing temp folder packages 
	 */
	public static void delete(){
		
		String folderName= "./src/com/oracle/pgbu/temp";
		File[] filesList = new File(folderName).listFiles();
		findFolderToDel(filesList);
	   
	}
	/*
	 * find exact path of the .java file 
	 * 
	 */
	public boolean findFileName(String files, String testCaseFile) {
		
		File[] filesList = new File(files).listFiles();
	    findFile(filesList, testCaseFile);
	    
	    // if file not found there the method resides return false else return true
	    if(fileDst==null){
	    	return false;
	    }
	    else{
	    	return true;
	    }
	}
	
	
	public void findFile(File [] filesList, String testCaseFile)
    {
        if(filesList!=null)
        for (File fil : filesList)
        {
            if (fil.isDirectory())
            {
                findFile(fil.listFiles(),testCaseFile);
            }
            else if (testCaseFile.equalsIgnoreCase(fil.getName()))
            {
            	filePath=fil.getAbsolutePath();
            	fileDst=fil.getParentFile();
            	
            }
        }
    }
	

	public void addToSetOfPackage(String pack) throws IOException
	{
		
		pack=pack.replace("\\", "&");
		
		if(pack.contains("&temp&")){
			String[] parts = pack.split("&com");
			String part2 = parts[1]; 
		    pack="import "+"&com"+part2+";";
		    pack=pack.replace("&", ".");
		    setOfPackages.add(pack);
		}
	    
	}
	
    
    
	
	/*
	 * Create new files for failed test cases w.r.t classes
	 */
	void createRerunFailedTestCasesFiles(String files) throws IOException
	{
		HashMap<Object, Object> map=this.getList();
		
		Iterator<Object> iterator = map.keySet().iterator();

		while (iterator.hasNext())
		{
		      String key = (String) iterator.next();
		      String keys[]=key.toString().split("\\.");
		      String testCaseFile=keys[keys.length-1]+".java";
		      
		      boolean isFileFound=findFileName(files, testCaseFile);
		      
		      if(isFileFound){
			      // add to set of packages
			      addToSetOfPackage(fileDst+"\\temp"+"\\"+keys[keys.length-1]);
			    		  
			      File dtFile=new File(fileDst+"\\temp"+"\\"+keys[keys.length-1]+".java");
			      FileUtils.copyFile(new File(filePath), dtFile);
			      String[] tests ;
			      if(map.get(key).toString().contains(","))
			    	  tests=map.get(key).toString().split(",");
			      else{
			    	  tests=new String[1];
			    	  tests[0]=map.get(key).toString();
			      }
			      Scanner scan=new Scanner(dtFile);
			      StringBuilder sb=new StringBuilder();
			      int jCount=0;
			      while(scan.hasNextLine())
			      {
			    	  String line=scan.nextLine();
			    	  if(line.contains("package ") && jCount<1){
			    		  line = line.substring(0, line.length()-1).trim()+".temp;";
			    		  jCount++;
			    	  }
			    		  
			    	  if(line.contains("@Test"))
			    		 sb.append(line.replace("@Test", "").trim()+"\n");
			    	  else
			    		 sb.append(line+"\n");
			    	    
			      }
			     
			      scan.close();
			      
			      String file=dtFile.getName();
			      
			      RetryFailSuiteAll.createTmpFile(testCaseFile, sb.toString(), fileDst+"\\temp");
			      
			      sb=null;
			     
			      StringBuilder sbCollect=new StringBuilder();
			    
			      for(int i=0;i<tests.length;i++)
			      {
			    	  int iCnt=0;
			    	  boolean flag=false;
			    	  if(i==0)
			    	  {
			    		  scan=new Scanner(dtFile);
			    		  String line=null;
			    		  while(scan.hasNextLine())
			    		  {
			    			   line=scan.nextLine();
			    			   
			    			  if(line.contains(tests[i]) && !tests[i].contains("["))
			    			  {
			    				  if(!flag){
			    				  sbCollect.append("@Test\n");
			    				  flag=true;
			    				  }
			    			  }
			    		  
			    			  sbCollect.append(line+"\n");
			    		  	}
			    		  scan.close();
			    	  }
			    	  else
			    	  {
			    		  if(!tests[i].contains("[")){
			    			  int offset=sbCollect.indexOf("public void "+tests[i]);
			    			  try{
			    				  sbCollect.insert(offset, "@Test\n");
			    			  }catch(Exception ex){}
			    		  }
			    	  }
			    	  
			      }
			   
			      
			      file=dtFile.getName();
			      
			      if(dtFile.exists())
			      if(sbCollect.toString().length()>0)
			    	  RetryFailSuiteAll.createTmpFile(testCaseFile, sbCollect.toString(), fileDst+"\\temp");
			      else
			      	{
			    	  scan=new Scanner(dtFile);
			    	  int iCnt=0;
			    	  while(scan.hasNextLine())
			    	  {
			    		  String line=scan.nextLine();
			    		  if(iCnt==0){
			    			  iCnt++;
			    		  }else
			    			  sbCollect.append(line+"\n");
			    	  }
			    	  RetryFailSuiteAll.createTmpFile(testCaseFile, sbCollect.toString(), fileDst+"\\temp");
			      	}
			      }
		}
		suites.createSuite(CreateFailedSuites.gridProject, "AllTestsFailedSuite", "All");
		suites.createJenkinsSuite("JenkinsBatchFailedProgress");
	}
	
	
	public static void main(String args[]) throws IOException
	{
		
		//String resultFile="D:\\Unifier\\RATS\\RATS3-IE1.xml";
		String resultFile="D:\\retrigger\\retrigger.txt";
		//String resultFile=ApplicationProperties.getInstance().getV16v1ProjectPath()+"../../reports/TEST-com.oracle.pgbu.unifier.testsuites.JenkinsBatchProgess.xml";
		
		File resultFiles=new File(resultFile);
		String file =resultFiles.getAbsolutePath();
		String files= "./src/com/oracle/pgbu/temp";
		
		//delete();
		RetryFailSuiteAll d=new RetryFailSuiteAll(file);
		d.createRerunFailedTestCasesFiles(files);
		System.out.println("comp");
	}

	
}
