package phcs.alix.server;

public class Server {
	private final int _port;
	private boolean _started = false;
	private ServerThread _serverThread;
	public Server(int port)
	{
		this._port = port;
	}
	public void start() {
		if(!_started)
		{
			_serverThread = new ServerThread(_port);
			_serverThread.start();
			_started = true;
			System.out.println("Server Started on port: "+_port);
		}else 
		{
			System.out.println("Server already started ");
		}
	}
	public void stop(){
		if(_started)
		{
			_serverThread.terminateServer();
			try
			{
				Thread.sleep(500);
			}catch(InterruptedException ex)
			{
				System.out.println("Thread Interrupted");
			}
		}
	}
}
