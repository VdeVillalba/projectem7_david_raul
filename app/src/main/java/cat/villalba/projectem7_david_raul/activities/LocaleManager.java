package cat.villalba.projectem7_david_raul.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;


import androidx.annotation.StringDef;
import androidx.preference.PreferenceManager;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Locale;

public class LocaleManager {
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({ENGLISH, CATALAN, SPANISH})
    public @interface LocaleDef {
        String[] SUPPORTED_LOCALES = {ENGLISH, CATALAN, SPANISH};
    }

    static final String ENGLISH = "en";
    static final String CATALAN = "ca";
    static final String SPANISH = "es";

    private static final String LANGUAGE_KEY = "language_key";

    public static Context setLocale(Context mContext) {
        return updateResources(mContext, getLanguagePref(mContext));
    }

    public static Context setNewLocale(Context mContext, @LocaleDef String language) {
        setLanguagePref(mContext, language);
        return updateResources(mContext, language);
    }

    public static String getLanguagePref(Context mContext) {
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        return mPreferences.getString(LANGUAGE_KEY, CATALAN);
    }

    private static void setLanguagePref(Context mContext, String localeKey) {
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        mPreferences.edit().putString(LANGUAGE_KEY, CATALAN).apply();
    }

    private static Context updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());
            config.setLocale(locale);
            context = context.createConfigurationContext(config);

        return context;
    }

    public static Locale getLocale(Resources res) {
        Configuration config = res.getConfiguration();
        return config.getLocales().get(0);
    }
}