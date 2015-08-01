/**
* Student ID: V00816592
* Date: 07/17/15
* Program Name: InfixCalc.java 
* Program Description: InfixCalc contains methods to take a infix expression and
* convert it into a postfix expression
* @author Dr. Mike Zastre 
* @author Isaac Sahle 
*/
public class InfixCalc {
    /**
     * First ensure the arithmetic operations plus parentheses
     * are surrounded by spaces. Then go ahead and split up the
     * whole expression using spaces (i.e, the operands will be
     * nicely separated from everything else by at least a single
     * space). This will not work for negative numbers.
     * @param s string passed to be tokenized 
     * @return a string array containing each token
     */
    public static String[] tokenize(String s) {
        // Note the missing minus character...
        String symbols[] = {"\\(", "\\)", "\\+", "\\-", "\\*", "\\/",
            "\\^"};

        // First eliminate any quotation marks
        s = s.replaceAll("'", " ");
        s = s.replaceAll("\"", " ");

        // Now all operators or parentheses, surround the character
        // with a single space on either side.
        for (int i = 0; i < symbols.length; i++) {
            String regex = symbols[i];
            String replace = " " + regex + " ";
            s = s.replaceAll(regex, replace);
        }

        // Special case: If there is a unary minus, then then
        // what appears to the right of the symbol is whitespace
        // and a digit; what appears to the left whitespace
        // and a non-digit symbol.
        s = s.replaceAll("(^|([\\(\\+\\-\\*\\/]))\\s+\\-\\s+(\\d+)", "$1 -$3");

        // Eliminate extra whitespaces at start and end of the
        // transformed string. 
        s = s.trim();

        // Finally, take advantage of the whitespace to create an
        // array of strings where each item in the array is one
        // of the elements in the original string passed in to this
        // method.
        return s.split("\\s+");
    }

