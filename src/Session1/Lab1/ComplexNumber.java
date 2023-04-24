package Session1.Lab1;

////// EXERCISE 1 //////

import static java.lang.Math.abs;

public class ComplexNumber {

    private final double real;
    private final double imaginary;

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    // function to print real number
    public void showcomplexnumbers() {
        if (this.imaginary > 0)
            System.out.print(this.real + "+" + this.imaginary + "i");
        else if (this.imaginary < 0)
            System.out.print(this.real + "-" + abs(this.imaginary) + "i\n");
        else
            System.out.print(this.real);
    }

    //method to add the complex numbers
    public ComplexNumber add(ComplexNumber other) {
        return new ComplexNumber(this.real + other.real, this.imaginary + other.imaginary);
    }

    //method to multiply the complex numbers
    public ComplexNumber multiply(ComplexNumber other) {
        double realPart = this.real * other.real - this.imaginary * other.imaginary;
        double imaginaryPart = this.real * other.imaginary + this.imaginary * other.real;
        return new ComplexNumber(realPart, imaginaryPart);
    }

    public String toString() {
        if (this.imaginary >= 0) {
            return this.real + "+" + this.imaginary + "i";
        } else {
            return this.real + "" + this.imaginary + "i";
        }
    }


    public static void main(String[] args) {
        ComplexNumber c1 = new ComplexNumber(2, 5);
        ComplexNumber c2 = new ComplexNumber(4, -1);

        // printing complex numbers
        System.out.print("First Complex number: ");
        c1.showcomplexnumbers();

        System.out.print("\nSecond Complex number: ");
        c2.showcomplexnumbers();

        ComplexNumber sum = c1.add(c2);
        ComplexNumber product = c1.multiply(c2);

        System.out.println("The sum of the complex numbers is: " + sum);
        System.out.println("The product of the complex numbers is: " + product);
    }
}
