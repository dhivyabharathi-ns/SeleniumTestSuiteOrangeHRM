package utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class CheckerProg {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 String filePath = "C:\\Users\\dhivy\\eclipse-workspace\\OrangeHRMTest\\src\\test\\resources\\testdata\\profilepic.png";
	  	 
	  	 System.out.println(filePath);
	  	try {
            Robot robot = new Robot();

            // Simulate pressing the 'A' key
            robot.keyPress(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_A);
            robot.delay(100); // Wait for 100 milliseconds

             //Simulate pressing the 'ENTER' key
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.delay(100);

            System.out.println("Simulated 'A' key press and 'ENTER' key press.");

        } catch (AWTException e) {
            System.err.println("Error creating Robot instance: " + e.getMessage());
        }
	  		   
		
	}

}

