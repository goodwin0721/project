package pers.goodwin.shopSystem.model;

public class IndentShow {
	private int goodsId;
	private String goodsName;
	private String pictureUrl;
	private int price;// 单价
	private int amount;// 商品数量
	private int aubtotal;// 小计
	private String tradeTime;// 交易时间

	public IndentShow() {
		super();
	}

	public IndentShow(int goodsId, String goodsName, String pictureUrl, int price, int amount, int aubtotal,
			String tradeTime) {
		super();
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.pictureUrl = pictureUrl;
		this.price = price;
		this.amount = amount;
		this.aubtotal = aubtotal;
		this.tradeTime = tradeTime;
	}

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
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

	public int getAubtotal() {
		return aubtotal;
	}

	public void setAubtotal(int aubtotal) {
		this.aubtotal = aubtotal;
	}

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	@Override
	public String toString() {
		return "IndentShow [goodsId=" + goodsId + ", goodsName=" + goodsName + ", pictureUrl=" + pictureUrl + ", price="
				+ price + ", amount=" + amount + ", aubtotal=" + aubtotal + ", tradeTime=" + tradeTime + "]";
	}

}
