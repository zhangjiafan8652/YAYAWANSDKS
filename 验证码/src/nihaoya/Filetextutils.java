package nihaoya;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Filetextutils {

	 // 读取指定路径文本文件  
    public static String read(String filePath) {  
        StringBuilder str = new StringBuilder();  
        BufferedReader in = null;  
        try {  
            in = new BufferedReader(new FileReader(filePath));  
            String s;  
            try {  
                while ((s = in.readLine()) != null)  
                    str.append(s + '\n');  
            } finally {  
                in.close();  
            }  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return str.toString();  
    }  
    
    // 读取指定路径文本文件  
    public static String read(String filePath,String encode) {  
    	String text = null;
    	BufferedReader br = null;
    	try {
			File file=new File(filePath);
			br=new BufferedReader(new InputStreamReader(new FileInputStream(file),encode));
			
			String line=null;
			        while((line=br.readLine())!=null){
			            if(!line.trim().equals("")){
			               text=text+line+"\n";
			            }
			        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally{
			try {
				br.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
        return text;  
    }  
  
    // 写入指定的文本文件，append为true表示追加，false表示重头开始写，  
    //text是要写入的文本字符串，text为null时直接返回  
    public static void write(String filePath, boolean append, String text) {  
        if (text == null)  
            return;  
        try {  
            BufferedWriter out = new BufferedWriter(new FileWriter(filePath,  
                    append));  
            try {  
                out.write(text);  
            } finally {  
                out.close();  
            }  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  
    
    // 写入指定的文本文件，append为true表示追加，false表示重头开始写，  
    //text是要写入的文本字符串，text为null时直接返回  
    public static void write(String filePath, boolean append, String text,String encode) {  
        if (text == null)  
            return;  
        try {  
        	
        	// String FileContent = "文件内容"; 
        	    try { 
        	        FileOutputStream fos = new FileOutputStream(filePath); 
        	        OutputStreamWriter osw = new OutputStreamWriter(fos,encode); 
        	        osw.write(text); 
        	        //
        	        osw.flush(); 
        	        osw.close();
        	    } catch (Exception e) { 
        	        e.printStackTrace(); 
        	    }
          
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }
}
