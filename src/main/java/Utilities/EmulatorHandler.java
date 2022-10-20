package Utilities;

import java.io.File;
import java.io.IOException;

public class EmulatorHandler 
{
	private static String BATCH_PATH =  "C:\\Users\\Melbourne\\Documents\\Automation Batch Files";
	private static String APPIUM_BATCH = "Execute_Emulator.bat";
	
	public static void startEmulator() throws IOException 
	{
		ProcessBuilder process = new ProcessBuilder();
		process.command("cmd.exe", "/c", new File(BATCH_PATH, APPIUM_BATCH).getPath());
		process.start();
		System.out.println("Emulator started running... ");
	}
	
	public static void stopEmulator() throws IOException 
	{
		ProcessBuilder process = new ProcessBuilder();
		process.command("cmd.exe", "/c", "adb -s emulator-5554 emu kill");
		process.start();
		System.out.println("Emulator stopped.");
	}
	
//	public static boolean isEmulatorRunning() throws IOException 
//	{
//		boolean isRunning = false;
//		ProcessBuilder process = new ProcessBuilder();
//		process.command("cmd.exe", "/c", "adb devices");
//		Process startProcess = process.start();
//		
//		String result = startProcess.getInputStream().readAllBytes().toString();
//		if(result.contains("emulator-5554"))
//			isRunning = true;
//		
//		return isRunning;
//	}
}
