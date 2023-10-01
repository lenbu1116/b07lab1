import java.io.File;
import java.io.IOException;

public class Driver {
    public static void main(String[] args) throws IOException {
        // Testing empty case
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));

        // Testing add
        System.out.println("add");

        double[] c1 = {6, -2, 5};
        int[] e1 = {0, 1, 3};
        Polynomial p1 = new Polynomial(c1, e1);

        double[] c2 = {2, -1, 3, 2};
        int[] e2 = {0, 1, 2, 3};
        Polynomial p2 = new Polynomial(c2, e2);

        Polynomial s = p1.add(p2);

        System.out.println("Polynomial Add case 1");
        for (int i = 0; i < s.coefficients.length; i++) {
            System.out.println("Coeff = " + s.coefficients[i]);
            System.out.println("Exp = " + s.exponents[i]);
        }

        double[] bc1 = {5, 9, 10, 22};
        int[] be1 = {3, 7, 4, 23};
        Polynomial bp1 = new Polynomial(bc1, be1);

        double[] bc2 = {11, 3, 19, 17, -22};
        int[] be2 = {0, 1, 7, 13, 23};
        Polynomial bp2 = new Polynomial(bc2, be2);

        Polynomial bct = bp2.add(bp1);

        System.out.println("Polynomial Add case 2");
        for (int i = 0; i < bct.coefficients.length; i++) {
            System.out.println("Coeff = " + bct.coefficients[i]);
            System.out.println("Exp = " + bct.exponents[i]);
        }

        // Testing evaluate
        System.out.println("testing evaluate");
        System.out.println("s(1) = " + s.evaluate(1));
        System.out.println("s(2) = " + s.evaluate(2));
        System.out.println("bct(0) = " + bct.evaluate(0));
        System.out.println("bct(1) = " + bct.evaluate(1));
        System.out.println("bct(2) = " + bct.evaluate(2));

        // Testing multiply
        System.out.println("testing multiply");

        double[] c3 = {-1.0, 1.0};
        int[] e3 = {0, 1};
        Polynomial p3 = new Polynomial(c3, e3);

        double[] c4 = {-2.0, 1.0};
        int[] e4 = {0, 1};
        Polynomial p4 = new Polynomial(c4, e4);

        Polynomial s2 = p3.multiply(p4);

        System.out.println("Multiply 1");
        for (int i = 0; i < s2.coefficients.length; i++) {
            System.out.println("Coeff = " + s2.coefficients[i]);
            System.out.println("Exp = " + s2.exponents[i]);
        }

        double[] c5 = {7, 5, 11};
        int[] e5 = {0, 1, 2};
        Polynomial p5 = new Polynomial(c5, e5);

        double[] c6 = {2, 8, 7, 6};
        int[] e6 = {0, 1, 2, 3};
        Polynomial p6 = new Polynomial(c6, e6);

        Polynomial s3 = p6.multiply(p5);

        System.out.println("multiply 2");
        for (int i = 0; i < s3.coefficients.length; i++) {
            System.out.println("Coeff = " + s3.coefficients[i]);
            System.out.println("Exp = " + s3.exponents[i]);
        }

        double[] c8 = {6, -2, 4, 1};
        int[] e8 = {0, 1, 2, 3};
        Polynomial p8 = new Polynomial(c8, e8);

        double[] c9 = {7, 2};
        int[] e9 = {0, 1};
        Polynomial p9 = new Polynomial(c9, e9);

        Polynomial s10 = p8.multiply(p9);

        System.out.println("multiply 3");
        for (int i = 0; i < s10.coefficients.length; i++) {
            System.out.println("coeff = " + s10.coefficients[i]);
            System.out.println("exp = " + s10.exponents[i]);
        }

        // Testing hasRoot
        System.out.println("hasRoot");
        if (s2.hasRoot(2)) {
            System.out.println("2 is a root of s2");
        } 
        else {
            System.out.println("2 is not a root of s2");
        }
        if (s2.hasRoot(1)) {
            System.out.println("1 is a root of s2");
        } 
        else {
            System.out.println("1 is not a root of s2");
        }
        if (s2.hasRoot(0)) {
            System.out.println("0 is a root of s2");
        } 
        else {
            System.out.println("0 is not a root of s2");
        }
        if (s3.hasRoot(-2)) {
            System.out.println("-2 is a root of s3");
        } 
        else {
            System.out.println("-2 is not a root of s3");
        }
        if (s3.hasRoot(4)) {
            System.out.println("4 is a root of s3");
        } 
        else {
            System.out.println("4 is not a root of s3");
        }
    }
}
