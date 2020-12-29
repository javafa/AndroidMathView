[![](https://jitpack.io/v/javafa/AndroidMath.svg)](https://jitpack.io/#javafa/AndroidMath)

# AndroidMathView
- This Project's cloned from https://github.com/gregcockroft/AndroidMath
- Just upgraded the latest gradle version

<img src="./img/phonescreen.png" width="320">


Installation from the command line
----------------------------------

```
git clone https://github.com/javafa/AndroidMathView.git

cd AndroidMathView
./gradlew installDebug
```

Installation for Android Studio
----------------------------------

Clone this project, run CDep.
CDep pulls in the freetype dependency.


```
git clone https://github.com/javafa/AndroidMathView.git
cd AndroidMathView/mathdisplaylib
./cdep 
```

Open the project in Android Studio 


Using library in your app
-------------------------

* [Sample App](https://github.com/javafa/AndroidMathView/tree/master/sampleapp) For a complete simple project

This is using jitpack.io
Add below lines to root's build.gradle

```groovy
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Add below lines to apps's build.gradle

```groovy
dependencies {
	        implementation 'com.github.javafa:AndroidMathView:1.0.14'
	}
	
```

```xml
<ConstraintLayout ...>

    <com.taeim.mathdisplay.AndroidMathView
        android:id="@+id/mathView1"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:fontSize="20sp"
        app:fontType="LatinModernMath"
        app:latex="\log_b(x) = \frac{\log_a(x)}{\log_a(b)}"
        app:fontColor="@color/colorAccent"
        app:textAlignment="center" />

    <com.taeim.mathdisplay.AndroidMathView
        android:id="@+id/mathView2"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:fontSize="50sp"
        app:fontType="TeXGyreTermes"
        app:textAlignment="center" />

    <com.taeim.mathdisplay.AndroidMathView
        android:id="@+id/mathView3"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:fontSize="10sp"
        app:fontType="XITSMath"
        app:textAlignment="center"
        />


</ConstraintLayout>
```

### Attributes
| Name | Type | Example
|------|------|-------------
| latex | String | \log_b(x) = \frac{\log_a(x)}{\log_a(b)}   
| fontColor | Color | @color/colorId   
| fontSize | Dimmension | 11sp   
| fontType | Enum | LatinModernMath, TeXGyreTermes, XITSMath   
| textAlignment | Enum | left, center, right   

```kotlin
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) } 

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.mathView2.latex = "{n \\brace k} = \\frac{1}{k!}\\sum_{j=0}^k (-1)^{k-j}\\binom{k}{j}(k-j)^n"
        binding.mathView2.setColorResource(R.color.colorPrimary)
        binding.mathView2.setFontSize(33.5f)

        binding.mathView3.latex = "\\int_{-\\infty}^\\infty \\! e^{-x^2} dx = \\sqrt{\\pi}"
        binding.mathView3.setColorString("#5312f3")
    }
	
```
### Api
| Name | Parameter | Return | Example
|------|-----------|--------|-------------
| setColorResource(@ColorRes colorId:Int) | Int | void | mathView2.setColorResource(R.color.colorPrimary)   
| setColorString(color:String) | String | void | mathView3.setColorString("#5312f3")
| setFontSize(dp:Float) | Float | void | 11.0f   
* not support setTextSize use setFontSize instead

#### Credits:


* [iosMath](https://github.com/kostub/iosMath) This project is a Kotlin port for Android of the iosMath project 
* [Freetype](https://www.freetype.org/) is used for rendering glyphs and font metrics.
* [Freetype jni](https://github.com/mlomb/freetype-jni) was copied as a starting point to access the native  freetype library.




## Related Projects

* [MathView](https://github.com/kexanie/MathView) uses [MathJax](http://www.mathjax.org/) in a WebView to render math on Android.

For people looking for things beyond just rendering math, there are two
related projects:

* [MathEditor](https://github.com/kostub/MathEditor): A WYSIWYG editor
  for math equations on iOS.
* [MathSolver](https://github.com/kostub/MathSolver): A library for
  solving math equations.
  
## License

AndroidMath is available under the MIT license. See the [LICENSE](./LICENSE)
file for more info.

### Fonts
This distribution contains the following fonts. These fonts are
licensed as follows:
* Latin Modern Math: 
    [GUST Font License](./mathdisplaylib/src/main/assets/fonts/GUST-FONT-LICENSE.txt)
* Tex Gyre Termes:
    [GUST Font License](./mathdisplaylib/src/main/assets/fonts/GUST-FONT-LICENSE.txt)
* [XITS Math](https://github.com/khaledhosny/xits-math):
    [Open Font License](./mathdisplaylib/src/main/assets/fonts/OFL.txt)
