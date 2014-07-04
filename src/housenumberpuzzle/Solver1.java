package housenumberpuzzle;

import java.math.BigDecimal;
import java.math.MathContext;


public class Solver1 implements Runnable{

	private final static BigDecimal TWO	=	new BigDecimal("2");
	
	private static int nOfThreads;
	private static long startTime;
	private static int solutionsFound;
	private static int candidateSolutions;
	private static double mValueOf20thSolution;
	private int threadNo;
	
	public static void main(String[] args) {
		startTime				=	System.currentTimeMillis();
		nOfThreads				=	4;
		solutionsFound			=	0;
		mValueOf20thSolution	=	Double.POSITIVE_INFINITY;
		
		for(int i = 0;i<nOfThreads;i++)
			new Thread(new Solver1(i)).start();		
	}
	
	public Solver1(int threadNo){
		this.threadNo	=	threadNo;
	}
	
	@Override
	public void run() {
		for(double candidateM = (2 + threadNo); candidateM < mValueOf20thSolution; candidateM+=(nOfThreads)){
			double sqrtHalfMRaw		=	Math.sqrt(0.5 * candidateM);
			double sqrtMPlus1Raw	=	Math.sqrt(candidateM+1);
			
			double sqrtHalfMUlp		=	Math.ulp(sqrtHalfMRaw);
			double sqrtMPlus1Ulp	=	Math.ulp(sqrtMPlus1Raw);
			
			double sqrtHalfMBottom	=	sqrtHalfMRaw - sqrtHalfMUlp;
			double sqrtHalfMTop		=	sqrtHalfMRaw + sqrtHalfMUlp;
			double sqrtMPlus1Bottom	=	sqrtMPlus1Raw - sqrtMPlus1Ulp;
			double sqrtMPlus1Top	=	sqrtMPlus1Raw + sqrtMPlus1Ulp;
			
			double candidateH		=	Math.floor(sqrtHalfMTop * sqrtMPlus1Top);
			
			if(sqrtHalfMBottom * sqrtMPlus1Bottom <= candidateH){
				synchronized(this.getClass()){
					candidateSolutions++;
				}				
				BigDecimal candidateHBD	=	BigDecimal.valueOf(candidateH);
				BigDecimal candidateMBD	=	BigDecimal.valueOf(candidateM);
				BigDecimal squareH		=	candidateHBD.multiply(candidateHBD, MathContext.UNLIMITED);
				BigDecimal mPart		=	candidateMBD.multiply(candidateMBD, MathContext.UNLIMITED).add(candidateMBD, MathContext.UNLIMITED).divide(TWO, MathContext.UNLIMITED);
		        if(squareH.compareTo(mPart) == 0){
		        	synchronized(this.getClass()){
						solutionsFound++;
						if(solutionsFound == 20)
							mValueOf20thSolution = candidateM; // question is first 20, maybe this thread finds a later solution before first 20 are found
						System.out.println(  "Thread " + threadNo + " found solution " + solutionsFound + ": (" + (long)candidateH 
				                 	+ "," + (long)(candidateM) + "). Seen " + candidateSolutions + " candidate solutions so far. Time taken:" 
				                 	+ ((System.currentTimeMillis() - startTime)/1000) + " seconds");
					}		         
		        }
		     }
			}
	}	
}