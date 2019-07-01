package exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmptyFileException extends Exception {

    private static final Logger LOGGER = LogManager.getLogger(EmptyFileException.class);
    private static final long serialVersionUID = 3456L;

    public EmptyFileException(String message) {
        super(message);
        LOGGER.warn("EmptyFileException \n");
    }

    public EmptyFileException() {
        super();
        LOGGER.warn("EmptyFileException \n");
    }

    public EmptyFileException(String message, Throwable cause) {
        super(message, cause);
        LOGGER.warn("EmptyFileException \n");
    }

    public EmptyFileException(Throwable cause) {
        super(cause);
        LOGGER.warn("EmptyFileException \n");
    }

    protected EmptyFileException(String message, Throwable cause,
                                 boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        LOGGER.warn("EmptyFileException \n");
    }
}
