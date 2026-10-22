package school.sptech;

public class ConsumoArtista {

    private Integer id;
    private Integer qtdStreams; // coluna qtd_streams
    private Integer ranking;
    private Integer idArtista;
    private Integer idRegiao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQtdStreams() {
        return qtdStreams;
    }

    public void setQtdStreams(Integer qtdStreams) {
        this.qtdStreams = qtdStreams;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Integer getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(Integer idArtista) {
        this.idArtista = idArtista;
    }

    public Integer getIdRegiao() {
        return idRegiao;
    }

    public void setIdRegiao(Integer idRegiao) {
        this.idRegiao = idRegiao;
    }
}