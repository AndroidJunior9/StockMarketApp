package com.androidjunior9.stockmarketapp.featurestockspricedata.presentation.companyinfo


import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.androidjunior9.stockmarketapp.featurestockspricedata.domain.model.CompanyPriceInfo

@Composable
fun LineChart(
    modifier:Modifier = Modifier,
    infos: List<CompanyPriceInfo>,
    viewModel: CompanyInfoViewModel = hiltViewModel()
){
    val spacing = 100f
    var upperValue = remember(infos){
        infos.maxOfOrNull { it.close }?:0
    }
    var lowerValue = remember(infos) {
        infos.minOfOrNull { it.close }?:0
    }
    val density = LocalDensity.current
    val textPaint = remember(density){
        Paint().apply{
            color = android.graphics.Color.WHITE
            textAlign = Paint.Align.CENTER
            textSize = density.run{10.sp.toPx()}
        }
    }
    Canvas(modifier = modifier){
        val spacePerUnit = (size.width-spacing)/infos.size
        if(upperValue == lowerValue){
            upperValue = upperValue.toFloat()+0.4
            lowerValue = lowerValue.toFloat()-0.4

        }
        val height = size.height
        val step = infos.size/5
        (0 until infos.size - 1 step step).forEach { i ->
            val info = infos[i]
            val month = info.date.month.toString().take(3)
            val year = info.date.year.toString().takeLast(2)
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    "$month $year",
                    spacing + i * spacePerUnit,
                    height - 5,
                    textPaint
                )
            }
        }
        val priceStep = (upperValue.toFloat() - lowerValue.toFloat()) / 5f
        (0..5).forEach { i ->
            val text = viewModel.roundToDecimal(lowerValue.toFloat() + priceStep*i)
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    text.toString(),
                    30f,
                    height - spacing - i * height / 5f,
                    textPaint
                )
            }
        }
        for(i in infos.indices) {
            val info = infos[i]
            val nextInfo = infos.getOrNull(i + 1) ?: infos.last()
            val leftRatio = (info.close.toFloat() - lowerValue.toFloat()) / (upperValue.toFloat() - lowerValue.toFloat())
            val rightRatio = (nextInfo.close - lowerValue.toFloat()) / (upperValue.toFloat() - lowerValue.toFloat())

            val x1 = spacing + i * spacePerUnit
            val y1 = height - spacing - (leftRatio * height)
            val x2 = spacing + (i + 1) * spacePerUnit
            val y2 = height - spacing - (rightRatio * height).toFloat()
            if(infos.size>1) {
                drawLine(
                    start = Offset(x1, y1),
                    end = Offset(x2, y2),
                    color = Color.Green,
                    strokeWidth = Stroke.DefaultMiter
                )
            }
            drawCircle(
                color = Color.Green,
                radius = 2f,
                center = Offset(x1,y1)
            )
    }
    }
}