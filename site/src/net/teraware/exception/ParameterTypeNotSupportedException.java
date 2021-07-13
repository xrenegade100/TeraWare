package net.teraware.exception;

/**
 * Eccezione throwata se al ParametersValidator viene passato un tipo numerico non supportato.
 * Runtime Exception perch� lo sviluppatore che usa l'API deve correggere il codice.
 * */
@SuppressWarnings("rawtypes")
public class ParameterTypeNotSupportedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ParameterTypeNotSupportedException(Class c) {
		super(
			"Class of type " +
			c.getName() +
			" is not supported. Use one of Integer, Double or Float instead"
		);
	}
}
