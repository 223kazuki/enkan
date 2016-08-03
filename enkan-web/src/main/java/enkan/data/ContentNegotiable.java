package enkan.data;

import javax.ws.rs.core.MediaType;
import java.util.Locale;

/**
 * @author kawasima
 */
public interface ContentNegotiable extends Extendable {
    default MediaType getMediaType() {
        return (MediaType) getExtension("mediaType");
    }

    default void setMediaType(MediaType mediaType) {
        setExtension("mediaType", mediaType);
    }

    default Locale getLocale() {
        return (Locale) getExtension("locale");
    }

    default void setLocale(Locale locale) {
        setExtension("locale", locale);
    }
}
