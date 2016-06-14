package com.davidelp17.arnolflorez.esudea.Home.Logger;

public interface LogNode
{
    public void println(int priority, String tag, String msg, Throwable tr);
}
