package school.sptech;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class DAO {

    private static final String PAIS_PADRAO = "Brasil";

    private final JdbcTemplate jdbcTemplate;

    public DAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private List<Regiao> buscarRegiaoPorNome(String nome) {
        return jdbcTemplate.query(
                "SELECT * FROM Regiao WHERE nome = ?",
                new BeanPropertyRowMapper<>(Regiao.class), nome);
    }

    public int buscarRegiao(String nome) {
        List<Regiao> regioes = buscarRegiaoPorNome(nome);

        return regioes.get(0).getIdRegiao();
    }

    private List<Artista> buscarArtistaPorNome(String nome) {
        return jdbcTemplate.query(
                "SELECT * FROM Artista WHERE nome = ?",
                new BeanPropertyRowMapper<>(Artista.class), nome);
    }

    /** Devolve o id do artista; se ele ainda não existir, insere antes. */
    public int buscarOuInserirArtista(String nome) {
        List<Artista> artistas = buscarArtistaPorNome(nome);

        if (artistas.isEmpty()) {
            jdbcTemplate.update(
                    "INSERT INTO Artista (nome, pais_origem) VALUES (?, ?)", nome, PAIS_PADRAO);
            artistas = buscarArtistaPorNome(nome);
        }

        return artistas.get(0).getIdArtista();
    }

    private List<Genero> buscarGeneroPorNome(String nome) {
        return jdbcTemplate.query(
                "SELECT * FROM Genero WHERE nome = ?",
                new BeanPropertyRowMapper<>(Genero.class), nome);
    }

    public int buscarOuInserirGenero(String nome) {
        List<Genero> generos = buscarGeneroPorNome(nome);

        if (generos.isEmpty()) {
            jdbcTemplate.update("INSERT INTO Genero (nome) VALUES (?)", nome);
            generos = buscarGeneroPorNome(nome);
        }

        return generos.get(0).getIdGenero();
    }

    // return true se inseriu, false se a linha inteira já existia
    public boolean inserirConsumoArtista(Consumo consumo) {
        int idRegiao = buscarRegiao(consumo.regiao());
        int idArtista = buscarOuInserirArtista(consumo.nome());

        List<ConsumoArtista> existentes = jdbcTemplate.query(
                "SELECT * FROM ConsumoArtista "
                        + "WHERE qtd_streams = ? AND ranking = ? AND idArtista = ? AND idRegiao = ?",
                new BeanPropertyRowMapper<>(ConsumoArtista.class),
                consumo.qtdStreams(), consumo.ranking(), idArtista, idRegiao);

        if (!existentes.isEmpty()) {
            return false;
        }

        jdbcTemplate.update(
                "INSERT INTO ConsumoArtista (qtd_streams, ranking, idArtista, idRegiao) "
                        + "VALUES (?, ?, ?, ?)",
                consumo.qtdStreams(), consumo.ranking(), idArtista, idRegiao);
        return true;
    }

    public boolean inserirConsumoGenero(Consumo consumo) {
        int idRegiao = buscarRegiao(consumo.regiao());
        int idGenero = buscarOuInserirGenero(consumo.nome());

        List<ConsumoGenero> existentes = jdbcTemplate.query(
                "SELECT * FROM ConsumoGenero "
                        + "WHERE qtd_streams = ? AND ranking = ? AND idGenero = ? AND idRegiao = ?",
                new BeanPropertyRowMapper<>(ConsumoGenero.class),
                consumo.qtdStreams(), consumo.ranking(), idGenero, idRegiao);

        if (!existentes.isEmpty()) {
            return false;
        }

        jdbcTemplate.update(
                "INSERT INTO ConsumoGenero (qtd_streams, ranking, idGenero, idRegiao) "
                        + "VALUES (?, ?, ?, ?)",
                consumo.qtdStreams(), consumo.ranking(), idGenero, idRegiao);
        return true;
    }
}