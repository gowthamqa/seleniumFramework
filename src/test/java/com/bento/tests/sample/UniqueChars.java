package com.bento.tests.sample;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.poi.util.SystemOutLogger;

import com.bento.common.utils.AutomationUtils;

public class UniqueChars {

	public static void main(String[] args) {
		//duplicate();
		//secondLargetArray();
		//reverseString();
		//reverseStringWOspl();
		System.out.println(AutomationUtils.getTodaysDateWithFormat("MMddYYYY"));
		
		
	}
	
	public static void reverseStringWOspl() {
		String str = "Ab,c.de!$";
		String res = "";
		String resF = "";
		char[] resArr = new char[str.length()];
		System.out.println(str);
		char[] ch = str.toCharArray();
		int length = ch.length-1;
		String temp = "";
		while(length>=0) {
			char cha = str.charAt(length);
			if( (cha>=65 && cha<=90 || cha>=97 && cha<=122) ) {
				res = res+cha;
				resArr[length]='4';
				temp = cha+temp;
				length--;
			}else {
				int i = str.indexOf(cha,length);
				resArr[length]=cha;
				temp = cha+temp;
				System.out.println(i);
				length--;
			}
			
		}
		System.out.println(res);
		System.out.println(temp);
		for(char c:resArr) {

			if(c == 52) {
				for(int i=0;i<res.length();i++) {
					c = res.charAt(i);
				}
			}
			System.out.print(c);
			
		}
		System.out.println();
		System.out.println(resF);
		//resF = resF.replaceAll("\\s","3");
		int j = 0;
		for(int i=0;i<str.length();i++) {
			char cha = resF.charAt(i);
			
			if(cha == 52) {
				//resF.replace('\u0000', res.charAt(j));
				//resF.replace(resF.charAt(i), res.charAt(j));
				System.out.println(res.charAt(j));
				j++;				
			}
			else {
				System.out.println("no space");
			}
		}
		System.out.println(resF);
		/*for(int i=0;i<=length;i++) {
			if( (ch[length-i]>=65 && ch[length-i]<=90 || ch[length-i]>=97 && ch[length-i]<=122) && 			  				(ch[i]>=65)&&(ch[i]<=90 || ch[i]>=97 && ch[i]<122)) {
				temp = ch[length-i];
				ch[length-i] = ch[i];
				ch[i] = temp;
			}
			
		}*/
		/*for(char c:ch) {
			System.out.print(c);
		}*/
		
	}
	
	public static void reverseString() {
		String given = "Str!ng";
		//given = given.toUpperCase();
		String result = "";
		System.out.println(given);
		
		char temp;
		char[] arrgiven = given.toCharArray();
		char[] arrres = new char[arrgiven.length];
		for(int i = 0;i<arrgiven.length;i++) {
			for(int j=i+1;j<arrgiven.length;j++) {
				if(((arrgiven[i]>=65)&&(arrgiven[i]<=90))||((arrgiven[i]>=97)&&(arrgiven[i]<=122)) && ((arrgiven[j]>=65)&&(arrgiven[j]<=90))||((arrgiven[j]>=97)&&(arrgiven[j]<=122))) {
					//System.out.println("Test");
					temp = arrgiven[i];
					arrgiven[i] = arrgiven[j];
					arrres[j] = temp;
				}
			}
		}
		
		/*for(int i = 0;i<arrgiven.length;i++) {

				if(((arrgiven[i]>=65)&&(arrgiven[i]<=90))) {
				
				}
					
		}*/
		
		for(char ch:arrres) {
			System.out.print(ch);
		}
		System.out.println();
		System.out.println("ed,c,bA!$");
		
		for(int i = given.length()-1;i>=0;i--) {
			result = result+given.charAt(i);
		}
		//System.out.println(result);
	}
	
	public static void unique() {
		String str = "apple";
		int len = str.length();
		int count = 0;
		Map<Character, Integer> numChars = new HashMap<Character, Integer>();
		for (int i = 0; i < len; i++)
		{
		    char charAt = str.charAt(i);
		    if (!numChars.containsKey(charAt))
		    {
		        numChars.put(charAt, 1);
		        
		    }
		    else
		    {
		        numChars.put(charAt, numChars.get(charAt) + 1);
		    }
		}

		System.out.println(numChars);
		
		Set<Character> charsInString = numChars.keySet();
		for (Character ch : charsInString)
        {
            if(numChars.get(ch) == 1)
            {
            	System.out.println(ch);
                //If any char has a count of more than 1, printing it's count
                System.out.println(ch +" : "+ numChars.get(ch));
            }
        }
	}
	
	public static void duplicate() 
    {
        String[] strArray = {"abc", "def", "mno", "xyz", "pqr", "xyz", "def"};
 
        HashSet<String> set = new HashSet<String>();
 
        for (String arrayElement : strArray)
        {
            if(!set.add(arrayElement)) // returns zero if already presents
            {
                System.out.println("Duplicate Element is : "+arrayElement);
            }
        }
    }    
	public static void secondLargetArray() 
    {
        int[] a = {1,2,3,4,5,6,7,8,9,0};
        int temp=0;
        for(int i = 0;i<a.length;i++) {
        	for(int j = i+1;j<a.length;j++) {
        		if (a[i] > a[j])   
                {  
                    temp = a[i];  
                    a[i] = a[j];  
                    a[j] = temp;  
                }  
        		
        	}
        }
        
        System.out.println(a[a.length-2]);
        for(int i = 0;i<a.length;i++) {
        	System.out.print(a[i]+"  ");        	
        }
        
        
    }    

}
