package universityProgram;

class MultithreadingDemo extends Thread 
{ 
    public void run() 
    { 
        try
        { 
        	new ConvertPostalCode("L6C2L8");
        	
            System.out.println ("Thread " + 
                  Thread.currentThread().getId() + 
                  " is running"); 
  
        } 
        catch (Exception e) 
        { 
            // Throwing an exception 
            System.out.println ("Exception is caught"); 
        } 
    } 
} 

public class TESTING {
	
	
	
	public static void main(String[] args) {
		
		//for(int x=0; x<5; x++) {
			
			//
			
			//new ConvertPostalCode("M5R1C4");
			
			//new ConvertPostalCode("L6E1V4");
			
			//new ConvertPostalCode("M5R1H3");
			
			//new ConvertPostalCode("M5R1M7");
			
			//new ConvertPostalCode("M5R1R1");
			
			//new ConvertPostalCode("M5R1T8");
			
			//new ConvertPostalCode("M4W2L8");
			
		//}
		
		for(int x=0; x<20; x++) {
		MultithreadingDemo object = new MultithreadingDemo(); 
        object.start(); 
		}
		
		System.out.print("Done");
		
	}

}
