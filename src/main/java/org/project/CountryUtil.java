package org.project;

import io.quarkus.qute.i18n.Localized;
import io.quarkus.qute.i18n.MessageBundles;
import org.project.service.i18n.messages.AppMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

public class CountryUtil {

    private static final Logger logger = LoggerFactory.getLogger(CountryUtil.class);

    private CountryUtil() {
    }

    public static String resolveLocalizedCountryName(String abbr, String locale) {
        AppMessages appMessages;
        try {
            if (!locale.equalsIgnoreCase(Locale.ENGLISH.getLanguage()))
                appMessages = MessageBundles.get(AppMessages.class, Localized.Literal.of(locale));
            else appMessages = MessageBundles.get(AppMessages.class);
            switch (abbr) {
                case "ARM":
                    return appMessages.ARM();
                case "DNK":
                    return appMessages.DNK();
                case "BGR":
                    return appMessages.BGR();
                case "CAN":
                    return appMessages.CAN();
                case "BEL":
                    return appMessages.BEL();
                default:
                    return "No Country Found";
            }
        } catch (Exception e) {
            logger.error("Unable to get MessageBundle for locale: {}", locale);
            return "Unknown";
        }
    }
}
