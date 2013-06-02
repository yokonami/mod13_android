package com.example.yokonamifirstapp;

public class Tramp {
	private int suit;
	public static final String[] suitExpression = {"heart", "diamond", "spade", "club"};
	private int number;
	private String imagePath;
	private int image;
	public Tramp(int serialNumber){
		this.suit = serialNumber%4;
		this.number = serialNumber%13 + 1;
		setImage(R.drawable.tramp);
	}
	public Tramp(int suit, int number){
		this.suit = suit;
		this.number = number;
	}
	public void setImage(int d){
		this.image = d;
	}
	public int getSuit(){
		return suit;
	}
	public int getNumber(){
		return number;
	}
	public String getImagePath(){
		return imagePath;
	}
	public int getImage(){
		return this.image;
	}
	public String toString(){
		return suitExpression[suit] + String.valueOf(number);
	}
}
