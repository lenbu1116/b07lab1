import java.io.*;
import java.util.Arrays;

public class Polynomial {

    public double[] coefficients;
    public int[] exponents;

    public Polynomial() {
        this.coefficients = new double[]{0};
        this.exponents = new int[]{0};
    }

    public Polynomial(double[] coefficients, int[] exponents) {
        this.coefficients = coefficients;
        this.exponents = exponents;
    }

    public Polynomial(File polyFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(polyFile));
        String textPoly = reader.readLine();
        reader.close();

        textPoly = textPoly.replace("+", " +").replace("-", " -");
        String[] individualTerms = textPoly.split("\\s+");
        int totalTerms = individualTerms.length;

        double[] coefficient = new double[totalTerms];
        int[] exponent = new int[totalTerms];

        for (int i = 0; i < totalTerms; i++) {
            String term = individualTerms[i];
            String[] coeffAndExp = term.split("x");
            int xCount = (int) term.chars().filter(ch -> ch == 'x').count();

            if (xCount == 0) {
                coefficient[i] = Double.parseDouble(term);
                exponent[i] = 0;
            } else {
                coefficient[i] = Double.parseDouble(coeffAndExp[0]);
                exponent[i] = Integer.parseInt(coeffAndExp[1]);
            }
        }

        this.coefficients = coefficient;
        this.exponents = exponent;
    }

    public Polynomial add(Polynomial argPoly) {
        int totalLength = this.coefficients.length + argPoly.coefficients.length;
        double[] newCoeff = new double[totalLength];
        int[] newExp = new int[totalLength];

        System.arraycopy(this.coefficients, 0, newCoeff, 0, this.coefficients.length);
        System.arraycopy(this.exponents, 0, newExp, 0, this.exponents.length);

        int newIndex = this.coefficients.length;

        for (int i = 0; i < argPoly.coefficients.length; i++) {
            boolean foundMatch = false;

            for (int j = 0; j < this.coefficients.length; j++) {
                if (this.exponents[j] == argPoly.exponents[i]) {
                    foundMatch = true;
                    newCoeff[j] += argPoly.coefficients[i];
                    break;
                }
            }

            if (!foundMatch) {
                newCoeff[newIndex] = argPoly.coefficients[i];
                newExp[newIndex] = argPoly.exponents[i];
                newIndex++;
            }
        }

        sortCoefficientsAndExponents(newCoeff, newExp);

        int nonZeroCount = countNonZeroCoefficients(newCoeff);
        double[] nonZeroCoeff = Arrays.copyOfRange(newCoeff, 0, nonZeroCount);
        int[] nonZeroExp = Arrays.copyOfRange(newExp, 0, nonZeroCount);

        return new Polynomial(nonZeroCoeff, nonZeroExp);
    }

    public double evaluate(double x) {
        double result = 0.0;

        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * Math.pow(x, exponents[i]);
        }

        return result;
    }

    public boolean hasRoot(double x) {
        return evaluate(x) == 0;
    }

    public Polynomial multiply(Polynomial multiplier) {
        int total = this.coefficients.length + multiplier.exponents.length - 1;
        double[] mCoeff = new double[total];
        int[] mExp = new int[total];

        for (int i = 0; i < this.coefficients.length; i++) {
            for (int j = 0; j < multiplier.coefficients.length; j++) {
                mCoeff[i + j] += this.coefficients[i] * multiplier.coefficients[j];
                mExp[i + j] = this.exponents[i] + multiplier.exponents[j];
            }
        }

        sortCoefficientsAndExponents(mCoeff, mExp);

        int nonZeroCount = countNonZeroCoefficients(mCoeff);
        double[] nonZeroCoeff = Arrays.copyOfRange(mCoeff, 0, nonZeroCount);
        int[] nonZeroExp = Arrays.copyOfRange(mExp, 0, nonZeroCount);

        return new Polynomial(nonZeroCoeff, nonZeroExp);
    }

    public void saveToFile(String polyFile) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(polyFile));

        for (int i = 0; i < coefficients.length; i++) {
            if (i == 0) {
                if (coefficients[i] > 0) {
                    writer.write(Double.toString(coefficients[i]));
                    if (exponents[i] != 0) {
                        writer.write('x' + Integer.toString(exponents[i]));
                    }
                } else {
                    writer.write(Double.toString(coefficients[i]));
                    if (exponents[i] != 0) {
                        writer.write('x' + Integer.toString(exponents[i]));
                    }
                }
            } else {
                if (coefficients[i] > 0) {
                    writer.write("+" + Double.toString(coefficients[i]));
                    writer.write('x' + Integer.toString(exponents[i]));
                } else {
                    writer.write(Double.toString(coefficients[i]));
                    writer.write('x' + Integer.toString(exponents[i]));
                }
            }
        }
        writer.close();
    }

    public void sortCoefficientsAndExponents(double[] coeff, int[] exp) {
        boolean swapped;
        int n = coeff.length;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (exp[j] > exp[j + 1]) {
                    swap(coeff, j, j + 1);
                    swap(exp, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    public void swap(double[] arr, int i, int j) {
        double temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public int countNonZeroCoefficients(double[] coeff) {
        int count = 0;
        for (double value : coeff) {
            if (value != 0) {
                count++;
            }
        }
        return count;
    }
}
