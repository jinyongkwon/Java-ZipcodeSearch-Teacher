import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.Charset;

public class TextToCsvFile implements TexttoCsvFileAble {
    private static final String WORKSPACE = "C:\\green_workspace\\zipcode_lab\\zipcode-search\\";
    private static final String RESOURCE = WORKSPACE + "resource\\";

    // path = 내프로젝트 경로와 내부 resouce 폴더가 내장되어 있습니다.
    // encType = UTF-8, EUC-KR등을 입력해주면 됩니다.
    protected String readFIle(String path, String encType) {
        try {
            FileReader reader = new FileReader(RESOURCE + path, Charset.forName(encType));
            BufferedReader br = new BufferedReader(reader);

            String str;
            StringBuilder sb = new StringBuilder();
            while ((str = br.readLine()) != null) {
                sb.append(str + "\n");
            }

            reader.close();
            br.close();
            return sb.toString();
        } catch (Exception e) {
            System.out.println("파일 경로가 잘못되었습니다.");
        }
        return null;
    }

    protected String changeCsv(String data, String gubun) {
        return data.replaceAll("\\" + gubun, ",");
    }

    protected int writeFile(String path, String encType, String csvStr) {
        try {
            FileWriter writer = new FileWriter(new File(RESOURCE + path), Charset.forName("UTF-8"));
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(csvStr);
            bw.flush();
            bw.close();
            return 1;
        } catch (Exception e) {
            System.out.println("파일 쓰기에 실패하였습니다. 이유:" + e.getMessage());
        }
        return -1;
    }

    // 부모의 메서드가 오버라이드 되었음.
    public int process(String readFilePath, String writeFilePath) {
        String data = readFIle(readFilePath, "UTF-8");
        String data2 = changeCsv(data, "^");
        return writeFile(writeFilePath, "UTF-8", data2);
    }
}
