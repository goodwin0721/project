package pers.goodwin.shopSystem.model;

public class Cart {
	private int id;
	private int userId;
	private int goodsId;
	private int amount;
	private int price;
	
	public Cart() {
		super();
	}
	public Cart(int id, int userId, int goodsId, int amount, int price) {
		super();
		this.id = id;
		this.userId = userId;
		this.goodsId = goodsId;
		this.amount = amount;
		this.price = price;
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
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Cart [id=" + id + ", userId=" + userId + ", goodsId=" + goodsId + ", amount=" + amount + ", price="
				+ price + "]";
	}
	
}
