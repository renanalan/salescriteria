package rasc.com.br.salescriteria.util;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONStringer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonHelper {

    public static final String GMT_ISO_DATE_TIME = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    public static String convertToString(JSONArray jsonArray) {
        String payload = "[]";
        try {
            JSONStringer stringer = new JSONStringer();
            stringer.array();
            for (int i = 0; i < jsonArray.length(); i++) {
                stringer.value(jsonArray.getJSONArray(i).getJSONObject(0));
            }
            stringer.endArray();
            payload = stringer.toString();
        } catch (JSONException e) {
            Log.e(JsonHelper.class.getName(), "Unable to convert json into string - " + e.getMessage(), e);
        }
        return payload;
    }

    public static <T> List<T> fromGsonPayload(String payload, Class<T> type) {
        return fromGsonPayload(payload, GMT_ISO_DATE_TIME, type);
    }

    public static <T> List<T> fromGsonPayload(JSONArray jsonArray, Class<T> type) {
        return fromGsonPayload(convertToString(jsonArray), GMT_ISO_DATE_TIME, type);
    }

    public static <T> List<T> fromGsonPayload(String payload, String datePattern, Class<T> type) {
        Gson gson = new GsonBuilder().setDateFormat(datePattern).create();
        Type collectionType = com.google.gson.internal.$Gson$Types.newParameterizedTypeWithOwner(null, ArrayList.class, type);
        return gson.fromJson(payload, collectionType);
    }
}
