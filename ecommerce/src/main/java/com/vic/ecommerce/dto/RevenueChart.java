package com.vic.ecommerce.dto;

import lombok.Data;

@Data
public class RevenueChart {
    private String date;
    private double revenue;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }
}
