package test.up.day.day.com.traintestone.util;

import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Administrator on 2017/6/30.
 */

public final class XLogger {

    private static final String DEFAULT_TAG = "KyokuX";

    private static boolean sEnable = true;
    private static String sTag = DEFAULT_TAG;
    private static String mLogFileTag = null;

    private static File mLogFile = null;

    /**
     * Set current log tag.
     *
     * @param tag tag to set.
     * @return true if set success, false otherwise.
     */
    public static boolean setTag(String tag) {
        if (TextUtils.isEmpty(tag)) {
            return false;
        }
        sTag = tag;
        return true;
    }

    /**
     * Open / Close all logs.
     *
     * @param enable true for open, false for close.
     */
    public static void setEnable(boolean enable) {
        sEnable = enable;
    }

    /**
     * Get current log tag.
     *
     * @return current log tag.
     */
    public static String getCurrentTag() {
        return sTag;
    }

    /**
     * Return current log state.
     *
     * @return true for open, false for close.
     */
    public static boolean isEnable() {
        return sEnable;
    }

    /**
     * Log with the common tag, default is "KyokuX", in verbose level.
     *
     * @param message log message.
     */
    public static void v(String message) {
        v(sTag, message);
    }

    /**
     * Log with the common tag, default is "KyokuX", in debug level.
     *
     * @param message log message.
     */
    public static void d(String message) {
        d(sTag, message);
    }

    /**
     * Log with the common tag, default is "KyokuX", in info level.
     *
     * @param message log message.
     */
    public static void i(String message) {
        i(sTag, message);
    }

    /**
     * Log with the common tag, default is "KyokuX", in warn level.
     *
     * @param message log message.
     */
    public static void w(String message) {
        w(sTag, message);
    }

    /**
     * Log with the common tag, default is "KyokuX", in error level.
     *
     * @param message log message.
     */
    public static void e(String message) {
        e(sTag, message);
    }

    /**
     * Log with the special tag, in verbose level.
     *
     * @param tag log tag.
     * @param message log message.
     */
    public static void v(String tag, String message) {
        if (!sEnable) {
            return;
        }
        saveLogToFile(message, tag);
        Log.v(tag, message);
    }

    /**
     * Log with the special tag, in debug level.
     *
     * @param tag log tag.
     * @param message log message.
     */
    public static void d(String tag, String message) {
        if (!sEnable) {
            return;
        }
        saveLogToFile(message, tag);
        Log.d(tag, message);
    }

    /**
     * Log with the special tag, in info level.
     *
     * @param tag log tag.
     * @param message log message.
     */
    public static void i(String tag, String message) {
        if (!sEnable) {
            return;
        }
        saveLogToFile(message, tag);
        Log.i(tag, message);
    }

    /**
     * Log with the special tag, in warn level.
     *
     * @param tag log tag.
     * @param message log message.
     */
    public static void w(String tag, String message) {
        if (!sEnable) {
            return;
        }
        saveLogToFile(message, tag);
        Log.w(tag, message);
    }

    /**
     * Log with the special tag, in error level.
     *
     * @param tag log tag.
     * @param message log message.
     */
    public static void e(String tag, String message) {
        if (!sEnable) {
            return;
        }
        saveLogToFile(message, tag);
        Log.e(tag, message);
    }

    /**
     * Begin to record follow-up logs to a specified file until call end.
     *
     * @param fileName log file name with absolute path.
     * @return true if log begin, false otherwise.
     */
    public static boolean beginLogFile(String fileName) {
        return beginLogFile(sTag, fileName);
    }

    public static boolean beginLogFile(String tag, String fileName) {
        if (TextUtils.isEmpty(fileName) || TextUtils.isEmpty(tag)) {
            return false;
        }
        File logFile = new File(fileName);
        try {
            if (!logFile.exists()) {
                File parent = logFile.getParentFile();
                if (parent == null) {
                    parent = new File(logFile.getParent());
                }
                if (!parent.exists()) {
                    parent.mkdirs();
                }
                if (!logFile.createNewFile()) {
                    return false;
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
            return false;
        }
        mLogFile = logFile;
        mLogFileTag = tag;
        return true;
    }

    /**
     * Stop record log to file.
     */
    public static void stopLogFile() {
        mLogFile = null;
        mLogFileTag = null;
    }

    private static void saveLogToFile(String message, String tag) {
        if (mLogFile == null || TextUtils.isEmpty(mLogFileTag)) {
            return;
        }
        if (!mLogFileTag.equals(tag)) {
            return;
        }
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(mLogFile, true));
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            bufferedWriter.close();
            Log.d(tag, "save log success.");
        } catch(IOException e) {
            e.printStackTrace();
            Log.d(tag, "save log failture: " + e.getLocalizedMessage());
        }
    }

    private XLogger() {
        // Noninstantiable utility class.
    }
}