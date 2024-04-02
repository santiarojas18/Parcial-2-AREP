package edu.escuelaing.arem.ASE.app;
import java.util.ArrayList;

import static spark.Spark.*;

public class ServiceProxy {
    private static String[] urlServices = {"http://ec2-44-211-150-196.compute-1.amazonaws.com:4567", "http://ec2-34-207-164-18.compute-1.amazonaws.com:4567"};
    private static int service = 0;
    public static void main( String[] args )
    {
        staticFiles.location("/public");
        port(getPort());

        get("/factors", (req, res) -> {
            res.type("application/json");
            int n = Integer.parseInt(req.queryParams("value"));
            return HttpConnection.makeGet(urlToUse() + "/factors?value=" + n);
        });

        get("/primes", (req, res) -> {
            res.type("application/json");
            int n = Integer.parseInt(req.queryParams("value"));
            return HttpConnection.makeGet(urlToUse() + "/primes?value=" + n);
        });
    }

    private static String urlToUse () {
        String url = urlServices[service];
        if (service == 0) {
            service = 1;
        } else if (service == 1) {
            service = 0;
        }
        return url;
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 45000;
    }
}
