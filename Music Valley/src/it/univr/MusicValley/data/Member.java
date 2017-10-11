package it.univr.MusicValley.data;

public class Member {
	
	private final String name;
	private final String instrument;
	private final String birthDate;
	private final String genre;
	
	// --------------------------------------------------------------------------------------------
	
	public Member(String[] memberData) {
		this.name = memberData[0];
		this.instrument = memberData[1];
		this.birthDate =  memberData[2];
		this.genre = memberData[3];
	}

	// --------------------------------------------------------------------------------------------
	
	public String getName() 		{ return name; }
	public String getInstrument() 	{ return instrument; }
	public String getBirthDate() 	{ return birthDate; }
	public String getGenre() 		{ return genre; }
	
}
