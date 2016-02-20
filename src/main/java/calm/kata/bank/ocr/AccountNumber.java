package calm.kata.bank.ocr;

/**
 * Created with IntelliJ IDEA.
 * User: edward
 * Date: 1/1/16
 * Time: 7:50 PM
 */
public class AccountNumber {

    int i = 0;
    AccountNumber[] alternates = new AccountNumber[20];
    int altIndex = 0;
    private boolean partial;
    private boolean ambiguous;
    private boolean illegible;
    private PipeUnderscoreNumber[] accountNum = new PipeUnderscoreNumber[9];
    
    public AccountNumber() {

        partial = true;
    }

    public AccountNumber(int num) {
        
        if(String.valueOf(num).length() == 9) {
            partial = false;
        }
        int divisor = 100000000;
        while(divisor > 0) {
            appendDigit(PipeUnderscoreNumber.fromInt(num / divisor));
            num = num % divisor;
            divisor = divisor / 10;
        }
    }
    
    public AccountNumber(PipeUnderscoreNumber[] accountNum) {
        
        this.accountNum = accountNum;
        partial = false;
    }

    public boolean isAmbiguous() {

        return ambiguous;
    }

    public boolean appendDigit(PipeUnderscoreNumber ocrNumber) {

        int i = 0;
        while (i < accountNum.length && accountNum[i] != null) {
            i++;
        }
        if (i < accountNum.length) {
            accountNum[i] = ocrNumber;
        } else {
            partial = false;
        }

        return partial;
    }
    
    /**
     *
     *  account number:  3  4  5  8  8  2  8  6  5
     *  position names:  d9 d8 d7 d6 d5 d4 d3 d2 d1
     *  
     *  checksum calculation:
     *  (d1 + 2*d2 + 3*d3 +..+9*d9) mod 11 = 0
     */
    public boolean isValid() {
        
        int checksum = 0;
        for (int i = 0; i < accountNum.length; i++) {
            if(PipeUnderscoreNumber.ILLEGIBLE.equals(accountNum[i])) {
                return false;
            }
            checksum = checksum + Integer.parseInt(accountNum[i].getNumber()) * (9 - i);
        }
        
        return checksum % 11 == 0;
    }
    
    public boolean isPartial() {
        return partial;
    }
    
    public int toInt() {
       
        return Integer.parseInt(this.toString());
    }

    @Override
    public String toString() {

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < accountNum.length; i++) {
            result.append(accountNum[i]);
        }
        return result.toString();
    }

    public boolean isIllegible() {

        for (int i = 0; i < accountNum.length; i++) {
            if(PipeUnderscoreNumber.ILLEGIBLE.equals(accountNum[i])) {
                return true;
            }
        }
        return false;
    }
    
    
    protected AccountNumber findAlternates() {

        return findAlts(i, null);
    }
    
    protected AccountNumber findSingleSubstitutionAlternate() {
        
        AccountNumber result;
//        AccountNumber[] alternates = new AccountNumber[20];
        AccountNumber prospective;
        
        for(int i = 0; i < accountNum.length; i++) {
            String[] alts = accountNum[i].getAlternates();
            prospective = new AccountNumber(accountNum.clone());    // have to clone it!
            if (alts != null) {
                for(int j = 0; j < alts.length; j++) {
                    prospective.accountNum[i] = PipeUnderscoreNumber.fromString(alts[j]);
                    if(prospective.isValid()) {
                        alternates[altIndex++] = prospective;
                    }
                }
            }
        }
        if(alternates[1] != null) {
            // found too many
            ambiguous = true;
            result = null;
        } else if(alternates[0] != null) {
            // found a good alternate
            result = alternates[0];
        } else {
            illegible = true;
            result = null;
        }
        return result;
    }
    

    /**
     *  This method recursively looks at all possible alternate numbers,
     *  allowing for multiple pipe or underscore replacements.
     *  
     *  The original requirement say:
     *  
     *      your next task is to look at numbers that have come back as ERR or ILL,
     *      and try to guess what they should be, by adding or removing just one pipe
     *      or underscore.
     *      
     *  Based on the use case examples, I believe this means just replace one pipe or
     *  underscore per account num.  That will be implemented in a different method.
     *
     */
    protected AccountNumber findAlts(int index, AccountNumber prospective) {
        
        // Terminating condition 1
        if(alternates[1] != null) {
            ambiguous = true;
            return null;
        }

        if(prospective != null && prospective.isValid()) {
            alternates[altIndex++] = prospective;
        }
        if(prospective == null) {
            prospective = new AccountNumber(this.accountNum);
        }
        if(index < 9 && prospective.accountNum[index].hasAlternates()) {
            String[] alts = prospective.accountNum[index].getAlternates();
            for(int i = 0; i < alts.length; i++) {
                prospective.accountNum[index] = PipeUnderscoreNumber.fromString(alts[i]);

                // call findAlts with incremented index and new prospective AccountNumber
                findAlts(index + 1, new AccountNumber(prospective.accountNum));
            }
        } else {
            findAlts(++i, new AccountNumber(prospective.accountNum));
        }
        
        return alternates[0];
    }
}
