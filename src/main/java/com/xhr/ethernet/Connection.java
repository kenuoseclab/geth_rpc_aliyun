package com.xhr.ethernet;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;

public class Connection implements Runnable {

	private String ip;
	private FileWriter fileWritter;

	public Connection(String ip, FileWriter fileWriter) {
		this.ip = ip;
		this.fileWritter = fileWriter;
	}

	public void run() {
		try {
			fileWritter = new FileWriter(App.rfile.getName(), true);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Map<String, String> headers = new HashMap<String, String>(1);
			headers.put("Content-Type", "application/json");
			JsonRpcHttpClient client = new JsonRpcHttpClient(new URL(ip), headers);
			Object json = client.invoke("eth_accounts", new Object[] {}, Object.class);
			System.out.println("成功访问到了一台IP地址为:"+ip+"数据结果"+json);
			fileWritter.write(ip+"\r\n");
			fileWritter.flush();
		} catch (Throwable e) {
			System.out.println("无法连接到端口:" + ip);
		}
	}
}
