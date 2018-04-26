package practicum.getfitla_v3;

import org.junit.Test;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by teagan on 4/12/18.
 */

public class MapsActivityTest {

    @Test
    public void DownloadKmlFileURLTest() {
        String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        String mUrl = "http://www.google.com/maps/d/u/0/kml?forcekml=1&mid=1LuVbYbhZpTtSep4GIRD0BEXWeKc";
        Pattern urlPattern = Pattern.compile(regex);
        Matcher matcher = urlPattern.matcher(mUrl);

        System.out.println("Valid URL format: " + matcher.matches());

        URL url = null;
        int code = 0;
        try {
            url = new URL(mUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            code = connection.getResponseCode();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        if(code == 200) {
            System.out.println("Reachable URL");
        } else {
            System.out.println("URL not reachable, code: " + code);
        }


    }

}