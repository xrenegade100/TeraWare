package net.teraware.util;

import com.google.gson.Gson;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import javax.servlet.ServletContext;

/**
 * Classe di utilit� per leggere e scrivere un file di configurazione che si trova nella cartella WEB-INF
 * */
public class ConfigHelper {

	private ServletContext ctx;
	private final String configPath;

	/**
	 * @param ctx Contesto della servlet
	 * */
	public ConfigHelper(ServletContext ctx) {
		this.ctx = ctx;
		this.configPath = ctx.getRealPath("/") + "/WEB-INF/config.json";
	}

	/**
	 * Scrive il file <pre>config.json</pre> nella path passata dal parametro <pre>ctx</pre> del costruttore
	 * @param config L'oggetto config da scrivere nel file
	 * */
	public void writeConfig(Config config) throws IOException {
		File configFile = new File(this.configPath);

		if (configFile.exists()) return;
		BufferedWriter writer = new BufferedWriter(
			new OutputStreamWriter(new FileOutputStream(configFile))
		);
		writer.write(new Gson().toJson(config));
		writer.flush();
		writer.close();
	}

	/**
	 * Legge il file <pre>config.json</pre> dalla path passata dal parametro <pre>ctx</pre> del costruttore
	 * */
	public Config readConfig() throws FileNotFoundException {
		Scanner sc = new Scanner(new File(configPath));
		StringBuilder finalString = new StringBuilder();
		while (sc.hasNextLine()) {
			finalString.append(sc.nextLine());
		}
		sc.close();
		return new Gson().fromJson(finalString.toString(), Config.class);
	}

	public ServletContext getServletCtx() {
		return this.ctx;
	}
}
