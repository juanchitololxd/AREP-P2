package eci.arep.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;
/**
 * Proxy server that redirects to EC2 instances using round robin
 */
public class ProxyController {
    public static List<String> servers = instanceServersList();
    private static final String USER_AGENT = "Mozilla/5.0";
    public static List<Boolean> useServer;


    public static void main(String... args){
        port(5000);
        get("lucasseq", (req,res) -> {
            StringBuilder response = new StringBuilder();
            URL obj = new URL(getAvailableServer() +"lucasseq?value=" + req.queryMap("value").value());
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
            }
            return response.toString();
        });
    }

    private static String getAvailableServer() {
        for (int i = 0; i < useServer.size(); i++){
            if (!useServer.get(i)){
                useServer.set(i, true);
                return servers.get(i);
            }
        }

        useServer.replaceAll(ignored -> false);
        useServer.set(0, true);
        return servers.get(0);
    }

    private static List<String> instanceServersList() {
        List<String> rta = new ArrayList<>();
        useServer = new ArrayList<>();
        if (false){
            rta.add("https://ec2-34-229-24-87.compute-1.amazonaws.com:4567/");
            useServer.add(false);
            rta.add("https://ec2-34-229-24-87.compute-1.amazonaws.com:4568/");
            useServer.add(false);
        }else {
            rta.add("http://localhost:4567/");
            useServer.add(false);
            rta.add("http://localhost:4568/");
            useServer.add(false);
        }

        return rta;
    }

}
