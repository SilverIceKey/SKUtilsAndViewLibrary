package com.silvericekey.skutilslibrary.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import com.google.zxing.BarcodeFormat
import com.google.zxing.BinaryBitmap
import com.google.zxing.EncodeHintType
import com.google.zxing.RGBLuminanceSource
import com.google.zxing.common.BitMatrix
import com.google.zxing.common.HybridBinarizer
import com.google.zxing.qrcode.QRCodeReader
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel

/*
* 二维码工具类
* */
object QRCodeUtil {
    /*
    * 快捷创建二维码bitmap
    * */
    fun createQRCode(info: String, size: Int): Bitmap = createQRCode(info, size, -1)

    fun createQRCode(info: String, size: Int, logo: Bitmap?): Bitmap =
            createQRCode(info, size, -1, logo)

    /*
    * 根绝bitmap读取二维码
    * */
    fun readQRCode(bitmap: Bitmap): String {
        var pixels = intArrayOf()
        bitmap.getPixels(pixels, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)
        var luminanceSource = RGBLuminanceSource(bitmap.width, bitmap.height, pixels)
        var binaryBitmap = BinaryBitmap(HybridBinarizer(luminanceSource))
        return QRCodeReader().decode(binaryBitmap).text
    }

    fun createQRCode(info: String, size: Int, color: Int): Bitmap {
        return createQRCode(info, size, color, null)
    }

    fun createQRCode(info: String, size: Int, color: Int, logo: Bitmap?): Bitmap {
        var hint = HashMap<EncodeHintType, Any>()
        hint.put(EncodeHintType.MARGIN, 0)
        hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H)
        return bitMatrixToBitmap(
                QRCodeWriter().encode(
                        String(
                                info.toByteArray(Charsets.UTF_8),
                                Charsets.ISO_8859_1
                        ), BarcodeFormat.QR_CODE, size, size, hint
                ), color
                , logo
        )
    }


    private fun bitMatrixToBitmap(bitMatrix: BitMatrix, color: Int, logo: Bitmap?): Bitmap {
        val width = bitMatrix.width
        val height = bitMatrix.height

        val pixels = IntArray(width * height)
        for (y in 0 until height) {
            for (x in 0 until width) {
                pixels[y * width + x] = if (bitMatrix.get(x, y)) {
                    if (color == -1 || color == Color.parseColor("#ffffff")) -0x1000000 else color
                } else -0x1
            }
        }
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height)
        return addLogoToQRCode(bitmap, logo)
    }

    /**
     * 添加logo到二维码图片上
     */
    private fun addLogoToQRCode(src: Bitmap, logo: Bitmap?): Bitmap {
        if (logo == null) {
            return src
        }
        val srcWidth = src.width
        val srcHeight = src.height
        var logoWidth = logo.width
        var logoHeight = logo.height
        var bitmap = Bitmap.createBitmap(srcWidth, srcHeight, Bitmap.Config.ARGB_8888)
        try {
            val canvas = Canvas(bitmap)
            canvas.drawBitmap(src, 0f, 0f, null)
            var matrix = Matrix()
            val max = Math.max(logoWidth, logoHeight)
            var scale = 0f
            if (max > srcWidth / 2.2f) {
                scale = srcWidth / 2.2f / logoWidth
                logoWidth = (logoWidth * scale).toInt()
                logoHeight = (logoHeight * scale).toInt()
            }
            matrix.postScale(scale, scale)
            var centerLeftposition = (srcWidth - logoWidth) / 2f
            var centerTopposition = (srcHeight - logoHeight) / 2f
            matrix.postTranslate(centerLeftposition, centerTopposition)
            canvas.drawBitmap(
                    logo,
                    matrix,
                    null
            )
            canvas.save()
            canvas.restore()
        } catch (e: Exception) {
            e.printStackTrace()
            bitmap = null
        }
        return bitmap
    }
}