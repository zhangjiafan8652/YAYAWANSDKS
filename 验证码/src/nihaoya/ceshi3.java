package nihaoya;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

public class ceshi3 {

	 private static int k = 1; // 定义递归次数变量  
	public static String publickey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC47RHyzQJXRQaaqjLKva+dl+CfzD7PsUG2lX1Vwu/YS1T9GX/E2Q7420qXZMhpBynEQ5807agh9Idou0u5bX5R0dTXWitPJyoaQfCkWyxF7oMQarPYKU0+Q9Y2DXzut66Q3QPB9vmCQzLZIak+nt3mtn+zY4SIJ3MPYKDPSLh0OwIDAQAB";
	
	public static String privatekey="MIICXgIBAAKBgQC47RHyzQJXRQaaqjLKva+dl+CfzD7PsUG2lX1Vwu/YS1T9GX/E2Q7420qXZMhpBynEQ5807agh9Idou0u5bX5R0dTXWitPJyoaQfCkWyxF7oMQarPYKU0+Q9Y2DXzut66Q3QPB9vmCQzLZIak+nt3mtn+zY4SIJ3MPYKDPSLh0OwIDAQABAoGBAI0CUL5YruFGxAtpnoNT8rZzmhefkYGjgx0rjj+3/lVq0+qO6At9C9qctJsOgXb8vE9o1NxyH9OMdzhYIRzKrhd+ouDn4lvxCZ2ASJg5Vv0q4JxUwf1BeWCzbMX9RzbSVdawefhGJ0CHyubZmu4ecnB/Tm2L1JZw1eIROt5DC/4xAkEA7RLRhdHM1uHDOpLmXP/zv8L+eWU47gAPXilZOUtVF4Wm1oGFH+3wBxzfidsg1RKoenoVmoqnL3eBjCVVMVHD0wJBAMewfWlUHZu1FAIViL9GU6NfBflT5fwjwSXlnZkrDs0MF+XvUBiKzUeMYmmVY2T8Oi8D/5EtoKkR48jJkis5lPkCQQCR7fSFEAir+NooswYUFwVec9Qau6EYd3b+OiuO5f0h9oYlIiLk7ZzDdBVxkS4bmlmU0pN7xn9063YV42EWc8M7AkBHjItDBCqfsd/7Zy/aKR6quM9xKIiNMVpxc7ZmPTFOcvjVIEw4nvMyobBZWHeGpYFLe0lFAW5BZfGR7fd7mWQxAkEA6FOPtvKRuSW7+eg1O9+r4rf4DDpg88X4mAChjz8U8KV0v2MiGu6vWoEH3I4rE3ldI6aN4QunskW2TrqdMe1gKQ==";
	public static void main(String[] args) throws Exception {
		
		 String re="yj-wxdf01";
	        String read = Filetextutils.read("D:\\zippackage\\yj.txt", "utf-8")
					.replace("null", "").replaceAll("\r|\n", "").replace(" ", "");
	        String[] names = read.split(";");
	        System.out.println("+++++++++++++++++++++++++++"+names.length);
	        for (int i = 0; i < names.length+1; i++) {
	        	System.out.println("D:\\zippackage\\"+re+"======"+"D:\\zippackage\\config.json"+names[i]);
				Fileutils.copyFolder("D:\\zippackage\\"+re, "D:\\zippackage\\"+names[i]);
			
				rePagename("D:\\zippackage\\"+names[i]+"\\index.html", re, names[i]);
				
				ZipUtil.ZIP("D:\\zippackage\\"+names[i],"D:\\zippackage\\"+names[i]+".zip"
		                );
				
			//	ZipUtil zipUtil = new ZipUtil("D:\\zippackage\\"+names[i]+".zip", "D:\\zippackage\\"+names[i]) ;
			//	zipUtil.zip();
		        //zip("D:\\zippackage\\"+names[i],"D:\\zippackage\\"+names[i]+".zip",filter);  
	        }
	}
	 // 更改文件的包名
	private static void rePagename(String mest_filepath, String oldpagename,
			String newpagename) {
		
		// TODO Auto-generated method stub
		if (oldpagename.trim().isEmpty() || oldpagename.equals("1")) {
			return;
		}
		String read = Filetextutils.read(mest_filepath);
		String replace = read.replace(oldpagename, newpagename);
		Filetextutils.write(mest_filepath, false, replace);
	}
	
