package com.jiafan.utils;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
public class ImageHelper {

	public static String createiconzip(String iconpath) throws Exception{
		File fromPic=new File(iconpath); 
		int lastIndexOf = iconpath.lastIndexOf("\\");
		String filename = iconpath.substring(lastIndexOf+1, iconpath.length());
		String filerootpath = iconpath.substring(0, lastIndexOf);
		System.out.println(filename);
		String filedirname=filename.substring(0,filename.lastIndexOf("."));
		System.out.println(filedirname);
				
		Fileutils.createDir(filerootpath+"\\"+filedirname);
		Fileutils.createDir(filerootpath+"\\"+filedirname+"\\res");		
		Fileutils.createDir(filerootpath+"\\"+filedirname+"\\res\\drawable");
		ImageHelper.zoomImage(iconpath, filerootpath+"\\"+filedirname+"\\res\\drawable\\icon.png", 192, 192);
		
		Fileutils.createDir(filerootpath+"\\"+filedirname+"\\res\\drawable-hdpi");
		ImageHelper.zoomImage(iconpath, filerootpath+"\\"+filedirname+"\\res\\drawable-hdpi\\icon.png", 72, 72);
		Fileutils.createDir(filerootpath+"\\"+filedirname+"\\res\\drawable-hdpi-v4");
		ImageHelper.zoomImage(iconpath, filerootpath+"\\"+filedirname+"\\res\\drawable-hdpi-v4\\icon.png", 72, 72);
		
		Fileutils.createDir(filerootpath+"\\"+filedirname+"\\res\\drawable-ldpi");
		ImageHelper.zoomImage(iconpath, filerootpath+"\\"+filedirname+"\\res\\drawable-ldpi\\icon.png", 36, 36);
		Fileutils.createDir(filerootpath+"\\"+filedirname+"\\res\\drawable-ldpi-v4");
		ImageHelper.zoomImage(iconpath, filerootpath+"\\"+filedirname+"\\res\\drawable-ldpi-v4\\icon.png", 36, 36);
		
		Fileutils.createDir(filerootpath+"\\"+filedirname+"\\res\\drawable-mdpi");
		ImageHelper.zoomImage(iconpath, filerootpath+"\\"+filedirname+"\\res\\drawable-mdpi\\icon.png", 48, 48);
		Fileutils.createDir(filerootpath+"\\"+filedirname+"\\res\\drawable-mdpi-v4");
		ImageHelper.zoomImage(iconpath, filerootpath+"\\"+filedirname+"\\res\\drawable-mdpi-v4\\icon.png", 48, 48);
		
		Fileutils.createDir(filerootpath+"\\"+filedirname+"\\res\\drawable-xhdpi");
		ImageHelper.zoomImage(iconpath, filerootpath+"\\"+filedirname+"\\res\\drawable-xhdpi\\icon.png", 96, 96);
		Fileutils.createDir(filerootpath+"\\"+filedirname+"\\res\\drawable-xhdpi-v4");
		ImageHelper.zoomImage(iconpath, filerootpath+"\\"+filedirname+"\\res\\drawable-xhdpi-v4\\icon.png", 96, 96);
		
		Fileutils.createDir(filerootpath+"\\"+filedirname+"\\res\\drawable-xxhdpi");
		ImageHelper.zoomImage(iconpath, filerootpath+"\\"+filedirname+"\\res\\drawable-xxhdpi\\icon.png", 144, 144);
		Fileutils.createDir(filerootpath+"\\"+filedirname+"\\res\\drawable-xxhdpi-v4");
		ImageHelper.zoomImage(iconpath, filerootpath+"\\"+filedirname+"\\res\\drawable-xxhdpi-v4\\icon.png", 144, 144);
		
		Fileutils.createDir(filerootpath+"\\"+filedirname+"\\res\\drawable-xxxhdpi");
		ImageHelper.zoomImage(iconpath, filerootpath+"\\"+filedirname+"\\res\\drawable-xxxhdpi\\icon.png", 192, 192);
		Fileutils.createDir(filerootpath+"\\"+filedirname+"\\res\\drawable-xxxhdpi-v4");
		ImageHelper.zoomImage(iconpath, filerootpath+"\\"+filedirname+"\\res\\drawable-xxxhdpi-v4\\icon.png", 192, 192);
	
		return filerootpath+"\\"+filedirname+"\\res";
	}
	/*
	 * 根据尺寸图片居中裁剪
	 */
	 public static void cutCenterImage(String src,String dest,int w,int h) throws IOException{ 
		 Iterator iterator = ImageIO.getImageReadersByFormatName("jpg"); 
         ImageReader reader = (ImageReader)iterator.next(); 
         InputStream in=new FileInputStream(src);
         ImageInputStream iis = ImageIO.createImageInputStream(in); 
         reader.setInput(iis, true); 
         ImageReadParam param = reader.getDefaultReadParam(); 
         int imageIndex = 0; 
         Rectangle rect = new Rectangle((reader.getWidth(imageIndex)-w)/2, (reader.getHeight(imageIndex)-h)/2, w, h);  
         param.setSourceRegion(rect); 
         BufferedImage bi = reader.read(0,param);   
         ImageIO.write(bi, "jpg", new File(dest));           
  
	 }
	/*
	 * 图片裁剪二分之一
	 */
	 public static void cutHalfImage(String src,String dest) throws IOException{ 
		 Iterator iterator = ImageIO.getImageReadersByFormatName("jpg"); 
         ImageReader reader = (ImageReader)iterator.next(); 
         InputStream in=new FileInputStream(src);
         ImageInputStream iis = ImageIO.createImageInputStream(in); 
         reader.setInput(iis, true); 
         ImageReadParam param = reader.getDefaultReadParam(); 
         int imageIndex = 0; 
         int width = reader.getWidth(imageIndex)/2; 
         int height = reader.getHeight(imageIndex)/2; 
         Rectangle rect = new Rectangle(width/2, height/2, width, height); 
         param.setSourceRegion(rect); 
         BufferedImage bi = reader.read(0,param);   
         ImageIO.write(bi, "jpg", new File(dest));   
	 }
	/*
	 * 图片裁剪通用接口
	 */

