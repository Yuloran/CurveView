package com.yuloran.demo.util;

import android.util.Log;

import com.yuloran.demo.BuildConfig;

import java.util.Locale;

/**
 * Author & Date: Yuloran, 2018/7/22 11:33
 * Function:
 */
public final class Logger {
    private static final String APP_TAG = "DEMO";

    private static final boolean canLog = BuildConfig.DEBUG;

    private Logger() {
    }

    /**
     * Send a {@link Log#VERBOSE} log message.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void v(String tag, String msg) {
        if (canLog) {
            Log.v(APP_TAG, tag + ": " + msg);
        }
    }

    /**
     * Send a {@link Log#VERBOSE} log message and log the exception.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    public static void v(String tag, String msg, Throwable tr) {
        if (canLog) {
            Log.v(APP_TAG, tag + ": " + msg, tr);
        }
    }

    /**
     * Send a {@link Log#DEBUG} log message.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void d(String tag, String msg) {
        if (canLog) {
            Log.d(APP_TAG, tag + ": " + msg);
        }
    }

    /**
     * Send a {@link Log#DEBUG} log message.
     *
     * @param tag    Used to identify the source of a log message.  It usually identifies
     *               the class or activity where the log call occurs.
     * @param format A <a href="../util/Formatter.html#syntax">format string</a>
     * @param args   Arguments referenced by the format specifiers in the format
     *               string.  If there are more arguments than format specifiers, the
     *               extra arguments are ignored.  The number of arguments is
     *               variable and may be zero.  The maximum number of arguments is
     *               limited by the maximum dimension of a Java array as defined by
     *               <cite>The Java&trade; Virtual Machine Specification</cite>.
     *               The behaviour on a
     *               <tt>null</tt> argument depends on the <a
     *               href="../util/Formatter.html#syntax">conversion</a>.
     */
    public static void d(String tag, String format, Object... args) {
        if (canLog) {
            Log.d(APP_TAG, tag + ": " + String.format(Locale.ENGLISH, format, args));
        }
    }

    /**
     * Send a {@link Log#DEBUG} log message and log the exception.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    public static void d(String tag, String msg, Throwable tr) {
        if (canLog)

        {
            Log.d(APP_TAG, tag + ": " + msg, tr);
        }
    }

    /**
     * Send an {@link Log#INFO} log message.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void i(String tag, String msg) {
        Log.i(APP_TAG, tag + ": " + msg);
    }

    /**
     * Send a {@link Log#INFO} log message.
     *
     * @param tag    Used to identify the source of a log message.  It usually identifies
     *               the class or activity where the log call occurs.
     * @param format A <a href="../util/Formatter.html#syntax">format string</a>
     * @param args   Arguments referenced by the format specifiers in the format
     *               string.  If there are more arguments than format specifiers, the
     *               extra arguments are ignored.  The number of arguments is
     *               variable and may be zero.  The maximum number of arguments is
     *               limited by the maximum dimension of a Java array as defined by
     *               <cite>The Java&trade; Virtual Machine Specification</cite>.
     *               The behaviour on a
     *               <tt>null</tt> argument depends on the <a
     *               href="../util/Formatter.html#syntax">conversion</a>.
     */
    public static void i(String tag, String format, Object... args) {
        if (canLog) {
            Log.i(APP_TAG, tag + ": " + String.format(Locale.ENGLISH, format, args));
        }
    }

    /**
     * Send a {@link Log#INFO} log message and log the exception.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    public static void i(String tag, String msg, Throwable tr) {
        Log.i(APP_TAG, tag + ": " + msg, tr);
    }

    /**
     * Send a {@link Log#WARN} log message.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void w(String tag, String msg) {
        Log.w(APP_TAG, tag + ": " + msg);
    }

    /**
     * Send a {@link Log#WARN} log message and log the exception.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    public static void w(String tag, String msg, Throwable tr) {
        Log.w(APP_TAG, tag + ": " + msg, tr);
    }

    /**
     * Send an {@link Log#ERROR} log message.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void e(String tag, String msg) {
        Log.e(APP_TAG, tag + ": " + msg);
    }

    /**
     * Send a {@link Log#ERROR} log message and log the exception.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    public static void e(String tag, String msg, Throwable tr) {
        Log.e(APP_TAG, tag + ": " + msg, tr);
    }

}
