package iaraliev.rashid.bigbroserver.model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Monitoring {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Long left;
    private Long top;
    private Long right;
    private Long bottom;
    @ManyToOne
    @JoinColumn(name = "camera_id")
    private Camera camera;
    private Date crateDate = new Date();
    private Boolean isActive = true;
    private Boolean isHereTheft = false;

    public Boolean getHereTheft() {
        return isHereTheft;
    }

    public void setHereTheft(Boolean hereTheft) {
        isHereTheft = hereTheft;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getLeft() {
        return left;
    }

    public void setLeft(Long left) {
        this.left = left;
    }

    public Long getTop() {
        return top;
    }

    public void setTop(Long top) {
        this.top = top;
    }

    public Long getRight() {
        return right;
    }

    public void setRight(Long right) {
        this.right = right;
    }

    public Long getBottom() {
        return bottom;
    }

    public void setBottom(Long bottom) {
        this.bottom = bottom;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public Date getCrateDate() {
        return crateDate;
    }

    public void setCrateDate(Date crateDate) {
        this.crateDate = crateDate;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
