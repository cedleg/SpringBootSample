package app.domainRepository;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

public class Domain {


    @Id
    private long id;

    @Indexed(unique = true)
    private String domain;

    private boolean displayAds;

    public Domain() {
    }

    public Domain(String domain, boolean displayAds) {
        this.domain = domain;
        this.displayAds = displayAds;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public boolean isDisplayAds() {
        return displayAds;
    }

    public void setDisplayAds(boolean displayAds) {
        this.displayAds = displayAds;
    }

//getters and setters
}
