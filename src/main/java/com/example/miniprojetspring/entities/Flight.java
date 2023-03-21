package com.example.miniprojetspring.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;


@Entity
@Table(name = "flight")
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "flight_number")
	private String flightNumber;
	@OneToOne
	private Crew pilot;
	@OneToOne
	private Crew co_pilot;
	@Column(name = "seats_number")
	private Integer seatsNumber;
	@Column(name = "departure_time")
	private Date departureTime;
	@Column(name = "arrival_time")
	private Date arrivalTime;
	@Column(name = "delay_time")
	private Integer delayTime;
	@Column(name = "status")
	private Status_enum status;
	@Column(name = "gate")
	private String gate;
	@OneToOne
	private Airport origin;
	@OneToOne
	private Airport destination;
	@ManyToMany
    @JoinTable(name = "restauration_company",
             joinColumns = @JoinColumn(name = "flight_id"),
             inverseJoinColumns = @JoinColumn(name = "restauration_id"))
    private List<RestaurationCompany> restaurationCompany = new ArrayList<>();
	@Column(name = "classe")
	private Class_enum classe;
	
	
	public Flight() {
		
	}


	public Flight(String flightNumber, Crew pilot, Crew co_pilot, Integer seatsNumber, Date departureTime,
			Date arrivalTime, Integer delayTime, Status_enum status, String gate, Airport origin, Airport destination,
			List<RestaurationCompany> restaurationCompany, Class_enum classe) {
		super();
		this.flightNumber = flightNumber;
		this.pilot = pilot;
		this.co_pilot = co_pilot;
		this.seatsNumber = seatsNumber;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.delayTime = delayTime;
		this.status = status;
		this.gate = gate;
		this.origin = origin;
		this.destination = destination;
		this.restaurationCompany = restaurationCompany;
		this.classe = classe;
	}


	public String getFlightNumber() {
		return flightNumber;
	}


	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}


	public Crew getPilot() {
		return pilot;
	}


	public void setPilot(Crew pilot) {
		this.pilot = pilot;
	}


	public Crew getCo_pilot() {
		return co_pilot;
	}


	public void setCo_pilot(Crew co_pilot) {
		this.co_pilot = co_pilot;
	}


	public Integer getSeatsNumber() {
		return seatsNumber;
	}


	public void setSeatsNumber(Integer seatsNumber) {
		this.seatsNumber = seatsNumber;
	}


	public Date getDepartureTime() {
		return departureTime;
	}


	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}


	public Date getArrivalTime() {
		return arrivalTime;
	}


	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}


	public Integer getDelayTime() {
		return delayTime;
	}


	public void setDelayTime(Integer delayTime) {
		this.delayTime = delayTime;
	}


	public Status_enum getStatus() {
		return status;
	}


	public void setStatus(Status_enum status) {
		this.status = status;
	}


	public String getGate() {
		return gate;
	}


	public void setGate(String gate) {
		this.gate = gate;
	}


	public Airport getOrigin() {
		return origin;
	}


	public void setOrigin(Airport origin) {
		this.origin = origin;
	}


	public Airport getDestination() {
		return destination;
	}


	public void setDestination(Airport destination) {
		this.destination = destination;
	}


	public List<RestaurationCompany> getRestaurationCompany() {
		return restaurationCompany;
	}


	public void setRestaurationCompany(List<RestaurationCompany> restaurationCompany) {
		this.restaurationCompany = restaurationCompany;
	}


	public Class_enum getClasse() {
		return classe;
	}


	public void setClasse(Class_enum classe) {
		this.classe = classe;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}
	
	
	
	
}
