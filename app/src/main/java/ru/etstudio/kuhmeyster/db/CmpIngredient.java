package ru.etstudio.kuhmeyster.db;


import android.provider.BaseColumns;

public final class CmpIngredient {

    private int _id;

    private int dishId;

    private int ingredientId;

    private int amount;

    private String measure;

    public static final String SQL_CREATE_TABLE = new StringBuffer()
            .append("CREATE TABLE ")
            .append(Entry.TABLE_NAME)
            .append(" (")
            .append(Entry._ID).append(" INTEGER PRIMARY KEY, ")
            .append(Entry.COLUMN_DISH_ID).append(" INTEGER, ")
            .append(Entry.COLUMN_INGREDIENT_ID).append(" INTEGER, ")
            .append(Entry.COLUMN_AMOUNT).append(" INTEGER, ")
            .append(Entry.COLUMN_MEASURE).append(" TEXT")
            .append(")").toString();

    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + Entry.TABLE_NAME;

    public CmpIngredient() {

    }

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

    public static abstract class Entry implements BaseColumns {

        public static final String TABLE_NAME = "cmp_ingredient";

        public static final String COLUMN_DISH_ID = "dish_id";

        public static final String COLUMN_INGREDIENT_ID = "ingredient_id";

        public static final String COLUMN_AMOUNT = "amount";

        public static final String COLUMN_MEASURE = "measure";
    }
}
