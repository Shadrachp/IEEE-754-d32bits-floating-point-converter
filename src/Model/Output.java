package Model;

public class Output {
    private char signBit;//1bit
    private String sCombinationBits;//5bits
    private String sExponentialComBits;//6bits
    private String sMantissa; //20bits

    public Output(char signBit, String sCombinationBits, String sExponentialComBits, String sMantissa) {
        this.signBit = signBit;
        this.sCombinationBits = sCombinationBits;
        this.sExponentialComBits = sExponentialComBits;
        this.sMantissa = sMantissa;
    }

    public char getSignBit() {
        return signBit;
    }

    public String getsCombinationBits() {
        return sCombinationBits;
    }

    public String getsExponentialComBits() {
        return sExponentialComBits;
    }

    public String getsMantissa() {
        return sMantissa;
    }
}
