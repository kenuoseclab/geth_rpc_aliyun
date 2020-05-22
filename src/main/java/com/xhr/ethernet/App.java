package com.xhr.ethernet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;

/**
 * Test
 *
 */
public class App {

	/**
	 * geth节点可调用的json-rpc接口地址和端口
	 */
	private static final String URL = "https://interface.lb2.tbudr.top/rpc/eth/main";
	public static File rfile = new File("result.txt");

	public static void main(String[] args) throws IOException, InterruptedException {
		File file = new File("ip.txt");
		// 使用true，即进行append file

		FileWriter fileWritter = new FileWriter(rfile.getName(), true);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));// 构造一个BufferedReader类来读取文件
		String s = null;
		int i = 0;
		while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
			i++;
			if (i % 500 == 0 && i != 0) {
				Thread.sleep(10000);
			}
			String ip = "http://" + s + ":" + 8545;
			new Thread(new Connection(ip, fileWritter)).start();
		}
		fileWritter.close();

	}

	/**
	 * 阿里云所有IP段
	 * 
	 * @throws IOException
	 */
	public static void createIp() throws IOException {

		File file = new File("ip.txt");
		if (!file.exists()) {
			file.createNewFile();
		}

		// 使用true，即进行append file

		FileWriter fileWritter = new FileWriter(file.getName(), true);

		String ip = "115";
		for (int b = 28; b <= 29; b++) {
			for (int i = 0; i <= 255; i++) {
				for (int j = 0; j <= 255; j++) {
					fileWritter.write(ip + "." + b + "." + i + "." + j + "\r\n");
				}
			}
		}
		fileWritter.close();
	}

}