	   private static String comment = "";  
	      
	    public static void setComment(String kcomment) {  
	      comment = kcomment;  
	    }  
	  
	      
	    public static void zip(String src, String dest, List filter) throws Exception {  
	        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(dest));  
	        File srcFile = new File(src);  
	        zip(out,srcFile,"",filter);  
	        out.close();  
	    }  
	      
	      
	    public static void zip(ZipOutputStream out, File srcFile, String base, List filter) throws Exception {  
	        if(srcFile.exists()==false) {  
	            throw new Exception("压缩目录不存在!");  
	        }  
	          
	        if(srcFile.isDirectory()) {  
	            File[] files = srcFile.listFiles();  
	            base = base.length() == 0 ? "" : base + "/";  
	            if (isExist(base, filter)) {  
	                out.putNextEntry(new ZipEntry(base));  
	            }  
	            for(int i=0; i<files.length; i++) {  
	                zip(out,files[i],base + files[i].getName(),filter);  
	            }  
	        } else {  
	            if (isExist(base, filter)) {  
	                base = base.length() == 0 ? srcFile.getName() : base ;  
	                ZipEntry zipEntry = new ZipEntry(base);  
	                zipEntry.setComment(comment);  
	                out.putNextEntry(zipEntry);  
	                FileInputStream in = new FileInputStream(srcFile);  
	                int length = 0;  
	                byte[] b = new byte[1024];  
	                while((length=in.read(b,0,1024))!=-1) {  
	                    out.write(b,0,length);  
	                }  
	                in.close();  
	            }  
	        }  
	    }  
	      
	      
	    public static boolean isExist(String base, List list) {  
	        if (list != null && !list.isEmpty()) {  
	            for (int i = 0; i < list.size(); i++) {  
	                if (base.indexOf((String) list.get(i)) >= 0) {  
	                    return true;  
	                }  
	            }  
	        }  
	        return false;  
	    }  
	      
	      
	    public void unZip(String srcFile,String dest,boolean deleteFile)  throws Exception {  
	        File file = new File(srcFile);  
	        if(!file.exists()) {  
	            throw new Exception("解压文件不存在!");  
	        }  
	        ZipFile zipFile = new ZipFile(file);  
	        Enumeration e = zipFile.getEntries();  
	        while(e.hasMoreElements()) {  
	            ZipEntry zipEntry = (ZipEntry)e.nextElement();  
	            if(zipEntry.isDirectory()) {  
	                String name = zipEntry.getName();  
	                name = name.substring(0,name.length()-1);  
	                File f = new File(dest + name);  
	                f.mkdirs();  
	            } else {  
	                File f = new File(dest + zipEntry.getName());  
	                f.getParentFile().mkdirs();  
	                f.createNewFile();  
	                InputStream is = zipFile.getInputStream(zipEntry);  
	                FileOutputStream fos = new FileOutputStream(f);  
	                int length = 0;  
	                byte[] b = new byte[1024];  
	                while((length=is.read(b, 0, 1024))!=-1) {  
	                    fos.write(b, 0, length);  
	                }  
	                is.close();  
	                fos.close();  
	            }  
	        }  
	          
	        if (zipFile != null) {  
	            zipFile.close();  
	        }  
	          
	        if(deleteFile) {  
	            file.deleteOnExit();  
	        }  
	    }  
	      
	      
	    public static String getZipComment(String srcFile) {  
	        String comment = "";  
	        try {  
	            ZipFile zipFile = new ZipFile(srcFile);  
	            Enumeration e = zipFile.getEntries();  
	  
	            while (e.hasMoreElements()) {  
	                ZipEntry ze = (ZipEntry) e.nextElement();  
	  
	                comment = ze.getComment();  
	                if (comment != null && !comment.equals("")  
	                        && !comment.equals("null")) {  
	                    break;  
	                }  
	            }  
	  
	            zipFile.close();  
	        } catch (Exception e) {  
	            System.out.println("获取zip文件注释信息失败:" + e.getMessage());  
	        }  
	  
	        return comment;  
	    } 
	
}
