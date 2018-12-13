package assignments2017.A2PostedCode;

/* Student Name: Baricisse Ousmane
   Student ID: 260683211

 */

import java.util.Stack;
import java.util.ArrayList;

public class Expression  {
    private ArrayList<String> tokenList;

    //  Constructor    
    /**
     * The constructor takes in an expression as a string
     * and tokenizes it (breaks it up into meaningful units)
     * These tokens are then stored in an array list 'tokenList'.
     */

    Expression(String expressionString) throws IllegalArgumentException{
        tokenList = new ArrayList<String>();
        StringBuilder token = new StringBuilder();

        //ADD YOUR CODE BELOW HERE
        //..
        for(int i=0; i<expressionString.length();i++){
          char stringCharacter = expressionString.charAt(i);
          if(token.length() == 0 && stringCharacter != ' '){
            token.append(stringCharacter);
          }
          else if(isInteger(token.toString())){
            if(stringCharacter >= '0' && stringCharacter<='9' ){
              token.append(stringCharacter);
            }
              else{
                tokenList.add(token.toString());
                token = new StringBuilder();
                if(stringCharacter!= ' '){
                  token.append(stringCharacter);
                }
              }
          }

          if (token.toString().equals("(") || token.toString().equals(")") || token.toString().equals("*") ||
                  token.toString().equals("/") || token.toString().equals("[") || token.toString().equals("]")) {
              tokenList.add(token.toString());
              token = new StringBuilder();
          } else if (token.toString().equals("+")) {
              if (!(i == expressionString.length() - 1) && expressionString.charAt(i + 1) == '+') {
                  token.append("+");
                  i++;
              }
              tokenList.add(token.toString());
              token = new StringBuilder();
          } else if (token.toString().equals("-")) {
              if (!(i == expressionString.length() - 1) && expressionString.charAt(i + 1) == '-') {
                  token.append("-");
                  i++;
              }
              tokenList.add(token.toString());
              token = new StringBuilder();
          }


          if (i == expressionString.length() - 1 && !(token.length() == 0)) {
              tokenList.add(token.toString());
              token = new StringBuilder();
          }

        }
        
        //..
        //ADD YOUR CODE ABOVE HERE
    }

    /**
     * This method evaluates the expression and returns the value of the expression
     * Evaluation is done using 2 stack ADTs, operatorStack to store operators
     * and valueStack to store values and intermediate results.
     * - You must fill in code to evaluate an expression using 2 stacks
     */
    public Integer eval(){
        Stack<String> operatorStack = new Stack<String>();
        Stack<Integer> valueStack = new Stack<Integer>();
        
        //ADD YOUR CODE BELOW HERE
        //.. push numbers into valueStack and push operators into operatorStack. After we meet a closed parenthensis we pop the operator from the Stack, we see it from the stack, if binary, operator we pop 2 values, evaluate,
        //    and put it back in.
        for(int i = 0; i<tokenList.size(); i++){
          if(isInteger(tokenList.get(i))){
            valueStack.add(Integer.valueOf(tokenList.get(i)));
          }
          else if (!(tokenList.get(i).equals("(") || tokenList.get(i).equals(")") || tokenList.get(i).equals("[") || tokenList.get(i).equals("]"))){
            operatorStack.add(tokenList.get(i));
            }
          else if(tokenList.get(i).equals(")") && !(operatorStack.isEmpty())){
            String element = operatorStack.pop();
            //evaluating expression for all different types of operators. and adding it to the ValueStack.
            if(element.equals("+")){
              Integer number1 = valueStack.pop();
              Integer number2 = valueStack.pop();
              valueStack.add(number1 + number2);
            }
            else if(element.equals("-")){
            Integer number1 = valueStack.pop();
            Integer number2 = valueStack.pop();
            valueStack.add(number2-number1);  
          }
          else if(element.equals("*")){
            Integer number1 = valueStack.pop();
            Integer number2 = valueStack.pop();
            valueStack.add(number1*number2);
          }
          else if(element.equals("/")){
            Integer number1 = valueStack.pop();
            Integer number2 = valueStack.pop();
            valueStack.add(number2/number1);
          }
          else if(element.equals("++")){
            Integer number1 = valueStack.pop();
            valueStack.add(number1+1);
          }
          else if(element.equals("--")){
            Integer number1 = valueStack.pop();
            valueStack.add(number1-1);
          }
        }
          else if(tokenList.get(i).equals("]")){
            Integer number1 = valueStack.pop();
            if(number1<0){
              number1= -number1;
            }
            valueStack.add(number1);
            
          }
        }
        //..
        //ADD YOUR CODE ABOVE HERE

        return valueStack.pop();   // popping the element ValueStack
    }

    //Helper methods
    /**
     * Helper method to test if a string is an integer
     * Returns true for strings of integers like "456"
     * and false for string of non-integers like "+"
     * - DO NOT EDIT THIS METHOD
     */
    private boolean isInteger(String element){
        try{
            Integer.valueOf(element);
        }catch(NumberFormatException e){
            return false;
        }
        return true;
    }

    /**
     * Method to help print out the expression stored as a list in tokenList.
     * - DO NOT EDIT THIS METHOD    
     */

    @Override
    public String toString(){   
        String s = new String(); 
        for (String t : tokenList )
            s = s + "~"+  t;
        return s;       
    }

}

