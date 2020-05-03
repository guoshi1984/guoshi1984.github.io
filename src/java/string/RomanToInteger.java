/* LeetCode 13
 * Given a number in Roman, convert it to integer
 */
public class RomanToInteger
{
	public static int mapping(char c)
	{ 	
		switch(c){
			case 'I': return 1;
     			case 'V': return 5;
     			case 'X': return 10;
     			case 'L': return 50;
     			case 'C': return 100;
     			case 'D': return 500;
     			case 'M': return 1000;
     			default: return 0;
  		}
	}



	public static int getRomanToInt(String s)
	{ 
		int result=0;
  		for(int i=0; i< s.length(); i++)
  		{
     			if(i>0 && mapping(s.charAt(i))> mapping(s.charAt(i-1))){ 
				result=result+ mapping(s.charAt(i))-2*mapping(s.charAt(i-1)); 
			}
     			else{ 
				result=result+mapping(s.charAt(i)); 
			}

  		}
  		return result;
	}

	public static void main(String[] args)
	{  
		String s1 = new String("IX");
   		System.out.println(getRomanToInt(s1));
		String s2 = new String("III");
   		System.out.println(getRomanToInt(s2));
		String s3 = new String("IV");
   		System.out.println(getRomanToInt(s3));
		String s4 = new String("LVIII");
   		System.out.println(getRomanToInt(s4));
		String s5 = new String("MCMXCIV");
   		System.out.println(getRomanToInt(s5));
   		return;
	}

}

