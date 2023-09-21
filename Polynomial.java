public class Polynomial {
	double[] coefficients;
	
	public Polynomial() {
		this.coefficients = new double[1];
	}
	public Polynomial(double[] input) {
		this.coefficients = new double[input.length];
		for (int i = 0; i < input.length; i++) {
			coefficients[i] = input[i];
		}
	}
	public Polynomial add(Polynomial poly2) {
		int max = 0;
		if (this.coefficients.length >= poly2.coefficients.length) {
			max = this.coefficients.length;
		}
		else max = poly2.coefficients.length;
		double[] tmp = new double[max];
		
		for (int i = 0; i < max; i++) {
			if (i < this.coefficients.length && i < poly2.coefficients.length) {
				tmp[i] = this.coefficients[i] + poly2.coefficients[i];	
			}
			else if (this.coefficients.length > poly2.coefficients.length) {
				tmp[i] = this.coefficients[i];
			}
			else {
				tmp[i] = poly2.coefficients[i];
			}		
		}
		Polynomial sum = new Polynomial(tmp);
		return sum;
	}
	
	public double evaluate(double value) {
		double sum = 0;
		for (int i = 0; i < this.coefficients.length; i++) {
			double product = 1;
			for (int j = 0; j < i; j++) {
				product *= value;
			}
			sum += this.coefficients[i] * product;
		}
		return sum;
	}
	
	public boolean hasRoot(double potent_root) {
		return this.evaluate(potent_root) == 0;
	}
	
}