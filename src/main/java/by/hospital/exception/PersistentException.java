package by.hospital.exception;

/**
 * Created by Admin on 08.04.2017.
 */
public class PersistentException extends Exception {
    public PersistentException(){

    }

    public PersistentException (String message){
        super(message);
    }

    public PersistentException (String message, Throwable cause){
        super(message, cause);
    }

    public PersistentException (Throwable cause){
        super(cause);
    }

    public PersistentException(String message, Throwable cause, boolean enableSuppression, boolean writebleStackTrace){
        super(message, cause,enableSuppression,writebleStackTrace);
    }
}
