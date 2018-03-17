package csc212.guitarchord;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.os.Parcelable;

public class GraphView extends View {
	
	private int indexChord;
	private int indexKey;
	
	private Paint stringPaint;
	private Paint fretPaint;
	private Paint fingerOnePaint;
	private Paint fingerTwoPaint;
	private Paint fingerThreePaint;
	private Paint fingerFourPaint;
	private Paint barreOnePaint;
	private Paint textPaint;

	public int getIndexChord() {
		return indexChord;
	}

	public void setIndexChord(int indexChord) {
		this.indexChord = indexChord;
	}

	public int getIndexKey() {
		return indexKey;
	}

	public void setIndexKey(int indexKey) {
		this.indexKey = indexKey;
	}

	public GraphView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		
		Init();
	}

	public GraphView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	Init();
	}

	public GraphView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		Init();
	}
	
	public void Init(){
		
		setIndexChord(0);
		setIndexKey(0);
		
		stringPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		stringPaint.setStyle(Paint.Style.STROKE);
		stringPaint.setStrokeWidth(2);
		stringPaint.setColor(Color.BLACK);
		
		fretPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		fretPaint.setStyle(Paint.Style.STROKE);
		fretPaint.setStrokeWidth(3);
		fretPaint.setColor(Color.BLACK);
		
		fingerOnePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		fingerOnePaint.setStyle(Paint.Style.FILL);
		fingerOnePaint.setColor(Color.BLUE);
		
		fingerTwoPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		fingerTwoPaint.setStyle(Paint.Style.FILL);
		fingerTwoPaint.setColor(Color.RED);
		
		fingerThreePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		fingerThreePaint.setStyle(Paint.Style.FILL);
		fingerThreePaint.setColor(Color.GREEN);
		
		fingerFourPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		fingerFourPaint.setStyle(Paint.Style.FILL);
		fingerFourPaint.setColor(Color.MAGENTA);
		
		barreOnePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		barreOnePaint.setStyle(Paint.Style.FILL);
		barreOnePaint.setStrokeWidth(20);
		barreOnePaint.setColor(Color.BLUE);
		
		textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		textPaint.setTextSize(20);
		textPaint.setStyle(Paint.Style.FILL);
		textPaint.setStrokeWidth(2);
		textPaint.setColor(Color.BLACK);
		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		canvas.drawColor(Color.WHITE);
		
		for (double y = -7; y <= 7; y++)
			canvas.drawLine(interpX(-6), interpY(y), interpX(6), interpY(y), stringPaint);
	
		for (double x = -5; x <= 5; x++)
		canvas.drawLine(interpX(x), interpY(7), interpX(x), interpY(-7), fretPaint);	
	   
	int stringNumber = 1;
	int fretNumber = 4;
	
	textPaint.setTextAlign(Paint.Align.LEFT);
	for (double y = -5.90; y <= 5.90; y++){
    canvas.drawText(Integer.toString(stringNumber), interpX(-4.5), interpY(y), textPaint);
	stringNumber++;
	}	
	
	textPaint.setTextAlign(Paint.Align.LEFT);
	for (double x = -3.90; x <= 3.90; x++){
		if(fretNumber!=0){
		canvas.drawText(Integer.toString(fretNumber), interpX(x), interpY(-6.75), textPaint);
		fretNumber--;
		}
	}
	
	   if((getIndexChord() == 0)&&(getIndexKey() == 0)){
			canvas.drawLine(interpX(-1.50), interpY(-2.75), interpX(-1.50), interpY(-5.25), barreOnePaint);
		   }
	   else if((getIndexChord() == 0)&&(getIndexKey() == 1)){
		   canvas.drawCircle(interpX(-0.5), interpY(-6), 12, fingerOnePaint);
		   canvas.drawCircle(interpX(-1.5), interpY(-4), 12, fingerTwoPaint);
		   canvas.drawCircle(interpX(-1.5), interpY(-5), 12, fingerThreePaint);
	   }
	   
	   if((getIndexChord()==1)&&(getIndexKey()==0)){
		   canvas.drawLine(interpX(-1.5), interpY(-1.75), interpX(-1.5), interpY(-6.25), barreOnePaint);
		   canvas.drawCircle(interpX(-3.5), interpY(-3), 12, fingerTwoPaint);
		   canvas.drawCircle(interpX(-3.5), interpY(-4), 12, fingerThreePaint);
		   canvas.drawCircle(interpX(-3.5), interpY(-5), 12, fingerFourPaint);
	   }
	   else if((getIndexChord()==1)&&(getIndexKey()==1)){
		   canvas.drawLine(interpX(-1.5), interpY(-1.75), interpX(-1.5), interpY(-6.25), barreOnePaint);
		   canvas.drawCircle(interpX(-2.5), interpY(-5), 12, fingerTwoPaint);
		   canvas.drawCircle(interpX(-3.5), interpY(-4), 12, fingerThreePaint);
		   canvas.drawCircle(interpX(-3.5), interpY(-3), 12, fingerFourPaint);
	   }
	   
	   if((getIndexChord()==2)&&(getIndexKey()==0)){
		   canvas.drawCircle(interpX(-0.5), interpY(-5), 12, fingerOnePaint);
		   canvas.drawCircle(interpX(-1.5), interpY(-3), 12, fingerTwoPaint);
		   canvas.drawCircle(interpX(-2.5), interpY(-2), 12, fingerThreePaint);
	   }
	   else if((getIndexChord()==2)&&(getIndexKey()==1)){
		   canvas.drawCircle(interpX(-0.5), interpY(-5), 12, fingerOnePaint);
		   canvas.drawCircle(interpX(-0.5), interpY(-3), 12, fingerTwoPaint);
		   canvas.drawCircle(interpX(-2.5), interpY(-2), 12, fingerFourPaint);
	   }
	
	if((getIndexChord()==3)&&(getIndexKey()==0)){
		canvas.drawCircle(interpX(-1.5), interpY(-4), 12, fingerOnePaint);
		canvas.drawCircle(interpX(-1.5), interpY(-6), 12, fingerTwoPaint);
		canvas.drawCircle(interpX(-2.5), interpY(-5), 12, fingerThreePaint);
	}
	else if((getIndexChord()==3)&&(getIndexKey()==1)){
		canvas.drawCircle(interpX(-0.5), interpY(-6), 12, fingerOnePaint);
		canvas.drawCircle(interpX(-1.5), interpY(-4), 12, fingerTwoPaint);
		canvas.drawCircle(interpX(-2.5), interpY(-5), 12, fingerThreePaint);
	}
	
	if((getIndexChord()==4)&&(getIndexKey()==0)){
		canvas.drawCircle(interpX(-0.5), interpY(-4), 12, fingerOnePaint);
		canvas.drawCircle(interpX(-1.5), interpY(-2), 12, fingerTwoPaint);
	    canvas.drawCircle(interpX(-1.5), interpY(-3), 12, fingerThreePaint);
	}
	else if((getIndexChord()==4)&&(getIndexKey()==1)){
		canvas.drawCircle(interpX(-1.5), interpY(-2), 12, fingerTwoPaint);
		canvas.drawCircle(interpX(-1.5), interpY(-3), 12, fingerThreePaint);
	}
	
	if((getIndexChord()==5)&&(getIndexKey()==0)){
		canvas.drawLine(interpX(-0.5), interpY(-6.25), interpX(-0.5), interpY(-4.75), barreOnePaint);
		canvas.drawCircle(interpX(-1.5), interpY(-4), 12, fingerTwoPaint);
		canvas.drawCircle(interpX(-2.5), interpY(-3), 12, fingerThreePaint);
	}
	else if ((getIndexChord()==5)&&(getIndexKey()==1)){
		canvas.drawLine(interpX(-0.5), interpY(-6.25), interpX(-0.5), interpY(-0.75), barreOnePaint);
		canvas.drawCircle(interpX(-2.5), interpY(-2), 12, fingerThreePaint);
		canvas.drawCircle(interpX(-2.5), interpY(-3), 12, fingerFourPaint);
	}
	
	if((getIndexChord()==6)&&(getIndexKey()==0)){
		canvas.drawCircle(interpX(-1.5), interpY(-2), 12, fingerTwoPaint);
		canvas.drawCircle(interpX(-2.5), interpY(-1), 12, fingerThreePaint);
		canvas.drawCircle(interpX(-2.5), interpY(-6), 12, fingerFourPaint);
	}
	else if((getIndexChord()==6)&&(getIndexKey()==1)){
		canvas.drawCircle(interpX(-0.5), interpY(-2), 12, fingerOnePaint);
		canvas.drawCircle(interpX(-2.5), interpY(-1), 12, fingerTwoPaint);
		canvas.drawCircle(interpX(-2.5), interpY(-5), 12, fingerThreePaint);
		canvas.drawCircle(interpX(-2.5), interpY(-6), 12, fingerFourPaint);
	}
	}
	
	private float interpX(double x) {
        	  double width = this.getWidth();

	       return (float) ((x + 5)
	    		   / (5) * width);
          }

	 private float interpY(double y) {
		 double height = this.getHeight();

	       return (float) ((y + 7)
	    		   /  (7) * -height + height);
	 }
	   
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int measuredHeight=measureHeight(heightMeasureSpec);
		int measuredWidth = measureWidth(widthMeasureSpec);
		setMeasuredDimension(measuredWidth, measuredHeight);
	}

	 private int measureWidth(int widthMeasureSpec) {

	       int specSize = MeasureSpec.getSize(widthMeasureSpec) -
	       this.getPaddingLeft() - this.getPaddingRight();

	       return specSize;

	   }

	   private int measureHeight(int heightMeasureSpec) {

	       int specSize = MeasureSpec.getSize(heightMeasureSpec);

	         return specSize;

	   }	  
	 
}
