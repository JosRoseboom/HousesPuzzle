package housenumberpuzzle;

import java.math.BigDecimal;
import java.math.BigInteger;

public class SolverMax {
	private final static BigDecimal TWO					=	new BigDecimal(2);
	private final static BigDecimal THREE				=	new BigDecimal(3);
	private final static BigDecimal FOUR				=	new BigDecimal(4);
	private static BigDecimal THREEMINUSTWOSQRT2;
	private static BigDecimal THREEPLUSWOSQRT2;
	private static BigDecimal FOURSQRTTWO;
	
	public static void main(String[] args) {
		boolean valid			=	true;
		//int power				=	Math.min(999999999, Integer.MAX_VALUE/THREEMINUSTWOSQRT2.scale());
		int power				=	1000;
		initNumbers();
		while(true){
			long startTime			=	System.currentTimeMillis();
			BigDecimal minusPower	=	THREEMINUSTWOSQRT2.pow(power);
			BigDecimal plusPower	=	THREEPLUSWOSQRT2.pow(power);			
			BigInteger houseNumber	=	minusPower.subtract(plusPower).divide(FOURSQRTTWO).negate().toBigInteger();
			BigInteger houses		=	minusPower.add(plusPower).subtract(TWO).divide(FOUR).toBigInteger();
			valid					=	Util.verify(houseNumber, houses);
			if(valid){
				System.out.println("power: " + power + " correct? "+valid + " "  + houseNumber + ", " + houses);
				System.out.println("Time taken:" + (long)((System.currentTimeMillis()- startTime)/1000 + 0.5) + " seconds.");
				power *= 1.1;
			} else{
				Util.increasePrecision();
				initNumbers();
			}
		}
	}
	
	private static void initNumbers(){
		BigDecimal TWOSQRT2		=	TWO.multiply(Util.bigSqrt(TWO));
		THREEMINUSTWOSQRT2		=	THREE.subtract(TWOSQRT2);
		THREEPLUSWOSQRT2		=	THREE.add(TWOSQRT2);
		FOURSQRTTWO				=	TWO.multiply(TWOSQRT2);
	}
}