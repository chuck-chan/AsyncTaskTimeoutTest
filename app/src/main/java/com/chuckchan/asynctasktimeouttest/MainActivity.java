package com.chuckchan.asynctasktimeouttest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MainActivity
        extends AppCompatActivity
{

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
    }

    public void test1( View v){
        AsyncTaskTest asyncTaskOne = new AsyncTaskTest("AsyncTask1");
        asyncTaskOne.execute( ( long ) (20 * 1000) );

        AsyncTaskTest asyncTaskTwo = new AsyncTaskTest("AsyncTask2");
        asyncTaskTwo.execute( ( long ) (5 * 1000) );

        new Thread(new AsyncTaskTimeout( asyncTaskTwo )).start();
    }

    public void test2( View view )
    {
        AsyncTaskTest asyncTaskOne = new AsyncTaskTest("AsyncTask3");
        asyncTaskOne.execute( ( long ) (5 * 1000) );

        AsyncTaskTest asyncTaskTwo = new AsyncTaskTest("AsyncTask4");
        asyncTaskTwo.execute( ( long ) (5 * 1000) );

        new Thread(new AsyncTaskTimeout( asyncTaskTwo )).start();
    }

    public void test3( View view )
    {
        AsyncTaskTest asyncTaskOne = new AsyncTaskTest("AsyncTask5");
        asyncTaskOne.execute( ( long ) (12 * 1000) );

        AsyncTaskTest asyncTaskTwo = new AsyncTaskTest("AsyncTask6");
        asyncTaskTwo.execute( ( long ) (5 * 1000) );

        new Thread(new AsyncTaskTimeout( asyncTaskTwo )).start();
    }

    public void test4( View view )
    {
        AsyncTaskTest asyncTaskOne = new AsyncTaskTest("AsyncTask7");
        asyncTaskOne.execute( ( long ) (12 * 1000) );

        try
        {
            Thread.sleep( 2 * 1000 );
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }

        AsyncTaskTest asyncTaskTwo = new AsyncTaskTest("AsyncTask8");
        asyncTaskTwo.execute( ( long ) (5 * 1000) );

        new Thread(new AsyncTaskTimeout( asyncTaskTwo )).start();
    }

    public void test5( View view )
    {
        AsyncTaskTest asyncTaskOne = new AsyncTaskTest("AsyncTask9");
        asyncTaskOne.execute( ( long ) (12 * 1000) );

        try
        {
            Thread.sleep( 4 * 1000 );
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }

        AsyncTaskTest asyncTaskTwo = new AsyncTaskTest("AsyncTask10");
        asyncTaskTwo.execute( ( long ) (5 * 1000) );

        new Thread(new AsyncTaskTimeout( asyncTaskTwo )).start();
    }

    public void test6( View view )
    {
        AsyncTaskTest asyncTaskOne = new AsyncTaskTest("AsyncTask11");
        asyncTaskOne.executeOnExecutor( AsyncTask.THREAD_POOL_EXECUTOR, ( long ) (20 * 1000) );

        AsyncTaskTest asyncTaskTwo = new AsyncTaskTest("AsyncTask12");
        asyncTaskTwo.executeOnExecutor( AsyncTask.THREAD_POOL_EXECUTOR, ( long ) (5 * 1000) );

        AsyncTaskTest asyncTaskThree = new AsyncTaskTest("AsyncTask13");
        asyncTaskThree.executeOnExecutor( AsyncTask.THREAD_POOL_EXECUTOR, ( long ) (20 * 1000) );

        AsyncTaskTest asyncTaskFour = new AsyncTaskTest("AsyncTask14");
        asyncTaskFour.executeOnExecutor( AsyncTask.THREAD_POOL_EXECUTOR, ( long ) (5 * 1000) );

        new Thread(new AsyncTaskTimeout( asyncTaskFour )).start();
    }


    static class AsyncTaskTimeout
            implements Runnable
    {
        private AsyncTaskTest mAsyncTask;

        public AsyncTaskTimeout( AsyncTaskTest mAsyncTask )
        {
            this.mAsyncTask = mAsyncTask;
        }

        @Override
        public void run()
        {
            try
            {
                mAsyncTask.get( 15 * 1000, TimeUnit.MILLISECONDS );
            }
            catch ( ExecutionException e )
            {
                e.printStackTrace();
            }
            catch ( InterruptedException e )
            {
                e.printStackTrace();
            }
            catch ( TimeoutException e )
            {
                e.printStackTrace();
                System.out.println( mAsyncTask.getMessage() + " timeout" );
            }
        }
    }
}
