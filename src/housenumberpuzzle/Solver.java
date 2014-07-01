package housenumberpuzzle;

public class Solver {

	public static void main(String[] args) {
		int solutionsFound	=	0;
		long startTime		=	System.currentTimeMillis();
		for(double candidate = 2.0; solutionsFound < 20; candidate++){
			double sqrtDiscriminant	=	Math.sqrt(2 * candidate * candidate + 0.25);
			if(sqrtDiscriminant%1 == 0.5){
				solutionsFound++;
				System.out.println("Solution " + solutionsFound + ": (" + ((long)candidate) + "," + ((long)(sqrtDiscriminant -0.5)) + ")");
			}
		}
		System.out.println("Time taken:" + ((System.currentTimeMillis() - startTime) * 0.001) + " seconds.");
	}
}