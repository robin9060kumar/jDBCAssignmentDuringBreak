package com.movie.manager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import com.db.connection.CreateConnection;
import com.movie.exception.LesserProductionCostException;
import com.movie.exception.ShorterMovieNameException;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

public class MovieControl {
	public static void main(String[] args) {
		while(true) {
			System.out.println(" Select from Menu");
			System.out.println(" Press 1 to Insert a new Movie into the table");
			System.out.println(" Press 2 to retrive movie detail on the basis movieName");
			System.out.println(" Press 3 to retrive movie based on productionCost");
			System.out.println(" Press 4 to Delete movie detail");
			System.out.println(" Press 5 to Display details of all movies");
			System.out.println(" Press 6 to retrive movie basis on releasdate");
			System.out.println(" press 7 to exit");
			Scanner scanner = new Scanner(System.in);
			int ch = scanner.nextInt();
			scanner.nextLine();
			System.out.println("-----------------------------------------------");
			if(ch==1) {
				MovieControl movieControl = new MovieControl();
				movieControl.insertMovie();	
			}else if(ch==2) {
				MovieControl movieControl = new MovieControl();
				System.out.println("Enter the moviename to retrive the data: ");
				String movieName = scanner.nextLine();
				Movie movie = movieControl.getMovie(movieName);
                movie.printDetails(movie);
				
			}else if(ch==3) {
				MovieControl movieControl = new MovieControl();
				System.out.println("Enter the minimum production cost to retrive the data: ");
				Double productionCost = scanner.nextDouble();
				ArrayList<Movie> movies = movieControl.getMovie(productionCost);
				Movie movie = new Movie();
				movie.printAllDetails(movies);
			}else if(ch==4) {
				MovieControl movieControl = new MovieControl();
				movieControl.deleteMovie();

			}else if(ch==5) {
				MovieControl movieControl = new MovieControl();
				ArrayList<Movie> movies = movieControl.getAllMovie();
				Movie movie = new Movie();
				movie.printAllDetails(movies);
			}else if(ch==6){
				MovieControl movieControl = new MovieControl();
				Movie movie = new Movie();
				ArrayList<Movie> movies = movieControl.getMovie();
                movie.printAllDetails(movies);
			}else {
				System.out.println("Clossing Program");
				break;
			}
		}
	}
	public void insertMovie() {
		ValidateInput validate = new ValidateInput();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Movie Name :");
		String movieName = scanner.nextLine();
		try {
		validate.validateMovieName(movieName);
		}catch(ShorterMovieNameException shorterMovieNameException){
			System.out.println(shorterMovieNameException.getMessage());
			return;
		}

        System.out.print("Enter release date (DD-MM-YYYY): ");
        String releaseDate = scanner.nextLine();

        System.out.print("Enter production cost (in Crores): ");
        double productionCost = scanner.nextDouble();

        try {
            validate.validateProductionCost(productionCost);
        } catch (LesserProductionCostException lesserProductionCostException) {
            System.out.println(lesserProductionCostException.getMessage());
            return; // Abort insertion if the production cost is too low
        }

        System.out.print("Enter number of screens released: ");
        int noOfScreensReleased = scanner.nextInt();

        scanner.nextLine(); // Consume the remaining newline character

        System.out.print("Enter director name: ");
        String directedBy = scanner.nextLine();

        System.out.print("Enter producer name: ");
        String producedBy = scanner.nextLine();

        System.out.print("Enter movie status (true/false): ");
        boolean status = scanner.nextBoolean();
        try {
        CreateConnection createConnection = new CreateConnection();
        Connection connection = createConnection.create();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Movie (movieName, releaseDate, productionCost, noOfScreensReleased, directedBy, producedBy, status) VALUES (?, ?, ?, ?, ?, ?, ?)");
        statement.setString(1, movieName);
        statement.setString(2, releaseDate);
        statement.setDouble(3, productionCost);
        statement.setInt(4, noOfScreensReleased);
        statement.setString(5, directedBy);
        statement.setString(6, producedBy);
        statement.setBoolean(7, status);
        statement.executeUpdate();
        System.out.println("Movie inserted successfully!");
        }catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteMovie() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the MovieName of record to delete: ");
		String movieName = scanner.nextLine(); 
		try {
			CreateConnection cc = new CreateConnection();
			Connection connection = cc.create();
			PreparedStatement statement = connection.prepareStatement("delete from Movie where movieName = ?");
			statement.setString(1, movieName);
			int result = statement.executeUpdate();
			if(result>0) {
				System.out.println("Record deleted Succesfully");
			}else {
				System.out.println("Record not found");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
  }
	public ArrayList<Movie> getAllMovie() {
		ArrayList<Movie> movies = new ArrayList<>();
		try {
			CreateConnection cc = new CreateConnection();
			Connection connection = cc.create();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("Select * from Movie ");
			while(result.next()) {
		          Movie movie = new Movie();
	                movie.setMovieName(result.getString("movieName"));
	                movie.setReleaseDate(result.getString("releaseDate"));
	                movie.setProductionCost(result.getDouble("productionCost"));
	                movie.setNoOfScreensReleased(result.getInt("noOfScreensReleased"));
	                movie.setDirectedBy(result.getString("directedBy"));
	                movie.setProducedBy(result.getString("producedBy"));
	                movie.setStatus(result.getBoolean("status"));
	                movies.add(movie);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return movies;
	}
	public Movie getMovie(String movieName) {
		Movie movie = null;
		try {
			CreateConnection cc = new CreateConnection();
			Connection connection = cc.create();
			PreparedStatement statement = connection.prepareStatement("Select * from Movie where movieName =?");
			statement.setString(1, movieName);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
                movie = new Movie();
                movie.setMovieName(result.getString("movieName"));
                movie.setReleaseDate(result.getString("releaseDate"));
                movie.setProductionCost(result.getDouble("productionCost"));
                movie.setNoOfScreensReleased(result.getInt("noOfScreensReleased"));
                movie.setDirectedBy(result.getString("directedBy"));
                movie.setProducedBy(result.getString("producedBy"));
                movie.setStatus(result.getBoolean("status"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return movie;
	}
	public ArrayList<Movie> getMovie(double productionCost) {
		Movie movie = null;
		ArrayList<Movie> movies = new ArrayList<Movie>();
		try {
			CreateConnection cc = new CreateConnection();
			Connection connection = cc.create();
			PreparedStatement statement = connection.prepareStatement("Select * from Movie where productionCost >?");
			statement.setDouble(1, productionCost);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
                movie = new Movie();
                movie.setMovieName(result.getString("movieName"));
                movie.setReleaseDate(result.getString("releaseDate"));
                movie.setProductionCost(result.getDouble("productionCost"));
                movie.setNoOfScreensReleased(result.getInt("noOfScreensReleased"));
                movie.setDirectedBy(result.getString("directedBy"));
                movie.setProducedBy(result.getString("producedBy"));
                movie.setStatus(result.getBoolean("status"));
                movies.add(movie);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return movies;
	}
	public ArrayList<Movie> getMovie() {
		Scanner scanner= new Scanner(System.in);
		System.out.println("Enter the releasedate to retrive the record");
		String releaseDate = scanner.nextLine();
		ArrayList<Movie> movies = new ArrayList<Movie>();
		Movie movie = null;
		try {
			CreateConnection cc = new CreateConnection();
			Connection connection = cc.create();
			PreparedStatement statement = connection.prepareStatement("Select * from Movie where releaseDate =?");
			statement.setString(1, releaseDate);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
                movie = new Movie();
                movie.setMovieName(result.getString("movieName"));
                movie.setReleaseDate(result.getString("releaseDate"));
                movie.setProductionCost(result.getDouble("productionCost"));
                movie.setNoOfScreensReleased(result.getInt("noOfScreensReleased"));
                movie.setDirectedBy(result.getString("directedBy"));
                movie.setProducedBy(result.getString("producedBy"));
                movie.setStatus(result.getBoolean("status"));
                movies.add(movie);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return movies;
	
	}
}
