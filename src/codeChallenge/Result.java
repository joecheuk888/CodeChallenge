package codeChallenge;

import java.util.ArrayList;
import java.util.List;

public class Result {
	String productName;
	List<Listing> listings;
	
	public Result(String name){
		this.productName = name;
		listings = new ArrayList<Listing>();
	}
	
	public void addListing(Listing lst){
		listings.add(lst);
	}
	
	public int size(){
		return listings.size();
	}
	
	public String printForm(){
		String result = "{\n\"product_name\": " + this.productName + "\n";
		result += "\"listings\": [";
		for (Listing l : listings){
			result += "\n\"" + l.getTitle() + "\"";
		}
		result += "]\n";
		return result + "\n}";
	}
}
