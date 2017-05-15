package com.example.zhangjiawen.asynctasktest;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;

/**
 * Created by zhangjiawen on 2017/5/14.
 */
public class ProgressBarTest extends Activity {

    private ProgressBar progressBar;
    private MyAsyncTask mTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progressbar);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        mTask = new MyAsyncTask();
        mTask.execute();//启动
    }

    @Override
    protected void onPause() {//当点击返回时，结束任务
        super.onPause();
        //mTask不为空，而且正在进行
        if (mTask != null && mTask.getStatus() == AsyncTask.Status.RUNNING){
            mTask.cancel(true);//cancel()方法只是将对应的AsyncTask标记为cancel状态，并不是真正的取消线程的执行
        }
    }

    class MyAsyncTask extends AsyncTask<Void,Integer,Void>{

        @Override
        protected Void doInBackground(Void... params) {
            //模拟进度更新
            for (int i = 0; i < 100; i++){
                if (isCancelled()){//判断是否标记为cancel状态
                    break;
                }
                publishProgress(i);
                try {
                    Thread.sleep(300);//增加睡眠时间
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (isCancelled()){//判断是否标记为cancel状态
                return;
            }
            //获取进度更新值
            progressBar.setProgress(values[0]);
        }
    }
}
