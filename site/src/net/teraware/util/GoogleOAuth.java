package net.teraware.util;

import com.google.gson.Gson;
import java.io.IOException;
import net.teraware.auth.GoogleOAuthCodeResponse;
import net.teraware.auth.GoogleOAuthUser;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class GoogleOAuth {

	public static final String CODE_BASE_URL = "https://oauth2.googleapis.com/";
	public static final String GAPIS_BASE_URL = "https://www.googleapis.com/oauth2/";

	/**
	 * Dato un codice (ottenuto da oauth di Google), lo scambia con un access token e ottiene le info sull'utente
	 * @param code 	Il codice ottenuto dal form di oauth
	 * @returns Un'istanza di {@link GoogleOAuthUser} contenente i dati dell'utente restituiti da Google
	 * */
	public static GoogleOAuthUser getGoogleUser(String code)
		throws ClientProtocolException, IOException {
		//req to gapis/v3/userinfo with bearer access token
		GoogleOAuthCodeResponse codeExchange = exchangeCode(code);
		if (codeExchange == null) return null;
		HttpUriRequest userReqBuilder = RequestBuilder
			.get()
			.setUri(GAPIS_BASE_URL + "/v3/userinfo")
			.addHeader("Authorization", "Bearer " + codeExchange.getAccessToken())
			.build();
		HttpResponse userinfoResponse = HttpClients.custom().build().execute(userReqBuilder);
		return new Gson()
			.fromJson(EntityUtils.toString(userinfoResponse.getEntity()), GoogleOAuthUser.class);
	}

	/**
	 * FIXME Gestire gli errori relativi allo scambio
	 * Dato un codice lo scambia con un access token tramite endpoint di Google
	 * @param code Il codice ottenuto dal form di oauth da scambiare
	 * @returns L'access token ottenuto
	 * */
	private static GoogleOAuthCodeResponse exchangeCode(String code) {
		final String params[] = new String[] {
			"code:" + code,
			"client_id:888798606090-pnnru5lhij5sql06560cpp8uescchku6.apps.googleusercontent.com",
			"client_secret:" + SecretsReader.getInstance().getSecrets().getClientSecret(),
			"redirect_uri:http://localhost/TeraWere_Site/oauth",
			"grant_type:authorization_code"
		};

		RequestBuilder tokReqBuilder = RequestBuilder
			.post()
			.setUri(CODE_BASE_URL + "token")
			.addHeader("Content-Type", "application/x-www-form-urlencoded"); // Dalla documentazione di Google
		for (String param : params) { // Imposto i parametri della post request con un for loop
			final int separatorIndex = param.indexOf(':'); //nomeparametro:valore -> divido da 0 fino a indexof(:) e poi da indexof(:) fino alla fine
			String paramName = param.substring(0, separatorIndex);
			String paramValue = param.substring(separatorIndex + 1, param.length());
			tokReqBuilder.addParameter(paramName, paramValue);
			System.out.println(paramName + " " + paramValue);
		}
		HttpUriRequest accessTokenRequest = tokReqBuilder.build();
		HttpClient client = HttpClients.custom().build();
		GoogleOAuthCodeResponse resp = null;
		try {
			HttpResponse accessTokenResponse = client.execute(accessTokenRequest); // FIXME Gestire gli errori che possono succede qua (codice non esistente...)
			String jsonResponse = EntityUtils.toString(accessTokenResponse.getEntity());
			resp = new Gson().fromJson(jsonResponse, GoogleOAuthCodeResponse.class);
			return resp;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resp;
	}
}
