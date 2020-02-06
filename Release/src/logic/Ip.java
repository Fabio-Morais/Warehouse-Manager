package logic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Ip {
	/**
	 * Retorna o ip da m�quina
	 * @return String - ip da m�quina/ string vazia caso haja erro
	 */
	public String getIp() {
		InetAddress ip;
        try {
            ip = InetAddress.getLocalHost();
            return ip+"";
        } catch (UnknownHostException e) {
        	return "";
        }
	}
}
