package tw.org.iii.appps.brad36;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {
    private boolean isInit;
    private float viewW, viewH, centerW, centerH, ballX, ballY,ballR;
    private Paint paintLine, paintBall;


    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.BLACK);
    }

    private void init(){
        viewW = getWidth(); viewH = getHeight();
        centerW = viewW / 2; centerH = viewH / 2;
        ballX = centerW; ballY = centerH;
        ballR = 40;

        paintLine = new Paint(); paintLine.setColor(Color.RED); paintLine.setStrokeWidth(1);
        paintBall = new Paint(); paintBall.setColor(Color.YELLOW);
        isInit = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isInit) init();

        canvas.drawCircle(ballX, ballY, ballR, paintBall);

        canvas.drawLine(0, centerH, viewW, centerH, paintLine);
        canvas.drawLine(centerW, 0, centerW, viewH, paintLine);

    }

    public float getW(){
        return viewW;
    }
    public float getH(){
        return viewH;
    }

    public void setBallXY(float x, float y, float z){
        ballX = x; ballY = y; //ballR = z;
        invalidate();
    }

}
