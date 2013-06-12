package com.example.yokonamifirstapp;

public class Tramp {
	public static final int N_SUIT = 4;
	public static final int N_NUMBER = 13;
	private int suit;
	public static final String[] suitExpression = {"heart", "diamond", "spade", "club"};
	private int number;
	private String imagePath;
	private int image;
	public Tramp(int serialNumber){
		this.suit = serialNumber%4;
		this.number = serialNumber%13 + 1;
	}
	public Tramp(int serialNumber, int resID){
		this.suit = serialNumber%4;
		this.number = serialNumber%13 + 1;
		this.image = resID;
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
