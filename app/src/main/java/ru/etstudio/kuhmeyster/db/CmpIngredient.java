package ru.etstudio.kuhmeyster.db;


public class CmpIngredient {

    private int _id;

    private int dishId;

    private int ingredientId;

    private int amount;

    private String measure;

    public CmpIngredient(int id, int dishId, int ingredientId, int amount, String measure) {
        this._id = id;
        this.dishId = dishId;
        this.ingredientId = ingredientId;
        this.amount = amount;
        this.measure = measure;
    }

    public CmpIngredient(int dishId, int ingredientId, int amount, String measure) {
        this.dishId = dishId;
        this.ingredientId = ingredientId;
        this.amount = amount;
        this.measure = measure;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }
}
