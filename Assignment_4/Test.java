public class Test{







public static void main(String[] args) {
	        InfixCalc test = new InfixCalc();
        String one = "{adasavd}";
        //Test behavior of is balanced
        if(test.isBalanced(one)){
        System.out.println("Test 1 (isBalanced): passed");
        }else{
        System.out.println("Test 1 (isBalanced): FAILED");
        }
        
        //test id isoperator works
        String two = "^";
        System.out.println(test.isOperator(two));

        //test if is operand works
        String three = "8";
        System.out.println(test.isOperand(three));

        //test if isGreater works
        String four = "^";
        String five = "-";
        System.out.println(test.isGreater(four,five));

        //Test toPostfix functionality 1 
        String [] tokens = new String[4];
        StringList result;
        tokens[0] = "20";
        tokens[1] = "+";
        tokens[2] = "10";    
        tokens[3] = "30";
        result = test.toPostfix(tokens);
        System.out.println(result.getLength());

        for(int i = 0; i < result.getLength(); i++){
             System.out.println(result.retrieve(i));
        }

        //Test toPostfix functionality 2
        String [] tokens2 = new String[5];
        StringList result2;
        tokens2[0] = "2";
        tokens2[1] = "10";
        tokens2[2] = "+";
        tokens2[3] = "2";
        tokens2[4] = ")";    
        
        result2 = test.toPostfix(tokens2);
        System.out.println();
        System.out.println(result2);

        //Test toPostfix functionality 3 
        String [] tokens3 = new String[11];
        StringList result3;
        tokens3[0] = "1";
        tokens3[1] = "-";
        tokens3[2] = "(";
        tokens3[3] = "2";
        tokens3[4] = "+";
        tokens3[5] = "3";
        tokens3[6] = "*";
        tokens3[7] = "4";
        tokens3[8] = ")";
        tokens3[9] = "/";
        tokens3[10] = "5";      
        
        result3 = test.toPostfix(tokens3);
        System.out.println();
        for(int x = 0; x < result3.getLength(); x++){
             System.out.println(result3.retrieve(x));
        }

        //Test toPostfix functionality 4 
        String [] tokens4 = new String[19];
        StringList result4;
        tokens4[0] = "(";
        tokens4[1] = "(";
        tokens4[2] = "-81";
        tokens4[3] = "*";
        tokens4[4] = "5";
        tokens4[5] = ")";
        tokens4[6] = "+";
        tokens4[7] = "(";
        tokens4[8] = "33";
        tokens4[9] = "/";
        tokens4[10] = "4";
        tokens4[11] = ")";
        tokens4[12] = "*";
        tokens4[13] = "(";
        tokens4[14] = "1";
        tokens4[15] = "-";
        tokens4[16] = "5";
        tokens4[17] = ")";
        tokens4[18] = ")";


        System.out.println();
        result4 = test.toPostfix(tokens4);
        
        for(int z = 0; z < result4.getLength(); z++){
             System.out.println(result4.retrieve(z));
        }

        StringList list = new StringList();
        list.insertTail("20");
        list.insertTail("10");
        list.insertTail("30");
        list.insertTail("^");
        list.insertTail("-");
        list.insertTail("+");

        System.out.println(test.evaluatePostfix(list));

}










}