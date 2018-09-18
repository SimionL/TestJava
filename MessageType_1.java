public class MessageType_1 implements MessageType{

	private Sale sale = new Sale();

	MessageType_1(String productType, int value){
		if(productType != null && !productType.trim().isEmpty() && value > 0) {
			sale.setProductType(productType);
			sale.setValue(value);
		}
	}

	public Sale getSale() {
		return sale;
	}
}