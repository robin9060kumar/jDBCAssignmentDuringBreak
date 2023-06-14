package com.movie.manager;

import com.movie.exception.LesserProductionCostException;
import com.movie.exception.ShorterMovieNameException;

public class ValidateInput {
	public void validateMovieName(String movieName) throws ShorterMovieNameException{
		if (movieName.length()<3) {
			throw new ShorterMovieNameException("Movie name must be at least 3 characters long.");
		}
	}
	public void validateProductionCost(double productionCost) throws LesserProductionCostException{
		if(productionCost<10.0) {
			throw new LesserProductionCostException("Procduction cost must be minimun 10 cr,");
		}
	}
}
