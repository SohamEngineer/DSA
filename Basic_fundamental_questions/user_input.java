import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int age;
        String name;
        System.out.println("Enter Your Name:");
        name=sc.nextLint();
        System.out.println("Enter Your Age:");
        age=sc.nextInt();
        System.out.println("My name is:",name);
        System.out.println("My age is:",age);
    }
}
