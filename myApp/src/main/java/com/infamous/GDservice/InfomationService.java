package com.infamous.GDservice;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.Json;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.DriveScopes;


public class InfomationService {

	public static final String APPLICATION_NAME = "Demo Upload 2 Google Drive";

	public static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"),
			".credentials/demo-spring-upload");

	public static FileDataStoreFactory DATA_STORE_FACTORY;

	public static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	public static HttpTransport HTTP_TRANSPORT;

	public static final List<String> SCOPES = Arrays.asList(DriveScopes.DRIVE);

	static {
		try {
			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
		} catch (Throwable t) {
			t.printStackTrace();
			System.exit(1);
		}
	}

	public InfomationService(){}
	
	public  Credential authorize() throws IOException, ParseException {
		JSONParser parser=new JSONParser();
		JSONObject json = (JSONObject) parser.parse("{\n" +
                "\t\"installed\": {\n" +
                "\t\t\"client_id\": \"493968193744-udj51ek65bv4moi08jsag81hnc9gfhbk.apps.googleusercontent.com\",\n" +
                "\t\t\"project_id\": \"khanhfirstprojectpleaserespect\",\n" +
                "\t\t\"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
                "\t\t\"token_uri\": \"https://accounts.google.com/o/oauth2/token\",\n" +
                "\t\t\"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
                "\t\t\"client_secret\": \"5Mfbnj-zgqXcZBrjYwH5JHfi\",\n" +
                "\t\t\"redirect_uris\": [\n" +
                "\t\t\t\"urn:ietf:wg:oauth:2.0:oob\",\n" +
                "\t\t\t\"http://localhost\"\n" +
                "\t\t]\n" +
                "\t}\n" +
                "}\n");
		//json.
		
		InputStream in =new ByteArrayInputStream(json.toString().getBytes());
		
		
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				clientSecrets, SCOPES).setDataStoreFactory(DATA_STORE_FACTORY).setAccessType("offline").build();
		Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("donkihote2305@gmail.com");
		return credential;
	}
	public static void main(String... args){
		InputStream in = InfomationService.class.getResourceAsStream("client_secret.json");
		
		System.out.print(in.toString());
	}
	
}
