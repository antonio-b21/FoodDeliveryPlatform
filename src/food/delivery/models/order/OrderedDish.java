package food.delivery.models.order;

import java.util.Objects;

public class OrderedDish {

    private int id;
    private int orderId;
    private int dishId;

    public OrderedDish() {
    }

    public OrderedDish(int orderId, int dishId) {
        this.orderId = orderId;
        this.dishId = dishId;
    }

    public OrderedDish(int id, int orderId, int dishId) {
        this.id = id;
        this.orderId = orderId;
        this.dishId = dishId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderedDish that = (OrderedDish) o;
        return getId() == that.getId() && getOrderId() == that.getOrderId() && getDishId() == that.getDishId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOrderId(), getDishId());
    }

}
