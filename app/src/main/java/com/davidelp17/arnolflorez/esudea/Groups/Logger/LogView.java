package com.davidelp17.arnolflorez.esudea.Groups.Logger;

import android.app.Activity;
import android.content.Context;
import android.util.*;
import android.widget.TextView;

public class LogView extends TextView implements LogNode
{
    public LogView(Context context)
    {
        super(context);
    }

    public LogView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public LogView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    @Override
    public void println(int priority, String tag, String msg, Throwable tr)
    {
        String priorityStr = null;

        // For the purposes of this View, we want to print the priority as readable text.
        switch(priority) {
            case android.util.Log.VERBOSE:
                priorityStr = "VERBOSE";
                break;
            case android.util.Log.DEBUG:
                priorityStr = "DEBUG";
                break;
            case android.util.Log.INFO:
                priorityStr = "INFO";
                break;
            case android.util.Log.WARN:
                priorityStr = "WARN";
                break;
            case android.util.Log.ERROR:
                priorityStr = "ERROR";
                break;
            case android.util.Log.ASSERT:
                priorityStr = "ASSERT";
                break;
            default:
                break;
        }

        // Handily, the Log class has a facility for converting a stack trace into a usable string.
        String exceptionStr = null;
        if (tr != null)
        {
            exceptionStr = android.util.Log.getStackTraceString(tr);
        }

        final StringBuilder outputBuilder = new StringBuilder();

        String delimiter = "\t";
        appendIfNotNull(outputBuilder, priorityStr, delimiter);
        appendIfNotNull(outputBuilder, tag, delimiter);
        appendIfNotNull(outputBuilder, msg, delimiter);
        appendIfNotNull(outputBuilder, exceptionStr, delimiter);

        ((Activity) getContext()).runOnUiThread( (new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                // Display the text we just generated within the LogView.
                appendToLog(outputBuilder.toString());
            }
        })));

        if (mNext != null)
        {
            mNext.println(priority, tag, msg, tr);
        }
    }

    public LogNode getNext()
    {
        return mNext;
    }

    public void setNext(LogNode node)
    {
        mNext = node;
    }

    private StringBuilder appendIfNotNull(StringBuilder source, String addStr, String delimiter)
    {
        if (addStr != null)
        {
            if (addStr.length() == 0)
            {
                delimiter = "";
            }
            return source.append(addStr).append(delimiter);
        }
        return source;
    }

    // The next LogNode in the chain.
    LogNode mNext;

    /** Outputs the string as a new line of log data in the LogView. */
    public void appendToLog(String s)
    {
        append("\n" + s);
    }
}
