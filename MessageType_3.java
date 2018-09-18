public class MessageType_3 implements MessageType{
	private Sale sale = new Sale();
	private AdjustmentOperation adOp;

	MessageType_3(String productType, int value, AdjustmentOperation adOp){

		if(productType != null && !productType.trim().isEmpty() && value > 0 && adOp != null) {

			sale.setProductType(productType);
			sale.setValue(value);
			this.adOp = adOp;
		}
	}

	public Sale getSale() {
		return sale;
	}

	public AdjustmentOperation getAdOp() {
		return adOp;
	}
}