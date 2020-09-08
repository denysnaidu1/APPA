package com.daredevil.appa;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SimpleAsycnTask extends AsyncTask <String,Integer,String>{

        TextView tv;
        ProgressBar bar;
        int x=0;
        public SimpleAsycnTask(TextView view,ProgressBar bar){
            tv=view;
            this.bar=bar;
        }

        @Override
        protected String doInBackground(String... strings) {
            for(int i=0;i<100;i++){
                try{
                    Thread.sleep(100);
                    publishProgress(i);
                    if(isCancelled())break;
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }

            return strings[0]+" Executed";
        }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        tv.setText("Cancel");
    }

    @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            bar.setProgress(values[0]);
        }

}
