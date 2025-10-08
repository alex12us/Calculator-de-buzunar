package ro.usv;
import java.util.*;

public class Evaluator {
   
	public static  double Evaluare(String expr) {
		List<String>tokens=tokenize(expr);
		List<String>rpn=toRPN(tokens);
		return evalRPN(rpn);
	}
	//RPN - Reverse Polish Notation tradus in limba romana notatia poloneza  inversa
      //face referire la operatorii postfixati
	 //tokenizarea unui cuvant este in medie a 4 caractere pe fiecare cuvant
	private static List<String>tokenize(String s){
		List<String>tokens = new ArrayList<>();
		int i = 0;
		while(i<s.length()) {
			char c = s.charAt(i);
			if(Character.isWhitespace(c)) {
				i++;
			}
			else if("+-*/".indexOf(c)!= -1) {
				tokens.add(String.valueOf(c));
				i++;
			}else if (Character.isDigit(c) || c=='.') {
				StringBuilder numar = new StringBuilder();
				while(i<s.length()&& (Character.isDigit(s.charAt(i))||s.charAt(i)=='.')) {
				
					numar.append(s.charAt(i++));
				}
				tokens.add(numar.toString());
			}else {
				throw new IllegalArgumentException("Caracter invalid: "+c);
			}
		}
		return tokens;
	}
	 private  static List<String> toRPN(List<String> tokens) {
	        List<String> output = new ArrayList<>();
	        Stack<String> ops = new Stack<>();
	        for (String t : tokens) {
	            if (t.matches("[0-9.]+")) {
	                output.add(t);
	            } else if (esteOperator(t)) {
	                while (!ops.isEmpty() && precedenta(ops.peek()) >= precedenta(t)) {
	                    output.add(ops.pop());
	                }
	                ops.push(t);
	            }
	        }
	        while (!ops.isEmpty()) output.add(ops.pop());
	        return output;
	    }

	    private  static double evalRPN(List<String> rpn) {
	        Stack<Double> stack = new Stack<>();
	        for (String t : rpn) {
	            if (t.matches("[0-9.]+")) {
	                stack.push(Double.parseDouble(t));
	            } else if (esteOperator(t)) {
	                double b = stack.pop();
	                double a = stack.pop();
	                switch (t) {
	                    case "+" : stack.push(a + b);
	                    break;
	                    case "-" : stack.push(a - b);
	                    break;
	                    case "*" : stack.push(a * b);
	                    break;
	                    case "/" : {
	                        if (b == 0) throw new ArithmeticException("Împărțire la zero");
	                        stack.push(a / b);
	                    }
	                    break;
	                }
	            }
	        }
	        return stack.pop();
	    }
	
	    //verific daca tokenul este operator
	private static boolean esteOperator(String s) {
		   return "+-*/".contains(s);
	}
	//stabileste prioritataea operatorului
	private  static int precedenta(String op)  
	{
		return (op.equals("*")||op.equals("/"))?2:1;
	
	}
	
}
