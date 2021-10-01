package com.doglab.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.swing.JOptionPane;

import org.apache.commons.text.StringEscapeUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.doglab.entities.HistoryLabel;

public class API {

	static String title = "Mesa de Dados";
	public static int current = 1;
	public static String slot = "Slot " + current;

	public static void main(String[] args) throws JSONException {
		String gameCode = "39ad5357f3", pageText, slots[];
		//Um botão de Nova mesa para gerar a nova página no editor de texto online
		gameCode = generateNewPage();
		JOptionPane.showMessageDialog(null,"Código da sua mesa: " +  gameCode);
		//Em algum momento carregar as informações para o mestre ter as fichas atualizadas
		pageText = readPage(gameCode);
		JOptionPane.showMessageDialog(null,"Conteúdo da página: \n" +  pageText);
		//Em algum momento salvar as informações do personagem no site
		updatePage(gameCode, "teste");
	}


	//O mï¿½todo gera um novo documento com um novo cï¿½digo na URL para ser usado na mesa
	public static String generateNewPage() throws JSONException {
		String generateNewPage = null;

		String text = "<p>{\\\"Slot 1\\\": \\\"Vazio\\\",</p><p>\\\"Slot 2\\\": \\\"Vazio\\\",</p><p>\\\"Slot 3\\\": \\\"Vazio\\\",</p><p>\\\"Slot 4\\\": \\\"Vazio\\\",</p><p>\\\"Slot 5\\\": \\\"Vazio\\\",</p><p>\\\"Slot 6\\\": \\\"Vazio\\\",</p><p>\\\"Slot 7\\\": \\\"Vazio\\\",</p><p>\\\"Slot 8\\\": \\\"Vazio\\\",</p><p>\\\"Slot 9\\\": \\\"Vazio\\\",</p><p>\\\"Slot 10\\\": \\\"Vazio\\\",</p><p>\\\"Slot 11\\\": \\\"Vazio\\\",</p><p>\\\"Slot 12\\\": \\\"Vazio\\\",</p><p>\\\"Slot 13\\\": \\\"Vazio\\\",</p><p>\\\"Slot 14\\\": \\\"Vazio\\\",</p><p>\\\"Slot 15\\\": \\\"Vazio\\\",</p><p>\\\"Slot 16\\\": \\\"Vazio\\\",</p><p>\\\"Slot 17\\\": \\\"Vazio\\\",</p><p>\\\"Slot 18\\\": \\\"Vazio\\\",</p><p>\\\"Slot 19\\\": \\\"Vazio\\\",</p><p>\\\"Slot 20\\\": \\\"Vazio\\\"}</p>";

		try {
			URL url = new URL("https://api.newtextdocument.com/texts/share");
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setRequestMethod("POST");
			http.setDoOutput(true);
			http.setRequestProperty("Content-Type", "application/json");

			String data = "{\"title\":\"" + generateNewDicePage() + "\",\"content\":\"" + text
					+ "\",\"editable\":true,\"expiration\":\"never\"}";

			byte[] out = data.getBytes(StandardCharsets.UTF_8);

			OutputStream stream = http.getOutputStream();
			stream.write(out);

			//System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
			BufferedReader br = null;
			if (http.getResponseCode() == 200 || http.getResponseCode() == 201) {
				br = new BufferedReader(new InputStreamReader(http.getInputStream()));
				String strCurrentLine;
				while ((strCurrentLine = br.readLine()) != null) {
					//System.out.println(strCurrentLine);
					strCurrentLine = strCurrentLine.replace("{\"data\":", "").replace("}}", "}");
					JSONObject jsonObject = new JSONObject(strCurrentLine);
					generateNewPage = (String) jsonObject.get("hash");
				}

			} else { // Quando a pï¿½gina retorna código de erro.
				br = new BufferedReader(new InputStreamReader(http.getErrorStream()));
				readLinesFromBufferedReader(br);
			}
			http.disconnect();
		} catch (IOException e) {}
		return generateNewPage;
	}

