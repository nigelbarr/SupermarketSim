package edu.collin.cocs2436.nbarreras.SuperMarketSim;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SupermarketDemo {

	public static void main(String[] args) {
		Checkout checkout =  new Checkout();
		Supermarket market = new Supermarket(checkout);
		
		market.initializeCarts(5);  // Creates 5 initial carts
		System.out.println("");
		
		SmartShelf<Cereal> cerealShelf = new SmartShelf<>("Cereal Shelf");
		SmartShelf<Chips> chipsShelf = new SmartShelf<>("Chips Shelf");
		SmartShelf<Soda> sodaShelf = new SmartShelf<>("Soda Shelf");
		SmartShelf<Soap> soapShelf = new SmartShelf<>("Soap Shelf");
		
		market.addShelf(cerealShelf.getName(), cerealShelf);
		market.addShelf(chipsShelf.getName(), chipsShelf);
		market.addShelf(sodaShelf.getName(), sodaShelf);
		market.addShelf(soapShelf.getName(), soapShelf);
		
		ArrayList<Cereal> cerealitems = new ArrayList<>();
		cerealitems.add(new Cereal("Corn Flakes"));
		cerealitems.add(new Cereal("Honey Nut Cheerios"));
		cerealitems.add(new Cereal("Apple Jacks")); 
		cerealitems.add(new Cereal("Corn Flakes"));
		cerealitems.add(new Cereal("Froot Loops"));
		cerealitems.add(new Cereal("Cap'n Crunch"));
		System.out.println("");
		
		ArrayList<Chips> chipsitems = new ArrayList<>();
		chipsitems.add(new Chips("Fritos"));
		chipsitems.add(new Chips("Hot Fries"));
		chipsitems.add(new Chips("Pringles")); 
		chipsitems.add(new Chips("Lays BBQ"));
		chipsitems.add(new Chips("Ruffles"));
		chipsitems.add(new Chips("Funyuns"));
		System.out.println("");
		
		ArrayList<Soda> sodaitems = new ArrayList<>();
		sodaitems.add(new Soda("Coke"));
		sodaitems.add(new Soda("Pepsi"));
		sodaitems.add(new Soda("Dr. Pepper")); 
		sodaitems.add(new Soda("Fanta"));
		sodaitems.add(new Soda("Mounain Dew"));
		sodaitems.add(new Soda("Mug"));
		System.out.println("");
		
		ArrayList<Soap> soapitems = new ArrayList<>();
		soapitems.add(new Soap("Dove"));
		soapitems.add(new Soap("Aveeno"));
		soapitems.add(new Soap("Irish Spring")); 
		soapitems.add(new Soap("Native"));
		soapitems.add(new Soap("Olay"));
		soapitems.add(new Soap("Dial"));
		System.out.println("");
		
		Map<RetailItemType, PricingCategory> typeCategory = new HashMap<>();
		RetailItemLookup retailItemLookup = new RetailItemLookup(4,typeCategory);
		
		try {
			retailItemLookup.addPricingCategory(RetailItemType.SOAP, true);
			retailItemLookup.addPricingCategory(RetailItemType.SODA, true);
			retailItemLookup.addPricingCategory(RetailItemType.CEREAL, true);
			retailItemLookup.addPricingCategory(RetailItemType.CHIPS, true);
		} catch (DuplicateCategoryException e1) {
			e1.printStackTrace();
		}
		
		System.out.println("");
		
		for (Cereal item : cerealitems) {
			Random rand = new Random();
			try {
				double price = 2.0 + rand.nextDouble() * 3.0;
				price = Math.round(price * 100.0) / 100.0;
				retailItemLookup.addItemEntry(item, price);
			} catch (MissingCategoryException e) {
				System.out.println(e);
			}
		}
		
		System.out.println();
		
		for (Chips item : chipsitems) {
			Random rand = new Random();
			try {
				double price = 2.0 + rand.nextDouble() * 3.0;
				price = Math.round(price * 100.0) / 100.0;
				retailItemLookup.addItemEntry(item, price);
			} catch (MissingCategoryException e) {
				System.out.println(e);
			}
		}
		
		System.out.println();
		
		for (Soda item : sodaitems) {
			Random rand = new Random();
			try {
				double price = 2.0 + rand.nextDouble() * 3.0;
				price = Math.round(price * 100.0) / 100.0;
				retailItemLookup.addItemEntry(item, price);
			} catch (MissingCategoryException e) {
				System.out.println(e);
			}
		}
		
		System.out.println();
		
		for (Soap item : soapitems) {
			Random rand = new Random();
			try {
				double price = 2.0 + rand.nextDouble() * 3.0; 
				price = Math.round(price * 100.0) / 100.0;
				retailItemLookup.addItemEntry(item, price);
			} catch (MissingCategoryException e) {
				System.out.println(e);
			}
		}
		
		System.out.println();
		
		Promotions thanksgiving = new Promotions("Thanksgiving", 10,LocalDate.parse("2022-11-24"));
		Promotions christmas = new Promotions("Christmas", 15, LocalDate.parse("2022-12-25"));
		Promotions blackfriday = new Promotions("Black Friday", 20, LocalDate.parse("2022-11-25"));
		
		thanksgiving.addAllItems(chipsitems);
		thanksgiving.addAllItems(sodaitems);
		christmas.addAllItems(cerealitems);
		christmas.addAllItems(soapitems);
		
		blackfriday.addItem(new Cereal("Corn Flakes"));
		blackfriday.addItem(new Chips("Hot Fries"));
		blackfriday.addItem(new Soda("Fanta"));
		blackfriday.addItem(new Soap("Olay"));
		
		ActivePromotions activePromotions = new ActivePromotions();
		activePromotions.addPromotion(thanksgiving);
		activePromotions.addPromotion(christmas);
		activePromotions.addPromotion(blackfriday);
		
		Cashregister register = new Cashregister();
		register.setRetailItemLookup(retailItemLookup);
		register.setActivePromotions(activePromotions);
		
		ArrayList<CheckoutCounter> counters = new ArrayList<>();
		counters.add(new CheckoutCounter(register, "1"));
		counters.add(new CheckoutCounter(register, "2"));
		counters.add(new CheckoutCounter(register, "3"));
		checkout.addCounters(counters);
		
		System.out.println();
		
		Cashier sandra = new Cashier("Sandra", register, counters.get(0));
		Cashier paul = new Cashier("Paul", register, counters.get(1));	
		Cashier ron = new Cashier("Ron", register, counters.get(2));	
		
		Employee jack = new Employee("Jack");
		jack.placeItems(cerealShelf, cerealitems);
		System.out.println(cerealShelf);
		jack.placeItems(chipsShelf, chipsitems);
		System.out.println(chipsShelf);
		jack.placeItems(sodaShelf, sodaitems);
		System.out.println(sodaShelf);
		jack.placeItems(soapShelf, soapitems);
		System.out.println(soapShelf);
		
		Customer jill = new Customer("Jill", checkout);
		MyList<ShoppingListEntry> Jilllist = new MyLinkedList<>();
		Jilllist.addFirst(new ShoppingListEntry("Pepsi", RetailItemType.SODA, sodaShelf.getName()));
		Jilllist.addFirst(new ShoppingListEntry("Dial",RetailItemType.SOAP, soapShelf.getName()));
		Jilllist.addFirst(new ShoppingListEntry("Funyuns",RetailItemType.CHIPS, chipsShelf.getName()));
		Jilllist.addFirst(new ShoppingListEntry("Froot Loops",RetailItemType.CEREAL,cerealShelf.getName()));
		
		
		Customer john = new Customer("John", checkout);
		MyList<ShoppingListEntry> Johnlist = new MyLinkedList<>();
		Johnlist.addFirst(new ShoppingListEntry("Dove", RetailItemType.SOAP,"Soap Shelf"));
		Johnlist.addFirst(new ShoppingListEntry("Mug",RetailItemType.SODA,"Soda Shelf"));
		Johnlist.addFirst(new ShoppingListEntry("Pringles",RetailItemType.CHIPS,"Chips Shelf"));
		Johnlist.addFirst(new ShoppingListEntry("Corn Flakes",RetailItemType.CEREAL,"Cereal Shelf")); 
		
		Customer henry = new Customer("Henry", checkout);
		MyList<ShoppingListEntry> Henrylist = new MyLinkedList<>();
		Henrylist.addFirst(new ShoppingListEntry("Fanta", RetailItemType.SODA,"Soda Shelf"));
		Henrylist.addFirst(new ShoppingListEntry("Irish Spring",RetailItemType.SOAP,"Soap Shelf"));
		Henrylist.addFirst(new ShoppingListEntry("Hot Fries",RetailItemType.CHIPS,"Chips Shelf"));
		Henrylist.addFirst(new ShoppingListEntry("Honey Nut Cheerios",RetailItemType.CEREAL,"Cereal Shelf"));

		try {
			jill.shop(Jilllist, market);
		} catch (OutofStockException e) {
			System.out.println(e);
		} 
		try {
			john.shop(Johnlist, market);
		} catch (OutofStockException e) {
			System.out.println(e);
		}
		try {
			henry.shop(Henrylist, market);
		} catch (OutofStockException e) {
			System.out.println(e);
		} 
		
		// Customers go to checkout
		jill.checkout();
		john.checkout();
		henry.checkout();
		
		List<Cashier> cashiers = Arrays.asList(sandra, ron, paul);
		for (Cashier cashier : cashiers) {
		    while (cashier.getCounter().hasCustomers()) {
		        cashier.handleCheckout();
		    }
		}
		
	
	}

}
