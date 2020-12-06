package tp4.arvoreBinariaBusca.testes;

import tp4.arvoreBinariaBusca.Main;

import java.io.*;

import org.junit.Test;

public class MainTest {

    @Test
    public void testeEncontraENaoEncontraJogadorEMostraCaminho() throws IOException {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream(
                "1\n2\n3\n8\n9\nFIM\nCliff Barker\nNelson Bobb\n \nFIM\n".getBytes()
        );
        System.setIn(in);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        Main.main(new String[0]);

        assert baos.toString().equals("Cliff Barker SIM\nCliff Barker Leo Barnhorst Vince Boryla NAO\nCliff Barker NAO\n");

        System.setOut(old);
        System.setIn(sysInBackup);
    }
}