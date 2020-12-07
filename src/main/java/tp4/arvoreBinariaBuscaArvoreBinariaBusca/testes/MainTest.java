package tp4.arvoreBinariaBuscaArvoreBinariaBusca.testes;

import org.junit.Test;
import tp4.arvoreBinariaBuscaArvoreBinariaBusca.Main;

import java.io.*;

public class MainTest {

    @Test
    public void testeEncontraENaoEncontraJogadorEMostraCaminho() throws IOException {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream(
                "1\n2\n3\n8\n9\n23\nFIM\nCliff Barker\nNelson Bobb\n \nFIM\n".getBytes()
        );
        System.setIn(in);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        Main.main(new String[0]);

        assert baos.toString().equals("1 Cliff Barker SIM\n1 6 9 13 Don Carlson NAO\n1 \n");

        System.setOut(old);
        System.setIn(sysInBackup);
    }
}
