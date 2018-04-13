package practicum.getfitla_v3;

import org.junit.Test;

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

        System.out.println(matcher.matches());

    }

}