import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.FileReader;
import java.io.IOException;

public class ShamirSecretSharing {
    
    public static BigInteger decodeYValue(String base, String value) {
        return new BigInteger(value, Integer.parseInt(base));
    }
    
    public static BigInteger lagrangeInterpolation(List<Point> points) {
        // calculate f(0) using Lagrange interpolation


        BigInteger result = BigInteger.ZERO;
        
        for (int j = 0; j < points.size(); j++) {
            Point pointJ = points.get(j);
            BigInteger numerator = BigInteger.ONE;
            BigInteger denominator = BigInteger.ONE;
            
            for (int m = 0; m < points.size(); m++) {
                if (m != j) {
                    Point pointM = points.get(m);
// for f(0) we calculate (0 - x_m)/(x_j - x_m)
                    numerator = numerator.multiply(BigInteger.ZERO.subtract(pointM.x));
                    denominator = denominator.multiply(pointJ.x.subtract(pointM.x));
                }
            }
            
            // calculate y_j *(numerator / denominator)
            BigInteger term = pointJ.y.multiply(numerator).divide(denominator);
            result = result.add(term);
        }
        
        return result;
    }
    
    public static BigInteger solveTestCase(String filePath) throws IOException {
        Gson gson = new Gson();
        JsonObject data = gson.fromJson(new FileReader(filePath), JsonObject.class);
        
        JsonObject keys = data.getAsJsonObject("keys");
        int k = keys.get("k").getAsInt();
        
        List<Point> points = new ArrayList<>();
        
        for (Map.Entry<String, JsonElement> entry : data.entrySet()) {
            String key = entry.getKey();
            if (!key.equals("keys")) {
                JsonObject valueObj = entry.getValue().getAsJsonObject();
                String base = valueObj.get("base").getAsString();
                String value = valueObj.get("value").getAsString();
                
                BigInteger x = new BigInteger(key);
                BigInteger y = decodeYValue(base, value);
                points.add(new Point(x, y));
                
                if (points.size() == k) {
                    break;
                }
            }
        }
        
        return lagrangeInterpolation(points);
    }
    
    public static void main(String[] args) {
        try {
            System.out.println("Solving First Sample Test Case:");
            BigInteger sampleResult = solveTestCase("sample_test_case.json");
            System.out.println("Constant term C: " + sampleResult);
            
            System.out.println("\nSolving Second Sample Test Case:");
            BigInteger secondResult = solveTestCase("second_test_case.json");
            System.out.println("Constant term C: " + secondResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

