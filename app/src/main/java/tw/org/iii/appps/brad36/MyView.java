package tw.org.iii.appps.brad36;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {
    private boolean isInit;
    private float viewW, viewH, centerW, centerH;
    private Paint paintLine;


    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.BLACK);
    }

    private void init(){
        viewW = getWidth(); viewH = getHeight();
        centerW = viewW / 2; centerH = viewH / 2;

        paintLine = new Paint(); paintLine.setColor(Color.RED); paintLine.setStrokeWidth(1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isInit) init();

        canvas.drawLine(0, centerH, viewW, centerH, paintLine);
        canvas.drawLine(centerW, 0, centerW, viewH, paintLine);
    }
}
