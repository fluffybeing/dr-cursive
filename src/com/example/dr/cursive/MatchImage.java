package com.example.dr.cursive;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.widget.Toast;

import java.lang.Math;

class MatchImage{
	Bitmap img1, img2;

	MatchImage(Bitmap img1, Bitmap img2){
		this.img1 = img1;
		this.img2 = img2;

	}

	double cvt2rgb(int rgb)
	{
		int alpha = rgb>>24;
		int red=(rgb & 0x00FF0000)>>16;
		int green=(rgb & 0x0000FF00)>>8;
		int blue=(rgb & 0x000000FF);
		return (red + green + blue);
		/*
		int red = Color.red((int) rgb);
		int green = Color.green((int) rgb);
		int blue = Color.blue((int) rgb);
		return (red + green + blue);
		*/
	}
	String MAE()
	{   
		double mae = 0.0;
        int img1w, img1h; 
        img1w = img2.getWidth();
        img1h = img2.getHeight();
        int[] data1 = new int[img1w * img1h];
		int[] data2 = new int[img1w * img1h];
        img1.getPixels(data1, 0, img1w, 0, 0, img1w, img1h);
        img2.getPixels(data2, 0, img1w, 0, 0, img1w, img1h);
        
        for (int i = 0; i < (img1h * img1w); i++)
        {
        	mae += Math.pow((int)data1[i] - (int)data2[i],2);
        	//System.out.println(cvt2rgb(data1[i]));
        	
        }
	
        return (String.valueOf(Math.sqrt(mae/(img1w * img1h))));
	}
}
