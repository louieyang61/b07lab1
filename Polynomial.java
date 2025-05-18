public class Polynomial {
    double[] coefficients;

    public Polynomial() {
        this.coefficients = new double[1];
    }

    public Polynomial(double[] inputCoefficients) {
        this.coefficients = inputCoefficients;
    }

    public Polynomial add(Polynomial other) {
        int thisDegree = this.coefficients.length;
        int otherDegree = other.coefficients.length;

        if (thisDegree < otherDegree) {
            double[] newCoefficients = new double[otherDegree];

            for (int i = 0; i < thisDegree; i++) {
                newCoefficients[i] = other.coefficients[i] + this.coefficients[i];
            }

            for (int i = thisDegree; i < otherDegree; i++) {
                newCoefficients[i] = other.coefficients[i];
            }

            return new Polynomial(newCoefficients);
        }

        if (thisDegree == otherDegree) {
            double[] newCoefficients = new double[thisDegree];

            for (int i = 0; i < thisDegree; i++) {
                newCoefficients[i] = other.coefficients[i] + this.coefficients[i];
            }

            return new Polynomial(newCoefficients);
        }

        double[] newCoefficients = new double[thisDegree];

        for (int i = 0; i < otherDegree; i++) {
            newCoefficients[i] = other.coefficients[i] + this.coefficients[i];
        }

        for (int i = otherDegree; i < thisDegree; i++) {
            newCoefficients[i] = this.coefficients[i];
        }

        return new Polynomial(newCoefficients);
    }

    public double evaluate(double x) {
        int degree = this.coefficients.length;
        double result = 0.0;

        for (int i = 0; i < degree; i++) {
            result += this.coefficients[i] * Math.pow(x, i);
        }

        return result;
    }

    public boolean hasRoot(double x) {
        return evaluate(x) == 0.0;
    }
}
