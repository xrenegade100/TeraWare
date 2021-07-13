/*******************************************************
 * Copyright (C) 2021-2022 Antonio Scognamiglio <antonio.scognamiglio12@outlook.it>
 *
 * This file is part of ParametersValidator.
 *
 * ParametersValidator can not be copied and/or distributed without the express
 * permission of Antonio Scognamiglio
 *******************************************************/

package net.teraware.validator;

public class ParameterLenghtException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ParameterLenghtException(String name, int min, int max) {
		super(
			"Parameter `" +
			name +
			"` has " +
			min +
			" as its minimum length, and " +
			max +
			" as its maximum length, but " +
			min +
			" > " +
			max
		);
	}
}
