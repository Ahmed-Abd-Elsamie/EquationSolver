import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GaussianElimination {
    private static float[][] A;
    private static float[] B;
    private static List<String> vars;

    /**
     * Gaussian Elimination with Partial Pivoting, extracts coefficients from
     * List of Equation-s
     **/
    public static void solve(List<Equation> eqList) {
        extractVarNamesList(eqList);
        equationsToArrays(eqList);
        solve(A, B);
    }

    /** Gaussian Elimination with Partial Pivoting **/
    public static void solve(float[][] A, float[] B) {
        if (!(A == GaussianElimination.A && B == GaussianElimination.B)) {
            vars = null;
            GaussianElimination.A = A;
            GaussianElimination.B = B;
        }

        System.out.println("\n" + "Matrix : " + "\n");
        printMatrix(A, B);

        int N = B.length;
        for (int k = 0; k < N; k++) {
            /** find pivot row **/
            int max = k;
            for (int i = k + 1; i < N; i++)
                if (Math.abs(A[i][k]) > Math.abs(A[max][k]))
                    max = i;

            /** swap row in A matrix **/
            float[] temp = A[k];
            A[k] = A[max];
            A[max] = temp;

            /** swap corresponding values in constants matrix **/
            float t = B[k];
            B[k] = B[max];
            B[max] = t;

            /** pivot within A and B **/
            for (int i = k + 1; i < N; i++) {
                float factor = A[i][k] / A[k][k];
                B[i] -= factor * B[k];
                for (int j = k; j < N; j++)
                    A[i][j] -= factor * A[k][j];
            }
        }

        /** Print row echelon form **/
        printRowEchelonForm(A, B);

        /** back substitution **/
        float[] solution = new float[N];
        for (int i = N - 1; i >= 0; i--) {
            float sum = 0;
            for (int j = i + 1; j < N; j++)
                sum += A[i][j] * solution[j];
            solution[i] = (B[i] - sum) / A[i][i];
        }

        /** Print solution **/
        printSolution(solution, vars);
    }

    /** function to print in row echelon form **/
    public static void printRowEchelonForm(float[][] A, float[] B) {
        int N = B.length;
        System.out.println("\nRow Echelon form : ");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.printf("%.3f ", A[i][j]);
            System.out.printf("| %.3f\n", B[i]);
        }
        System.out.println();
    }

    /** function to print solution **/
    public static void printSolution(float[] sol, List<String> vars) {
        int N = sol.length;
        System.out.println("\nSolution : ");

        if (vars == null) {
            for (int i = 0; i < N; i++) {
                System.out.println(i + "=" + sol[i]);
            }
        }

        else {
            for (int i = 0; i < N; i++) {
                System.out.println(vars.get(i) + "=" + sol[i]);
            }
        }
    }

    private static void extractVarNamesList(List<Equation> list) {
        vars = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Equation e = list.get(i);
            for (int k = 0; k < e.getList().size(); k++) {
                if (!vars.contains(e.getList().get(k).getVar())) {
                    vars.add(e.getList().get(k).getVar());
                }
            }
        }

        Collections.sort(vars);
        // printing Variables

        System.out.println("\n");
        System.out.println("Variables Names : " + "\n");

        for (int i = 0; i < vars.size(); i++) {
            System.out.println(vars.get(i));
        }
    }

    private static void equationsToArrays(List<Equation> list) {
        int n = list.size();

        A = new float[n][n];
        B = new float[n];

        for (float[] row : A)
            Arrays.fill(row, 0);

        for (int i = 0; i < n; i++) {
            Equation e = list.get(i);
            B[i] = -1 * e.getConstant();
            for (int k = 0; k < e.getList().size(); k++) {
                Term t = e.getList().get(k);
                String ss = t.getVar();
                int index = vars.indexOf(ss);
                A[i][index] = t.getCoff();
            }
        }
    }

    public static void printMatrix(float[][] arr, float[] constants) {
        for (int i = 0; i < constants.length; i++) {
            for (int k = 0; k < constants.length; k++) {
                System.out.print(arr[i][k] + "   ");
            }
            System.out.println("|  " + constants[i]);
        }
    }
}
