package tp4.arvoreAvl.tests;

import org.junit.Test;

import tp4.arvoreAvl.questao3;

import java.io.*;

public class MainTest {

    @Test
    public void testeOrdenaJogadores() throws IOException {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream(
                "1\n2\n3\n8\n9\nFIM\nCliff Barker\nNelson Bobb\n \nFIM\n".getBytes()
        );
        System.setIn(in);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        questao3.main(new String[0]);

        assert baos.toString().equals("Ed Bartels Cliff Barker SIM\nEd Bartels Leo Barnhorst NAO\nEd Bartels Cliff Barker NAO\n");

        System.setOut(old);
        System.setIn(sysInBackup);
    }
}

