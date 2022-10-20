package Utilities;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;

public class AppiumServerHandler 
{
	private static String BATCH_PATH =  "C:\\Users\\Melbourne\\Documents\\Automation Batch Files";
	private static String APPIUM_BATCH = "Execute_AppiumServer.bat";
	private static int PORT = 4723;
	
	public static void startServer() throws IOException 
	{
		if(!isServerRunning()) {
			ProcessBuilder process = new ProcessBuilder();
			process.command("cmd.exe", "/c cmd.exe /k", new File(BATCH_PATH, APPIUM_BATCH).getPath());
			process.start();
			System.out.println("Appium server started... ");
		}
	}
	
	public static void stopServer() throws IOException 
	{
		ProcessBuilder process = new ProcessBuilder();
		process.command("taskkill", "/F", "/IM", "node.exe");
		process.start();
		System.out.println("Appium server stopped.");
	}
	
	public static boolean isServerRunning() 
	{
		boolean isRunning = false;
		ServerSocket serverSocket;
		try 
		{
			serverSocket = new ServerSocket(PORT);
			serverSocket.close();
		}
		catch(IOException e) 
		{
			isRunning = true;
			System.out.println("Appium server is already running... ");
		}
		finally 
		{
			serverSocket = null;
		}
		return isRunning;
	}
}
