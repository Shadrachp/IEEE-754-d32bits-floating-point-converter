package Model;

import java.util.ArrayList;

/**
 * Converts 1set of BCD to densely packed BCD
 */
public class DenselyPackedBCD {
    private String[] arrBCD;
    private ArrayList<Integer> aZeroes;
    private String sAEI;

    /**
     * Constructor for initializing the set of BCD var [arrBCD]
     * @param arrBCD - the set of BCDE
     */
    public DenselyPackedBCD(String[] arrBCD) {
        this.arrBCD = arrBCD;
    }

    /**
     * initializes and calls the method to convert to densely packed bcd
     * returns densely packed bcd
     */
    public String convert(){
        initAEI(arrBCD);
        return applyDPBCD(arrBCD);
    }

    /**
     * Converts the BCD to densely packed bcd
     * @param arrBCD - the set of BCD
     * returns densely packed bcd
     */
    private String applyDPBCD(String[] arrBCD){
        int z = countZero(sAEI);
        return z==3 ? rule1BCD3(arrBCD)
                : z==2 ? rule2BCD3(arrBCD)
                : z==1 ? rule3BCD3(arrBCD)
                : rule4BCD3(arrBCD);
    }

    /**
     * AEI consists of all zeroes
     * @param BCD3 - 3bits from bcd
     * returns the resulting bcd
     */
    private String rule1BCD3(String[] BCD3){
        String str = "";
        for (int i = 0; i < BCD3.length; i++) {
            if(i == 2)
                str += "0";
            str += BCD3[i].substring(1);
        }
        return str;
    }

    /**
     * AEI does contains two 0s
     * @param BCD3 - 3bits from bcd
     * returns the resulting bcd
     */
    private String rule2BCD3(String[] BCD3){
        String sR = BCD3[0].charAt(3) + "",
                sU = BCD3[1].charAt(3) + "" ,
                sY = BCD3[2].charAt(3) + "";

        if(aZeroes.contains(0) && aZeroes.contains(1)){
            sR = BCD3[0].substring(1, 3) + sR;
            sU = BCD3[1].substring(1, 3) + sU;
            sY = "100" + sY;
        }else if(aZeroes.contains(0) && aZeroes.contains(2)){
            sR = BCD3[0].substring(1, 3) + sR;
            sU = BCD3[2].substring(1, 3) + sU;
            sY = "101" + sY;
        }else{
            sR = BCD3[2].substring(1, 3) + sR;
            sU = BCD3[1].substring(1, 3) + sU;
            sY = "110" + sY;
        }
        return sR + sU + sY;
    }

    /**
     * AEI does contains only one 0
     * @param BCD3 - 3bits from bcd
     * returns the resulting bcd
     */
    private String rule3BCD3(String[] BCD3){
        String sR = BCD3[0].charAt(3) + "",
                sU = BCD3[1].charAt(3) + "" ,
                sY = BCD3[2].charAt(3) + "";
        if(aZeroes.contains(0)){
            sR = BCD3[0].substring(1, 3) + sR;
            sU = "10" + sU;
            sY = "111" + sY;
        }else if(aZeroes.contains(1)){
            sR = BCD3[1].substring(1, 3) + sR;
            sU = "01" + sU;
            sY = "111" + sY;
        }else{
            sR = BCD3[2].substring(1, 3) + sR;
            sU = "00" + sU;
            sY = "111" + sY;
        }
        return sR + sU + sY;
    }

    /**
     * AEI does not contain a 0
     * @param BCD3 - 3bits from bcd
     * returns the resulting bcd
     */
    private String rule4BCD3(String[] BCD3){
        String sR = BCD3[0].charAt(3) + "",
                sU = BCD3[1].charAt(3) + "" ,
                sY = BCD3[2].charAt(3) + "";
        return "00" + sR + "11" + sU + "111"+ sY;
    }

    /**
     * Counts and Locates the zeroes from BCD
     * @param BCD3 - BCD without the first bit
     * returns the # of zeroes found
     */
    private int countZero(String BCD3){
        int count = 0;
        aZeroes = new ArrayList<>();
        for (int i = 0; i <BCD3.length() ; i++)
            if(BCD3.charAt(i) == '0') {
                count++;
                aZeroes.add(i);
            }
        return count;
    }

    private void initAEI(String[] arrBCD){
        sAEI = "" + arrBCD[0].charAt(0) + arrBCD[1].charAt(0) + arrBCD[2].charAt(0);
    }

}
