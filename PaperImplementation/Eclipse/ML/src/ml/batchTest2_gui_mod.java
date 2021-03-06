package ml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class batchTest2_gui_mod
{
	public static void main(String args[]) throws IOException
	{
		int N=5,C_h=10,C_s=6,C_v=6;
		/*for 250 reized data*/ // int cellDimension=250;
		/*for 32*32 data*/ int cellDimension=32;
		int count=1;
		
		for(int n=5;n<=10;n=n+1)
		{
			for(int h=5;h<=10;h=h+1)
			{
				for(int s=5;s<=10;s=s+1)
				{
					for(int v=5;v<=10;v=v+1)
					//int h=10,s=10,v=10;
					{
						predictM(count,n,h,s,v,cellDimension, null, null);
						count++;
					}
				}                                                                                                     
			}
			
		}
		
		//predictM(count,10,8,6,6);
		
	
                                                               

	}
	
	public static void predictM(int count,int N,int C_h,int C_s,int C_v,int cellDimension, String trainFolder, String testFolder) throws IOException
	{
		Date start,end;
		String ip,executionTime;
		long executionTimeMS;
		//StringBuffer logData = new StringBuffer();

		
		System.out.println("Test Number : "+count);
		System.out.println("N="+N+",C_h="+C_h+",C_s="+C_s+",C_v="+C_v);
		//logData.append("Test Number : "+count+"\n"+"N="+N+",C_h="+C_h+",C_s="+C_s+",C_v="+C_v+"\n");
		
		//extract train features
		//ip = new String("/home/shinchan/graphAccuracy/200TrainResized");
		
		start =new Date();	    
	    ML.batchColorFeatureBuilder(trainFolder, N, C_h, C_s, C_v,new File("batchTest.train"),new File("batchTest.list"), cellDimension); 
	    end = new Date();		
		executionTimeMS = end.getTime()-start.getTime(); //in milliseconds
		executionTime = Float.toString((float)executionTimeMS/(60*1000))+" minutes";
		System.out.print("Time taken for feature extraction from training images: "+executionTime+"\n");
		//logData.append("Time taken for feature extraction from training images: "+executionTime+"\n");
		
		//extract test features
		//ip = new String("/home/shinchan/graphAccuracy/ga3");
		start =new Date();
	    ML.batchColorFeatureBuilder(testFolder, N, C_h, C_s, C_v,new File("batchTest.test"),new File("batchTest.list"), cellDimension); 
	    end = new Date();
		executionTimeMS = end.getTime()-start.getTime(); //in milliseconds
		executionTime = Float.toString((float)executionTimeMS/(60*1000))+" minutes";
		System.out.print("Time taken for feature extraction from test images: "+executionTime+"\n");
		//logData.append("Time taken for feature extraction from test images: "+executionTime+"\n");
		
		

	    //file declarations
	    File trainFeatures = new File("batchTest.train");
	    File testFeatures = new File("batchTest.test");
	    File modelFile = new File("batchTest.model");
	    File logFile = new File("batchTest.log");
		String resultsName = "batchTest.result";		
    	FileWriter op = new FileWriter(logFile.toString());


		
		//generate model
		start =new Date();
		String temp[]={trainFeatures.toString(),modelFile.toString()};
		svm_train.main(temp);
	    end = new Date();
		executionTimeMS = end.getTime()-start.getTime(); //in milliseconds
		executionTime = Float.toString((float)executionTimeMS/(60*1000))+" minutes";
		System.out.print("Time taken to generate model: "+executionTime+"\n");
		//logData.append("Time taken to generate model: "+executionTime+"\n");

		//prediction
		start =new Date();
		String argv[]={testFeatures.toString(),modelFile.toString(),resultsName};
		svm_predict.main(argv);
		end = new Date();
		executionTimeMS = end.getTime()-start.getTime(); //in milliseconds
		executionTime = Float.toString((float)executionTimeMS/(60*1000))+" minutes";
		System.out.print("Time taken for prediction : "+executionTime+"\n----x----\n");
		//logData.append("Time taken for prediction : "+executionTime+"\n----x----\n");
		
		
	}
}
