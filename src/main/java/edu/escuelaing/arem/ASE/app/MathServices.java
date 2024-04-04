package edu.escuelaing.arem.ASE.app;
import java.util.ArrayList;

import static spark.Spark.*;
/**
 * Hello world!
 *
 */
public class MathServices
{
    public static void main( String[] args )
    {
        port(getPort());

        get("/factors", (req, res) -> {
            res.type("application/json");
            int n = Integer.parseInt(req.queryParams("value"));
            String result = "{\"operation\":\"factors\", \"input\":\"" + n + "\", \"output\":\"";
            ArrayList<Integer> factorsToAdd = factors(n);
            for (int i = 0; i < factorsToAdd.size(); i++) {
                if (i != factorsToAdd.size()-1) {
                    result += factorsToAdd.get(i) + ", ";
                } else {
                    result += factorsToAdd.get(i);
                }
            }
            result += "\"}";
            return result;
        });

        get("/primes", (req, res) -> {
            res.type("application/json");
            int n = Integer.parseInt(req.queryParams("value"));
            String result = "{\"operation\":\"primes\", \"input\":\"" + n + "\", \"output\":\"";
            for (int i = 1; i <= n; i++) {
                if (factors(i).size() == 2) {
                    if (i != 2) {
                        result += ", " + i;
                    } else {
                        result += i + "";
                    }
                }
            }
            result += "\"}";
            return result;
        });
    }

    private static ArrayList<Integer> factors (int n) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 1; i <= n/2; i++) {
            if (n % i == 0) {
                result.add(i);
            }
        }
        result.add(n);
        return result;
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

}
