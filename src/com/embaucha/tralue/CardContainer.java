package com.embaucha.tralue;

public class CardContainer {
	String name, award_program;
	float percentage;
	String origin, destination, service_class;
	float price;
	int sql_id;
	String nosql_id;
	
	//necessary information for display on NewListOfCards:
	//name, percentage, key_carrier_names
	
	//necessary information per bundle to pass to SingleCardDisplay:
	//origin, destination, class of service, price
	
	//necessary information per card to pass to SingleCardDisplay:
	//name, KEY_CARD_ID
	
	public CardContainer(String name, String award_program, float percentage, String origin, String destination, String service_class, float price, int sql_id) {
		this.name = name;
		this.award_program = award_program;
		this.percentage = percentage;
		this.origin = origin;
		this.destination = destination;
		this.service_class = service_class;
		this.price = price;
		this.sql_id = sql_id;
		
	}
	
	public CardContainer(String name, String award_program, float percentage, String origin, String destination, String service_class, float price, String nosql_id) {
		this.name = name;
		this.award_program = award_program;
		this.percentage = percentage;
		this.origin = origin;
		this.destination = destination;
		this.service_class = service_class;
		this.price = price;
		this.nosql_id = nosql_id;
		
	}
	
	public int getSql_id() {
		return sql_id;
	}

	public String getName() {
		return name;
	}

	public String getAward_program() {
		return award_program;
	}

	public float getPercentage() {
		return percentage;
	}

	public String getOrigin() {
		return origin;
	}

	public String getDestination() {
		return destination;
	}

	public String getService_class() {
		return service_class;
	}

	public float getPrice() {
		return price;
	}
	
	public String getNosql_id() {
		return nosql_id;
	}

}