    public static void cutImage(String src,String dest,int x,int y,int w,int h) throws IOException{ 
           Iterator iterator = ImageIO.getImageReadersByFormatName("jpg"); 
           ImageReader reader = (ImageReader)iterator.next(); 
           InputStream in=new FileInputStream(src);
           ImageInputStream iis = ImageIO.createImageInputStream(in); 
           reader.setInput(iis, true); 
           ImageReadParam param = reader.getDefaultReadParam(); 
           Rectangle rect = new Rectangle(x, y, w,h);  
           param.setSourceRegion(rect); 
           BufferedImage bi = reader.read(0,param);   
           ImageIO.write(bi, "jpg", new File(dest));           

    } 
    /*
     * 图片缩放
     */
    public static void zoomImage(String src,String dest,int w,int h) throws Exception {
		double wr=0,hr=0;
		File srcFile = new File(src);
		File destFile = new File(dest);
		BufferedImage bufImg = ImageIO.read(srcFile);
		Image Itemp = bufImg.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		wr=w*1.0/bufImg.getWidth();
		hr=h*1.0 / bufImg.getHeight();
		AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
		Itemp = ato.filter(bufImg, null);
		try {
			ImageIO.write((BufferedImage) Itemp,dest.substring(dest.lastIndexOf(".")+1), destFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
    
    /** 检测是否是图片文件 */
    public static boolean isImageFile(String fileName, String contentType) {
    	if(fileName == null || contentType==null){
    		return false;
    	}
    	
        if(!contentType.startsWith("image/")){
        	return false;
        }
        
        String[] imgExts = {".gif", ".jpg", ".jpeg",".bmp", ".png"};
        for(String ext : imgExts) {
            if(fileName.toLowerCase().endsWith(ext)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 获取图片宽度
     * @param file  图片文件
     * @return 宽度
     */
    public static int getImgWidth(File file) {
        InputStream is = null;
        BufferedImage src = null;
        int ret = -1;
        try {
            is = new FileInputStream(file);
            src = javax.imageio.ImageIO.read(is);
            ret = src.getWidth(null); // 得到源图宽
            is.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return ret;
    }
    
    
    /**
     * 获取图片高度
     * @param file  图片文件
     * @return 高度
     */
    public static int getImgHeight(File file) {
        InputStream is = null;
        BufferedImage src = null;
        int ret = -1;
        try {
            is = new FileInputStream(file);
            src = javax.imageio.ImageIO.read(is);
            ret = src.getHeight(null); // 得到源图高
            is.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return ret;
    }
}
