import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Equation {
    private String origEq;
    private String idealEq = "";
    private List<Term> list  = new ArrayList<>();
    private float constant = 0;

    public Equation(String eq) {
        origEq = eq;
        IdealizeEq();
        sortByKey();
    }

    public String GetOriginalEquation() {
        return origEq;
    }

    public String GetIdealizedEquation() {
        if (idealEq == "") {
            StringBuilder sb = new StringBuilder();
            for (int k = 0; k < list.size(); k++) {
                String varName = list.get(k).getVar();
                float coeff = list.get(k).getCoff();
                if (k != 0 && coeff >= 0) {
                    sb.append('+');
                }
                sb.append(coeff);
                sb.append(varName);
            }
            sb.append("=" + (-1 * constant));
            idealEq = sb.toString();
        }

        return idealEq;
    }

    public float getConstant() {
        return constant;
    }

    public List<Term> getList() {
        return list;
    }

    public Term getTermByName(String var) {
        for (int i = 0; i < list.size(); i++) {
            Term t = list.get(i);
            if (var.equals(t.getVar())) {
                return t;
            }
        }

        return null;
    }

    private void IdealizeEq() {
        String s = origEq.replaceAll(" ", "");
        int leftPos = 0;
        int sideMultiplier = 1;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '=') {
                if (sideMultiplier != 1) {
                    // syntax error
                    throw new IllegalArgumentException("2 or more '=' signs");
                }

                processTerm(s.substring(leftPos, i), sideMultiplier);
                sideMultiplier = -1;
                leftPos = i + 1;

                continue;
            }

            if (c == '+' || c == '-') {
                if (c == '-' && (i == 0 || s.charAt(i - 1) == '=')) {
                    continue;
                }

                processTerm(s.substring(leftPos, i), sideMultiplier);
                leftPos = i;
            }
        }

        processTerm(s.substring(leftPos), sideMultiplier);
    }

    private void processTerm(String s, float multiplier) {
        String varName = extractVarNameFromString(s);
        float coefficient = extractCoeffFromString(s) * multiplier;

        if (varName == "") {
            constant += coefficient;
        } else {
            Term t = getTermByName(varName);
            if (t != null) {
                t.setCoff(t.getCoff() + coefficient);
            } else {
                t = new Term();
                t.setVar(varName);
                t.setCoff(coefficient);
                list.add(t);
            }
        }
    }

    private static float extractCoeffFromString(String s) {
        String f = s.replace(extractVarNameFromString(s), "");

        if (f.equals("")) {
            return 1;
        }

        if (f.equals("-")) {
            return -1;
        } else if (f.equals("+")) {
            return 1;
        }

        return Float.parseFloat(f);
    }

    private static String extractVarNameFromString(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!(Character.isDigit(c) || c == '.' || c == '+' || c == '-')) {
                return s.substring(i, s.length());
            }
        }
        return ""; // for constants
    }

    private void sortByKey() {
        Collections.sort(list, NameComparator);
    }

    private static Comparator<Term> NameComparator = new Comparator<Term>() {
        @Override
        public int compare(Term e1, Term e2) {
            return e1.getVar().compareTo(e2.getVar());
        }
    };
}
