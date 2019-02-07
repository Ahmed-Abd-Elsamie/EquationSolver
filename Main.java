package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        List<Equation> list = new ArrayList<>();
        System.out.print("Please enter the number of equations : ");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for(int i = 1; i <= n; i++){
            System.out.print("equation " + i + " : ");
            Scanner s = new Scanner(System.in);
            String eq = s.nextLine();
            Equation E = new Equation(eq);
            list.add(E);
        }
        // printing Ideal form for Equations
        System.out.println("\n");
        System.out.println("Arranged Equations : " + "\n");
        
        for(int i = 0; i < list.size(); i++){
            Equation equation = list.get(i);
            for(int k = 0; k < equation.getList().size(); k++){
                if(k == 0){
                    System.out.print(equation.getList().get(k).getCoff() + equation.getList().get(k).getVar());
                }else if(equation.getList().get(k).getCoff() > 0){
                    System.out.print("+"+equation.getList().get(k).getCoff() + equation.getList().get(k).getVar());
                }else{
                    System.out.print(equation.getList().get(k).getCoff() + equation.getList().get(k).getVar());
                }
            }
            System.out.println("=" + (-1 * equation.getConstant()));
        }
        
        long t1 = System.currentTimeMillis();
        GaussianElimination.solve(list);
        long t2 = System.currentTimeMillis();
        System.out.println("Total Time : " + (t2 - t1));
    }
}