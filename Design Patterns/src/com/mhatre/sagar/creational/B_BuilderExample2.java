package com.mhatre.sagar.creational;

public class B_BuilderExample2 {

	public static void main(String[] args) {
		OuterClassWithLotsOfArgumentsInConstructorOrWithTooManySettersOrWithTelescopicConstructors2.Builder b 
		= new OuterClassWithLotsOfArgumentsInConstructorOrWithTooManySettersOrWithTelescopicConstructors2.Builder(
				"mandatoryMemeberVariable1", 0);
		
		OuterClassWithLotsOfArgumentsInConstructorOrWithTooManySettersOrWithTelescopicConstructors2
		o1 = b.build();
		
		OuterClassWithLotsOfArgumentsInConstructorOrWithTooManySettersOrWithTelescopicConstructors2
		o2 = b.setOptionalMemeberVariable1("optionalMemeberVariable1").build();
		
		OuterClassWithLotsOfArgumentsInConstructorOrWithTooManySettersOrWithTelescopicConstructors2
		o3 = b.setOptionalMemeberVariable2(2).build();
		
		OuterClassWithLotsOfArgumentsInConstructorOrWithTooManySettersOrWithTelescopicConstructors2
		o4 = b.setOptionalMemeberVariable1("optionalMemeberVariable1").setOptionalMemeberVariable2(2).build();
		
		
		
		
	}

}

class OuterClassWithLotsOfArgumentsInConstructorOrWithTooManySettersOrWithTelescopicConstructors2 {

	private String mandatoryMemeberVariable1;
	private int mandatoryMemeberVariable2;
	
	// Since our Builder class with extend this class, the optional variables
	// will need to be protected so the t the builder can expose their setters
	protected String optionalMemeberVariable1;
	protected int optionalMemeberVariable2;

	// Define only getters for all the member variables and no setters.

	public String getMandatoryMemeberVariable1() {
		return mandatoryMemeberVariable1;
	}
	public int getMandatoryMemeberVariable2() {
		return mandatoryMemeberVariable2;
	}
	public String getOptionalMemeberVariable1() {
		return optionalMemeberVariable1;
	}
	public int getOptionalMemeberVariable2() {
		return optionalMemeberVariable2;
	}

	public static /* inner */ class Builder
	extends
	OuterClassWithLotsOfArgumentsInConstructorOrWithTooManySettersOrWithTelescopicConstructors2
	{
		public Builder(String mandatoryMemeberVariable1, int mandatoryMemeberVariable2) {
			super(mandatoryMemeberVariable1 , mandatoryMemeberVariable2);
		}

		public Builder setOptionalMemeberVariable1(String optionalMemeberVariable1) {
			this.optionalMemeberVariable1 = optionalMemeberVariable1;
			return this;
		}

		public Builder setOptionalMemeberVariable2(int optionalMemeberVariable2) {
			this.optionalMemeberVariable2 = optionalMemeberVariable2;
			return this;
		}

		public OuterClassWithLotsOfArgumentsInConstructorOrWithTooManySettersOrWithTelescopicConstructors2 build() {
			return new OuterClassWithLotsOfArgumentsInConstructorOrWithTooManySettersOrWithTelescopicConstructors2(this);
		}
	}

	// Define a constructor with Builder as the parameter 
	public OuterClassWithLotsOfArgumentsInConstructorOrWithTooManySettersOrWithTelescopicConstructors2(Builder builder) {
		this.optionalMemeberVariable1 = builder.optionalMemeberVariable1;
		this.optionalMemeberVariable2 = builder.optionalMemeberVariable2;
	}
	
	private  OuterClassWithLotsOfArgumentsInConstructorOrWithTooManySettersOrWithTelescopicConstructors2(String mandatoryMemeberVariable1, int mandatoryMemeberVariable2) {
		this.mandatoryMemeberVariable1 = mandatoryMemeberVariable1;
		this.mandatoryMemeberVariable2 = mandatoryMemeberVariable2;
	}

}