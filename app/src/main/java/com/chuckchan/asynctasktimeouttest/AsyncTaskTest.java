package com.chuckchan.asynctasktimeouttest;

import android.os.AsyncTask;

public class AsyncTaskTest
        extends AsyncTask<Long,Void, String>
{
    private String message;

    public AsyncTaskTest( String message )
    {
        this.message = message;
    }

    @Override
    protected String doInBackground( Long... args )
    {
        long sleepMills =  args[ 0 ];

        System.out.println( message + " start doInBackground, sleep " + sleepMills + "ms");
        try
        {
            Thread.sleep( sleepMills );
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }
        return message;
    }

    @Override
    protected void onPostExecute( String s )
    {
        System.out.println( s + " complete ");
    }

    public String getMessage()
    {
        return message;
    }
}
