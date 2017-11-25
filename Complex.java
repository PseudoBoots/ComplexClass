/*
* @author Victor Bieniek
* This class can hold a complex number and do basic mathmatical operations with it
*/
import java.util.Scanner;
import java.math.BigDecimal;

public class Complex
{
	private double real;
	private double imag;
	private boolean invalid;
	
	public Complex(double r, double i)
	{
		this.real = r;
		this.imag = i;
		this.invalid = false;
	}
	public Complex()
	{
		this.real = 0;
		this.imag = 0;
		this.invalid = true;
	}
	
	@Override
	public String toString()
	{
		if(this.invalid)
		{
			return "Not Valid";
		}
		return String.format("%.2f %s %.2fi", this.real, 
				(this.imag >= 0) ? "+" : "-", 
				(this.imag >= 0) ? this.imag : this.imag * -1);
	}
	
	public double getReal()
	{
		return this.real;
	}
	
	public double getImaginary()
	{
		return this.imag;
	}
	
	public Complex add(Complex c)
	{
		return new Complex(this.real + c.getReal(), this.imag + c.getImaginary());
	}

	public Complex sub(Complex c)
	{
		return new Complex(this.real - c.getReal(), this.imag - c.getImaginary());
	}

	public Complex mult(Complex c)
	{
		double newReal = (this.real * c.getReal()) - (this.imag * c.getImaginary());
		double newImag = (this.real * c.getImaginary()) + (this.imag * c.getReal());
		return new Complex(newReal, newImag);
	}

	public Complex div(Complex c)
	{
		if(c.getReal() + c.getImaginary() == 0)
		{
			//this returns an invalid Complex number because you cannot divide by zero
			return new Complex();
		}
		double newReal = (this.real * c.getReal() + this.imag * c.getImaginary()) / (c.getReal() * c.getReal() + c.getImaginary() * c.getImaginary());
		double newImag = (this.imag * c.getReal() - this.real * c.getImaginary()) / (c.getReal() * c.getReal() + c.getImaginary() * c.getImaginary());
		return new Complex(newReal, newImag);
	}

	private static double askAndParseDouble(String question)
	{
		Scanner scan = new Scanner(System.in);
		String ans;

		System.out.print(question);
		ans = scan.next();
		try{
			if((new BigDecimal(ans)).compareTo(BigDecimal.valueOf(Double.MAX_VALUE)) > 0)
			{
				throw new Exception("Value too large");
			}
			return Double.parseDouble(ans);
		} catch(Exception e){
			System.out.println("Invalid format, please enter a valid number");
			return askAndParseDouble(question);
		}
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Complex com1;
		Complex com2;
		double realTemp;
		double imagTemp;

		//TODO: validate input
		realTemp = askAndParseDouble("Please enter first real number: ");
		imagTemp = askAndParseDouble("Please enter first imaginary number: ");
		com1 = new Complex(realTemp, imagTemp);
		realTemp = askAndParseDouble("Please enter second real number: ");
		imagTemp = askAndParseDouble("Please enter second imaginary number: ");
		com2 = new Complex(realTemp, imagTemp);

		System.out.printf("%s plus %s is %s\n", com1.toString(), com2.toString(), com1.add(com2).toString());
		System.out.printf("%s minus %s is %s\n", com1.toString(), com2.toString(), com1.sub(com2).toString());
		System.out.printf("%s times %s is %s\n", com1.toString(), com2.toString(), com1.mult(com2).toString());
		System.out.printf("%s divided by %s is %s\n", com1.toString(), com2.toString(), com1.div(com2).toString());
	}
}//end of class
