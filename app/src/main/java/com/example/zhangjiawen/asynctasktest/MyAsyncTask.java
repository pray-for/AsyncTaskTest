//package com.example.zhangjiawen.asynctasktest;
//
//import android.os.AsyncTask;
//import android.util.Log;
//
///**
// * Created by zhangjiawen on 2017/5/14.
// */
//public class MyAsyncTask extends AsyncTask<Void,Void,Void> {
//
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
//        Log.d("测试","onPreExecute");
//    }
//
//    @Override
//    protected Void doInBackground(Void... voids) {
//        Log.d("测试","doInBackground");
//        publishProgress();
//        return null;
//
//    }
//
//    @Override
//    protected void onPostExecute(Void aVoid) {
//        super.onPostExecute(aVoid);
//        Log.d("测试","onPostExecute");
//    }
//
//    @Override
//    protected void onProgressUpdate(Void... values) {
//        super.onProgressUpdate(values);
//        Log.d("测试","onProgressUpdate");
//    }
//}
