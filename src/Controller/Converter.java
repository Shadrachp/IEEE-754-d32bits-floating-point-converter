package Controller;


import java.io.IOException;

import Model.Output;
import javafx.scene.control.TextArea;

/*
* IEEE-756/2018 converter
* Single Precision
* No support for special cases yet
*/

public class Converter {
    private String sExponent; //Exponent input
    private String sBase; //Base-n input
    private String sDecimal; //Decimal input
    private String sMantissa; //Mantissa for the output 20bits
    private char cSign; //The sign bit of decimal +/- later to 1/0 //done
    private char cExpSign; //The sign char of exponent +/-
    private String sResult2 =""; // Hexadecimal output
    private char MSB;
    private String binMSB;
    private final int INT_BIAS = 101;

    /**
     * Constructor for converter class, initializes fields
     * @param sExponent [String] - the exponent input of the program
     * @param sBase [String] - the base input of the program
     * @param sDecimal [String] - Decimal input from the user
     */
    public Converter(String sDecimal, String sBase, String sExponent) {
        //try catch for input with char
        try{
            cSign = getSign(sDecimal);
            cExpSign = getSign(sExponent);
            this.sDecimal = sDecimal;
            this.sExponent = sExponent;
            stringToInt(sDecimal); //Throws an error if it contains letters
            stringToInt(sExponent); //Throws an error if it contains letters
            stringToInt(sBase); //Throws an error if it contains letters
            if(!sBase.equals("10")) {
                invalidInput();
            }
//           if(cExpSign == '-') this.nExponent *= -1;
        }catch(Exception e){
           invalidInput();
        }
    }

    private void invalidInput(){
        sResult2 = null;
    }

    //Does not work for special cases
    /**
     * Conversion method
     * calls every step of the conversion process
     * TODO: Densely packed BCD for MANTISSA
     * returns an object [Output] if input is valid, otherwise null
     * */
    public Output convert(){
        if(sResult2 != null) {
            String sCB, seCB;
            normalize();
            extend();
            setMSB();
            String decBCD = toBCD(sDecimal.substring(1));
            sExponent = toBinary(ePrime(sExponent));

            if (sExponent.length() < 8)
                sExponent = extend(sExponent, 8 - sExponent.length());

            seCB = get2Bits(sExponent);
            if (MSB == '8' || MSB == '9')
                sCB = "11" + seCB + binMSB.charAt(binMSB.length() - 1);
            else
                sCB = seCB + binMSB.substring(1);

            return new Output(cSign, sCB, sExponent.substring(2), sMantissa);
        }
        return null;
    }

    /**
     * @param bin - the binary to be used for getting the first two bits
     * returns the first two bits of the passed binary input as String
     */
    private String get2Bits(String bin){
        return bin.substring(0, 2);
    }

    /**
     * 0 Extension for decimal value if length is less than 7
     */
    private void extend(){
        if(sDecimal.length() < 7)
            for (int i = 0; i < 7 - sDecimal.length(); i++)
                sDecimal = "0" + sDecimal;
    }


    /**
     * zero extension of binary
     * @param bin - the binary to be extended with zeroes
     * @param count - number of 0s to be appended
     * returns the new binary with zero extension
     */
    private String extend(String bin, int count){
        for (int i = 0; i < count; i++) {
            bin = "0" + bin;
        }
        return bin;
    }

    /**
     * sets the most significant bit
     */
    private void setMSB(){
        MSB = sDecimal.charAt(0);
        binMSB = toBCD(MSB+"");
    }

    /**
     * Converts input to BCD
     * @param str [String] - input to be converted (base10)
     * returns the BCD equivalent of the input
     */
    private String toBCD(String str){
        String BCD = "";
        if(str.contains("-") || str.contains("+"))
            str = str.substring(1);
        for (int i = 0; i < str.length(); i++) {
            String bin = toBinary(Integer.parseInt(str.charAt(i) + ""));
            bin = extend(bin, 4 - bin.length());
            BCD += bin;
        }
        return BCD;
    }

    /**
     * Converts Base10 to Base2
     * @param i [String] - Decimal to be converted
     * returns the base2 equivalent of base10 as string
     * */
    private String toBinary(int i){
        String binary = "";
        boolean pos = true;
        if(i < 0) {
            pos = !pos;
            i *= -1;
        }
        while(i > 0) {
            binary = (i % 2) + binary;
            i /= 2;
        }
        if(!pos)
            binary = 1 + binary;
        return binary;
    }

    /**
     * e' = e + INT_BIAS
     * @param e [String] - exponent
     * returns the e'
     */
    private int ePrime(String e){
        return (stringToInt(e) + INT_BIAS);
    }

    /**
     * Converts String to int
     * @param str [String] - string input to be converted to integer
     * returns the resulting integer
     */
    private int stringToInt(String str){
        return Integer.parseInt(str);
    }

    /**
    * @param str [String] - string to be converted to float
    * returns the resulting float value
    */
    private float stringToFloat(String str){
        return  Float.parseFloat((str.charAt(0) == '+'  || str.charAt(0) == '-') ?  str.substring(1) : str);
    }

    /**
    * Extracts the sign char of the input
    * @param sNum [String] - the input which may contain the sign char
    * returns the sign char +/-
     */
    private char getSign(String sNum){
       return sNum.charAt(0) == '+' ? '0'
                :  sNum.charAt(0) == '-' ? '1'
                : '0';
    }

    /**
    * Normalizes the input sDecimal removing any decimal values
    */
    private void normalize(){
        if(sDecimal.charAt(0) == '+' || sDecimal.charAt(0) == '-')
            sDecimal = sDecimal.substring(1);
        if(sDecimal.contains(".")) {
           String[] arr =sDecimal.split("\\.");
           sDecimal = arr[0] + arr[arr.length - 1];
           sExponent = "" + (stringToInt(sExponent) - arr[arr.length -1].length());
        }
    }
}
