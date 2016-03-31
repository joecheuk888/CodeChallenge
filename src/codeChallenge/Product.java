package codeChallenge;

public class Product {
	String productName;
	String manufacturer;
	String model;
	String family;
	String date;

	public Product(String pname, String manu, String model, String fam, String date){
		this.productName = pname;
		this.manufacturer = manu;
		this.model = model;
		this.family = fam;
		this.date = date;
	}
	
	public Product(String pname, String manu, String model, String date){
		this.productName = pname;
		this.manufacturer = manu;
		this.model = model;
		this.date = date;
	}
	
	public String getManu(){
		return this.manufacturer;
	}
	
	public String getModel(){
		return this.model;
	}
	
	public String getName(){
		return this.productName;
	}
}
