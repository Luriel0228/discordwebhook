package com.github.luriel.mcpl_customcommand.api;

import java.io.OutputStream;
import java.net.InetAddress;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class webhook {

    public static void sendWebhook() {
        try {
            String tokenWebhook = "URL";
            InetAddress localHost = InetAddress.getLocalHost();
            String serverName = localHost.getHostName();
            String serverIP = localHost.getHostAddress();

            String title = "커스텀커멘드 플러그인";
            String message = "서버이름 : `" + serverName + "`\n서버 주소 : `" + serverIP + "`";
            String jsonBrut = "{\"embeds\": [{" +
                    "\"title\": \"" + title + "\"," +
                    "\"description\": \"" + message + "\"," +
                    "\"color\": 15258703" +
                    "}]}";

            URL url = new URL(tokenWebhook);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.addRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            OutputStream stream = con.getOutputStream();
            stream.write(jsonBrut.getBytes());
            stream.flush();
            stream.close();

            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            con.getInputStream().close();
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
