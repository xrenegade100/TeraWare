package net.teraware.util;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.servlet.ServletContext;
import net.teraware.auth.Secrets;

public class SecretsReader {

	private static SecretsReader instance;
	private static Secrets secrets;

	public static void initInstance(ServletContext ctx) {
		if (instance == null) instance = new SecretsReader(ctx);
	}

	public static SecretsReader getInstance() {
		return instance;
	}

	public Secrets getSecrets() {
		return secrets;
	}

	private SecretsReader(ServletContext ctx) {
		try {
			String jsonSecrets = readFile(ctx);
			secrets = new Gson().fromJson(jsonSecrets, Secrets.class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Legge il file `secrets.json` che si trova nella cartella `WebContents/WEB-INF/`
	 * @param ctx L'istanza di {@link ServletContext} che contiene le informazioni sulla servlet che chiama questo metodo
	 * @returns Una stringa contente l'intero contenuto del file
	 * */
	private String readFile(ServletContext ctx) throws FileNotFoundException {
		InputStream is = ctx.getResourceAsStream("/WEB-INF/secrets.json");
		InputStreamReader isReader = new InputStreamReader(is);
		BufferedReader bf = new BufferedReader(isReader);
		String line;
		StringBuilder sb = new StringBuilder();
		try {
			while ((line = bf.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
