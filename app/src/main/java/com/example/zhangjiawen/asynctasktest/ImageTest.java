package com.example.zhangjiawen.asynctasktest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by zhangjiawen on 2017/5/14.
 */
public class ImageTest extends Activity {

    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private static String URL = "http://img.mukewang.com/5916bd250001a57714401280-200-200.jpg";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image);
        mImageView = (ImageView) findViewById(R.id.image);
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        //设置传递进去的参数
        new MyAsyncTask().execute(URL);
    }

    class MyAsyncTask extends AsyncTask<String,Void,Bitmap>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);//显示进度条

        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            //操作UI，设置图像
            super.onPostExecute(bitmap);
            mProgressBar.setVisibility(View.GONE);//隐藏进度条
            mImageView.setImageBitmap(bitmap);
        }

        @Override
        protected Bitmap doInBackground(String... pramas) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String url = pramas[0];//取出对应的URL
            Bitmap bitmap = null;
            URLConnection urlConnection;//定义网络连接对象
            InputStream inputStream;//获取网络的输入流
            try {//将URL对应的图像解析成bitmap
                urlConnection = new URL(url).openConnection();//获取网络连接对象
                inputStream = urlConnection.getInputStream();//获取输入流
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                bitmap = BitmapFactory.decodeStream(bufferedInputStream);//将输入流解析成bitmap
                inputStream.close();//关闭
                bufferedInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //将bitmap作为返回值
            return bitmap;
        }
    }

}
