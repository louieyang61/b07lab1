public class Polynomial{
    double[] coefficients;

    public Polynomial(){
        this.coefficients = new double[1];
    }

    public Polynomial(double[] B){
        int len = B.length;
        this.coefficients = new double[len];
        for (int i = 0; i < len; i++){
            this.coefficients[i] = B[i];
        }
    }

    public Polynomial add(Polynomial C){
        int len1 = this.coefficients.length;
        int len2 = C.coefficients.length;
        Polynomial ret;
        if (len1 > len2) {
            ret = new Polynomial(this.coefficients);
            for (int i = 0; i < len2; i++) {
                ret.coefficients[i] += C.coefficients[i];
            }
        }else{
            ret = new Polynomial(C.coefficients);
            for (int i = 0; i < len1; i++){
                ret.coefficients[i] += this.coefficients[i];
            }
        }
        return ret;
    }

    public double evaluate(double x){
        double ans = 0;
        for (int i = 0; i < this.coefficients.length; i++){
            ans = ans + this.coefficients[i]*(Math.pow(x,i));
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