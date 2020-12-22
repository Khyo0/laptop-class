package test;

import java.util.Arrays;

// ��ǰ ������ ������ beans �������� ����
public class Product {

	// ��ǰ ���
	private String[] productList = { "item1", "item2", "item3", "item4", "item5" };

	// �׽�Ʈ ����
	private int price = 100;
	private int amount = 1000;

	public String[] getProductList() {
		return productList;
	}

	public int getPrice() {
		return price;
	}

	public int getAmount() {
		return amount;
	}

	public String getDisplay() {
		return "price: " + price + ", amount: " + amount;
	}

	@Override
	public String toString() {
		return "Product [productList=" + Arrays.toString(productList) + ", price=" + price + ", amount=" + amount + "]";
	}

}