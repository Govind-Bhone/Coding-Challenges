/**
 * Created by govind.bhone on 2/1/2017.
 */
public class JavaTest {
     public static void main(String[] args)
        {
            System.out.println(solutions("ba1","1Ad"));
            System.out.println(solutions("A2Le","2pL1"));
            System.out.println(solutions("a10","10a"));
        }

        public static boolean solutions(String input1, String input2) {
            StringBuffer inputexpanded1 = new StringBuffer();
            StringBuffer inputexpanded2 = new StringBuffer();
            inputexpanded1 = expandInput(input1,inputexpanded1);
            inputexpanded2 = expandInput(input2,inputexpanded2);
            boolean finalOutput =false;
            if(inputexpanded1.length()!= inputexpanded2.length())
                return false;
            else
            {
                for(int i=0;i<inputexpanded1.length();i++) {
                    if(inputexpanded1.charAt(i) == inputexpanded2.charAt(i)
                            || Character.isLetter(inputexpanded1.charAt(i)) && inputexpanded2.charAt(i) =='?'
                            || Character.isLetter(inputexpanded2.charAt(i)) && inputexpanded1.charAt(i) =='?'
                            )
                    {
                        finalOutput =true;
                    }else
                    {
                        finalOutput =false;
                        break;
                    }
                }

            }
            return finalOutput;
        }

        public static StringBuffer expandInput(String input,StringBuffer extendedInput)
        {
            char tempHolder;
            int len1 = input.length();

            for (int i = 0; i < len1; i++) {
                tempHolder = input.charAt(i);
                //System.out.println(tempHolder);
                if (Character.isDigit(input.charAt(i))) {
                    for (int k = 0; k < Character.getNumericValue(tempHolder); k++) {
                        extendedInput.append("?");
                    }
                } else {
                    extendedInput.append(tempHolder);
                }
            }
            return extendedInput;
        }
    }

