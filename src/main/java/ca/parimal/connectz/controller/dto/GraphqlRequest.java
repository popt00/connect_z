package ca.parimal.connectz.controller.dto;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GraphqlRequest {
    public JSONObject send(String userName, String query){
        String url ="https://graphql.anilist.co";
//        System.out.println("Query:__"+query);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString("query="+query))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            //System.out.println(response.body().toString());
            JSONParser parse = new JSONParser();
            JSONObject json = (JSONObject) parse.parse(response.body().toString());
            return json;
        }
        catch (ConnectException e){
            System.out.println("Connection refused, \n\nCheck Netwok connection & Try again \n\n");
            return null;
        }
        catch (IOException e) {
            System.out.println("IO exception: "+e.getClass().getSimpleName());
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            System.out.println("Interrupt exception: "+e.getClass().getSimpleName());
            throw new RuntimeException(e);
        } catch (ParseException e) {
            System.out.println("Parse exception: "+e.getClass().getSimpleName());
            throw new RuntimeException(e);
        }
    }
}