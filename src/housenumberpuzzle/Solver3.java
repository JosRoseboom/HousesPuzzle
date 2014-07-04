package housenumberpuzzle;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Solver3 {
	
    private final static BigDecimal minusPHalf 	= BigDecimal.valueOf(-0.5);
    private final static BigDecimal pHalfPow2 	= BigDecimal.valueOf(0.25);
     
	public static void main(String[] args) {
		long startTime			=	System.currentTimeMillis();
	    BigInteger SIX 			= BigInteger.valueOf(6);
        BigInteger houseNumber0 = BigInteger.valueOf(1);
        BigInteger HouseNumber1 = BigInteger.valueOf(6);
        
        computeHousesBasedOnHouseNumber(HouseNumber1, 1);
        
        for (int i=0; i<19; i++)
        {
            BigInteger houseNumber = HouseNumber1.multiply(SIX);
            houseNumber = houseNumber.subtract(houseNumber0);
            computeHousesBasedOnHouseNumber(houseNumber, i+2);
            houseNumber0 = HouseNumber1;
            HouseNumber1 = houseNumber;
        }
        System.out.println("Time taken:" + (System.currentTimeMillis() - startTime) + "ms.");
	}
	
	/**
	 * uses m		= -0.5 + √(0.25 + 2h²)
	 * @param houseNumber house number you live in
	 * @return total number of houses 
	 */
	public static void computeHousesBasedOnHouseNumber(BigInteger houseNumber, int solutionIndex){
        BigInteger houseNumberSquare 		= houseNumber.multiply(houseNumber);
        BigInteger doubleHouseNumberSquare 	= houseNumberSquare.add(houseNumberSquare);
        BigDecimal sqrtTerm					= pHalfPow2.add(new BigDecimal(doubleHouseNumberSquare.toString(10)));
        BigInteger houses 					= minusPHalf.add(Util.bigSqrt(sqrtTerm)).toBigInteger();
        System.out.println("Solution " + solutionIndex + ": (" + houseNumber + ", "+houses+")");
	}
}