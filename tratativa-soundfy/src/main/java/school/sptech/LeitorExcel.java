package school.sptech;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class LeitorExcel {

    private static final String ABA_ARTISTAS = "Top Artistas";
    private static final String ABA_GENEROS = "Top Gêneros";
    private static final int TOTAL_POSICOES = 10;

    private final String caminho;
    private final DataFormatter formatter = new DataFormatter();

    public LeitorExcel(String caminho) {
        this.caminho = caminho;
    }

    public List<Consumo> lerTopArtistas() throws IOException {
        return lerAba(ABA_ARTISTAS);
    }

    public List<Consumo> lerTopGeneros() throws IOException {
        return lerAba(ABA_GENEROS);
    }

    private List<Consumo> lerAba(String nomeAba) throws IOException {
        List<Consumo> consumos = new ArrayList<>();

        try (InputStream arquivo = new FileInputStream(caminho);
             Workbook workbook = new XSSFWorkbook(arquivo)) {

            Sheet sheet = workbook.getSheet(nomeAba);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }

                String regiao = lerTexto(row.getCell(0));
                if (regiao.isEmpty()) {
                    continue;
                }

                for (int posicao = 1; posicao <= TOTAL_POSICOES; posicao++) {
                    String nome = lerTexto(row.getCell(posicao * 2 - 1));
                    Cell celulaPontos = row.getCell(posicao * 2);

                    if (nome.isEmpty() || celulaPontos == null) {
                        continue;
                    }

                    // Pontos podem vir como decimal, arredonda para inteiro
                    int qtdStreams = Math.toIntExact(Math.round(celulaPontos.getNumericCellValue()));

                    consumos.add(new Consumo(regiao, nome, posicao, qtdStreams));
                }
            }
        }

        return consumos;
    }

    private String lerTexto(Cell cell) {
        if (cell == null) {
            return "";
        }
        return formatter.formatCellValue(cell).trim();
    }
}
