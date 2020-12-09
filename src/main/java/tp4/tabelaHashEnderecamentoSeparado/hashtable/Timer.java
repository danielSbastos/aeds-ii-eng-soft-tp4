package tp4.tabelaHashEnderecamentoSeparado.hashtable;

import java.io.*;

public class Timer {

	long initTime;
	String fileName;

	public Timer(String name) {
		this.initTime = System.currentTimeMillis();
		this.fileName = "713557_" + name + ".txt";
	}

	public void recordTime(int comparisonCount) {
		long elapsedTime = System.currentTimeMillis() - this.initTime;
		try {
			FileWriter  logFile = new FileWriter(this.fileName);
			logFile.write("713557\t" + elapsedTime + "\t" + comparisonCount);
			logFile.close();
		}
		catch (IOException e) {
			System.out.println("Erro ao gerar o log");
		}
	}

}
