package model;

public class Food {
    private String Name,Image,Description,Price,Discount,MenuId;

    public Food() {
    }

    public Food(String Name, String Image, String Description, String Price, String Discount, String MenuId) {
        this.Name = Name;
        this.Image = Image;
        this.Description = Description;
        this.Price = Price;
        this.Discount = Discount;
        this.MenuId = MenuId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String Discount) {
        this.Discount = Discount;
    }

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String MenuId) {
        this.MenuId = MenuId;
    }
}
