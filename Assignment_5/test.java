import java.io.*;
public class test{
	




public static void main(String[] args) {
	


try{
	PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
		writer.println("HELLO WORLD");
		writer.close();

}catch(FileNotFoundException e){
	System.out.println("EXCeption found");
}catch(UnsupportedEncodingException e){
	System.out.println("EXCeption found");

}



}










}