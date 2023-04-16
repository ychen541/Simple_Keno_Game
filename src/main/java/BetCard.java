

public class BetCard {
    private int card;
    private boolean selected;


    BetCard(){
        this.card = 0;
        this.selected = false;
    }

    BetCard(int num){
        this.card = num;
    }

    public int getCard(){
        return this.card;
    }

    public void setSelected(boolean isSelected){
       this.selected = isSelected;
    }

    public boolean getSelected(){
        return this.selected;
    }

    public boolean isSelected(BetCard num){
        if (this.selected == true){
            return true;
        }
        else
            return false;
    }

    public int getRow() {
        return this.card / 10;
    }

    public int getColumn() {
        return this.card % 10;
    }


}