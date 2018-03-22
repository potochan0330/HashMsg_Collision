import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.io.*;

import javax.xml.stream.events.Characters;

import org.apache.commons.lang3.time.StopWatch;


public class main {
	
	private static int numofcol=0;
	private static String txt1, txt2, htxt1, htxt2;
	private static final String adding = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final char[] addwords = adding.toCharArray();
	private static int numofbit;
	private static boolean finsihflag = false;

	public static void checkhash(String h1[], String h2[]){
		
	}
	
	
	public static void main(String[] args) throws NoSuchAlgorithmException {

		StopWatch stopWatch = new StopWatch();
		HashTextTest hash = new HashTextTest();
		Scanner scanner = new Scanner(System.in);
		
		
		System.out.println("Enter Message 1:");
		txt1 = scanner.nextLine();
		System.out.println("Enter Message 2:");
		txt2 = scanner.nextLine();
		System.out.println("Number of collision need to find:");
		numofbit = scanner.nextInt();
		
		stopWatch.start();
		htxt1 = hash.sha1(txt1);
		htxt2 = hash.sha1(txt2);
		ArrayList<String> harr1 = new ArrayList<String>();
		ArrayList<String> harr2 = new ArrayList<String>();
		String[] hex1 = htxt1.split("");
		for(String hex:hex1){
		    harr1.add(hex);
		}
		String[] hex2 = htxt2.split("");
		for(String hex:hex2){
		    harr2.add(hex);
		}

		int colnum=0;
		boolean flag =true;
		Random ran = new Random();
		while(colnum*4<numofbit){
			colnum = 0;
			for(int i = 0; i<harr1.size();i++){
				if(harr1.get(i).equals(harr2.get(i))){
					colnum++;}}
			if(colnum*4>=numofbit){
				stopWatch.stop();
				System.out.println("text1 : " + txt1);
				System.out.println("text2 : " + txt2);
				System.out.println("Number of collision found: " + colnum*4);
				System.out.println("Time used: " + stopWatch.getTime()+" s");
				break;
			}
			else{

					if(!flag){
						txt1 =txt1.substring(0 , txt1.length()-5);
						txt2 =txt2.substring(0 , txt2.length()-5);
					}
						
					//System.out.println("text1b : " + txt1);
					//System.out.println("text2b : " + txt2);
					for(int i=0;i<5;i++){
						int num1 = ran.nextInt(addwords.length);
						int num2 = ran.nextInt(addwords.length);
						txt1 = txt1 + addwords[num1];
						txt2 = txt2 + addwords[num2];
					}
						//System.out.println("text1f : " + txt1);
						//System.out.println("text2f : " + txt2);
						htxt1 = hash.sha1(txt1);
						htxt2 = hash.sha1(txt2);
						harr1.clear();
						harr2.clear();
						hex1 = htxt1.split("");
						for(String hex:hex1){
						    harr1.add(hex);
						}
						hex2 = htxt2.split("");
						for(String hex:hex2){
						    harr2.add(hex);
						}

				flag = false;
			}
		}
	}
}	

