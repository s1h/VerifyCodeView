# VerifyCodeView
高仿钉钉验证码控件
[![](https://jitpack.io/v/s1h/VerifyCodeView.svg)](https://jitpack.io/#s1h/VerifyCodeView)


![示例](https://github.com/s1h/VerifyCodeView/raw/master/screenshots/preview.jpg)

###集成步骤


1. 在项目根build.gradle中添加maven依赖

   ```html
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
    ```

2. app中添加VerifyCodeView依赖

    ```html
	dependencies {
	        compile 'com.github.s1h:VerifyCodeView:latestVersion'
	}
	```

3. xml布局中添加VerifyCodeView控件

    ```html
    <com.shlib.verifycodelib.VerifyCodeView
        android:id="@+id/verifyCodeView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />
    ```
        
4. 为该控件添加监听

    ```html
    verifyCodeView.setCompleteListener(object : VerifyCodeCompleteListener {        override fun verifyCodeComplete() {
           Toast.makeText(this@MainActivity, verifyCodeView.getText(), Toast.LENGTH_SHORT).show()
        }
    })
     ```
