import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Equation> list = new ArrayList<>();
        System.out.print("Please enter the number of equations : ");

        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= n; i++) {
            System.out.print("equation " + i + " : ");
            String eq = scanner.nextLine();
            Equation E = new Equation(eq);
            list.add(E);
        }

        scanner.close();

        // printing Ideal form for Equations
        System.out.println("\n");
        System.out.println("Arranged Equations : " + "\n");

        for (int i = 0; i < list.size(); i++) {
            Equation equation = list.get(i);
            System.out.println(equation.GetIdealizedEquation());
        }

        long t1 = System.currentTimeMillis();
        GaussianElimination.solve(list);
        long t2 = System.currentTimeMillis();
        System.out.println("Total Time : " + (t2 - t1) + " ms");
    }
}
