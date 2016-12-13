package def;
import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Map;


public class JinShan_def {

//http://dict-co.iciba.com/api/dictionary.php?w=go&type=xml&key=78059181E8F2E448260E590E98B30347 
 private String url = "http://dict-co.iciba.com/api/dictionary.php";
 private String [] getresult=new String[100];
 private int count=0;
 
 //private String keyfrom = "zhaoxiong-122";
 private String key = "78059181E8F2E448260E590E98B30347";

 
 private String doctype = "xml";

 
 private String sendGet(String str) throws Exception{

  // 编码成UTF-8
  try {
   str = URLEncoder.encode(str, "utf-8");
  } catch (UnsupportedEncodingException e) {
   e.printStackTrace();
  }

  StringBuffer buf = new StringBuffer();
 // buf.append("keyfrom=");
 // buf.append(keyfrom);
  	buf.append("w=");
  	buf.append(str);
  	buf.append("&type=");
  	buf.append(doctype);
  	buf.append("&key=");
  	buf.append(key);
  
  
  
  String param = buf.toString();

  String result = "";
  String urlname=url+"?"+param;
  //System.out.println(urlname);
  URL realurl=new URL(urlname);
  InputStreamReader in=new InputStreamReader(realurl.openStream(),"utf-8");
  BufferedReader br=new BufferedReader(in);
  String temp;
  StringBuffer buffer=new StringBuffer();
  while((temp=br.readLine())!=null)
	      buffer.append(temp);
  result=buffer.toString();//转为一个String
  //System.out.println(result);
  return result;
 }
 
 
 
 
 public String getJinShanValue(String str) throws Exception{
  String result = null;

  // 发送GET请求翻译
  result = sendGet(str);
  int re1=result.indexOf("<pos>");
  int re2=result.indexOf(".</pos>");
  int re3=result.indexOf("<acceptation>");
  int re4=result.indexOf("</acceptation>");
  //int re5=result.indexOf("<sent>");
  System.out.println("=====基本释义====");
  getresult[count]="=====基本释义====";
  count++;
  while(re1!=-1){
	  String in1,in2;
	  in1=result.substring(re1+5, re2);
	  in2=result.substring(re3+13,re4);
	  System.out.println(in1);
	  System.out.println(in2);
	  getresult[count]=in1;
	  count++;
	  getresult[count]=in2;
	  count++;
	  result=result.substring(re4+14);
	   re1=result.indexOf("<pos>");
	   re2=result.indexOf(".</pos>");
	   re3=result.indexOf("<acceptation>");
	   re4=result.indexOf("</acceptation>");
	  
  }
  System.out.println("=====例句====");
  getresult[count]="=====例句====";
   re1=result.indexOf("<orig>");
   re2=result.indexOf("</orig>");
   re3=result.indexOf("<trans>");
   re4=result.indexOf("</trans>");
   
   while(re1!=-1){
		  String in1,in2;
		  in1=result.substring(re1+6, re2);
		  in2=result.substring(re3+7,re4);
		  System.out.println(in1);
		  System.out.println(in2);
		  getresult[count]=in1;
		  count++;
		  getresult[count]=in2;
		  count++;
		  result=result.substring(re4+8);
		  re1=result.indexOf("<orig>");
		   re2=result.indexOf("</orig>");
		   re3=result.indexOf("<trans>");
		   re4=result.indexOf("</trans>");
		   
		  
	  }
  
  
  
  
  return result;
  
 }

 public static void main(String[] args) throws Exception{

  String str = "java";

  JinShan_def test = new JinShan_def();
  
  String temp = test.getJinShanValue(str);
 // System.out.println(temp);
 }
}


