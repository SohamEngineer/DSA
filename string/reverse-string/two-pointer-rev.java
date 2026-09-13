/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

public class Main
{
	public static void main(String[] args) {
		String str= "hello";
        //convert string to char array
		char[] conv= str.toCharArray();
		int i= 0;
		int j=str.length()-1;
		while(i<j){
		    char temp=conv[i];
		    conv[i]=conv[j];
		    conv[j]=temp;
		    i++;
		    j--;
		}
		for(int k=0 ;k<conv.length;k++){
		    System.out.println(conv[k]);
		}
	}
}