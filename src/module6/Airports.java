package module6;

import demos.Airport;

public class Airports {
	
	private String city;
	private String country;
	private String code;
	
	public String getCity() {return this.city;}
	public String getCountry() {return this.country;}
	public String getCode() {return this.code;}
	
    public static String findCode(String toFind, Airports[] airports) {
	
    String code ="Not Found";
    	for (Airports airport: airports ) {
    	if (airport.getCountry() == toFind) {
    		code = airport.getCode();
    		break;
    	}
    }
    
	return code;
    }
    
    public static String LinearFindCode(String toFind, Airports[] airports) {
    	
    	int index = 0;
    	
    	while (index <= airports.length) {
    		Airports curr = airports[index];
    		if(toFind.equals(curr.getCountry())) {
    			return curr.getCode();
    		}
    		else {
    			index++;
    		}
    	}
    	
    	
    	return null;
    }

}
