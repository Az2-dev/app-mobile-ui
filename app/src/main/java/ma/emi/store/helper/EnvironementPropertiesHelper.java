package ma.emi.store.helper;

import android.content.Context;

import java.io.IOException;
import java.util.Properties;

public class EnvironementPropertiesHelper {

    private static final String API_BASE_URL_KEY = "api.base-url";

    private static String API_BASE_URL;

    public static void initEnvironementProperties(final Context context) throws IOException {
        Properties applicationProperties = new Properties();
        applicationProperties.load(context.getAssets().open("application.properties"));
        EnvironementPropertiesHelper.setApiBaseUrl(applicationProperties.getProperty(EnvironementPropertiesHelper.API_BASE_URL_KEY));
    }

    public static String getApiBaseUrl() {
        return EnvironementPropertiesHelper.API_BASE_URL;
    }

    public static void setApiBaseUrl(String apiBaseUrl) {
        EnvironementPropertiesHelper.API_BASE_URL = apiBaseUrl;
    }

}
