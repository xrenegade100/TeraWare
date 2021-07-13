/*******************************************************
 * Copyright (C) 2021-2022 Antonio Scognamiglio <antonio.scognamiglio12@outlook.it>
 *
 * This file is part of ParametersValidator.
 *
 * ParametersValidator can not be copied and/or distributed without the express
 * permission of Antonio Scognamiglio
 *******************************************************/

package net.teraware.validator;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import net.teraware.exception.ParameterTypeNotSupportedException;

/**
 * Classe helper per validare i parametri di richieste HTTP (presenza e tipo dei parametri)
 * */
@SuppressWarnings("rawtypes")
public class ParametersValidator {

	private HashMap<String, ParameterEntry> parameters;

	private final String EMAIL_REGEX =
		"(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
	private final String URL_REGEX =
		"https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)";

	public ParametersValidator() {
		this.parameters = new HashMap<>();
	}

	/**
	 * Valida i parametri di una richiesta HTTP
	 * @param request La richiesta di cui validare i parametri
	 * @return true Se tutti i parametri hanno passato la validazione
	 * @return false Se almeno un parametro ha fallito la validazione
	 * @throws @{@link ParameterTypeNotSupportedException} Se il formato di uno dei parametri numerici non � supportato
	 * */
	public boolean validate(HttpServletRequest request) {
		if (
			!validateRequiredParameters(request) ||
			!validateParametersTypes(request) ||
			!validateParametersLength(request)
		) return false;
		return true;
	}

	private boolean validateParametersLength(HttpServletRequest request) {
		for (Map.Entry<String, ParameterEntry> entry : this.parameters.entrySet()) {
			final String paramName = entry.getValue().getName();
			if (
				request.getParameter(paramName) != null &&
				!request.getParameter(paramName).trim().equals("") &&
				(
					entry.getValue().getMinLength() > request.getParameter(paramName).length() ||
					entry.getValue().getMaxLength() < request.getParameter(paramName).length()
				)
			) {
				return false;
			}
		}
		return true;
	}

	private boolean validateRequiredParameters(HttpServletRequest request) {
		for (Map.Entry<String, ParameterEntry> entry : this.parameters.entrySet()) {
			if (
				entry.getValue().isRequired() &&
				request.getParameter(entry.getValue().getName()) == null
			) return false;
		}
		return true;
	}

	private boolean validateParametersTypes(HttpServletRequest request) {
		for (Map.Entry<String, ParameterEntry> entry : this.parameters.entrySet()) {
			final Class parameterType = entry.getValue().getType();

			if (
				request.getParameter(entry.getKey()) != null &&
				!request.getParameter(entry.getKey()).trim().equals("")
			) {
				final String parameterValue = request.getParameter(entry.getKey());
				try {
					if (parameterType.equals(Integer.class)) {
						Integer.parseInt(parameterValue);
					} else if (parameterType.equals(Float.class)) {
						Float.parseFloat(parameterValue);
					} else if (parameterType.equals(Double.class)) {
						Double.parseDouble(parameterValue);
					} else if (parameterType.equals(String.class)) {
						if (entry.getValue().getStringType() != null) {
							switch (entry.getValue().getStringType()) {
								case EMAIL:
									if (!parameterValue.matches(EMAIL_REGEX)) return false;
									break;
								case URL:
									if (!parameterValue.matches(URL_REGEX)) return false;
									break;
								case DATE:
									break;
								default:
									break;
							}
						}
					} else {
						throw new ParameterTypeNotSupportedException(parameterType);
					}
				} catch (NumberFormatException e) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Aggiungi un parametro di tipo stringa da validare
	 * @param name Il nome del parametro
	 * @param required Parametro obbligatorio T/F
	 * */
	public void addStringParameter(String name, StringType stringType, boolean required) {
		this.addParameter(
				name,
				String.class,
				stringType,
				Integer.MIN_VALUE,
				Integer.MAX_VALUE,
				required
			);
	}

	public void addStringParameterLength(
		String name,
		StringType stringType,
		int minLength,
		int maxLength,
		boolean required
	) {
		if (minLength > maxLength) throw new ParameterLenghtException(name, minLength, maxLength);
		this.addParameter(name, String.class, stringType, minLength, maxLength, required);
	}

	private void addParameter(
		String name,
		Class type,
		StringType stringType,
		int minLength,
		int maxLength,
		boolean required
	) {
		this.parameters.put(
				name,
				new ParameterEntry(name, String.class, stringType, minLength, maxLength, required)
			);
	}

	/**
	 * Aggiungi un parametro numerico da validare
	 * @param name Il nome del parametro
	 * @param type Il tipo numerico di cui si vuole il parametro (<b>Integer</b>, <b>Float</b>, <b>Double</b>)
	 * @param required Parametro obbligatorio T/F
	 * */
	public void addNumberParameter(String name, Class<? extends Number> type, boolean required) {
		this.addParameter(name, type, null, Integer.MIN_VALUE, Integer.MAX_VALUE, required);
	}

	/**
	 * Ottieni i parametri (<b>nomi</b>) che valida questa istanza di validator
	 * */
	public String[] getParameters() {
		String[] params = new String[this.parameters.size()];
		int i = 0;
		for (Map.Entry<String, ParameterEntry> paramEntry : this.parameters.entrySet()) {
			params[i] = paramEntry.getValue().getName();
		}
		return params;
	}
}
