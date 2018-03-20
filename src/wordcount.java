package testpackge;
import java.io.*;
import java.util.*;

public class wordcount {
	 private String sFilename;    // �ļ���
	 private String stopwords;//ͣ�ʱ��ļ�
	 private String outputfile;//����ļ���
	 private String[] sParameter; // ��������  
	 private int iCharcount;      // �ַ���
	 private int iWordcount;      // ������
	 private int iLinecount;      // ������
	 private int iNullLinecount;  // ������
	 private int iCodeLinecount;  // ��������
	 private int iNoteLinecount;  // ע������
	 private int openStopList=0;//����ͣ�ôʱ�
	 private String[] StopList;//ͣ�ʱ�
	 String outContent="";//���������ļ������ݴ�
	 
	  wordcount(String[] sParameter, String sFilename,String stopwords,String outputfile)//���캯��
	 {
		 this.sParameter=sParameter;
		 this.sFilename=sFilename;
		 this.stopwords=stopwords;
		 this.outputfile=outputfile;

	 }
	
	 public void Operator()//�����жϺ������жϴ�������Щִ�й�����صĲ���
	 {int flagOfS=0;//�ж��Ƿ���Ҫ�ݹ鴦���ļ�
	  int flag=2;//�ж��Ƿ���Ҫ�������ļ�·��
		 
    if(flagOfS==0)
	     {
    	
    	for (String s :this.sParameter)
	      {
	         //  ��������
	    	 
	          if ((s.equals("-c") || s.equals("-w") || s.equals("-l")))
	         {
	        	  BaseCount();
	        	 
          
	         }
	          
	          
	          
	         else
	         {
	             System.out.println("���� "+s+"������");
	             break;
	         }
	     }
    	 Display();
	     outputTxt(flag);
	     
	 }
	 }  
	 
	 
	// �������ܣ��ַ��� ������ ����
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
	             charcount++;     // ͳ���ַ���

	             
	             if (nChar == '\n')
	             {
	                 linecount++; // ͳ������
	             }
	         }
	         
	         
	           st1.close();
	         
	        	 BufferedReader st2 = new BufferedReader(new FileReader(this.sFilename));//ͳ�Ƶ�����
	    		 String read1 = null;
	    		 int lengthofSplit=0;
	    		   while (( read1 =st2.readLine()) != null)
	    		   {
	    			  read1=read1.trim();//ȥ���ո�
	    		     String[] arrSplit =read1.split(" |,");//�Կո���߶��ŷָ�
	    		     lengthofSplit=arrSplit.length;
	    		     for(String s:arrSplit)
	    		     {
	    		    	 if(s.length()==0)
	    		    		 lengthofSplit--;//ȥ���ո�
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
	 

	
	 private void Display()//���������ݴ�������
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
	    	 outContent = outContent + this.sFilename + ",�ַ�����" +iCharcount + "\r\n";
	     }
	     if(flagOfw==1)
	     {
	    	 outContent = outContent + this.sFilename + ",��������" +iWordcount + "\r\n";
	     }
	     if(flagOfl==1)
	     {
	    	 outContent = outContent + this.sFilename + ",������" +iLinecount + "\r\n";
	     }
	    
	     
	     
	 }
	 
	 
	// ��չ���ܣ������� �������� ע������
	
	 
	
	 
	
	 
	 private void outputTxt(int flag)//��������ļ���Ϣ
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
				   countOfFunction++;//ͳ�ƹ��ܲ�������
		   }
		   
		   String[] functions=new String[countOfFunction];//���ܲ�������
		   String sFilename="";//�����ļ�
		   String stoplistFile="";//ͣ�ôʱ��ļ�
		   String outputFile="";//����ļ�
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
