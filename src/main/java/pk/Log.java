package pk;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Log {

    private static Logger LOG = LogManager.getLogger(Log.class);
    boolean isLogActive;

    public Log(){
        if("True".equals(System.getProperty("traceMode"))){
            isLogActive = true;
        }
        else{
            isLogActive = false;
        }
    }
    
    public void logMessage(String message){
        if (isLogActive)
        {
            LOG.trace(message);
        }
    }
}
