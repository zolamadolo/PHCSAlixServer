package phcs.alix.server;

public class ServerMain {
	private static int DEFAULT_PORT = 5000;
	public static void main(String[]args)
	{
		int port = DEFAULT_PORT;
		if(args.length>0)
		{
			port = Integer.parseInt(args[0]);
		}
		Server server = new Server(port);
		server.start();
	}
}
