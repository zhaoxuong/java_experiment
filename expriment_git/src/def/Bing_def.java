package def;
import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Map;


public class Bing_def {
//http://xtk.azurewebsites.net/BingDictService.aspx?type=xml&Word=what
//http://dict-co.iciba.com/api/dictionary.php?w=go&type=xml&key=78059181E8F2E448260E590E98B30347 
 private String url = "http://xtk.azurewebsites.net/BingDictService.aspx";

 
 //private String keyfrom = "zhaoxiong-122";
// private String key = "78059181E8F2E448260E590E98B30347";

 
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
  
  	buf.append("type=");
  	buf.append(doctype);
  	buf.append("&Word=");
  	buf.append(str);
  
  
  
  String param = buf.toString();

  String result = "";
  String urlname=url+"?"+param;
  System.out.println(urlname);
  URL realurl=new URL(urlname);
  InputStreamReader in=new InputStreamReader(realurl.openStream(),"utf-8");
  BufferedReader br=new BufferedReader(in);
  String temp;
  //int count=0;
  StringBuffer buffer=new StringBuffer();
  while((temp=br.readLine())!=null){
	      buffer.append(temp);
	      //System.out.println(temp);
	      //System.out.println(count);
	      //count++;
  }
  result=buffer.toString();//转为一个String
  System.out.println(result);
  return result;
 }
 
 
 
 
 public String getJinShanValue(String str) throws Exception{
  String result = null;

  // 发送GET请求翻译
  result = sendGet(str);
  int re1=result.indexOf("\"pos\":\"");
  result=result.substring(re1);
  re1=0;
  int re2=result.indexOf("\",\"");
  int re3=result.indexOf("\"def\":\"");
  int re4=result.indexOf("\"}");
  //System.out.println(re1+" "+re2+" "+re3+" "+re4);
  while(re3!=-1){
	  System.out.println("ok");
	  String in1=result.substring(re1+7,re2);
	  System.out.println("ok2");
	  String in2=result.substring(re3+7,re4);
	  System.out.println(in1);
	  System.out.println(in2);
	  result=result.substring(re4+4);
	  re1=result.indexOf("\"pos\":\"");
	  result=result.substring(re1+1);
	  re1=0;
	  re2=result.indexOf("\",\"");
	  re3=result.indexOf("\"def\":\"");
	  re4=result.indexOf("\"}");	  
	 // System.out.println(re1+" "+re2+" "+re3+" "+re4);
	  
  } 
  
  re1=result.indexOf("\"eng\":\"");
  result=result.substring(re1);  
  re1=0;
  re2=result.indexOf("\",");
  re3=result.indexOf("\"chn\":\"");
  re4=result.indexOf("\",\"mp3");
  //System.out.println(re1+" "+re2+" "+re3+" "+re4);
  while(re3!=-1){
	  String in1=result.substring(re1+7,re2);
	  String in2=result.substring(re3+7,re4);
	  System.out.println(in1);
	  System.out.println(in2);
	  result=result.substring(re4+3);
	  re1=result.indexOf("\"eng\":\"");
	  result=result.substring(re1+1);
	  re1=0;
	  re2=result.indexOf("\",");
	  re3=result.indexOf("\"chn\":\"");
	  re4=result.indexOf("\",\"mp3");
	  //System.out.println(re1+" "+re2+" "+re3+" "+re4);
  }
  
  
  return result;
  
 }

 public static void main(String[] args) throws Exception{

  String str = "java";

  Bing_def test = new Bing_def();
  
  String temp = test.getJinShanValue(str);
 // System.out.println(temp);
 }
}


