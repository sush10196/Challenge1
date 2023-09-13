package Briq;


	import java.io.FileWriter;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.net.HttpURLConnection;
	import java.net.URL;
	import java.text.SimpleDateFormat;
	import java.util.Date;
	import java.util.regex.Matcher;
	import java.util.regex.Pattern;
	import org.json.JSONArray;
	import org.json.JSONObject;

	public class APIChallenge {
	    public static void main(String[] args) {
	        try {
	            // Step 1: Make an HTTP GET request to the URL
	            String apiUrl = "https://data.sfgov.org/resource/p4e4-a5a7.json";
	            HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
	            connection.setRequestMethod("GET");

	            // Step 2: Retrieve JSON data from the response
	            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
	            StringBuilder response = new StringBuilder();
	            int charRead;
	            char[] buffer = new char[1024];
	            while ((charRead = reader.read(buffer)) != -1) {
	                response.append(buffer, 0, charRead);
	            }
	            reader.close();

	            // Step 3: Parse JSON data into a table format
	            JSONArray jsonArray = new JSONArray(response.toString());

	            // Step 4: Print current timestamp
	            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yy-HH-mm-ss");
	            String timestamp = dateFormat.format(new Date());
	            System.out.println("Current Timestamp: " + timestamp);

	            // Step 5: Print the table data to the console
	            for (int i = 0; i < jsonArray.length(); i++) {
	                JSONObject jsonObject = jsonArray.getJSONObject(i);
	                System.out.println(jsonObject.toString());
	            }

	            // Step 6: Use regex to search for lines related to "roof"
	            String regex = ".*roof.*";
	            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

	            for (int i = 0; i < jsonArray.length(); i++) {
	                JSONObject jsonObject = jsonArray.getJSONObject(i);
	                String jsonString = jsonObject.toString();
	                Matcher matcher = pattern.matcher(jsonString);
	                if (matcher.find()) {
	                    System.out.println("Line related to 'roof': " + jsonString);
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}




