package housenumberpuzzle;

import java.math.BigDecimal;

public class Solver2 implements Runnable{
	
	private static int solutionsFound;
	private static long nextHousesStart;
	private static long startTime;
	
	public static void main(String[] args) {
		startTime			=	System.currentTimeMillis();
		solutionsFound		=	0;
		nextHousesStart		=	2;
		int nOfThreads		=	4;
		
		for(int i = 0;i<nOfThreads;i++)
			new Thread(new Solver2()).start();
	}
	
	public void run(){
		while(solutionsFound < 20){
			long houses, nextHouses;
			synchronized (this.getClass()) {
				houses			=	nextHousesStart;
				nextHouses		=	houses + (long)1E11;
				nextHousesStart	=	nextHouses;
			}
			long homeNumber			= 	(long)(Math.sqrt(0.5 * houses) * Math.sqrt(houses + 1)); // make a nice guess
			long leftMinRight		=	BigDecimal.valueOf(homeNumber -1).multiply(BigDecimal.valueOf(0.5 * homeNumber))
											.subtract(
													BigDecimal.valueOf(houses - homeNumber).multiply(BigDecimal.valueOf(0.5 * (houses + homeNumber + 1)))
													).longValue();
			
			while(houses<nextHouses){
				if(leftMinRight > 0){
					houses++;
					leftMinRight -= houses;
				}else if(leftMinRight < 0){
					leftMinRight += homeNumber;
					homeNumber++;
					leftMinRight += homeNumber;
				}else {
					synchronized (this.getClass()) {
						solutionsFound++;
						System.out.println(  "Solution " + solutionsFound + ": (" + homeNumber + ", " + houses + ") in "+ ((System.currentTimeMillis() - startTime) * 0.001) + " seconds.");
					}
					leftMinRight += homeNumber;
					homeNumber++;
					leftMinRight += homeNumber;
				}
			}			
		}

	}
}