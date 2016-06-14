package com.davidelp17.arnolflorez.esudea.Home.Logger;

public class MessageOnlyLogFilter implements com.davidelp17.arnolflorez.esudea.Home.Logger.LogNode
{
    LogNode mNext;

    /**
     * Takes the "next" LogNode as a parameter, to simplify chaining.
     *
     * @param next The next LogNode in the pipeline.
     */
    public MessageOnlyLogFilter(LogNode next)
    {
        mNext = next;
    }

    public MessageOnlyLogFilter()
    {

    }

    @Override
    public void println(int priority, String tag, String msg, Throwable tr)
    {
        if (mNext != null) {
            getNext().println(Log.NONE, null, msg, null);
        }
    }

    /**
     * Returns the next LogNode in the chain.
     */
    public LogNode getNext()
    {
        return mNext;
    }

    /**
     * Sets the LogNode data will be sent to..
     */
    public void setNext(LogNode node)
    {
        mNext = node;
    }
}
