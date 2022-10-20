package functions;

import os.lab1.compfuncs.basic.DoubleOps;

import java.util.Optional;

public class F {
    public static void main(String[] args) {
        F f = new F();
        System.out.println(f.get_function_F(Integer.parseInt(args[0])));
    }
    //get value of function f
    public double get_function_F(int x) {
        Optional<Double> result = Optional.empty();
        try {
            result = DoubleOps.trialF(x);
            if(result.isEmpty()){
                return -2.0;
            }
        }catch(InterruptedException e){
            System.out.println(e);
        }
        return Double.parseDouble(String.valueOf(result.get()));
    }
}
