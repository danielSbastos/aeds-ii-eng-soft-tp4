package tp4.tabelaHashEnderecamentoSeparado.tests;

import tp4.tabelaHashEnderecamentoSeparado.Main;

import java.io.*;

import org.junit.Test;

public class MainTest {

  @Test
  public void testeEncontraENaoEncontraJogadorEMostraPosicao() throws IOException {
      InputStream sysInBackup = System.in;
      ByteArrayInputStream in = new ByteArrayInputStream(
              "557\n397\n2083\nFIM\nAdrian Smith\nAl Ferrari\nAlec Kessler\nFIM\n".getBytes()
      );
      System.setIn(in);

      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      PrintStream ps = new PrintStream(baos);
      PrintStream old = System.out;
      System.setOut(ps);

      Main.main(new String[0]);

      assert baos.toString().equals("0 SIM\nNAO\n26 SIM\n");

      System.setOut(old);
      System.setIn(sysInBackup);
  }
  
}
