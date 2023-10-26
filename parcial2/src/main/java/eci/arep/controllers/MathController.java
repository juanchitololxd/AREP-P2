package eci.arep.controllers;
import eci.arep.services.LucasService;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;


public class MathController {

    public static void main(String... args){
        port(getPort());
        get("lucasseq", (req,res) -> {
            List<Integer> seq = new ArrayList<>();
            int limit = Integer.parseInt(req.queryMap().get("value").value());
            LucasService.getSequence(limit, seq);
            res.header("ContentType", "application/json");

            return "{\"operation\": \"Secuencia de Lucas\", \"output\": \"" +
                    seq + "\"input\": \"" +
                    limit + "\"}";
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
