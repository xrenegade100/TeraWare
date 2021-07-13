/*******************************************************
 * Copyright (C) 2021-2022 Antonio Scognamiglio <antonio.scognamiglio12@outlook.it>
 *
 * This file is part of ParametersValidator.
 *
 * ParametersValidator can not be copied and/or distributed without the express
 * permission of Antonio Scognamiglio
 *******************************************************/

package net.teraware.validator;

@SuppressWarnings("rawtypes")
public class ParameterEntry {

	private String name;
	private Class type;
	private StringType stringType;
	private int minLength;
	private int maxLength;
	private boolean required;

	public ParameterEntry(
		String name,
		Class type,
		StringType stringType,
		int minLength,
		int maxLength,
		boolean required
	) {
		this.name = name;
		this.type = type;
		this.stringType = stringType;
		this.minLength = minLength;
		this.maxLength = maxLength;
		this.required = required;
	}

	public String getName() {
		return name;
	}

	public Class getType() {
		return type;
	}

	public StringType getStringType() {
		return stringType;
	}

	public int getMinLength() {
		return minLength;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public boolean isRequired() {
		return required;
	}
}
