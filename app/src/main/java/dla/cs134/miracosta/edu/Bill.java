package edu.miracostacollege.cs134.tipcalculator;

/**
 * Represents the restaurant bill
 */
public class Bill {
    private double mAmount;
    private double mTipPercent;
    private double mTipAmount;
    private double mTotalAmount;

    /**
     * Description: Creates bill with initializations of 0.0
     * Precondition: variables instantiated
     * Postcondition: sets all variables to 0.0
     */
    public Bill()
    {
        mAmount = 0.0;
        mTipPercent = 0.0;
        mTipAmount = 0.0;
        mTotalAmount = 0.0;
    }

    /**
     * Description: returns the amount of the bill before tip included
     * Precondition: variable instantiated
     * Postcondition: returns a double
     * @return the amount of the bill
     */
    public double getAmount() {
        return mAmount;
    }

    /**
     * Description: returns the tip percent
     * Precondition: variable instantiated
     * Postcondition: returns a double
     * @return the tip percent
     */
    public double getTipPercent() {
        return mTipPercent;
    }

    /**
     * Description: return tip amount
     * Precondition: variable instantiated
     * Postcondition : returns a double
     * @return the tip amount
     */
    public double getTipAmount() {
        return mTipAmount;
    }

    /**
     * Description: return total amount
     * Precondition: variable instantiated
     * Postcondition : returns a double
     * @return the total amount
     */
    public double getTotalAmount() {
        return mTotalAmount;
    }

    /**
     * Description: sets the amount of the bill
     * Precondition: variable instantiated, double passed in
     * Postcondition: sets the amount to whatever is passed in and recalculates tip and total
     * amount
     * @param amount amount of the bill
     */
    public void setAmount(double amount) {
        mAmount = amount;
        recalculateAmounts();
    }

    /**
     * Description: sets the tip percent of the bill
     * Precondition: variable instantiated, double passed in
     * Postcondition: sets the tip percent to whatever is passed in and recalculates tip and total
     * amount
     * @param tipPercent tip percent the user chooses
     */
    public void setTipPercent(double tipPercent) {
        mTipPercent = tipPercent;
        recalculateAmounts();
    }

    /**
     * Description: recalculate the tip and total amount
     * Precondition: variables instantiated
     * Postcondition: updated total and tip amount
     */
    private void recalculateAmounts()
    {
        mTipAmount = mAmount * mTipPercent;
        mTotalAmount = mAmount + mTipAmount;
    }
}
