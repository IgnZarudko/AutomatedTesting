package by.ignot.automation.qa.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import java.util.HashMap;

public class LogProvider {
    private static HashMap<Long, Logger> logs;

    public static Logger getLog() {
        if (logs == null) {
            logs = new HashMap<>();
        }

        Long threadId = Thread.currentThread().getId();
        Logger log = logs.get(threadId);
        if (log == null) {
            log = LogManager.getLogger("Test thread " + threadId);
            ThreadContext.put("ThreadID", String.valueOf(threadId));
            logs.put(threadId, log);
        }

        return log;
    }
}