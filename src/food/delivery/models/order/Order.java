package food.delivery.models.order;

import java.util.Date;
import java.util.Objects;

public class Order {

    private int id;
    private int userId;
    private int courierId;
    private int restaurantId;
    private Date placementDate;
    private double total;

    public Order() {
    }

    public Order(int userId, int restaurantId, Date placementDate, double total) {
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.placementDate = placementDate;
        this.total = total;
    }

    public Order(int id, int userId, int courierId, int restaurantId, Date placementDate, double total) {
        this.id = id;
        this.userId = userId;
        this.courierId = courierId;
        this.restaurantId = restaurantId;
        this.placementDate = placementDate;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCourierId() {
        return courierId;
    }

    public void setCourierId(int courierId) {
        this.courierId = courierId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Date getPlacementDate() {
        return placementDate;
    }

    public void setPlacementDate(Date placementDate) {
        this.placementDate = placementDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return getId() == order.getId() && getUserId() == order.getUserId() && getCourierId() == order.getCourierId() && getRestaurantId() == order.getRestaurantId() && Double.compare(order.getTotal(), getTotal()) == 0 && Objects.equals(getPlacementDate(), order.getPlacementDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserId(), getCourierId(), getRestaurantId(), getPlacementDate(), getTotal());
    }
}
