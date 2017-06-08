package packageSockets;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class PaqueteEnvio implements Serializable {

	private String nick;
	private String ip;
	private String msg;
	
	private ArrayList<String> ips;

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ArrayList<String> getIps() {
		return ips;
	}

	public void setIps(ArrayList<String> ips) {
		this.ips = ips;
	}

	
}
