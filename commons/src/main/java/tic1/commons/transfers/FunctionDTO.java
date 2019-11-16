package tic1.commons.transfers;


import java.time.LocalDateTime;

public class FunctionDTO {
    private NewMovieDTO movie;
    private String cinemaName;
    private long sala;
    private String local;
    private String providerName;
    private LocalDateTime startTime;
    private long secondId;

    public FunctionDTO() {
    }

    public long getSecondId() {
        return secondId;
    }

    public void setSecondId(long secondId) {
        this.secondId = secondId;
    }

    public NewMovieDTO getMovie() {
        return movie;
    }

    public void setMovie(NewMovieDTO movie) {
        this.movie = movie;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public long getSala() {
        return sala;
    }

    public void setSala(long sala) {
        this.sala = sala;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
}

