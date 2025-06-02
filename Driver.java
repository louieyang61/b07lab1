import java.io.File;

public class Driver {
	public static void main(String[] args) {
		// Create an empty polynomial and print its evaluation at x = 3
		Polynomial emptyPolynomial = new Polynomial();
		System.out.println(emptyPolynomial.evaluate(3));  // Expect 0.0

		// Build first polynomial: 6 + 5x^3
		double[] coefficients1 = { 6.0, 5.0 };
		int[] exponents1 = { 0, 3 };
		Polynomial poly1 = new Polynomial(coefficients1, exponents1);

		// Build second polynomial: -2x - 9x^4
		double[] coefficients2 = { -2.0, -9.0 };
		int[] exponents2 = { 1, 4 };
		Polynomial poly2 = new Polynomial(coefficients2, exponents2);

		// Add poly1 and poly2 → s(x)
		Polynomial sumPoly = poly1.add(poly2);
		System.out.println("s(0.1) = " + sumPoly.evaluate(0.1));

		// Check if x = 1 is a root of s(x)
		if (sumPoly.hasRoot(1.0)) {
			System.out.println("1 is a root of s");
		} else {
			System.out.println("1 is not a root of s");
		}

		// Multiply poly1 by poly2 → t(x)
		Polynomial productPoly = poly1.multiply(poly2);
		System.out.println("t(1) = " + productPoly.evaluate(1.0));
		System.out.println("t(2) = " + productPoly.evaluate(2.0));
		System.out.println("t(0) = " + productPoly.evaluate(0.0));

		// Save poly1 to a file "poly1.txt", then read it back into a new Polynomial
		String outputFilename = "poly1.txt";
		poly1.saveToFile(outputFilename);

		File savedFile = new File(outputFilename);
		Polynomial loadedPoly = new Polynomial(savedFile);

		// Add the loaded polynomial to poly2 → s2(x)
		Polynomial sumWithLoaded = loadedPoly.add(poly2);
		System.out.println("s2(0.1) = " + sumWithLoaded.evaluate(0.1));

		// Check if x = 1 is a root of s2(x)
		if (sumWithLoaded.hasRoot(1.0)) {
			System.out.println("1 is a root of s2");
		} else {
			System.out.println("1 is not a root of s2");
		}
	}
}
