/**
 *  Copyright 2013 Zola Madolo (http://people.cs.uct.ac.za/~zmadolo/)
 *	This work is licenced under the Creative Commons Attribution 2.5 South Africa License. 
 *	To view a copy of this licence, visit http://creativecommons.org/licenses/by/2.5/za/ or 
 *	send a letter to Creative Commons, 171 Second Street, 
 *	Suite 300, San Francisco, California 94105, USA.
 *
 *  Visit http://www.rcips.uct.ac.za/ip/copyright/bla/
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/*******************************************************************************
 * Copyright (c) 2013 Zola Madolo (http://people.cs.uct.ac.za/~zmadolo/).
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
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
