package com.movie.manager;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String movieName;
    private String releaseDate;
    private double productionCost;
    private int noOfScreensReleased;
    private String directedBy;
    private String producedBy;
    private boolean status;

    // Constructor for initializing Movie object with movieName, releaseDate, status
    public Movie(String movieName, String releaseDate, boolean status) {
        this.movieName = movieName;
        this.releaseDate = releaseDate;
        this.status = status;
    }

    // Constructor for initializing Movie object with movieName, directedBy, productionCost
    public Movie(String movieName, String directedBy, double productionCost) {
        this.movieName = movieName;
        this.directedBy = directedBy;
        this.productionCost = productionCost;
    }

    // Default constructor
    public Movie() {
        this.movieName = "Unknown";
        this.releaseDate = "Unknown";
        this.productionCost = 0.0;
        this.noOfScreensReleased = 0;
        this.directedBy = "Unknown";
        this.producedBy = "Unknown";
        this.status = false;
    }

    // Getter and setter methods for each variable
    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getProductionCost() {
        return productionCost;
    }

    public void setProductionCost(double productionCost) {
        this.productionCost = productionCost;
    }

    public int getNoOfScreensReleased() {
        return noOfScreensReleased;
    }

    public void setNoOfScreensReleased(int noOfScreensReleased) {
        this.noOfScreensReleased = noOfScreensReleased;
    }

    public String getDirectedBy() {
        return directedBy;
    }

    public void setDirectedBy(String directedBy) {
        this.directedBy = directedBy;
    }

    public String getProducedBy() {
        return producedBy;
    }

    public void setProducedBy(String producedBy) {
        this.producedBy = producedBy;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    
    public void printAllDetails(ArrayList<Movie> movie) {
    	for(Movie m: movie) {
			System.out.print(m.getMovieName()+"      ");
			System.out.print(m.getReleaseDate()+"      ");
			System.out.print(m.getProductionCost()+"      ");
			System.out.print(m.getProducedBy()+"      ");
			System.out.print(m.getNoOfScreensReleased()+"      ");
			System.out.print(m.getDirectedBy()+"      ");
			System.out.print(m.getProducedBy()+"      ");
			System.out.println(m.getStatus()+"      ");
    	}
    }
    public void printDetails(Movie movie) {
			System.out.print(movie.getMovieName()+"      ");
			System.out.print(movie.getReleaseDate()+"      ");
			System.out.print(movie.getProductionCost()+"      ");
			System.out.print(movie.getProducedBy()+"      ");
			System.out.print(movie.getNoOfScreensReleased()+"      ");
			System.out.print(movie.getDirectedBy()+"      ");
			System.out.print(movie.getProducedBy()+"      ");
			System.out.println(movie.getStatus()+"      ");
 
    }
    
}
