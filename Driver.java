import java.io.File;

public class Driver {
	public static void main(String[] args) {
		System.out.println("Test the default constructor and evaluate method:");
		Polynomial p = new Polynomial();
		System.out.println("p(3) = " + p.evaluate(3));
		System.out.println();

		// Test the file constructor
		System.out.println("Test the file constructor:");
		File inputFile = new File("input.txt");
		p = new Polynomial(inputFile);
		for (int i = 0; i < p.coef.length; i++) {
			System.out.println("Coefficient: " + p.coef[i] + ", Exponent: " + p.expo[i]);
		}
		System.out.println();

		System.out.println("Test saveToFile method: check 'output.txt'");
		File outputFile = new File("output.txt");
		p.saveToFile(outputFile);
		System.out.println("Polynomial saved to 'output.txt'.");
		System.out.println();

		double[] c1 = {6, 5};
		int[] e1 = {0, 3};
		Polynomial p1 = new Polynomial(c1, e1);

		double[] c2 = {-2, -9};
		int[] e2 = {1, 4};
		Polynomial p2 = new Polynomial(c2, e2);

		System.out.println("Test the add method and evaluate method:");
		Polynomial s = p1.add(p2);
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		for (int i = 0; i < s.coef.length; i++) {
			System.out.println("Coefficient: " + s.coef[i] + ", Exponent: " + s.expo[i]);
		}
		System.out.println();

		System.out.println("Test the multiply method and evaluate method:");
		Polynomial m = p1.multiply(p2);
		System.out.println("m(0.1) = " + m.evaluate(0.1));
		for (int i = 0; i < m.coef.length; i++) {
			System.out.println("Coefficient: " + m.coef[i] + ", Exponent: " + m.expo[i]);
		}
		System.out.println();
		
		System.out.println("Test the hasRoot method:");
		if (s.hasRoot(1)) {
			System.out.println("1 is a root of s");
		} else {
			System.out.println("1 is not a root of s");
		}
	}
}
