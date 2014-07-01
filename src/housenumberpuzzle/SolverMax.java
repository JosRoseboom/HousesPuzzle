package housenumberpuzzle;

public class SolverMax implements Runnable{
	
	private static int nOfThreads;
	private static long startTime;
	private static boolean foundSolution;
	private double startValue;
	
	public static void main(String[] args) {
		startTime				=	System.currentTimeMillis();
		nOfThreads				=	4;
		foundSolution			=	false;
		double maxPossibleValue	=	Math.floor(Math.sqrt((Double.MAX_VALUE - 0.25)/2));
		
		System.out.println(	"The max possible value with primitive double is: " + maxPossibleValue);
		
		for(int i = 0;i<nOfThreads;i++)
			new Thread(new SolverMax(maxPossibleValue-i)).start();		
	}
	
	public SolverMax(double startValue){
		this.startValue	=	startValue;
	}

	@Override
	public void run() {
		for(double candidate = startValue; !foundSolution; candidate-=nOfThreads){
			double sqrtDiscriminant	=	Math.sqrt(2 * candidate * candidate + 0.25);
			if(sqrtDiscriminant%1 == 0.5){
				foundSolution = true;
				System.out.println(	"Solution: (" + ((long)candidate) + "," + ((long)(sqrtDiscriminant -0.5)) + ")");
				System.out.println("Time taken:" + ((System.currentTimeMillis() - startTime) * 0.001) + " seconds.");
			}
		}		
	}
}