    /**
     * Checks if string expression has balanced parenthases 
     * @param expr string passed to be checked for balanced parentheses 
     * @return true if expression has balanced parenthases, false otherwise 
     */
    public static boolean isBalanced(String expr){
        boolean balanced = true;
        StringStackRefBased temp = new StringStackRefBased();
        
        //traverse string character by character
        //Try is for a stack operation throwing a exception 
        try{    

            for(int i = 0; i < expr.length(); i++){

                //if open brace, push to stack
                if(expr.charAt(i) == '('){
                    temp.push(Character.toString(expr.charAt(i)));
                }
                //if closed brace pop from stack
                if(expr.charAt(i) == ')'){
                    temp.pop();    
                }
            }

            //if stack is still not empty return false
            if(!(temp.isEmpty())){
                balanced = false;
            }
            return balanced;

        }catch(StringStackException e){
            balanced = false;
            return balanced;   
        }
    
    } 
    /**
     * Checks if string expression is one of the operators +,-,/,*,or ^
     * @param s string to be evaluated 
     * @return true if expression is a operator, false otherwise 
     */
    public static boolean isOperator(String s){
        boolean compare = false;
        if(s.equals("+") || s.equals("-") || s.equals("/") || s.equals("*") || s.equals("^")){
            compare = true;
        }
        return compare;
    }
    /**
     * Checks if string expression is a integer operand
     * @param operand string to be evaluated 
     * @return true if expression is a operand, false otherwise 
     */
    public static boolean isOperand(String operand){
        try{
            Integer.parseInt(operand);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
    /**
     * Checks if operator1 is of higher precedence than operator2
     * @param operator1 first operator 
     * @param operator2 second operator
     * @return true if operator1 is >= operator2, false otherwise 
     */
    public static boolean isGreater(String operator1, String operator2){
        int stat1 = 0;
        int stat2 = 0;
        //each operator is given a value according to precedence 
        if(operator1.equals("^")){
            stat1 = 3;
        }
        if(operator2.equals("^")){
            stat2 = 3;
        }
        if(operator1.equals("/") || operator1.equals("*")){
            stat1 = 2;
        }
        if(operator2.equals("/") || operator2.equals("*")){
            stat2 = 2;
        }
        if(operator1.equals("+") || operator1.equals("-")){
            stat1 = 1;
        }
        if(operator2.equals("+") || operator2.equals("-")){
            stat2 = 1;
        }

        if(stat1 < stat2){
            return false;
        }
            return true;

    }
    /**
     * Converts infix expression to an equivalent postfix expression
     * @param tokens string array containing infix expressions  
     * @return StringList containing the postfix expression 
     */
    public static StringList toPostfix(String [] tokens){
        StringStackRefBased stack = new StringStackRefBased();
        StringList expression = new StringList();

    try{
        //taverse list     
        for(int i = 0; i < tokens.length; i++){

            //if open brace push it to stack
            if(tokens[i].equals("(")){
                stack.push(tokens[i]);
            }//if character is not a integer, valid operator, or a opening/closing brace
            else if(!(isOperand(tokens[i])) && !(isOperator(tokens[i])) && !(tokens[i].equals("(")) && !(tokens[i].equals(")"))){
                System.out.println("<noninteger>");
                return null;
            }//if valid operand insert in final postfix expression list 
            else if(isOperand(tokens[i])){
                expression.insertTail(tokens[i]);
            }//if operator and stack is empty push to stack  
            else if(isOperator(tokens[i]) && stack.isEmpty()){
                    stack.push(tokens[i]);
            }//if operator but stack is not empty pop and insert to postfix until we hit a ( or a operator with higher or equal precedence   
            else if(isOperator(tokens[i]) && !(stack.isEmpty())){
                 while(!(stack.isEmpty())){

                    if((stack.peek().equals("(")) || isGreater(tokens[i],stack.peek())){
                        break;
                    }else{
                    expression.insertTail(stack.pop());
                    }
                 }

                stack.push(tokens[i]);
            }//if closing brace pop off stack and insert into output list until ( 
            else if(tokens[i].equals(")")){
                    while(!(stack.peek().equals("("))){
                    expression.insertTail(stack.pop());
                    }
                    stack.pop();
           }
        }
        //pop remaining items from stack and insert into postfix list 
        if(!(stack.isEmpty())){
           while(!(stack.isEmpty())){

            if(isOperator(stack.peek())){
            expression.insertTail(stack.pop()); 
            }else{
            stack.pop();    
            }

           }
        }
    }catch(StringStackException e){
        System.out.println("<syntax error>");
        expression = null;
        return expression;
    }    
        return expression;

    }
    /**
     * Computes postfix expression 
     * @param expr list containing postfix expression   
     * @return String representing evaluated postfix expression 
     */
    public static String evaluatePostfix(StringList expr) {
        //Create stack to temporarily store operands
        StringStackRefBased evaluate = new StringStackRefBased();
        int one;  
        int two;
        
    try{
        //Traverse list
        for(int i = 0; i < expr.getLength(); i++){

            //Expression should never not be an int or operator if evaluate postfix is called withouth fail, but as a fail safe
            if(isOperand(expr.retrieve(i)) && isOperator(expr.retrieve(i))){
                return null;
            }//if operand push onto stack 
            else if(isOperand(expr.retrieve(i))){
            evaluate.push(expr.retrieve(i));
            }//if operator pop two items off stack compute value and push back to the stack 
            else if(isOperator(expr.retrieve(i))){
            Integer three;
            one = new Integer(evaluate.pop());
            two = new Integer(evaluate.pop());

            //figure out if we are dealing with power operator and deal with accordingly     
            if(expr.retrieve(i).equals("^")){
                three = new Integer((int)Math.pow(two,one));
                evaluate.push(three.toString());
            }
            else if(expr.retrieve(i).equals("*")){
                three = new Integer(two * one);
                evaluate.push(three.toString());
            }
            else if(expr.retrieve(i).equals("/")){
                three = new Integer(two / one);
                evaluate.push(three.toString());    
            }
            else if(expr.retrieve(i).equals("+")){
                three = new Integer(two + one);
                evaluate.push(three.toString());
            }
            else if(expr.retrieve(i).equals("-")){
                three = new Integer(two - one);
                evaluate.push(three.toString());
            }

            }

        }
        //if stack is has more than one item error has occurred 
        if(evaluate.height() > 1){
            System.out.println("<syntax error>");
            return null;
        }

        return evaluate.peek();

    }catch(StringStackException e){
        System.out.println("<syntax error>");
        return null;
    }catch(ArithmeticException e){        
        return null;
    }catch(NullPointerException e){
        return null;
    }
         
    }

    /**
     * Converts the passed in infix expression into a postfix expression and returns the computed value 
     * @param expr string expression to be evaluated   
     * @return string representation of the evaluated expression  
     */
    public static String evaluateExpression(String expr) {
        String result;
        //check if braces are balanced 
        if(!(isBalanced(expr))){
            System.out.println("<unbalanced ()>");
            result = "";
            return result;
        }
        //call computing methods
        result = evaluatePostfix(toPostfix(tokenize(expr)));

        if(result == null){
            return "";
        }else{
            return result; 
        }

    }

    public static void main(String args[]) {
        if (args.length < 1) {
            System.err.println("usage: java InfixCalc '<expression>'");
            System.exit(1);
        }
            System.out.println(evaluateExpression(args[0]));
    }

}
