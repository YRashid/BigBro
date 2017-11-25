package iaraliev.rashid.bigbroserver.model.entity;

import javax.persistence.*;

@Entity
public class Camera {

    @Id
    @GeneratedValue
    private Long id;
    private String url;
    private String description;
    private Double latitude;
    private Double longitude;
    private Integer maxCars;
    private Integer curCars;
    private Long imgCount;
    private Integer height;
    private Integer width;

    @Override
    public String toString() {
        return "Camera{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", maxCars=" + maxCars +
                ", curCars=" + curCars +
                ", imgCount=" + imgCount +
                ", height=" + height +
                ", width=" + width +
                '}';
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getMaxCars() {
        return maxCars;
    }

    public void setMaxCars(Integer maxCars) {
        this.maxCars = maxCars;
    }

    public Integer getCurCars() {
        return curCars;
    }

    public void setCurCars(Integer curCars) {
        this.curCars = curCars;
    }

    public Long getImgCount() {
        return imgCount;
    }

    public void setImgCount(Long imgCount) {
        this.imgCount = imgCount;
    }
}
