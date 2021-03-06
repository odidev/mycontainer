package com.googlecode.mycontainer.maven.plugin;

import java.util.ArrayList;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import com.googlecode.mycontainer.util.ReflectionUtil;
import com.googlecode.mycontainer.util.Util;
import com.googlecode.mycontainer.util.tunnel.Tunnel;
import com.googlecode.mycontainer.util.tunnel.TunnelHandler;
import com.googlecode.mycontainer.util.tunnel.Tunnels;

/**
 * @goal tunnels
 * @aggregator
 * @requiresProject false
 */
public class MycontainerTunnelsMojo extends AbstractMojo {

	/**
	 * @parameter expression="${mycontainer.tunnels.waitfor}"
	 */
	private boolean tunnelsWaitfor = true;

	/**
	 * @parameter expression="${mycontainer.tunnels.list}"
	 */
	private String tunnelsList;

	/**
	 * @parameter expression="${mycontainer.tunnels.handler}"
	 */
	private String tunnelsHandler = "Redirect";

	@SuppressWarnings("resource")
	public void execute() throws MojoExecutionException, MojoFailureException {
		tunnelsList = Util.str(tunnelsList);
		if (tunnelsList == null) {
			throw new MojoExecutionException("tunnels is required, try: -Dmycontainer.tunnels.list=local-host:local-port:remote-host:remote-port,local-host:local-port:remote-host:remote-port,...");
		}
		String[] args = tunnelsList.split(",");

		TunnelHandler handler = (TunnelHandler) ReflectionUtil.newInstance(Tunnels.class.getPackage().getName() + "." + this.tunnelsHandler + "TunnelHandler");
		List<Tunnel> list = new ArrayList<Tunnel>();
		for (int i = 0; i < args.length; i++) {
			String str = args[i];
			Tunnel tunnel = Tunnel.parse(str);
			list.add(tunnel);
		}

		Tunnels tunnels = new Tunnels();
		try {
			tunnels.setHandler(handler);
			tunnels.bindAll(list);
		} catch (RuntimeException e) {
			Util.close(tunnels);
			throw new RuntimeException(e);
		}
		tunnels.start();
		if (tunnelsWaitfor) {
			tunnels.join();
		}

	}
}
