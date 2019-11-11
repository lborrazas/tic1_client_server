package tic1.commons.transfers;

public class CinemaDto {
    private long id;
    private String name;
    private String location;
    private long provider_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getProviderId() {
        return provider_id;
    }

    public void setProvider(long provider_id) {
        this.provider_id = provider_id;
    }
}
