package com.tars.counter.async;

/**
 * Created by lucasbonafe on 23/09/17.
 */

/**
 * This is a useful callback mechanism so we can abstract our AsyncTasks out into separate, re-usable
 * and testable classes yet still retain a hook back into the calling activity. Basically, it'll make classes
 * cleaner and easier to unit test.
 *
 * @param <T>
 */
public interface AsyncTaskListener<T> {

    /**
     * Invoked when the AsyncTask has started its execution.
     */
    public void onTaskStart();

    /**
     * Invoked when the AsyncTask has completed its execution.
     * @param result The resulting object from the AsyncTask.
     */
    public void onTaskComplete(T result);
}