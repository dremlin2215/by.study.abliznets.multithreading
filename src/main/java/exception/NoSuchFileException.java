package exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NoSuchFileException extends Exception {

    private static final Logger LOGGER = LogManager.getLogger(NoSuchFileException.class);
    private static final long serialVersionUID = -2432562L;

    public NoSuchFileException(String message) {
        super(message);
        LOGGER.warn("NoSuchFileException \n");
    }

    public NoSuchFileException() {
        super();
        LOGGER.warn("NoSuchFileException \n");
    }

    public NoSuchFileException(String message, Throwable cause) {
        super(message, cause);
        LOGGER.warn("NoSuchFileException \n");
    }

    public NoSuchFileException(Throwable cause) {
        super(cause);
        LOGGER.warn("NoSuchFileException \n");
    }

    protected NoSuchFileException(String message, Throwable cause,
                                  boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        LOGGER.warn("NoSuchFileException \n");
    }
}
