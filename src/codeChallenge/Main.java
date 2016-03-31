package codeChallenge;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
public class Main {
	
	public static Listing jsonToListing(String data){
		//{"title": String // description of product for sale
		//"manufacturer": String // who manufactures the product for sale
		//"currency": String // currency code, e.g. USD, CAD, GBP, etc.
		//"price": String // price, e.g. 19.99, 100.00}
		try{
			data = data.substring(1, data.length()-1);
			String[] dataSplit = data.split(",");
			String title, manu, cur, price;
			title = dataSplit[0].split(":")[1];
			title = title.substring(1, title.length()-1);
			manu = dataSplit[1].split(":")[1];
			manu = manu.substring(1, manu.length()-1);
			cur = dataSplit[2].split(":")[1];
			cur = cur.substring(1, cur.length()-1);
			price = dataSplit[3].split(":")[1];
			price = price.substring(1, price.length()-1);
			return new Listing(title, manu, cur, price);
		}catch(Exception e){
			return null;
		}
	}
	
	public static Product jsonToProdWoFam(String data){
		// {"product_name":"Samsung_TL240",
		// "manufacturer":"Samsung","model":"TL240",
		// "announced-date":"2010-01-05T19:00:00.000-05:00"}
		data = data.substring(1, data.length()-1);
		String[] dataSplit = data.split(",");
		String pname, manu, model, adate;
		pname = dataSplit[0].split(":")[1];
		pname = pname.substring(1, pname.length()-1);
		manu = dataSplit[1].split(":")[1];
		manu = manu.substring(1, manu.length()-1);
		model = dataSplit[2].split(":")[1];
		model = model.substring(1, model.length()-1);
		adate = dataSplit[3].split(":")[1];
		adate = adate.substring(1, adate.length()-1);
		return new Product(pname, manu, model, adate);
	}
	
	public static Product jsonToProdWFam(String data){
		// {"product_name":"Samsung_TL240",
		// "manufacturer":"Samsung","model":"TL240",
		// "family":"FinePix",
		// "announced-date":"2010-01-05T19:00:00.000-05:00"}
		data = data.substring(1, data.length()-1);
		String[] dataSplit = data.split(",");
		String pname, manu, model, fam, adate;
		pname = dataSplit[0].split(":")[1];
		pname = pname.substring(1, pname.length()-1);
		manu = dataSplit[1].split(":")[1];
		manu = manu.substring(1, manu.length()-1);
		model = dataSplit[2].split(":")[1];
		model = model.substring(1, model.length()-1);
		fam = dataSplit[3].split(":")[1];
		fam = fam.substring(1, fam.length()-1);
		adate = dataSplit[4].split(":")[1];
		adate = adate.substring(1, adate.length()-1);
		return new Product(pname, manu, model, fam, adate);
	}
	
	public static void main(String args[]){
		Map<String, ArrayList<Product>> manuToProds = new HashMap<String, ArrayList<Product>>();
		try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader("products.txt");

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
            String line;
            while((line = bufferedReader.readLine()) != null) {
            	Product p;
            	if (line.contains("family")){
            		p = jsonToProdWFam(line);
            	}else{
            		p = jsonToProdWoFam(line);
            	}
                if (!manuToProds.containsKey(p.getManu())){
                	manuToProds.put(p.getManu(), new ArrayList<Product>());
                }
                manuToProds.get(p.getManu()).add(p);
            }   

            fileReader.close();
            bufferedReader.close();

            Map<String, Result> result = new HashMap<String, Result>();
            fileReader = 
                    new FileReader("listings.txt");
            bufferedReader = 
                    new BufferedReader(fileReader);
            Listing lst;
                while((line = bufferedReader.readLine()) != null) {
                	 lst = jsonToListing(line);
                	 if (lst != null && manuToProds.containsKey(lst.getManu())){
                		 ArrayList<Product> prods = manuToProds.get(lst.getManu());
                		 for (Product p : prods){
                			 if (lst.getTitle().contains(p.getModel())){
                				 if (!result.containsKey(p.getName())){
                					 result.put(p.getName(), new Result(p.getName()));
                				 }
                				 result.get(p.getName()).addListing(lst);
                				 //System.out.println(result.get(p.getName()).printForm());
                				 break;
                			 }
                		 }
                	 }
                }
            fileReader.close();
            bufferedReader.close();
            
            FileWriter fw = new FileWriter("result.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			Iterator it = result.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry<String, Result> pair = (Map.Entry<String, Result>)it.next();
		        bw.write(pair.getValue().printForm());
		        it.remove(); // avoids a ConcurrentModificationException
		    }
			bw.close();
			fw.close();
            
        }
        catch(FileNotFoundException e) {
        	e.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}
}
