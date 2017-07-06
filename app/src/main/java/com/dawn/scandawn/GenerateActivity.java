package com.dawn.scandawn;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

/**
 * Created by 90449 on 2017/7/6.
 */

public class GenerateActivity extends AppCompatActivity {
    private ImageView ivGenerateCh;
    private ImageView ivGenerateEn;
    private ImageView ivGenerateEnLogo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);
        ivGenerateCh = (ImageView) findViewById(R.id.iv_generate_ch);
        ivGenerateEn = (ImageView) findViewById(R.id.iv_generate_en);
        ivGenerateEnLogo = (ImageView) findViewById(R.id.iv_generate_en_logo);
    }
    public void generateCh(View view){
        new AsyncTask<Void, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(Void... params) {
                return QRCodeEncoder.syncEncodeQRCode("二维码", BGAQRCodeUtil.dp2px(GenerateActivity.this, 150));
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                if (bitmap != null) {
                    ivGenerateCh.setImageBitmap(bitmap);
                } else {
                    Toast.makeText(GenerateActivity.this, "生成中文二维码(二维码)失败", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }
    public void generateEn(View view){
        new AsyncTask<Void, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(Void... params) {
                return QRCodeEncoder.syncEncodeQRCode("dawn", BGAQRCodeUtil.dp2px(GenerateActivity.this, 150), Color.parseColor("#ff0000"));
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                if (bitmap != null) {
                    ivGenerateEn.setImageBitmap(bitmap);
                } else {
                    Toast.makeText(GenerateActivity.this, "生成英文二维码(dawn)失败", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }
    public void generateEnLogo(View view){
        new AsyncTask<Void, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(Void... params) {
                Bitmap logoBitmap = BitmapFactory.decodeResource(GenerateActivity.this.getResources(), R.mipmap.ic_launcher);
                return QRCodeEncoder.syncEncodeQRCode("dawn", BGAQRCodeUtil.dp2px(GenerateActivity.this, 150), Color.parseColor("#00ff00"), logoBitmap);
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                if (bitmap != null) {
                    ivGenerateEnLogo.setImageBitmap(bitmap);
                } else {
                    Toast.makeText(GenerateActivity.this, "生成英文二维码带logo(dawn)失败", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }
}
