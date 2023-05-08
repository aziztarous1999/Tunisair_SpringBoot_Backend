package com.example.miniprojetspring.entities;

public class Dashboard {
	private long nbDish;
	private long nbStaff;
	private long nbCrew;
	private long nbAirport;
	public Dashboard(long nbDish, long nbStaff, long nbCrew, long nbAirport) {
		super();
		this.nbDish = nbDish;
		this.nbStaff = nbStaff;
		this.nbCrew = nbCrew;
		this.nbAirport = nbAirport;
	}
	public long getNbDish() {
		return nbDish;
	}
	public void setNbDish(long nbDish) {
		this.nbDish = nbDish;
	}
	public long getNbStaff() {
		return nbStaff;
	}
	public void setNbStaff(long nbStaff) {
		this.nbStaff = nbStaff;
	}
	public long getNbCrew() {
		return nbCrew;
	}
	public void setNbCrew(long nbCrew) {
		this.nbCrew = nbCrew;
	}
	public long getNbAirport() {
		return nbAirport;
	}
	public void setNbAirport(long nbAirport) {
		this.nbAirport = nbAirport;
	}
	
	
}
