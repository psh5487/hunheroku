package kr.or.hundbheroku.dto;

public class MusicDto {
	
	private String title;
	private String artist;
	private String composer;
	private String category;
	private String track;
	private String label;
	private String barcode;
	private String regdate;
	private int importance;
	private String numOfDisc;
	private int id;
	
	public MusicDto(String title, String artist, String composer, String category, String track, String label, String barcode, 
			        String regdate, int importance, String numOfDisc, int id) {
		super();
		
		this.title = title;
		this.artist = artist;
		this.composer = composer;
		this.category = category;
		this.track = track;
		this.label = label;
		this.barcode = barcode;
		this.regdate = regdate;
		this.importance = importance;
		this.numOfDisc = numOfDisc;
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getComposer() {
		return composer;
	}
	public void setComposer(String composer) {
		this.composer = composer;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTrack() {
		return track;
	}
	public void setTrack(String track) {
		this.track = track;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getImportance() {
		return importance;
	}
	public void setImportance(int importance) {
		this.importance = importance;
	}
	public String getNumOfDisc() {
		return numOfDisc;
	}
	public void setNumOfDisc(String numOfDisc) {
		this.numOfDisc = numOfDisc;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "MusicDto [title=" + title + ", artist=" + artist + ", composer=" + composer + ", category=" + category
				+ ", track=" + track + ", label=" + label + ", barcode=" + barcode + ", regdate="
				+ regdate + ", importance=" + importance + ", numOfDisc=" + numOfDisc + id + "]";
	}

}
