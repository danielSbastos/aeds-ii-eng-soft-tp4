package tp4.treeSort.testes;

import tp4.treeSort.questao4;

import java.io.*;

import org.junit.Test;

public class MainTest {

    @Test
    public void testeOrdenaJogadores() throws IOException {
        InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream();
        System.setIn(in);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        questao4.main(new String[0]);

        assert baos.toString().contains("[1723 ## A.C. Green ## 203 ## 106 ## 1960 ## nao informado ## nao informado ## nao informado]");
        assert baos.toString().contains("[2657 ## A.J. Bramlett ## 196 ## 88 ## 1973 ## nao informado ## nao informado ## nao informado]");
        assert baos.toString().contains("[2547 ## Zydrunas Ilgauskas ## 221 ## 107 ## 1975 ## nao informado ## Kaunas ## Lithuania]");

        System.setOut(old);
        System.setIn(sysInBackup);
    }
}
