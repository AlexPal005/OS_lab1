import java.io.*;
import java.util.Scanner;
public class Manager {
    private  Process process_func_f;
    private  Process process_func_g;
    public static void main(String[] args){
        new Manager().start();
    }
    private void start()  {
        int x = x();
        System.out.println("The calculation is in progress...");
        start_process_f(x);
        start_process_g(x);
        //print result
        result();
    }
    private int x() {
        System.out.print("Enter x: ");
        Scanner in = new Scanner(System.in);
        int val_x = in.nextInt();
        in.close();

        return val_x;
    }
    //read output function f
    private double read_res_f(){
        double res_f = 0.0;
        try {
            // exit status f
            if(process_func_f.exitValue() == 0){
                BufferedReader output = new BufferedReader(new InputStreamReader(process_func_f.getInputStream()));
                String value = output.readLine();
                res_f = Double.parseDouble(value);
            }
            else{
                return -1;
            }
        }catch(IOException e ){
            System.out.println("Error" + e);
        }
        return res_f;
    }
    //read output function g
    private double read_res_g(){
        double res_g = 0.0;
        try {
            // exit status g
            if(process_func_g.exitValue() == 0){
                BufferedReader output = new BufferedReader(new InputStreamReader(process_func_g.getInputStream()));
                String value = output.readLine();
                res_g = Double.parseDouble(value);
            }
            else{
                return -1;
            }
        }catch(IOException e ){
            System.out.println("Error" + e);
        }
        return res_g;
    }
    //start f
    private void start_process_f(int x){
        try {
            //start process for function f
            ProcessBuilder process_f = new ProcessBuilder();
            process_f.directory(new File("src"));
            process_f.command("java","-cp", ".;lab1.jar","functions.F",Integer.toString(x));

            process_func_f = process_f.start();
            Thread.sleep(4000);

            process_func_f.destroy();
        }catch (IOException | InterruptedException e ){
            System.out.println("Error" + e);
        }
    }
    //start g
    private void start_process_g(int x){
        try {
            //start process for function g
            ProcessBuilder process_g = new ProcessBuilder();

            process_g.directory(new File("src"));
            process_g.command("java","-cp", ".;lab1.jar", "functions.G",Integer.toString(x));

            process_func_g = process_g.start();
            Thread.sleep(4000);

            process_func_g.destroy();
        }catch (IOException | InterruptedException e ){
            System.out.println("Error" + e);
        }
    }
    public void result(){
        //read result of f
        double f = read_res_f();
        //read result of g
        double g = read_res_g();

        System.out.println("RESULT");
        if (f == -1 && g == -1 ){
            System.out.println("The function F froze!");
            System.out.println("The function G froze!");
        }
        else if (f == -2.0 && g == -2.0){
            System.out.println("Fail function F!");
            System.out.println("Fail function G!");
        }
        else if(f== -1 && g == -2){
            System.out.println("The function F froze!");
            System.out.println("Fail function G!");
        }
        else if(g == -1 && f == -2){
            System.out.println("Fail function F!");
            System.out.println("The function G froze!");
        }
        else if(f == -1 ){
            System.out.println("The function F froze!");
            System.out.println("Result function G: " + g);
        }
        else if(g == -1){
            System.out.println("The function G froze!");
            System.out.println("Result function F: " + f);
        }
        else if ( f == -2){
            System.out.println("Fail function F!");
            System.out.println("Result function G: " + g);
        }
        else if ( g == -2){
            System.out.println("Fail function G!");
            System.out.println("Result function F: " + f);
        }
        else{
            double result = f + g;
            System.out.println("Result F = " + f);
            System.out.println("Result G = " + g);
            System.out.println("Final Result = " + result);
        }
    }
}
