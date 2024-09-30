import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Polynomial {
    double[] coef;
    int[] expo;


    public Polynomial() {
        this.coef = new double[]{0};
        this.expo = new int[]{0};
    }

    // Constructor that initializes polynomial with given coefficients and exponents
    public Polynomial(double[] b, int[] c) {
        int length = b.length;
        this.coef = new double[length];
        this.expo = new int[length];
        for (int i = 0; i < length; i++) {
            this.coef[i] = b[i];
            this.expo[i] = c[i];
        }
    }

    public Polynomial(File f) {
        try (Scanner s = new Scanner(f)) {
            if (s.hasNextLine()) {
                String line = s.nextLine();
                String[] parts = line.split("(?=[+-])");
                int l = parts.length;

                this.coef = new double[l];
                this.expo = new int[l];

                for (int i = 0; i < l; i++) {
                    String term = parts[i].trim();
                    double coefficient = 1.0;
                    int exponent = 0;

                    // Handle sign
                    if (term.startsWith("+")) {
                        term = term.substring(1);
                    } else if (term.startsWith("-")) {
                        coefficient = -1.0;
                        term = term.substring(1);
                    }

                    if (term.contains("x")) {
                        String[] temp = term.split("x");
                        if (!temp[0].isEmpty()) {
                            coefficient *= Double.parseDouble(temp[0]);
                        }
                        if (temp.length > 1 && !temp[1].isEmpty()) {
                            exponent = Integer.parseInt(temp[1]);
                        } else {
                            exponent = 1;
                        }
                    } else {
                        coefficient *= Double.parseDouble(term);
                        exponent = 0;
                    }

                    this.coef[i] = coefficient;
                    this.expo[i] = exponent;
                }
            } else {
                this.coef = new double[]{0};
                this.expo = new int[]{0};
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + f.getName());
            // Initialize to zero polynomial to prevent NullPointerException
            this.coef = new double[]{0};
            this.expo = new int[]{0};
        }
    }

    public void saveToFile(File f) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.coef.length; i++) {
            double coefficient = this.coef[i];
            int exponent = this.expo[i];

            if (coefficient >= 0 && sb.length() > 0) {
                sb.append("+");
            }
            if (exponent == 0) {
                // Constant term
                sb.append(coefficient);
            } else {
                if (coefficient == 1.0) {
                    sb.append("x");
                } else if (coefficient == -1.0) {
                    sb.append("-x");
                } else {
                    sb.append(coefficient).append("x");
                }
                if (exponent != 1) {
                    sb.append(exponent);
                }
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(f))) {
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Polynomial add(Polynomial p) {
        int length1 = this.coef.length;
        int length2 = p.coef.length;
        double[] c = new double[length1 + length2];
        int[] e = new int[length1 + length2];
        int i = 0, j = 0, k = 0;

        while (i < length1 && j < length2) {
            if (this.expo[i] == p.expo[j]) {
                c[k] = this.coef[i] + p.coef[j];
                e[k] = this.expo[i];
                i++;
                j++;
            } else if (this.expo[i] < p.expo[j]) {
                c[k] = this.coef[i];
                e[k] = this.expo[i];
                i++;
            } else {
                c[k] = p.coef[j];
                e[k] = p.expo[j];
                j++;
            }
            k++;
        }

        while (i < length1) {
            c[k] = this.coef[i];
            e[k] = this.expo[i];
            i++;
            k++;
        }

        while (j < length2) {
            c[k] = p.coef[j];
            e[k] = p.expo[j];
            j++;
            k++;
        }
        
        int nonZeroCount = 0;
        for (int m = 0; m < k; m++) {
            if (c[m] != 0) {
                nonZeroCount++;
            }
        }
        double[] finalC = new double[nonZeroCount];
        int[] finalE = new int[nonZeroCount];
        int index = 0;
        for (int m = 0; m < k; m++) {
            if (c[m] != 0) {
                finalC[index] = c[m];
                finalE[index] = e[m];
                index++;
            }
        }

        return new Polynomial(finalC, finalE);
    }

    public Polynomial multiply(Polynomial p) {
        int maxExponent = 0;
        for (int ex1 : this.expo) {
            for (int ex2 : p.expo) {
                int sumEx = ex1 + ex2;
                if (sumEx > maxExponent) {
                    maxExponent = sumEx;
                }
            }
        }

        double[] tempCoef = new double[maxExponent + 1];

        for (int i = 0; i < this.coef.length; i++) {
            for (int j = 0; j < p.coef.length; j++) {
                int exponentSum = this.expo[i] + p.expo[j];
                tempCoef[exponentSum] += this.coef[i] * p.coef[j];
            }
        }

        int nonZeroCount = 0;
        for (double coef : tempCoef) {
            if (coef != 0) {
                nonZeroCount++;
            }
        }

        double[] finalCoef = new double[nonZeroCount];
        int[] finalExpo = new int[nonZeroCount];
        int index = 0;

        for (int i = 0; i < tempCoef.length; i++) {
            if (tempCoef[i] != 0) {
                finalCoef[index] = tempCoef[i];
                finalExpo[index] = i;
                index++;
            }
        }

        return new Polynomial(finalCoef, finalExpo);
    }

    public double evaluate(double x){
        double ans = 0;
        for (int i = 0; i < this.coef.length; i++){
            ans = ans + this.coef[i]*(Math.pow(x,i));
        }
        return ans;
    }

    public boolean hasRoot(double x){
        if (evaluate(x) == 0)
            return true;
        else
            return false;
    }
}
