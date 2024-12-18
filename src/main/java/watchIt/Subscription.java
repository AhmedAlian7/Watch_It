package watchIt;

import java.time.LocalDate;

public class Subscription {
    public enum enPlan {Basic, Standard, Premium, Non}

    private enPlan Plan;
    private float Price;

    private LocalDate StartDate;
    private int AllowedWatches;

    public LocalDate getStartDate() {
        return StartDate;
    }
    public void setStartDate(LocalDate startDate) {
        StartDate = startDate;
    }

    public int getAllowedWatches() {
        return AllowedWatches;
    }
    public void setAllowedWatches(int allowedWatches) {
        AllowedWatches = allowedWatches;
    }

    public float getPrice() {
        return Price;
    }
    public void setPrice(float price) {
        Price = price;
    }

    public enPlan getPlan() {
        return Plan;
    }
    public void setPlan(enPlan plan) {
        Plan = plan;
    }

    public Subscription(enPlan plan, LocalDate startDate) {
        Plan = plan;
        StartDate = startDate;
        setPrice();
        setAllowedWatches();
    }

    public Subscription() {
    }


    private void setPrice() {
        switch (Plan) {
            case Premium -> Price = 150;
            case Standard -> Price = 100;
            case Basic -> Price = 50;
        }
    }
    private void setAllowedWatches() {

        switch (Plan) {
            case Premium -> AllowedWatches = 30;
            case Standard -> AllowedWatches = 10;
            case Basic -> AllowedWatches = 5;
        }
    }

}
