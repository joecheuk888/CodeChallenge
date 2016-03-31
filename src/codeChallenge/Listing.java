package codeChallenge;

public class Listing {
	String title;
	String manufacturer;
	String currency;
	String price;
	
	public Listing(String tit, String manu, String cur, String price){
		this.title = tit;
		this.manufacturer = manu;
		this.currency = cur;
		this.price = price;
	}
	
	public String getManu(){
		return this.manufacturer;
	}
	
	public String getTitle(){
		return this.title;
	}
}
