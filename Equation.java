package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Equation {
    private String eq;
    private List<Term>list;
    private float constant = 0;
    
    public Equation(String eq){
        this.eq = eq;
        list = new ArrayList<>();
        IdealizeEq(eq);
        sortBykey();
    }
    public float getConstant(){
        return constant;
    }
    public List<Term> getList() {
        return list;
    }
    public void IdealizeEq(String s){
        int counter = 0;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '='){
                String T = s.substring(counter , i);
                if(isConst(var(T), String.valueOf(coff(T))) == true){
                    constant = constant + coff(T);
                }else{
                    if(isExist(var(T), -1 * coff(T)) == false){
                        Term t = new Term();
                        t.setVar(var(T));
                        t.setCoff(coff(T));
                        list.add(t);
                    }
                }
                counter = i + 1;
                for(int k = i + 1; k < s.length(); k++){
                    char d = s.charAt(k);
                    if(d == '+' || d == '-'){
                        if(k == i + 1&& d == '-'){
                            continue;
                        }
                        String T2 = s.substring(counter , k);
                        if(isConst(var(T2), String.valueOf(coff(T2))) == true){
                            constant = constant - coff(T2);
                        }else{
                            if(isExist(var(T2), -1 * coff(T2)) == false){
                                Term t = new Term();
                                t.setVar(var(T2));
                                t.setCoff(-1 * coff(T2));
                                list.add(t);
                            }
                        }
                        counter = k;
                    }
                    if(k == s.length() - 1){
                        String T2 = s.substring(counter, s.length());
                        if(isConst(var(T2), String.valueOf(coff(T2))) == true){
                            constant = constant - coff(T2);
                        }else{
                            if(isExist(var(T2), -1 * coff(T2)) == false){
                                Term t = new Term();
                                t.setVar(var(T2));
                                t.setCoff(-1 * coff(T2));
                                list.add(t);
                            }
                        }
                    }
                }
                break;
            }
            if(c == '+' || c == '-'){
                if(i == 0 && c == '-'){
                    continue;
                }
                String T = s.substring(counter , i);
                if(isConst(var(T), String.valueOf(coff(T))) == true){
                    constant = constant + coff(T);
                }else{
                    if(isExist(var(T), coff(T)) == false){
                        Term t = new Term();
                        t.setVar(var(T));
                        t.setCoff(coff(T));
                        list.add(t);
                    }
                }
                counter = i;
            }
        }
    }
    
    public float coff(String s){
        String f = s.replace(var(s),"");
        if(f.equals("")){
            char c = s.charAt(0);
            if(c == '0' ||c == '1' ||c == '2' ||c == '3' ||c == '4' ||c == '5' ||c == '6' ||c == '7' ||c == '8' ||c == '9' ||c == '.'|| c == '+'|| c == '-'|| c == '='){
                return Float.parseFloat(s);
            }else{
                return 1;
            }
        }
        if(f.equals("-")){
            return -1;
        }else if(f.equals("+")){
            return 1;
        }
        
        return Float.parseFloat(f);
    }
    
    public String var(String s){
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '0' ||c == '1' ||c == '2' ||c == '3' ||c == '4' ||c == '5' ||c == '6' ||c == '7' ||c == '8' ||c == '9' ||c == '.'|| c == '+'|| c == '-'|| c == '='){
            }else{
                return s.substring(i, s.length());
            }
        }
        return s; // for constants
    }
    
    public boolean isConst(String var, String coff){
        if(var.equals(coff)){
            return true;
        }else if(coff.equals("-" + var + ".0")){
            return true;
        }else if(coff.equals(var + ".0")){
            return true;
        }else if((var+".0").equals("+"+coff)){
            return true;
        }else if(var.equals("+"+coff)){
            return true;
        }
        return false;
    }    
    public boolean isExist(String var , float coff){
        for(int i = 0; i < list.size(); i++){
            Term t = list.get(i);
            if(var.equals(t.getVar())){
                float sum = t.getCoff() + coff;
                Term term = new Term();
                term.setCoff(sum);
                term.setVar(var);
                    list.set(i, term);
                    return true;
            }
        }
        return false;
    }
    
    public void sortBykey() {
        Collections.sort(list , NameComparator);
    } 
    public static Comparator<Term> NameComparator = new Comparator<Term>() {
        @Override
        public int compare(Term e1, Term e2) {
            return e1.getVar().compareTo(e2.getVar());
        }
    };
}