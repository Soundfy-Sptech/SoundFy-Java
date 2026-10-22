package school.sptech;

import java.io.IOException;
import java.util.List;

public class Main {

    private static final String ARQUIVO = "musica-brasil-por-regiao.xlsx";


    public static void main(String[] args) {
        System.out.println("Procurando em: " + new java.io.File(ARQUIVO).getAbsolutePath());
        Conexao conexao = new Conexao();

        try {
            DAO dao = new DAO(conexao.getJdbcTemplate());
            LeitorExcel leitor = new LeitorExcel(ARQUIVO);

            System.out.println("Lendo o arquivo " + ARQUIVO + "...");
            List<Consumo> topArtistas = leitor.lerTopArtistas();
            List<Consumo> topGeneros = leitor.lerTopGeneros();
            System.out.println("Registros lidos -> Artistas: " + topArtistas.size()
                    + " | Gêneros: " + topGeneros.size());

            int artistasInseridos = 0;
            for (Consumo consumo : topArtistas) {
                if (dao.inserirConsumoArtista(consumo)) {
                    artistasInseridos++;
                }
            }

            int generosInseridos = 0;
            for (Consumo consumo : topGeneros) {
                if (dao.inserirConsumoGenero(consumo)) {
                    generosInseridos++;
                }
            }

            System.out.println("ConsumoArtista -> inseridos: " + artistasInseridos
                    + " | ignorados (duplicados): " + (topArtistas.size() - artistasInseridos));
            System.out.println("ConsumoGenero  -> inseridos: " + generosInseridos
                    + " | ignorados (duplicados): " + (topGeneros.size() - generosInseridos));
            System.out.println("Importação concluída!");

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo Excel: " + e.getMessage());
        }
    }
}
