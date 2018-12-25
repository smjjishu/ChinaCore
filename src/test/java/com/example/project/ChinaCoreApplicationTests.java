package com.example.project;



import com.example.project.tool.DESUtilTool;
import com.example.project.tool.ZLibUtils;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;


@RunWith(value=SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ChinaCoreApplication.class)
public class ChinaCoreApplicationTests {

    @Test
    public void main() throws  Exception {

        String content = "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";
        byte[] inf = content.getBytes();
        byte[] aaa = Base64.encodeBase64(inf);
        // byte[] bbb= ZLibUtils.decompress(aaa);
        InputStream input = new ByteArrayInputStream(aaa);
        InflaterInputStream fs2 = new InflaterInputStream(input);
        byte[] ccc= ZLibUtils.decompressing(fs2);

        String info = new String(ccc);




        String info10 = DESUtilTool.encryptString("中华人民共和国");
        String info11 = DESUtilTool.decryptString(info10);
        String info20 = DESUtilTool.decryptString("LJeCmrrvuX5ib3kKuUfnWA==");
        String info21 = DESUtilTool.decryptString("5CPMvq3BAcTj3j0TOjZkgg==");


        int oo = 0;
    }


    public static void testwrite(String filename) throws IOException {
        FileOutputStream fs = new FileOutputStream(new File(filename), false);
        DeflaterOutputStream fs2 = new DeflaterOutputStream(fs, new Deflater(3));
        for (int i = 0; i < 50; i++)
            for (int j = 0; j < 40; j++)
                fs2.write((byte) (i + 0x30));
        fs2.close();
    }

    public static void testread(String filename) throws IOException {
        FileInputStream fs = new FileInputStream(new File(filename));
        InflaterInputStream fs2 = new InflaterInputStream(fs);
        int c, n = 0;
        while ((c = fs2.read()) >= 0) {
            System.out.print((char) c);
            if (n++ % 40 == 0)  System.out.println("");
        }
        fs2.close();
    }

}
