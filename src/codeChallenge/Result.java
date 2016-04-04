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
		String result = "{\"product_name\": \"" + this.productName + "\", ";
		result += "\"listings\": [";
		for (Listing l : listings){
			result += "{\"currency\": \"" + l.getCur() + "\", ";
			result += "\"price\": \"" + l.getPrice()+ "\", ";
			result += "\"manufacturer\": \"" + l.getManu() + "\", ";
			result += "\"title\": \"" + l.getTitle() + "\"}, ";
		}
		result = result.substring(0, result.length() - 3);
		result += "]}\n";
		return result	;
	}
}
