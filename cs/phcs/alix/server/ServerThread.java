package phcs.alix.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread implements Runnable{
	private final int _port;
	public ServerSocket _socketServer;
	public ServerThread(int port)
	{
		this._port = port;
	}
	public void run()
	{
		try
		{
			while(!isInterrupted())
			{
				Socket socket = _socketServer.accept();
				System.out.println("Android Connection Information");
				System.out.println("Just connected on "+ socket.getPort()+" Host: "+socket.getInetAddress().getAddress());
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				System.out.println("Data From Client "+in.readLine());
			}
		}catch(IOException ex)
		{
			System.out.println("Unexpected problem during Socket listening");
			System.out.println(ex.getMessage());
		}
	}
	public void start()
	{
		try
		{
			_socketServer = new ServerSocket(_port);
			super.start();
		}catch(Exception ex)
		{
			System.out.println("Unexpected problem during Socket listening");
			System.out.println(ex.getMessage());
		}
	}
	public void terminateServer()
	{
		try
		{
			if(_socketServer!=null)
			{
				_socketServer.close();
				System.out.println("Android Connection Closed...");
			}
			this.interrupt();
		}catch(Exception ex)
		{
			//ignore
		}
	}
}
