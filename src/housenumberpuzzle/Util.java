package housenumberpuzzle;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class Util {
	
	   public static int SQRT_DIG 			= 150;
       public static BigDecimal SQRT_PRE 	= new BigDecimal(10).pow(SQRT_DIG);

	   public static boolean verify(BigInteger x, BigInteger y)
	    {
	        BigInteger a = BigInteger.valueOf(2).multiply(x).multiply(x);
	        BigInteger b = y.multiply(y).add(y);
	        return a.equals(b);
	    }
	   
	   public static void increasePrecision(){
		   SQRT_DIG 	+=SQRT_DIG/2;
		   SQRT_PRE 	= new BigDecimal(10).pow(SQRT_DIG);
		   System.out.println("NOf digits increased to " +  SQRT_DIG);
	   }

	    /**
	     * Private utility method used to compute the square root of a BigDecimal.
	     * 
	     * @author Luciano Culacciatti 
	     * @url http://www.codeproject.com/Tips/257031/Implementing-SqrtRoot-in-BigDecimal
	     */
	    public static BigDecimal sqrtNewtonRaphson  (BigDecimal c, BigDecimal xn, BigDecimal precision){
	        BigDecimal fx = xn.pow(2).add(c.negate());
	        BigDecimal fpx = xn.multiply(new BigDecimal(2));
	        BigDecimal xn1 = fx.divide(fpx,2*SQRT_DIG,RoundingMode.HALF_DOWN);
	        xn1 = xn.add(xn1.negate());
	        BigDecimal currentSquare = xn1.pow(2);
	        BigDecimal currentPrecision = currentSquare.subtract(c);
	        currentPrecision = currentPrecision.abs();
	        if (currentPrecision.compareTo(precision) <= -1){
	            return xn1;
	        }
	        return sqrtNewtonRaphson(c, xn1, precision);
	    }

	    /**
	     * Uses Newton Raphson to compute the square root of a BigDecimal.
	     * 
	     * @author Luciano Culacciatti 
	     * @url http://www.codeproject.com/Tips/257031/Implementing-SqrtRoot-in-BigDecimal
	     */
	    public static BigDecimal bigSqrt(BigDecimal c){
	        return sqrtNewtonRaphson(c,new BigDecimal(1),new BigDecimal(1).divide(SQRT_PRE));
	    }
}
