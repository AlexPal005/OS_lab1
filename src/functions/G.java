package functions;

import os.lab1.compfuncs.basic.DoubleOps;

import java.util.Optional;

public class G {
    public static void main(String[] args) {
        G g = new G();
        System.out.println(g.get_function_G(Integer.parseInt(args[0])));
    }
    //get value of function g
    public double get_function_G(int x){
        Optional<Double> result = Optional.empty();
        try {
            result = DoubleOps.trialG(x);
            if(result.isEmpty()){
                return -2.0;
            }
        }catch(InterruptedException e){
            System.out.println(e);
        }
        return Double.parseDouble(String.valueOf(result.get()));
    }
}
