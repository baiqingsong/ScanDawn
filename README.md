# 二维码的使用

* [引用](#引用)
* [控件](#控件)
* [QRCodeView接口](#qrcodeview接口)
* [QRCodeView.Delegate](#qrcodeview.delegate)
* [QRCodeDecoder](#qrcodedecoder)
* [QRCodeEncoder](#qrcodeencoder)
* [参考地址](#参考地址)


## 引用
build.gradle里面添加：
```
dependencies {
    compile 'com.google.zxing:core:3.2.1'
    compile 'cn.bingoogolapple:bga-qrcodecore:latestVersion@aar'
    compile 'cn.bingoogolapple:bga-zxing:latestVersion@aar'
}
```
AndroidManifest.xml里面添加权限：
```
<uses-permission android:name="android.permission.CAMERA" />
<uses-permission android:name="android.permission.VIBRATE" />
<uses-permission android:name="android.permission.FLASHLIGHT" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```


## 控件
xml中调用cn.bingoogolapple.qrcode.zxing.ZXingView
```
<cn.bingoogolapple.qrcode.zxing.ZXingView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:id="@+id/scan_view"
    app:qrcv_animTime="1000"
    app:qrcv_borderColor="@android:color/white"
    app:qrcv_borderSize="1dp"
    app:qrcv_cornerColor="@color/colorPrimaryDark"
    app:qrcv_cornerLength="20dp"
    app:qrcv_cornerSize="3dp"
    app:qrcv_maskColor="#33FFFFFF"
    app:qrcv_rectWidth="200dp"
    app:qrcv_scanLineColor="@color/colorPrimaryDark"
    app:qrcv_scanLineSize="1dp"
    app:qrcv_topOffset="90dp" />
```
自定义属性说明：

|属性                        |说明                                                     |默认值                        |
| :------------------------: | :-----------------------------------------------------: | :--------------------------: |
|qrcv_topOffset              |  扫描框距离 toolbar 底部的距离                          |90dp                          | 
|qrcv_cornerSize             |	扫描框边角线的宽度                                     |3dp                           |
|qrcv_cornerLength           |	扫描框边角线的长度                                     |20dp                          |
|qrcv_cornerColor            |  扫描框边角线的颜色                                     |#FFFFFF                       |
|qrcv_rectWidth              |	扫描框的宽度                                           |200dp                         |
|qrcv_barcodeRectHeight      |  条码扫样式描框的高度                                   |140dp                         |
|qrcv_maskColor              |	除去扫描框，其余部分阴影颜色                           |#33FFFFFF                     |
|qrcv_scanLineSize           |	扫描线的宽度                                           |1dp                           |
|qrcv_scanLineColor          |  扫描线的颜色「扫描线和默认的扫描线图片的颜色」         |#FFFFFF                       |
|qrcv_scanLineMargin         |	扫描线距离上下或者左右边框的间距                       |0dp                           |
|qrcv_isShowDefaultScanLineDrawable|是否显示默认的图片扫描线「设置该属性后 qrcv_scanLineSize 将失效，可以通过 qrcv_scanLineColor 设置扫描线的颜色，避免让你公司的UI单独给你出特定颜色的扫描线图片」|false|
|qrcv_customScanLineDrawable |	扫描线的图片资源「默认的扫描线图片样式不能满足你的需求时使用，设置该属性后 qrcv_isShowDefaultScanLineDrawable、qrcv_scanLineSize、qrcv_scanLineColor 将失效」|null|
|qrcv_borderSize             |	扫描边框的宽度                                         |1dp                           |
|qrcv_borderColor            |	扫描边框的颜色                                         |#FFFFFF                       |
|qrcv_animTime               |  扫描线从顶部移动到底部的动画时间「单位为毫秒」         |1000                          |
|qrcv_isCenterVertical       |	扫描框是否垂直居中，该属性为true时会忽略 qrcv_topOffset 属性|false                    |
|qrcv_toolbarHeight          |	Toolbar 的高度，通过该属性来修正由 Toolbar 导致扫描框在垂直方向上的偏差|0dp           |
|qrcv_isBarcode              |	是否是扫条形码                                         |false                         |
|qrcv_tipText                |  提示文案                                               |null                          |
|qrcv_tipTextSize            |	提示文案字体大小                                       |14sp                          |
|qrcv_tipTextColor           |	提示文案颜色                                           |#FFFFFF                       |
|qrcv_isTipTextBelowRect     |	提示文案是否在扫描框的底部                             |false                         |
|qrcv_tipTextMargin          |	提示文案与扫描框之间的间距                             |20dp                          |
|qrcv_isShowTipTextAsSingleLine|是否把提示文案作为单行显示                             |false                         |
|qrcv_isShowTipBackground    |  是否显示提示文案的背景                                 |false                         |
|qrcv_tipBackgroundColor     |	提示文案的背景色                                       |#22000000                     |
|qrcv_isScanLineReverse      |	扫描线是否来回移动                                     |true                          |
|qrcv_isShowDefaultGridScanLineDrawable|是否显示默认的网格图片扫描线                   |false                         |
|qrcv_customGridScanLineDrawable|	扫描线的网格图片资源                               |null                          |
|qrcv_isOnlyDecodeScanBoxArea|  是否只识别扫描框区域的二维码                           |false                         |


## QRCodeView接口
```
/**
 * 设置扫描二维码的代理
 *
 * @param delegate 扫描二维码的代理
 */
public void setDelegate(Delegate delegate)

/**
 * 显示扫描框
 */
public void showScanRect()

/**
 * 隐藏扫描框
 */
public void hiddenScanRect()

/**
 * 打开后置摄像头开始预览，但是并未开始识别
 */
public void startCamera()

/**
 * 打开指定摄像头开始预览，但是并未开始识别
 *
 * @param cameraFacing  Camera.CameraInfo.CAMERA_FACING_BACK or Camera.CameraInfo.CAMERA_FACING_FRONT
 */
public void startCamera(int cameraFacing)

/**
 * 关闭摄像头预览，并且隐藏扫描框
 */
public void stopCamera()

/**
 * 延迟1.5秒后开始识别
 */
public void startSpot()

/**
 * 延迟delay毫秒后开始识别
 *
 * @param delay
 */
public void startSpotDelay(int delay)

/**
 * 停止识别
 */
public void stopSpot()

/**
 * 停止识别，并且隐藏扫描框
 */
public void stopSpotAndHiddenRect()

/**
 * 显示扫描框，并且延迟1.5秒后开始识别
 */
public void startSpotAndShowRect()

/**
 * 打开闪光灯
 */
public void openFlashlight()

/**
 * 关闭散光灯
 */
public void closeFlashlight()
```
setDelegate设置代理，本类实现QRCodeView.Delegate接口并且实现onScanQRCodeSuccess和onScanQRCodeOpenCameraError两个方法
Vibrator实现手机振动
```
public class ScanActivity extends AppCompatActivity implements QRCodeView.Delegate {
    @Override
    public void onScanQRCodeSuccess(String result) {
        Toast.makeText(this, result + "", Toast.LENGTH_SHORT).show();
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Log.e(TAG, "打开相机出错");
    }
}
```
startCamera打开相机开始预览
showScanRect显示扫描框
这两个方法在onStart里面调用，如果想实现打开扫描页面自动扫描二维码则需在后调用startSpot开始扫描
```
@Override
protected void onStart() {
    super.onStart();
    mQRCodeView.startCamera();
    mQRCodeView.showScanRect();
    mQRCodeView.startSpot();
}
```
startSpotAndShowRect显示扫描框并且开始扫描，所以上面的效果还可以这样写：
```
@Override
protected void onStart() {
    super.onStart();
    mQRCodeView.startCamera();
    mQRCodeView.startSpotAndShowRect();
}
```
stopCamera关闭相机预览，需要在页面停止时调用
```
@Override
protected void onStop() {
    mQRCodeView.stopCamera();
    super.onStop();
}
```
onDestroy控件销毁，需要在页面销毁时调用
```
@Override
protected void onDestroy() {
    mQRCodeView.onDestroy();
    super.onDestroy();
}
```


## QRCodeView.Delegate
```
/**
 * 处理扫描结果
 *
 * @param result
 */
void onScanQRCodeSuccess(String result)

/**
 * 处理打开相机出错
 */
void onScanQRCodeOpenCameraError()
```


## QRCodeDecoder
QRCodeDecoder 解析二维码图片。几个重载方法都是耗时操作，请在子线程中调用。
```
/**
 * 同步解析本地图片二维码。该方法是耗时操作，请在子线程中调用。
 *
 * @param picturePath 要解析的二维码图片本地路径
 * @return 返回二维码图片里的内容 或 null
 */
public static String syncDecodeQRCode(String picturePath)

/**
 * 同步解析bitmap二维码。该方法是耗时操作，请在子线程中调用。
 *
 * @param bitmap 要解析的二维码图片
 * @return 返回二维码图片里的内容 或 null
 */
public static String syncDecodeQRCode(Bitmap bitmap)
```
syncDecodeQRCode解析二维码，顺便提一下，从图库中获取二维码图片
首先引用：
```
dependencies {
    compile 'cn.bingoogolapple:bga-photopicker:1.2.3@aar'
    compile 'cn.bingoogolapple:bga-adapter:1.1.8@aar'
    compile 'com.github.bumptech.glide:glide:3.7.0'
    compile 'com.android.support:recyclerview-v7:25.3.1'
}
```
获取二维码图片：
```
public void selectImageScan(View view){
    /*
    从相册选取二维码图片，这里为了方便演示，使用的是
    https://github.com/bingoogolapple/BGAPhotoPicker-Android
    这个库来从图库中选择二维码图片，这个库不是必须的，你也可以通过自己的方式从图库中选择图片
     */
    startActivityForResult(BGAPhotoPickerActivity.newIntent(this, null, 1, null, false), REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY);
}
```
对二维码的处理：
```
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    mQRCodeView.showScanRect();

    if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY) {
        final String picturePath = BGAPhotoPickerActivity.getSelectedImages(data).get(0);

        /*
        这里为了偷懒，就没有处理匿名 AsyncTask 内部类导致 Activity 泄漏的问题
        请开发在使用时自行处理匿名内部类导致Activity内存泄漏的问题，处理方式可参考 https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/Android%E5%86%85%E5%AD%98%E6%B3%84%E6%BC%8F%E6%80%BB%E7%BB%93.md
         */
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                return QRCodeDecoder.syncDecodeQRCode(picturePath);
            }

            @Override
            protected void onPostExecute(String result) {
                if (TextUtils.isEmpty(result)) {
                    Toast.makeText(ScanActivity.this, "未发现二维码", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ScanActivity.this, result, Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }
}
```


## QRCodeEncoder
QRCodeEncoder 创建二维码图片。几个重载方法都是耗时操作，请在子线程中调用。
```
/**
 * 同步创建黑色前景色、白色背景色的二维码图片。该方法是耗时操作，请在子线程中调用。
 *
 * @param content 要生成的二维码图片内容
 * @param size    图片宽高，单位为px
 */
public static Bitmap syncEncodeQRCode(String content, int size)

/**
 * 同步创建指定前景色、白色背景色的二维码图片。该方法是耗时操作，请在子线程中调用。
 *
 * @param content         要生成的二维码图片内容
 * @param size            图片宽高，单位为px
 * @param foregroundColor 二维码图片的前景色
 */
public static Bitmap syncEncodeQRCode(String content, int size, int foregroundColor)

/**
 * 同步创建指定前景色、白色背景色、带logo的二维码图片。该方法是耗时操作，请在子线程中调用。
 *
 * @param content         要生成的二维码图片内容
 * @param size            图片宽高，单位为px
 * @param foregroundColor 二维码图片的前景色
 * @param logo            二维码图片的logo
 */
public static Bitmap syncEncodeQRCode(String content, int size, int foregroundColor, Bitmap logo)

/**
 * 同步创建指定前景色、指定背景色、带logo的二维码图片。该方法是耗时操作，请在子线程中调用。
 *
 * @param content         要生成的二维码图片内容
 * @param size            图片宽高，单位为px
 * @param foregroundColor 二维码图片的前景色
 * @param backgroundColor 二维码图片的背景色
 * @param logo            二维码图片的logo
 */
public static Bitmap syncEncodeQRCode(String content, int size, int foregroundColor, int backgroundColor, Bitmap logo)
```
syncEncodeQRCode生成二维码，下面的是生成添加颜色，并且包含logo和大小的英文二维码
```
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
```




## 参考地址
[https://github.com/bingoogolapple/BGAQRCode-Android](https://github.com/bingoogolapple/BGAQRCode-Android "参考地址")