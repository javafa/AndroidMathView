package com.agog.mathdisplay;

import android.Manifest
import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.agog.mathdisplay.parse.*
import com.agog.mathdisplay.render.*

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Before;
import org.junit.Assert.*
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Environment
import java.io.FileOutputStream
import java.io.IOException
import android.support.test.rule.GrantPermissionRule
import org.junit.Rule


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4::class)
public class ViewInstrumentedTest {

    private var context: Context? = null
    private var font: MTFont? = null
    private var bitmap: Bitmap? = null
    private var canvas: Canvas? = null

    @Rule
    @JvmField
    var mRuntimePermissionWriteRule = GrantPermissionRule.grant(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    @Rule
    @JvmField
    var mRuntimePermissionReadRule = GrantPermissionRule.grant(Manifest.permission.READ_EXTERNAL_STORAGE)

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getContext();
        assertNotNull(context)
        MTFontManager.setContext(context!!)
        font = MTFontManager.defaultFont()
    }


    fun getCanvas(w: Int, h: Int) {
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        assertNotNull(bitmap)
        canvas = Canvas(bitmap)
        assertNotNull(canvas)
        //canvas!!.translate(0.0f, BITMAPHEIGHT.toFloat())
        //canvas!!.scale(1.0f, -1.0f)

        //canvas!!.translate(100.0f, 100.0f) // for now

    }


    fun savebitmap(filename: String) {
        assertNotNull(context)
        assertNotNull(bitmap)

        val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/" + filename

        FileOutputStream(path).use {
            try {
                bitmap!!.compress(Bitmap.CompressFormat.PNG, 100, it) // bmp is your Bitmap instance
                // PNG is a lossless format, the compression factor (100) is ignored
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                try {
                    if (it != null) {
                        it!!.close()
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }
    }

    @Test
    public fun testMTMathView() {
        //val l =                 """\cos(\theta) = \cos(\theta)\cos(\varphi)"""
        //val l = """\underline{xyz}+\overline{abc}"""
        //val l = """\color{#33ffff}{\sqrt[\color{#3399ff}{e}]{\color{#3333ff}{d}}}"""
        val l = """\hat{xyz} \; \widehat{xyz}\; \vec{2ab}"""

        val mathView = MTMathView(context!!)
        mathView.fontSize = 60f
        mathView.latex = l

        assertEquals(MTParseErrors.ErrorNone, mathView.lastError.errorcode)
        //Cause the view to re-layout
        mathView.measure(0, 0);
        val w = mathView.getMeasuredWidth()
        val h = mathView.getMeasuredHeight()
        mathView.layout(0, 0, w, h)
        getCanvas(w, h)
        mathView.draw(canvas)
        savebitmap("testMTMathView.png")
    }


    //@Test
    public fun testInlineError() {
        val l = """\notacommand"""

        val mathView = MTMathView(context!!)
        mathView.fontSize = 60f
        mathView.latex = l

        val e = mathView.lastError
        assertEquals(MTParseErrors.InvalidCommand, e.errorcode)
        assertEquals("Invalid command notacommand", e.errordesc)
        //Cause the view to re-layout
        mathView.measure(0, 0);
        val w = mathView.getMeasuredWidth()
        val h = mathView.getMeasuredHeight()
        mathView.layout(0, 0, w, h)
        getCanvas(w, h)
        mathView.draw(canvas)
        savebitmap("testInlineError.png")
    }


    //@Test
    public fun testNotInlineError() {
        val l = """\notacommand"""

        val mathView = MTMathView(context!!)
        mathView.displayErrorInline = false
        mathView.fontSize = 60f
        mathView.latex = l

        val e = mathView.lastError
        assertEquals(MTParseErrors.InvalidCommand, e.errorcode)
        assertEquals("Invalid command notacommand", e.errordesc)
        //Cause the view to re-layout
        mathView.measure(0, 0);
        val w = mathView.getMeasuredWidth()
        val h = mathView.getMeasuredHeight()
        mathView.layout(0, 0, w, h)
        getCanvas(w, h)
        mathView.draw(canvas)
        savebitmap("testNotInlineError.png")
    }


}


