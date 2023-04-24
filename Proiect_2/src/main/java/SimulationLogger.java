import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class SimulationLogger {

    private static final Logger logger = Logger.getLogger(SimulationLogger.class.getName());
    private static FileHandler fileHandler;

    public static void initLogger() {
        try {
            fileHandler = new FileHandler("simulation_log.txt");
            Formatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to initialize logger", e);
        }
    }

    public static void log(String message) {
        logger.log(Level.INFO, message);
    }

    public static void closeLogger() {
        for (Handler handler : logger.getHandlers()) {
            handler.close();
        }
    }
}
