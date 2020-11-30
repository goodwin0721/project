package pers.goodwin.shopSystem.model;

public class Indent {
	private int id;
	private int userId;
	private int goodsId;
	private int price;
	private int amount;
	private String tradeTime;

	public Indent() {
		super();
	}

	public Indent(int id, int userId, int goodsId, int price, int amount, String tradeTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.goodsId = goodsId;
		this.price = price;
		this.amount = amount;
		this.tradeTime = tradeTime;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	@Override
	public String toString() {
		return "Indent [id=" + id + ", userId=" + userId + ", goodsId=" + goodsId + ", price=" + price + ", amount="
				+ amount + ", tradeTime=" + tradeTime + "]";
	}

}
