package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * pretty printer
 *
 * @author xiaotian.huang
 * @date 2019-04-08
 */
public class PrettyPrinter {

    private static ObjectMapper mapper = new ObjectMapper();

    public static  <T> void println(T t) {
        mapper.configure(SerializationFeature.INDENT_OUTPUT,true);
        String json;
        try {
            json = mapper.writeValueAsString(t);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        System.out.println("\r\n"+json);
    }
}

