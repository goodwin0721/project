package pers.goodwin.shopSystem.model;

/**
 * @author goodwin 商品
 */
public class Goods {
	private int id;
	private String name;// 商品名称
	private String described;// 描述
	private int price;// 价格
	private int stock;// 库存
	private String pictureUrl;// 图片地址
	private int sold;// 已售

	public Goods() {
		super();
	}
/*
	public Goods(int id, String name, String described, int price, int stock, String pictureUrl) {
		super();
		this.id = id;
		this.name = name;
		this.described = described;
		this.price = price;
		this.stock = stock;
		this.pictureUrl = pictureUrl;
	}
*/
	public Goods(int id, String name, String described, int price, int stock, String pictureUrl, int sold) {
		super();
		this.id = id;
		this.name = name;
		this.described = described;
		this.price = price;
		this.stock = stock;
		this.pictureUrl = pictureUrl;
		this.sold = sold;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescribed() {
		return described;
	}

	public void setDescribed(String described) {
		this.described = described;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public int getSold() {
		return sold;
	}

	public void setSold(int sold) {
		this.sold = sold;
	}

	@Override
	public String toString() {
		return "Goods [id=" + id + ", name=" + name + ", described=" + described + ", price=" + price + ", stock="
				+ stock + ", pictureUrl=" + pictureUrl + ", sold=" + sold + "]";
	}

}
