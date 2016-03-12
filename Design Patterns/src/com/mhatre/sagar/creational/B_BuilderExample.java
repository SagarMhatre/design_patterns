package com.mhatre.sagar.creational;

class OuterClassWithLotsOfArgumentsInConstructorOrWithTooManySettersOrWithTelescopicConstructors {

	//Telescopic constructors = multiple constructors with different combinations of parameters
	
	private String mandatoryMemeberVariable1;
	private int mandatoryMemeberVariable2;
	private String optionalMemeberVariable1;
	private int optionalMemeberVariable2;

	// Define only getters for all the member variables and no setters.
	// The setters will be defined by the Builder inner class

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

	public static /* inner, but not mandatory. The builder can also be an external class */ class Builder
	{		
		//Add all the member variables from the Outer class
		
		private String mandatoryMemeberVariable1;
		private int mandatoryMemeberVariable2;
		private String optionalMemeberVariable1;
		private int optionalMemeberVariable2;

		public Builder(String mandatoryMemeberVariable1, int mandatoryMemeberVariable2) {
			this.mandatoryMemeberVariable1 = mandatoryMemeberVariable1;
			this.mandatoryMemeberVariable2 = mandatoryMemeberVariable2;
		}

		public Builder setOptionalMemeberVariable1(String optionalMemeberVariable1) {
			this.optionalMemeberVariable1 = optionalMemeberVariable1;
			return this;
		}

		public Builder setOptionalMemeberVariable2(int optionalMemeberVariable2) {
			this.optionalMemeberVariable2 = optionalMemeberVariable2;
			return this;
		}

		public OuterClassWithLotsOfArgumentsInConstructorOrWithTooManySettersOrWithTelescopicConstructors build() {			
			return new OuterClassWithLotsOfArgumentsInConstructorOrWithTooManySettersOrWithTelescopicConstructors(this);
		}
	}

	// Define a single constructor with Builder as the parameter
	public OuterClassWithLotsOfArgumentsInConstructorOrWithTooManySettersOrWithTelescopicConstructors(Builder builder) {
		this.mandatoryMemeberVariable1 = builder.mandatoryMemeberVariable1;
		this.mandatoryMemeberVariable2 = builder.mandatoryMemeberVariable2;
		this.optionalMemeberVariable1 = builder.optionalMemeberVariable1;
		this.optionalMemeberVariable2 = builder.optionalMemeberVariable2;
	}
}

public class B_BuilderExample {

	public static void main(String[] args) {
		OuterClassWithLotsOfArgumentsInConstructorOrWithTooManySettersOrWithTelescopicConstructors.Builder b 
		= new OuterClassWithLotsOfArgumentsInConstructorOrWithTooManySettersOrWithTelescopicConstructors.Builder(
				"mandatoryMemeberVariable1", 0);
		
		OuterClassWithLotsOfArgumentsInConstructorOrWithTooManySettersOrWithTelescopicConstructors
		o1 = b.build();
		
		OuterClassWithLotsOfArgumentsInConstructorOrWithTooManySettersOrWithTelescopicConstructors
		o2 = b.setOptionalMemeberVariable1("optionalMemeberVariable1").build();
		
		OuterClassWithLotsOfArgumentsInConstructorOrWithTooManySettersOrWithTelescopicConstructors
		o3 = b.setOptionalMemeberVariable2(2).build();
		
		OuterClassWithLotsOfArgumentsInConstructorOrWithTooManySettersOrWithTelescopicConstructors
		o4 = b.setOptionalMemeberVariable1("optionalMemeberVariable1").setOptionalMemeberVariable2(2).build();
	}
}