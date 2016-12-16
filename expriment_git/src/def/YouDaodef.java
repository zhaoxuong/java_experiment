package def;
import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Map;


public class YouDaodef {

 
 private String url = "http://fanyi.youdao.com/openapi.do";
 private String [] getresult=new String[100];
 private int count=0;//计数getresult的个数多少，调用getYOUdaovalue，getreult返回要的东西

 
 private String keyfrom = "zhaoxiong-122";
 private String key = "304812833";

 
 private String doctype = "xml";
 public YouDaodef(String str) throws Exception{
	 // TODO Auto-generated constructor stub
	 getYouDaoValue(str);
}


 
 private String sendGet(String str) throws Exception{

  // 编码成UTF-8
  try {
   str = URLEncoder.encode(str, "utf-8");
  } catch (UnsupportedEncodingException e) {
   e.printStackTrace();
  }

  StringBuffer buf = new StringBuffer();
  buf.append("keyfrom=");
  buf.append(keyfrom);
  buf.append("&key=");
  buf.append(key);
  buf.append("&type=data&doctype=");
  buf.append(doctype);
  buf.append("&version=1.1&q=");
  buf.append(str);

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
  result=buffer.toString();
  //System.out.println(result);
  return result;
 }
 
 
 
 
 public String getYouDaoValue(String str) throws Exception{
  String result = null;
  
  // 发送GET请求翻译
  result = sendGet(str);
  int num1,num2;

  // 处理XML中的值
  int re1 = result.indexOf("<errorCode>");
  int re2 = result.indexOf("</errorCode>");
  //System.out.println(re1);
  String in = result.substring(re1 + 11, re2);
 // System.out.println("===========翻译是否成功============" + in);

  if (in.equals("0")) {
   //System.out.println("翻译正常");

   re1 = result.indexOf("<paragraph><![CDATA[");
   re2 = result.indexOf("]]></paragraph>");
   in = result.substring(re1 + 20, re2);
   //System.out.println(re1);
   System.out.println("==========有道翻译================" + ":");
   System.out.println(in);
   getresult[count]="==========有道翻译================";
   count++;
   getresult[count]=in;
   count++;
   result=result.substring(re2+20);
   //System.out.println(result);
   
   re1 = result.indexOf("<us-phonetic><![CDATA[");
   re2 = result.indexOf("]]></us-phonetic>");
   num1="<us-phonetic><![CDATA[".length();
   num2="]]></us-phonetic>".length();
   in = result.substring(re1 + num1, re2);
   //System.out.println(re1);
   System.out.println("===========美式音标============" + ":");
   System.out.println(in);
   getresult[count]="===========美式音标============";
   count++;
   getresult[count]=in;
   count++;
   result=result.substring(re2+num2);
   
   re1 = result.indexOf("<uk-phonetic><![CDATA[");
   re2 = result.indexOf("]]></uk-phonetic> ");
   num1="<uk-phonetic><![CDATA[".length();
   num2="]]></uk-phonetic> ".length();
   in = result.substring(re1 + num1, re2);
   //System.out.println(re1);
   System.out.println("===========英式音标============" + ":");
   System.out.println(in);
   getresult[count]="===========英式音标============";
   count++;
   getresult[count]=in;
   count++;
   result=result.substring(re2+num2);

   re1 = result.indexOf("<ex><![CDATA[");
   re2 = result.indexOf("]]></ex>");
   int re3=result.indexOf("<web>");
   in = result.substring(re1 + 13, re2);
  //System.out.println(re1+" "+re3);
   System.out.println("==========基本释义================" + ":");
   getresult[count]="==========基本释义=============";
   count++;
   //System.out.println(in);
   //result=result.substring(re2+8);
   while(re1<re3){
  
   in = result.substring(re1 + 13, re2);
   System.out.println(in);
   getresult[count]=in;
   count++;
   result=result.substring(re2+8);
   re1 = result.indexOf("<ex><![CDATA["); 
   re2 = result.indexOf("]]></ex>");
   re3=result.indexOf("<web>");
   }
   
   System.out.println("===========网络释义============" + ":");
   getresult[count]="===========网络释义============";
   count++;
   while(result.length()>100){
	   re1=result.indexOf("<![CDATA[");
	   re2=result.indexOf("]]>");
	   in = result.substring(re1 + 9, re2);
	   System.out.println(in);
	   getresult[count]=in;
	   count++;
	   result=result.substring(re2+8);
	   
   }
   
   
   

  } else if (in.equals("20")) {
   System.out.println("要翻译的文本过长");
   return "要翻译的文本过长";
  } else if (in.equals("30")) {
   System.out.println("无法进行有效的翻译");
   return "无法进行有效的翻译";
  } else if (in.equals("40")) {
   System.out.println("不支持的语言类型");
   return "不支持的语言类型";
  } else if (in.equals("50")) {
   System.out.println("无效的key");
   return "无效的key";
  }

  return result;
 }

 
 public String [] getGetresult() {
		return getresult;
	}
 public static void main(String[] args) throws Exception{

  String str = "java";

  YouDaodef test = new YouDaodef(str);
  
  //String temp = test.getYouDaoValue(str);
 // System.out.println(temp);
 }
}