	public static String generateNewDicePage() throws JSONException {
		
		String generateNewPage = null;
		String text = "<p>@</p>";
		
		try {
			URL url = new URL("https://api.newtextdocument.com/texts/share");
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setRequestMethod("POST");
			http.setDoOutput(true);
			http.setRequestProperty("Content-Type", "application/json");
			String data = "{\"title\":\"" + title + "\",\"content\":\"" + text
					+ "\",\"editable\":true,\"expiration\":\"never\"}";
			byte[] out = data.getBytes(StandardCharsets.UTF_8);
			OutputStream stream = http.getOutputStream();
			stream.write(out);
			//System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
			BufferedReader br = null;
			if (http.getResponseCode() == 200 || http.getResponseCode() == 201) {
				br = new BufferedReader(new InputStreamReader(http.getInputStream()));
				String strCurrentLine;
				while ((strCurrentLine = br.readLine()) != null) {
					//System.out.println(strCurrentLine);
					strCurrentLine = strCurrentLine.replace("{\"data\":", "").replace("}}", "}");
					JSONObject jsonObject = new JSONObject(strCurrentLine);
					generateNewPage = (String) jsonObject.get("hash");
				}

			} else { // Quando a pï¿½gina retorna cï¿½digo de erro.
				br = new BufferedReader(new InputStreamReader(http.getErrorStream()));
				readLinesFromBufferedReader(br);
			}
			http.disconnect();
		} catch (IOException e) {}
		return generateNewPage;
	}

	public static String readPageWithTitle(String gameCode) throws JSONException {
		String readPage = null;
		String[] strTitle = null;
		try {
			URL url = new URL("https://api.newtextdocument.com/texts/" + gameCode);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setRequestMethod("GET");
			http.setDoOutput(true);
			http.setRequestProperty("Content-Type", "application/json");

			//System.out.println("readPageWithTitle: "+http.getResponseCode() + " " + http.getResponseMessage());
			BufferedReader br = null;
			if (http.getResponseCode() == 200 || http.getResponseCode() == 201) {
				br = new BufferedReader(new InputStreamReader(http.getInputStream()));
				String strCurrentLine;
				while ((strCurrentLine = br.readLine()) != null) {
					//System.out.println(strCurrentLine);
					strTitle = strCurrentLine.split("title");
					strTitle[1] = strTitle[1].replace("\":\"", "").split("\",\"")[0];
					strCurrentLine = strCurrentLine.replace("{\"data\":", "").replace("}}", "}");
					strCurrentLine = StringEscapeUtils.unescapeHtml4(strCurrentLine);
					JSONObject jsonObject = new JSONObject(strCurrentLine);
					readPage = (String) jsonObject.get("content");
				}
			} else { // Quando a página retorna código de erro.
				br = new BufferedReader(new InputStreamReader(http.getErrorStream()));
				readLinesFromBufferedReader(br);
			}
			http.disconnect();
		} catch (IOException e) {}
		return readPage+"###"+strTitle[1];
	}
	
	public static String readPage(String gameCode) throws JSONException {
		String readPage = null;
		try {
			URL url = new URL("https://api.newtextdocument.com/texts/" + gameCode);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setRequestMethod("GET");
			http.setDoOutput(true);
			http.setRequestProperty("Content-Type", "application/json");

			//System.out.println("ReadPage: "+http.getResponseCode() + " " + http.getResponseMessage());
			BufferedReader br = null;
			if (http.getResponseCode() == 200 || http.getResponseCode() == 201) {
				br = new BufferedReader(new InputStreamReader(http.getInputStream()));
				String strCurrentLine;
				while ((strCurrentLine = br.readLine()) != null) {
					//System.out.println(strCurrentLine);
					strCurrentLine = strCurrentLine.replace("{\"data\":", "").replace("}}", "}");
					strCurrentLine = StringEscapeUtils.unescapeHtml4(strCurrentLine);
					JSONObject jsonObject = new JSONObject(strCurrentLine);
					readPage = (String) jsonObject.get("content");
				}
			} else { // Quando a página retorna o código de erro.
				br = new BufferedReader(new InputStreamReader(http.getErrorStream()));
				readLinesFromBufferedReader(br);
			}
			http.disconnect();
		} catch (IOException e) {}
		return readPage;
	}

