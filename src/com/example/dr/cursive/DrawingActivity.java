package com.example.dr.cursive;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.example.dr.cursive.R;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.os.Environment;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
 
public class DrawingActivity extends Activity implements OnTouchListener {
  ImageView imageView;
  Bitmap bitmap;
  Canvas canvas;
  Paint paint;
  Button saveimage;
  Path path;
  
  float startX = 0, startY = 0, endX = 0, endY = 0, movex = 0, movey = 0;
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.drawinglayout);
 
    imageView = (ImageView) this.findViewById(R.id.imageView1);
 
    Display currentDisplay = getWindowManager().getDefaultDisplay();
    float dw = currentDisplay.getWidth();
    float dh = currentDisplay.getHeight();
 
    bitmap = Bitmap.createBitmap((int) dw, (int) dh,
        Bitmap.Config.ARGB_8888);
    canvas = new Canvas(bitmap);
    paint = new Paint();
    paint.setColor(Color.BLACK);
    paint.setStrokeWidth(20);
    imageView.setImageBitmap(bitmap);
 
    imageView.setOnTouchListener(this);
    
    saveimage = (Button) findViewById(R.id.savebtn);
    
    saveimage.setOnClickListener(new View.OnClickListener() {
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			String sometext = saveToInternalStorage(bitmap);
			
		}
	});
    
  }
    
    String saveToInternalStorage(Bitmap bitmapImage){
    	File sdDir = new File(Environment.getExternalStorageDirectory(), "/SDImageTutorial/");
    	sdDir.mkdirs();
    	
    	File image = new File(sdDir, "myfile2.png");
    	FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(image);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			Bitmap.createScaledBitmap(bitmapImage, 100, 100, true);
			bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, bos); 
			bos.flush();
			bos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 return sdDir.getAbsolutePath();
    }
    
  
 
  public boolean onTouch(View v, MotionEvent event) {
    int action = event.getAction();
    switch (action) {
    	case MotionEvent.ACTION_DOWN:
    		startX=event.getX();
    		startY=event.getY();
    		//path.moveTo(startX, startY);
    		break;
    	case MotionEvent.ACTION_MOVE:
    		endX = event.getX();
    		endY = event.getY();
    		canvas.drawLine(startX,startY,endX,endY, paint);
    		imageView.invalidate();
    		startX = endX;
    		startY = endY;
    		break;
    	case MotionEvent.ACTION_UP:
    		break;
    	case MotionEvent.ACTION_CANCEL:
    		break;
    	default:
    		break;
    }
    return true;
  }
}