package testpackge;
import java.io.*;
import java.util.*;

public class wordcount {
	 private String sFilename;    // 文件名
	 private String stopwords;//停词表文件
	 private String outputfile;//输出文件名
	 private String[] sParameter; // 参数数组  
	 private int iCharcount;      // 字符数
	 private int iWordcount;      // 单词数
	 private int iLinecount;      // 总行数
	 private int iNullLinecount;  // 空行数
	 private int iCodeLinecount;  // 代码行数
	 private int iNoteLinecount;  // 注释行数
	 private int openStopList=0;//启用停用词表
	 private String[] StopList;//停词表
	 String outContent="";//输出到结果文件的数据串
	 
	  wordcount(String[] sParameter, String sFilename,String stopwords,String outputfile)//构造函数
	 {
		 this.sParameter=sParameter;
		 this.sFilename=sFilename;
		 this.stopwords=stopwords;
		 this.outputfile=outputfile;

	 }
	
	 public void Operator()//参数判断函数，判断传入了那些执行功能相关的参数
	 {int flagOfS=0;//判断是否需要递归处理文件
	  int flag=2;//判断是否需要变更结果文件路径
		 
    if(flagOfS==0)
	     {
    	
    	for (String s :this.sParameter)
	      {
	         //  基本功能
	    	 
	          if ((s.equals("-c") || s.equals("-w") || s.equals("-l")))
	         {
	        	  BaseCount();
	        	 
          
	         }
	          
	          
	          
	         else
	         {
	             System.out.println("参数 "+s+"不存在");
	             break;
	         }
	     }
    	 Display();
	     outputTxt(flag);
	     
	 }
	 }  
	 
	 
	// 基本功能：字符数 单词数 行数
	 private void BaseCount()
	 {
		 
	     try
	     {
	    	 BufferedReader st1 = new BufferedReader(new FileReader(this.sFilename));
	         int nChar;
	         int charcount = 0;
	         int wordcount = 0;
	         int stopWordCount=0;
	         int linecount = 0;
	         
	        
	         while ((nChar = st1.read()) != -1)
	         {
	             charcount++;     // 统计字符数

	             
	             if (nChar == '\n')
	             {
	                 linecount++; // 统计行数
	             }
	         }
	         
	         
	           st1.close();
	         
	        	 BufferedReader st2 = new BufferedReader(new FileReader(this.sFilename));//统计单词数
	    		 String read1 = null;
	    		 int lengthofSplit=0;
	    		   while (( read1 =st2.readLine()) != null)
	    		   {
	    			  read1=read1.trim();//去除空格
	    		     String[] arrSplit =read1.split(" |,");//以空格或者逗号分隔
	    		     lengthofSplit=arrSplit.length;
	    		     for(String s:arrSplit)
	    		     {
	    		    	 if(s.length()==0)
	    		    		 lengthofSplit--;//去除空格
	    		     }
	    		     wordcount+=lengthofSplit;
	    		     if(openStopList==1)
	    		     {
	    		      for(String s:arrSplit)
	    		      {
	    		    	 for(String stop:StopList)
	    		    	 if(s.equals(stop))
	    		    		 stopWordCount++;
	    		    		 
	    		      }
	    		     }
	    		     
	    		   }
	    		   st2.close();
	         
	         
	         iCharcount = charcount;
	         iWordcount = wordcount -stopWordCount;
	         iLinecount = linecount + 1;
	         
	     }
	     catch (IOException ex)
	     {
	    	 ex.printStackTrace();
	         return;
	     }
	 }
	 

	
	 private void Display()//输出结果数据串的设置
	 {int flagOfc=0;
	  int flagOfw=0;
	  int flagOfl=0;
	  int flagOfa=0;
	     for (String s :sParameter)
	     {

	         if(s.equals("-c"))
       	  {
	        	 flagOfc=1;

       	  }
       	  if(s.equals("-w"))
       	  {
       		flagOfw=1;
       		  
       	  }
       	  if(s.equals("-l"))
       	  {flagOfl=1;
       		
       	  }
       	
       	
	         
	     }
	     if(flagOfc==1)
	     {
	    	 outContent = outContent + this.sFilename + ",字符数：" +iCharcount + "\r\n";
	     }
	     if(flagOfw==1)
	     {
	    	 outContent = outContent + this.sFilename + ",单词数：" +iWordcount + "\r\n";
	     }
	     if(flagOfl==1)
	     {
	    	 outContent = outContent + this.sFilename + ",行数：" +iLinecount + "\r\n";
	     }
	    
	     
	     
	 }
	 
	 
	// 扩展功能：空行数 代码行数 注释行数
	
	 
	
	 
	
	 
	 private void outputTxt(int flag)//设置输出文件信息
	 {
		  if(flag==2)
			{
				try{
					String outpathname = "result.txt";
					File outfile = new File(outpathname);
		        	FileWriter fw = new FileWriter(outfile);  
		        	BufferedWriter outStream = new BufferedWriter(fw);   
		        	outStream.write(outContent);
		        	outStream.flush();  
		        	outStream.close(); 
					}
					catch(Exception e)
					{
						 e.printStackTrace();
					}
			}
	 }
	 
	
	 
	 
	 
	 
	 
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  
			  int countOfFunction=0;
		      int countOfFile=0;

		   for(String s:args)
		   {
			   if(s.length()==2)
				   countOfFunction++;//统计功能参数个数
		   }
		   
		   String[] functions=new String[countOfFunction];//功能参数数组
		   String sFilename="";//输入文件
		   String stoplistFile="";//停用词表文件
		   String outputFile="";//输出文件
		  // String[] files=new String[countOfFile];
		   int x=0,y=0,temp,flagoOfFile=0;
		  
				   for (String s:args)
				     {
				         
				         if( s.length()==2)
				         { functions[x]=s;
				              x++;
				         
				         
				        	 temp=(y+1)<args.length?(y+1):args.length;
				        	
				        	 if(s.equals("-o"))
				        	 {
				        		 outputFile=args[temp];
				        		
				        	 }
				        	 else if(s.equals("-e"))
				        	 {
				        		 stoplistFile=args[temp];
				        		 
				        	 }
				         }
				        	 else
				        	 {
				        		 if(flagoOfFile==0)
				        		 {
				        		 sFilename=s;
				        		 flagoOfFile=1;
				        		 }
				        	 }
				        	 
				        		 
				         
				        	y++; 
					  // System.out.println(s);
				     }
				  /* for(String s:functions)
				   {
					   System.out.println(s);
					   
				   }*/
				  System.out.println("sFilename "+sFilename);
				   System.out.println("stoplistFile "+stoplistFile);
				   System.out.println("outputFile "+outputFile);
	  
			       wordcount test=new wordcount(functions,sFilename,stoplistFile,outputFile);      
			       test.Operator(); 
			       
		  
		  
		 
	}


	

}