	public static void updateDiceTable(String gameCode, String save) throws JSONException {
		//Estruturar o texto da pagina para modificar somente uma linha
		String newText = save;
		String data = "{\"title\":\"" + title + "\",\"content\":\"" + newText
				+ "\",\"editable\":true,\"expiration\":\"never\"}";
		try {
			System.out.println("Texto: "+save);
			System.out.println("Table: "+gameCode);
			URL url = new URL("https://api.newtextdocument.com/texts/" + gameCode);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setRequestProperty("X-HTTP-Method-Override", "PATCH");
			http.setRequestMethod("POST");
			http.setDoOutput(true);
			http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

			byte[] out = data.getBytes(StandardCharsets.UTF_8);

			OutputStream stream = http.getOutputStream();
			stream.write(out);

			//System.out.println("UpdateDiceTable: "+http.getResponseCode() + " " + http.getResponseMessage());
			BufferedReader br = null;
			if (http.getResponseCode() == 200 || http.getResponseCode() == 201) {
				br = new BufferedReader(new InputStreamReader(http.getInputStream()));
				readLinesFromBufferedReader(br);

			} else { // Quando a pï¿½gina retorna cï¿½digo de erro.
				br = new BufferedReader(new InputStreamReader(http.getErrorStream()));
				readLinesFromBufferedReader(br);
			}
			http.disconnect();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public static void updatePage(String gameCode, String save) throws JSONException {
		//Estruturar o texto da pagina para modificar somente uma linha
		
		String pageText = readPage(gameCode);
		JSONObject jsonObject = new JSONObject(pageText.replace("<p>", "").replace("</p>", ""));
		jsonObject.put(slot, save);
		String newText = jsonObject.toString()
				.replace(",",",</p><p>")
				.replace("{", "<p>{")
				.replace("}", "}</p>")
				.replace("\"", "\\\"");

		String data = "{\"title\":\"" + HistoryLabel.diceTable + "\",\"content\":\"" + newText
				+ "\",\"editable\":true,\"expiration\":\"never\"}";
		try {

			URL url = new URL("https://api.newtextdocument.com/texts/" + gameCode);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setRequestProperty("X-HTTP-Method-Override", "PATCH");
			http.setRequestMethod("POST");
			http.setDoOutput(true);
			http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

			byte[] out = data.getBytes(StandardCharsets.UTF_8);

			OutputStream stream = http.getOutputStream();
			stream.write(out);

			//System.out.println("UpdatePage: "+http.getResponseCode() + " " + http.getResponseMessage());
			BufferedReader br = null;
			if (http.getResponseCode() == 200 || http.getResponseCode() == 201) {
				br = new BufferedReader(new InputStreamReader(http.getInputStream()));
				readLinesFromBufferedReader(br);

			} else { // Quando a página retorna código
				br = new BufferedReader(new InputStreamReader(http.getErrorStream()));
				readLinesFromBufferedReader(br);
			}
			http.disconnect();
		} catch (IOException e) {
			System.out.println(e);
		}

	}
	
	public static void updatePassword(String gameCode, String save) throws JSONException {
		//Estruturar o texto da pagina para modificar somente uma linha
		String pageText = readPage(gameCode);
		String newText = save;
		String data = "{\"title\":\"" + title + "\",\"content\":\"" + newText
				+ "\",\"editable\":true,\"expiration\":\"never\"}";
		try {

			URL url = new URL("https://api.newtextdocument.com/texts/" + gameCode);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setRequestProperty("X-HTTP-Method-Override", "PATCH");
			http.setRequestMethod("POST");
			http.setDoOutput(true);
			http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

			byte[] out = data.getBytes(StandardCharsets.UTF_8);

			OutputStream stream = http.getOutputStream();
			stream.write(out);

			//System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
			BufferedReader br = null;
			if (http.getResponseCode() == 200 || http.getResponseCode() == 201) {
				br = new BufferedReader(new InputStreamReader(http.getInputStream()));
				readLinesFromBufferedReader(br);

			} else { // Quando a pï¿½gina retorna cï¿½digo de erro.
				br = new BufferedReader(new InputStreamReader(http.getErrorStream()));
				readLinesFromBufferedReader(br);
			}
			http.disconnect();
		} catch (IOException e) {
			System.out.println(e);
		}

	}

	private static void readLinesFromBufferedReader(BufferedReader br) throws IOException {
		String strCurrentLine;
		while ((strCurrentLine = br.readLine()) != null) {
			System.out.println(strCurrentLine);
		}
	}
}

