package org.project;

import io.quarkus.qute.i18n.Localized;
import io.quarkus.qute.i18n.MessageBundles;
import org.project.service.i18n.messages.AppMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

/**
 * Utility class
 *
 */

public class CountryUtil {

    private static final Logger logger = LoggerFactory.getLogger(CountryUtil.class);

    private CountryUtil() {
    }

    /**
     * Resolve the localized country name
     *
     * @param abbr country name abbreviation e.g <i>BGR</i>
     * @param locale the locale
     * @return localized name of the country, or null if the message key is missing
     */
    public static String resolveLocalizedCountryName(String abbr, String locale) {
        AppMessages appMessages;
        try {
            if (!locale.equalsIgnoreCase(Locale.ENGLISH.getLanguage()))
                appMessages = MessageBundles.get(AppMessages.class, Localized.Literal.of(locale));
            else appMessages = MessageBundles.get(AppMessages.class);
            if (abbr != null && !abbr.isEmpty()) {
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
                        return null;
                }
            } else return null;
        } catch (Exception e) {
            logger.error("Unable to get MessageBundle for locale: {}", locale);
            return "Unknown";
        }
    }
}
