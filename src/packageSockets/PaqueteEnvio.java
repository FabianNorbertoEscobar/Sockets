package packageSockets;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PaqueteEnvio implements Serializable {

	private String nick;
	private String ip;
	private String msg;

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

	
}
