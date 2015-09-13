package ru.etstudio.kuhmeyster.db.entity;


public final class CmpIngredient implements DBContract {

    public static final String TABLE_NAME = "cmp_ingredient";

    public static final String COLUMN_DISH_ID = "dish_id";

    public static final String COLUMN_INGREDIENT_ID = "ingredient_id";

    public static final String COLUMN_AMOUNT = "amount";

    public static final String COLUMN_MEASURE = "measure";

    public static final String SQL_CREATE_TABLE = new StringBuffer()
            .append("CREATE TABLE ")
            .append(TABLE_NAME)
            .append(" (")
            .append(_ID).append(" INTEGER PRIMARY KEY, ")
            .append(COLUMN_DISH_ID).append(" INTEGER, ")
            .append(COLUMN_INGREDIENT_ID).append(" INTEGER, ")
            .append(COLUMN_AMOUNT).append(" INTEGER, ")
            .append(COLUMN_MEASURE).append(" TEXT")
            .append(")").toString();

    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;

    private long _id;

    private Dish dish;

    private Ingredient ingredient;

    private int amount;

    private String measure;

    public CmpIngredient() {

    }

    public CmpIngredient(long id, Dish dish, Ingredient ingredient, int amount, String measure) {
        this._id = id;
        this.dish = dish;
        this.ingredient = ingredient;
        this.amount = amount;
        this.measure = measure;
    }

    public CmpIngredient(Dish dish, Ingredient ingredient, int amount, String measure) {
        this.dish = dish;
        this.ingredient = ingredient;
        this.amount = amount;
        this.measure = measure;
    }

    @Override
    public long getId() {
        return _id;
    }

    public void setId(long id) {
        this._id = id;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
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
