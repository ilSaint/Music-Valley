package it.univr.MusicValley.data;

import java.io.File;
import java.util.ArrayList;

public class Product {
	
	private final int code;
	private final String title;
	private final String[] traces;
	private final float price;
	private final int date;
	private final String artist;
	private final String description;
	private final String genre;
	private final String[] participants;
	private final String[] instruments;
	private final ArrayList<Member> members;
	private final File cover;
	private final String type;
	private final int sales;
	private final int quantity;

	// --------------------------------------------------------------------------------------------
	
	public Product(int code, String title, String[] traces, float price, int date,
				   String artist, String description, String genre, String[] participants,
				   String[] instruments, ArrayList<Member> members, File cover, String type, int sales, int quantity) {
		
		this.code = code;
		this.title = title;
		this.traces = traces;
		this.price = price;
		this.date = date;
		this.artist = artist;
		this.description = description;
		this.genre = genre;
		this.participants = participants;
		this.instruments = instruments;
		this.members = members;
		this.cover = cover;
		this.type = type;
		this.sales = sales;
		this.quantity = quantity;
		
	}

	// --------------------------------------------------------------------------------------------
	
	public int getCode() 					{ return code; }
	public String getTitle() 				{ return title; }
	public String[] getTraces() 			{ return traces; }
	public float getPrice() 				{ return price; }
	public int getDate() 					{ return date; }
	public String getArtist() 				{ return artist; }
	public String getDescription() 			{ return description; }
	public String getGenre() 				{ return genre; }
	public String[] getParticipants() 		{ return participants; }
	public String[] getInstruments() 		{ return instruments; }
	public ArrayList<Member> getMembers() 	{ return members; }
	public File getCover() 					{ return cover; }
	public String getType() 				{ return type; }
	public int getSales() 					{ return sales; }
	public int getQuantity() 				{ return quantity; }
	
}
