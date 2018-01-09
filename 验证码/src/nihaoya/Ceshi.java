package nihaoya;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.Thread.State;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.print.attribute.standard.SheetCollate;
import javax.swing.plaf.SliderUI;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.util.Base64;
import android.util.TimeFormatException;


public class Ceshi {

	private static Object time;
	private static String substring;
	private static ScheduledExecutorService service;
	private static DateFormat df1;
	 static String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC47RHyzQJXRQaaqjLKva+dl+CfzD7PsUG2lX1Vwu/YS1T9GX/E2Q7420qXZMhpBynEQ5807agh9Idou0u5bX5R0dTXWitPJyoaQfCkWyxF7oMQarPYKU0+Q9Y2DXzut66Q3QPB9vmCQzLZIak+nt3mtn+zY4SIJ3MPYKDPSLh0OwIDAQAB";
	   // static String privateKey="MIICXgIBAAKBgQC47RHyzQJXRQaaqjLKva+dl+CfzD7PsUG2lX1Vwu/YS1T9GX/E2Q7420qXZMhpBynEQ5807agh9Idou0u5bX5R0dTXWitPJyoaQfCkWyxF7oMQarPYKU0+Q9Y2DXzut66Q3QPB9vmCQzLZIak+nt3mtn+zY4SIJ3MPYKDPSLh0OwIDAQABAoGBAI0CUL5YruFGxAtpnoNT8rZzmhefkYGjgx0rjj+3/lVq0+qO6At9C9qctJsOgXb8vE9o1NxyH9OMdzhYIRzKrhd+ouDn4lvxCZ2ASJg5Vv0q4JxUwf1BeWCzbMX9RzbSVdawefhGJ0CHyubZmu4ecnB/Tm2L1JZw1eIROt5DC/4xAkEA7RLRhdHM1uHDOpLmXP/zv8L+eWU47gAPXilZOUtVF4Wm1oGFH+3wBxzfidsg1RKoenoVmoqnL3eBjCVVMVHD0wJBAMewfWlUHZu1FAIViL9GU6NfBflT5fwjwSXlnZkrDs0MF+XvUBiKzUeMYmmVY2T8Oi8D/5EtoKkR48jJkis5lPkCQQCR7fSFEAir+NooswYUFwVec9Qau6EYd3b+OiuO5f0h9oYlIiLk7ZzDdBVxkS4bmlmU0pN7xn9063YV42EWc8M7AkBHjItDBCqfsd/7Zy/aKR6quM9xKIiNMVpxc7ZmPTFOcvjVIEw4nvMyobBZWHeGpYFLe0lFAW5BZfGR7fd7mWQxAkEA6FOPtvKRuSW7+eg1O9+r4rf4DDpg88X4mAChjz8U8KV0v2MiGu6vWoEH3I4rE3ldI6aN4QunskW2TrqdMe1gKQ==";
	   // static String privateKey="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIpDbKrTSUwZF8MUJp5EaPmdOqa2AiIEVJ80CSvrI2/KeMJmSysZxENTcO/VkhmdaMqoNUmYEMCbgRRaowdyyvz682QPrV5qClpqKXgb9ezUyMnYuf62n34edZEpoXz9eZBdnaF4s3HwklzD85Mi4n/ak8JuQRBNOsE2CQ4Nl5alAgMBAAECgYAnJT3QA7Ad7oDOx5bDeoTqHzzq1F84OFepF3d9Sr6muQGFsKaZllr94NL55FLQE8QGlKc5pecgUSazl5HO2w5jNR36+wFEoYjYvhtD1AyyWCuFn92plIFOy537DG0nzP0Pam3c5HATM3eMucu5vUBe1pgs9xszfWT+WtETjmdQpQJBAMQWVT2o0YNkn/oRhp8fDKlpHV46Y2sbs4OKP2EAesg8V1/wYA/rcbdZMZugr9Rrbhg+EfaWyURZ62ps+At2DbMCQQC0gjDQ4gywmUCLKaxR4fL2aNn5nk/rrwNBhNdfaVzoAaf6xo3vpUd9jHGLS8LpcXr86nPlQEEmJgCG1yUffg5HAkEAtpZZH0YweVncgT2KjmaaIrHHb/OMmbK8sJKtu4R30DYn4iTxPkGtrN24DMNOlszGQnqhvWzg0u2sAXigbAOscwJAOIpPIB6uOuLGBNEY2M0dfXF6VWXxQrGfI3aov4oRP9rE8xgefWIX8ot7RVaFxvl0vKPziWuG/JwKrjHVvveNIQJBALRyfbxu/1rrjB00MoUX8SiiOUmHNA6xD6pMghkm4QctttFSQqKr925YHelXaSsXEOW7XfeJ5xI7LbT+bu7eGG8=";
	    static String privateKey="MIICXgIBAAKBgQC47RHyzQJXRQaaqjLKva+dl+CfzD7PsUG2lX1Vwu/YS1T9GX/E2Q7420qXZMhpBynEQ5807agh9Idou0u5bX5R0dTXWitPJyoaQfCkWyxF7oMQarPYKU0+Q9Y2DXzut66Q3QPB9vmCQzLZIak+nt3mtn+zY4SIJ3MPYKDPSLh0OwIDAQABAoGBAI0CUL5YruFGxAtpnoNT8rZzmhefkYGjgx0rjj+3/lVq0+qO6At9C9qctJsOgXb8vE9o1NxyH9OMdzhYIRzKrhd+ouDn4lvxCZ2ASJg5Vv0q4JxUwf1BeWCzbMX9RzbSVdawefhGJ0CHyubZmu4ecnB/Tm2L1JZw1eIROt5DC/4xAkEA7RLRhdHM1uHDOpLmXP/zv8L+eWU47gAPXilZOUtVF4Wm1oGFH+3wBxzfidsg1RKoenoVmoqnL3eBjCVVMVHD0wJBAMewfWlUHZu1FAIViL9GU6NfBflT5fwjwSXlnZkrDs0MF+XvUBiKzUeMYmmVY2T8Oi8D/5EtoKkR48jJkis5lPkCQQCR7fSFEAir+NooswYUFwVec9Qau6EYd3b+OiuO5f0h9oYlIiLk7ZzDdBVxkS4bmlmU0pN7xn9063YV42EWc8M7AkBHjItDBCqfsd/7Zy/aKR6quM9xKIiNMVpxc7ZmPTFOcvjVIEw4nvMyobBZWHeGpYFLe0lFAW5BZfGR7fd7mWQxAkEA6FOPtvKRuSW7+eg1O9+r4rf4DDpg88X4mAChjz8U8KV0v2MiGu6vWoEH3I4rE3ldI6aN4QunskW2TrqdMe1gKQ==";
	   static {
	        try {
	            Map<String, Object> keyMap = RSAUtils.genKeyPair();
	            publicKey = RSAUtils.getPublicKey(keyMap);
	            privateKey = RSAUtils.getPrivateKey(keyMap);
	            System.err.println("公钥: \n\r" + publicKey);
	            System.err.println("私钥： \n\r" + privateKey);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    static void test() throws Exception {
	        System.err.println("公钥加密——私钥解密");
	        String source = "这是一";
	        System.out.println("\r加密前文字：\r\n" + source);
	        byte[] data = source.getBytes();
	        byte[] encodedData = RSAUtils.encryptByPublicKey(data, publicKey);
	        System.out.println(Base64.encodeToString(encodedData, Base64.NO_WRAP));
	        System.out.println("加密后文字：\r\n" +  encodeHexString(encodedData));
	       // System.out.println(encodeHexString(encodedData));
	        byte[] decodedData = RSAUtils.decryptByPrivateKey(encodedData, privateKey);
	        String target = new String(decodedData);
	       System.out.println("解密后文字: \r\n" + target);
	    }
	    private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
		
	    public static String encodeHexString(byte[] data) {
	    	
	        return new String(encodeHex(data, DIGITS_LOWER));
	       }
	     
	     //字节数组转16进制
	    protected static char[] encodeHex(byte[] data, char[] toDigits) {
	        int l = data.length;
	        char[] out = new char[l << 1];
	        for (int i = 0, j = 0; i < l; i++) {
	            out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
	            out[j++] = toDigits[0x0F & data[i]];
	        }
	        return out;
	    } 
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		 try {
			test();
			// System.out.println(publicKey);
			 
			 byte[] decode = Base64Utils.decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC9t2k2VMjHO0IEj69Lvt8nBpDDFhD80RyT6iouQocA/kxbaEjeyN/fV+XgSyNAmnoMZmWz/cs0GqbIdZ+HFEqDGY1gz7aw3Y8CnPNraNZvbDY8F7yQAGYeZT9k5mSgriCSurtnki0uYzXV604dXwavMSk5H0pzu/WKtrTaSbuODwIDAQAB");
			 
			 System.out.println( new String(decode, "GB2312"));
		 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static String timemFormate(String time){
		String tempstring=time+"000";
		Date date = new Date(Long.parseLong(tempstring));
		DateFormat df2 = null;//声明一个DateFormat
		
		df2 = DateFormat.getDateTimeInstance();//得到日期时间得到DateFormat对象
	
		return df2.format(date);
	}
	
	public static void stat(final String text){
		Runnable runnable1 = new Runnable() {  
	            public void run() {  
	                // task to run goes here  
	                System.out.println(text);
	                try {
						wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                notify();
	                
	            }  
	        };  
	      
	        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间  
	        service.scheduleAtFixedRate(runnable1, 0, 1, TimeUnit.SECONDS); 
	}
	/**
	 * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0
	 * 
	 * @param version1
	 * @param version2
	 * @return
	 */
	public static int compareVersion(String version1, String version2)
			throws Exception {
		if (version1 == null || version2 == null) {
			return 0;
		}
		String[] versionArray1 = version1.split("\\.");// 注意此处为正则匹配，不能用.；
		String[] versionArray2 = version2.split("\\.");
		int idx = 0;
		int minLength = Math.min(versionArray1.length, versionArray2.length);// 取最小长度值
		int diff = 0;
		while (idx < minLength
				&& (diff = versionArray1[idx].length()
						- versionArray2[idx].length()) == 0// 先比较长度
				&& (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {// 再比较字符
			++idx;
		}
		// 如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
		diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
		return diff;
	}
	 private static String StrToBinstr(String str) {
	        char[] strChar=str.toCharArray();
	        String result="";
	        for(int i=0;i<strChar.length;i++){
	            result +=Integer.toBinaryString(strChar[i])+ "";
	        }
	        return result;
	    }

}
