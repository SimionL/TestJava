public class MessageType_2 implements MessageType{

	private Sale sale = new Sale();
	int occurrencesNumber;

	MessageType_2(String productType, int value, int occurrencesNumber){

		if(productType != null && !productType.trim().isEmpty() && value > 0 && occurrencesNumber > 0) {

			sale.setProductType(productType);
			sale.setValue(value);
			this.occurrencesNumber = occurrencesNumber;
		}
	}

	public Sale getSale() {
		return sale;
	}

	public int getOccurrencesNumber() {
		return occurrencesNumber;
	}
}